#include <MD5.h>
#include <MemoryFree.h>
#include <SoftwareSerial.h>
#include <string.h>
#include <RTClib.h>
#include <DHT.h>
#include <SimpleTimer.h>


#define DHTPIN 2
#define DHTTYPE DHT22   // DHT 22  (AM2302)

RTC_DS3231 rtc;
DateTime now;
DHT dht(DHTPIN, DHTTYPE);
SimpleTimer temperature_timer;  //Setting simmer
SimpleTimer humidity_timer;

char server[]="192.168.0.2";
long interval_temperature=60000; //this is the interval of the timmer
long interval_humidity=60000; //this is the interval of the temp information sending
char port[]="8080";
char username[] = "user";
char password[] = "password";
char realm[] = "JCGRealm";
char uri[] = "/secure/v2/insertdata";
char qop[]= "auth";
char nc[]= "00000001";
char cnonce[]= "e79e26e0d17c978d";
char nonce[33];
char algorithm[]= "MD5";
char method[]="POST";
const byte rxPin=3;
const byte txPin=2;
char firstParameter[] = "GET /secure/v2/insertdata HTTP/1.1";
char *md5_a1;
char *md5_a2;
char *md5_response;
char contentType[]="Content-Type:application/json";
char contentLength[21];
char deviceId[]="0001";
int connectionAttempts=0;
long timestamp_t;
float temperature;
long timestamp_h;
float humidity;
bool newDataT=false;
bool newDataH=false;



void setup() {

Serial.begin(19200);
Serial.println("Starting...");

Serial1.begin(9600);
delay(200);
rtc.begin();
dht.begin();

Serial.println("ok");
temperature_timer.setInterval(interval_temperature, getTemperature);
humidity_timer.setInterval(interval_humidity, getHumidity);
}


void loop() {
	temperature_timer.run();
	humidity_timer.run();

	if (newDataT || newDataH)
		{
			bool connected = createSocket();
			if (newDataT)
			{
				if (connected)
				{
					bool dataSent = false;
					char * gotNonce = getNonce();
					char nonce[33];
					strcpy (nonce,gotNonce);
					char variable[]="temperature";
					dataSent = sendRequest(method,uri,nonce,deviceId,temperature,variable,timestamp_t);
					delay(1000);
					if (dataSent)
					{
						newDataT = false;
						temperature = -100;
						timestamp_t = 0;
					}
				}
				else
				{
					if (connectionAttempts > 10)
					{
						Serial.println("Error, I could not create the server socket, offline mode...");
						connectionAttempts=0;
					}
				}
			}
			if (newDataH)
			{
				if (connected)
				{
					bool dataSent = false;
					char * gotNonce = getNonce();
					char nonce[33];
					strcpy (nonce,gotNonce);
					char variable[]="humidity";
					dataSent = sendRequest(method,uri,nonce,deviceId,humidity,variable,timestamp_h);
					delay(1000);
					if (dataSent)
					{
						newDataH = false;
						humidity = -1;
						timestamp_h = 0;
					}
				}
				else
				{
					if (connectionAttempts > 10)
					{
						Serial.println("Error, I could not create the server socket, offline mode...");
						connectionAttempts=0;
					}
				}
			}

			Serial1.println("AT+CIPCLOSE=1");
			Serial.println(freeMemory());
		}

}

void getHumidity ()
{
	now = rtc.now();
	timestamp_h = now.unixtime();
	humidity = dht.readHumidity();
	if (isnan(humidity)) {
		Serial.println("Failed to read the humidity from DHT");
	}
	else
	{
		newDataH=true;

	}
}

void getTemperature ()
{

	now = rtc.now();
	timestamp_t = now.unixtime();
	temperature = dht.readTemperature();
	if (isnan(temperature)) {
		Serial.println("Failed to read the temperature from DHT");
	}
	else
	{
		newDataT=true;

	}
}

