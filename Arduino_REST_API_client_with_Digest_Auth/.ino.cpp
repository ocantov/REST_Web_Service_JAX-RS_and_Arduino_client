#ifdef __IN_ECLIPSE__
//This is a automatic generated file
//Please do not modify this file
//If you touch this file your change will be overwritten during the next build
//This file has been generated on 2017-09-22 00:17:21

#include "Arduino.h"
#include <MD5.h>
#include <MemoryFree.h>
#include <SoftwareSerial.h>
#include <string.h>
#include <RTClib.h>
#include <SimpleTimer.h>
#include <DHT.h>
#include <DHT.h>
void setup() ;
void loop() ;
void getHumidity () ;
void getTemperature () ;
bool sendRequest(char *method, char *uri, char *nonce, char *deviceId, float value_flt, char* variable , long timestamp ) ;
char * getNonce();
bool createSocket();

#include "Arduino_REST_API_client_with_Digest_Auth.ino"


#endif
