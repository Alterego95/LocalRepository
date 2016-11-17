
 
char inbyte;    //Setting a char variable as inbyte
int LED = 13;   //LED on pin 13
int ABC = 12; 
int XYZ = 7;
void setup(){
  // initialize the serial communications:
  Serial.begin(9600);
  digitalWrite(LED, LOW);
  digitalWrite(ABC, LOW);
  digitalWrite(XYZ, LOW);

  // initialize the LED pin as an output
  pinMode(LED, OUTPUT);
  pinMode(ABC, OUTPUT);
  pinMode(XYZ, OUTPUT);
}
 
void loop()
{
  // When a character arrives on the serial port this will be true
  if (Serial.available()) {
    // wait a bit for the entire message to arrive
    delay(100);
 
    //store the first character in var inbyte
    inbyte = Serial.read();
    if( inbyte == '1' ) function1(); //if the byte is an ASCII 0 execute function 1
    if( inbyte == '2' ) function2(); //if the byte is an ASCII 9 execute function 2
    if( inbyte == '3' ) function3();
    if( inbyte == '4' ) function4();
    if( inbyte == '5' ) function5();
    if( inbyte == '6' ) function6();
        
  }
}
 
void function1()
{
    digitalWrite(LED, HIGH);
}
 
void function2()
{
    digitalWrite(LED, LOW);
}
void function3()
{
  digitalWrite(ABC, HIGH);
}
void function4()
{
  digitalWrite(ABC, LOW);
}
void function5()
{
  digitalWrite(XYZ, HIGH);
}
void function6()
{
  digitalWrite(XYZ, LOW);
}

