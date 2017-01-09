#include <Servo.h>
 
int servoPin = A0;
Servo servo;  
 
void setup() {
  Serial.begin(9600);  
  Serial.println("init servo");
  servo.attach(servoPin);
}
 
 
void loop() {
	Serial.println("wat");
	servo.write(45);    
	delay(1000);        
	servo.write(90);    
	delay(1000);        
	servo.write(135);   
	delay(1000);        
	servo.write(90);    
	delay(1000);
  for(servoAngle = 0; servoAngle < 180; servoAngle++) {                                  
	servo.write(servoAngle);              
	delay(50);                  
  }
 
  for(servoAngle = 180; servoAngle > 0; servoAngle--) {                                
	servo.write(servoAngle);          
	delay(10);      
  }
}