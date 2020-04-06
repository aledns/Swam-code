#include <ESP8266WiFi.h>
#include <NTPClient.h>
#include <WiFiUdp.h>
#include <DHT.h>
#include <ESP8266mDNS.h>
#include <ESP8266HTTPClient.h>
#include <ArduinoJson.h>
#include <aREST.h>

#define MAX_ATTEMPT_CONNECTION 10
#define DELAY_TIME 5000

const char *ssid     = "";
const char *password = "";

const byte pinLed = 2;  // D4
const byte pinDht = 4;  // D2
const byte pinFail = 5; // D1

DHT dht(pinDht,DHT11);
double temp = 0.0;

int frequency = 10000;

String myName;

const int utcOffsetInSeconds = 3600;
WiFiUDP ntpUDP;
NTPClient timeClient(ntpUDP, "pool.ntp.org", utcOffsetInSeconds);

aREST rest = aREST();
WiFiServer server(80);
WiFiClient client;

String stamp;

const int SIZE_BUFF = 900;



double threshold = 30;
double valueReaded;

//String json;
char buffJson[SIZE_BUFF];


byte doConnection();
int doConnectionWithName(String newname);
int setFrequency(String value);
void makeJson();

void setup() {
  Serial.begin(115200);
  pinMode(pinLed,OUTPUT);
  pinMode(pinDht,INPUT);
  pinMode(pinFail,OUTPUT);

  digitalWrite(pinFail,HIGH);
  digitalWrite(pinLed,LOW);

  if (doConnection()==0) {
    while (true) {
      
    }
  }

  rest.function("hostname",doConnectionWithName);
  rest.function("frequency",setFrequency);

  digitalWrite(pinFail,LOW);
  Serial.println("Connesso");
  digitalWrite(pinLed,HIGH);
  timeClient.update();

  server.begin();

  myName = WiFi.hostname();
}

void loop() {

  HTTPClient http;
  
  http.begin("http://aledns:8080/SmartGateway/api/data");
  http.addHeader("Content-Type","application/json");

  timeClient.update();

  client = server.available();
  rest.handle(client);
  
  delay(frequency);
  temp = dht.readTemperature();
  stamp = timeClient.getFormattedTime();


  makeJson();
  int httpResponseCode = http.PUT(buffJson);
  memset(buffJson,0,SIZE_BUFF);

  if (WiFi.status() != WL_CONNECTED) {
    digitalWrite(pinLed, LOW);
    digitalWrite(pinFail,HIGH);
    while (true) {
    }
  }

  http.end();
}

byte doConnection() {
  int status;
  for (byte i = 0;i<MAX_ATTEMPT_CONNECTION;i++) {
    status = WiFi.begin(ssid,password);
    if (status == WL_CONNECTED)
      return 1;
    if (status != WL_CONNECTED)
      delay(DELAY_TIME);
  }
  return 0;
}

int doConnectionWithName(String newname) {
  int status;
  //WiFi.disconnect();
  for (byte i = 0;i<MAX_ATTEMPT_CONNECTION;i++) {
    WiFi.hostname(newname);
    status = WiFi.begin(ssid,password);
    if (status == WL_CONNECTED && MDNS.begin(newname)) {
      myName = newname;
      return 1;
    }
    if (status != WL_CONNECTED)
      delay(DELAY_TIME);
  }
  digitalWrite(pinFail,HIGH);
  digitalWrite(pinLed,LOW);
  return 0;
}


int setFrequency(String cmd) {
  frequency = cmd.toInt();
  return 1;
}



void makeJson() {
  StaticJsonDocument<SIZE_BUFF> doc;
  JsonArray sensors = doc.createNestedArray("sensors");
  JsonObject sensors_0 = sensors.createNestedObject();
  doc["type"] = "WemosEnv";
  doc["frequency"] = frequency;
  doc["name"] = myName;
  doc["lastread"] = stamp;

  sensors_0["type"] = "DHT11";
  sensors_0["name"] = "temperatura";
  sensors_0["threshold"] = threshold;
  sensors_0["valueReaded"] = temp;
  serializeJson(doc,buffJson,SIZE_BUFF);
}