bool sendRequest(char *method, char *uri, char *nonce, char *deviceId, float value_flt, char* variable , long timestamp )
{
	bool result = false;

//Stage 1 prepare the MD5 stuff

// step 1 : create a hash of username:realm:password
	char a1[strlen(username)+strlen(password)+strlen(realm)+2];
	sprintf(a1,"%s:%s:%s",username,realm,password);
	unsigned char * h_a1 = MD5::make_hash(a1);
	char *md5_a1 = MD5::make_digest(h_a1, 16);


//step 2: create a hash of the uri
	char a2[strlen(method)+1+strlen(uri)];
	sprintf(a2,"%s:%s",method,uri);
	unsigned char * h_a2 = MD5::make_hash(a2);
	char *md5_a2 = MD5::make_digest(h_a2, 16);


//step 3 create the response
	char response[33+1+strlen(nonce)+1+strlen(nc)+1+strlen(cnonce)+1+strlen(qop)+1+33];
	sprintf(response,"%s:%s:%s:%s:%s:%s",md5_a1,nonce,nc,cnonce,qop,md5_a2);
	unsigned char * h_response = MD5::make_hash(response);
	char *md5_response=MD5::make_digest(h_response, 16);


//Stage 1 prepare the request:
	char request[strlen(username)+strlen(realm)+strlen(nonce)+strlen(uri)+strlen(algorithm)+strlen(md5_response)+strlen(qop)+strlen(nc)+strlen(cnonce)+1];
	sprintf(request,"Authorization: Digest username=\"%s\",  realm=\"%s\",  nonce=\"%s\",  uri=\"%s\",  algorithm=%s,  response=\"%s\",  qop=%s, nc=%s,  cnonce=\"%s\"",username,realm,nonce,uri,algorithm,md5_response,qop,nc,cnonce);
	free(md5_response);
	free(md5_a1);
	free(md5_a2);
	free(h_a1);
	free(h_a2);
	free(h_response);

	char value_str[8];
	strcpy(value_str, "");
	dtostrf(value_flt, 2, 2, &value_str[strlen(value_str)]);


	char body[67+sizeof(variable)+sizeof(timestamp)+sizeof(deviceId)+sizeof(value_str)];
	sprintf(body,"{\"deviceId\":\"%s\",\"%s\":{\"timestamp\":\"%ld\",\"value\":\"%s\"}}",deviceId,variable,timestamp,value_str);;
	int size=strlen(body);
	sprintf(contentLength,"Content-Length:%d",size);
	char cipsend[17];
	char resource[11+strlen(method)+strlen(uri)];
	sprintf(resource,"%s %s HTTP/1.1",method,uri);
	char host[7+strlen(server)];
	sprintf(host, "Host: %s",server);
	sprintf(cipsend,"AT+CIPSEND=1,%d",strlen(request)+strlen(resource)+strlen(host)+strlen(contentType)+12+strlen(body)+strlen(contentLength)+1);
	Serial1.println(cipsend);
	delay (100);
	Serial1.println(resource);
	Serial1.println(host);
	Serial1.println(request);
	Serial1.println(contentType);
	Serial1.readString();
	Serial1.println(contentLength);
	Serial1.readString();
	Serial1.println();
	Serial1.println(body);
	char espBuffer[152] = {0};
	Serial1.readBytes(espBuffer, 151);
	if (strstr(espBuffer,"200 OK"))
	{
		Serial.println("Data sent successfully");
		result=true;

	}
	else
	{
		Serial.println(espBuffer);
	}

	return result;
}

char * getNonce(){
	char * pre_nonce;
	char secondParameter[7+strlen(server)];
	sprintf(secondParameter, "Host: %s",server);
	char cipsend[17];
	sprintf(cipsend,"AT+CIPSEND=1,%d",strlen(firstParameter)+strlen(secondParameter)+6);
	Serial1.println(cipsend);
	delay (150);
	Serial1.readStringUntil('\n');
	Serial1.println(firstParameter);
	Serial1.println(secondParameter);
	Serial1.println();
	bool found = false;
	char espBuffer[512] = {0};
	//Serial.println(Serial1.readString());
	while (Serial1.available() > 0 && found == false && strlen(espBuffer) < 251 )
	{
		Serial1.readBytes(espBuffer, 250);
		if (strstr(espBuffer,"nonce"))
		{
			//Serial.println(espBuffer);
			found = true;
			char * pch;
			pch = strtok (espBuffer,",");
			while (pch != NULL)
			{
				if (strstr(pch,"nonce"))
				{
					strtok(pch, "\""); // accounts for any chacacter between the quotes
					pre_nonce = strtok( NULL, "\"");
					//pre_nonce[33]='\0';
					if (strlen(pre_nonce)== 32)
					{
						Serial.print("Got Nonce: ");
						Serial.println(pre_nonce);
					}

					break;
				}
				pch = strtok (NULL, ",");
			}

			break;
		}
		else
		{
			pre_nonce = NULL;
			Serial.println("Cannot get the nonce");
		}
	}
	return pre_nonce;
}

bool createSocket(){
	char espBuffer[100] = {0};
	int readCount = 0;
	long startTime = millis();
	boolean result = false;
	Serial1.println("AT+CIPMUX=1");
	delay(100);
	Serial1.readString();
	char cipStart[28+strlen(server)+strlen(port)];
	sprintf(cipStart,"AT+CIPSTART=1,\"TCP\",\"%s\",%s",server,port);
	Serial1.println(cipStart);
	delay(100);

	while (millis() - startTime < 3000) { // Run for at least 5 seconds
	  // Check to make sure we don't exceed espBuffer's boundaries
	  if (Serial1.available() > readCount + sizeof(espBuffer) - 1)
	    break;
	  readCount += Serial1.readBytes(espBuffer + readCount, Serial1.available());
	}

	if (strstr(espBuffer,"CONNECT")!=NULL or strstr(espBuffer,"CONNECTED")!=NULL)
		{
			Serial.print("Connected to: ");
			Serial.println(server);
			connectionAttempts=0;
			result = true;
		}
	else
	{
		connectionAttempts++;
		Serial.print(server);
		Serial.print(" Connection refused... attempt: ");
		Serial.println(connectionAttempts);
		result = false;
	}
	Serial1.readString();

	 return result;
}
