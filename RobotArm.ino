#include <Servo.h>
 
int servoPin = A1;
int potPin = 0;
int servoAngle = 0;
Servo servo;  
 
void setup() {
  Serial.begin(9600);  
  Serial.println("init servo");
  servo.attach(servoPin);
}
 
 
void loop() {
	// // Serial.println(val);        
	Serial.println(Serial.read());
	delay(20);
	// int val = analogRead(potPin); 	 
	// val = map(val, 0, 1023, 0, 179); 
	// Serial.println(val);
	// servo.write(val); 
	// delay(50);
	// Serial.println("wat");
	// servo.write(45);    
	// delay(1000);        
	// servo.write(90);    
	// delay(1000);        
	// servo.write(135);   
	// delay(1000);        
	// servo.write(90);    
	// delay(1000);
 //  for(servoAngle = 12; servoAngle < 180; servoAngle++) {                                  
	// servo.write(servoAngle);              
	// delay(50);                  
 //  }
 
 //  for(servoAngle = 180; servoAngle > 0; servoAngle--) {                                
	// servo.write(servoAngle);          
	// delay(10);      
 //  }
}