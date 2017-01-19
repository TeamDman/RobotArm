#include <Servo.h>

Servo servoMain;  
Servo servoSecondary;  
Servo servoClaw;
Servo servoBase;

void setup() {
  Serial.begin(38400);  
  Serial.println("init servo");
  servoMain.attach(A2);
  servoSecondary.attach(A1);
  servoClaw.attach(A3);
  servoBase.attach(A4);
}

enum servos {
	MAIN='a',
	SECONDARY='b',
	CLAW='c',
	BASE='d'
};
char in;
void loop() {
	if ((in = Serial.read()) > -1) {
		Serial.print("echo ");
		Serial.print(in - '0');
		Serial.print("\t");
		switch(in) {
			case MAIN:
				Serial.print("Main Arm Adjusting");
				servoMain.write(in = Serial.read());
				break;
			case SECONDARY:
				Serial.print("Secondary Arm Adjusting");
				servoSecondary.write(in = Serial.read());
				break;
			case CLAW:
				Serial.print("Claw Adjusting");
				servoClaw.write(in = Serial.read());
				break;
			case BASE:
				Serial.print("Base Adjusting");
				servoBase.write(in = Serial.read());
				break;
			default:
				Serial.print("Unknown Input");	
		}
		Serial.print("\t");
		Serial.println(in-'0');
	}
	delay(20);
}