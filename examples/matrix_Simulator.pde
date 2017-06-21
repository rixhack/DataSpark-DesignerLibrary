// import dataspark.library.*;
// import controlP5.*;
int nwidth = 16; // het aantal getallen in de breedte weinig: 4, normaal: 8, hoog: 16
int nheight = 74; // aantal getallen in de hoogte
double blackpercentage = 0.5; // Dichtheid van de getallen, laag: 0.9, hoog: 0.1
float speed = 0.05; // De snelheid dat de cijfers vallen traag: 0.05, snel: 0.5
boolean numbersOnly = false; // true: alleen getallen, false: alle ASCII 
int counter = 0;
TwitterRequest tr;

Digit[] digits = new Digit[nwidth*nheight];
int n;

void settings() {
  size(240, 2220);
}
void setup() {
  tr = new TwitterRequest(this, "Eindhoven");
  tr.setMaxValue(50);
  tr.setMinValue(0);
  n = digits.length;
  colorMode(RGB, 100);
  background(0);
  textSize(32);
  fill(0, 95, 0);
  

  for (int i = 0; i < nwidth; i++) {
    for (int j = 0; j < nheight; j++) {
      digits[i + j *nwidth] = new Digit(i, j);
    }
  }
}
void draw() {
  counter++;
  if (counter >= 60){
    updateVariables();
    counter = 0;
  }
  background(0);
  //if (frameCount < 300) {

    for (int i = 0; i < n; i++) {
      digits[i].update();
      digits[i].write();
    }
  //} else {

//    for (int i = 0; i < n; i++) {

//      digits[i].update2();
//      digits[i].write();
//    }
//  }
}


class Digit {
  int index;
  float x;
  float y;
  PVector location;
  char digit;
  boolean yes;
  int green;



  Digit(float xin, float yin) {
    //index = indexin;  
    x = xin;
    y = yin;
    green = (int)random(40, 100);
    if(numbersOnly){
      digit = char((int) random (48, 57));
    }else{digit = char((int) random (32, 126)); }// (48, 57)); //}
    location = new PVector((x + 0.2) * width/nwidth, (y + 0.5) * height/nheight);
  }

  void update() {
    //if(x % 2 == 0){
    y = y + speed;    
    //}else{
    //y = y + 1; 
    //}

    if (y >= nheight) {
      y = 0; 
      yes = random(1) > blackpercentage;
    }
    if (y < 0) {
      y = nheight;
      yes = random(1) > blackpercentage;
    }


    location.x = (x + 0.2) * width/nwidth;
    location.y = (y - 0.5) * height/nheight;
  }
  
  void update2(){
    
        //if(x % 2 == 0){
    x = x + speed;    
    //}else{
    //y = y + 1; 
    //}

    if (x >= width) {
      x = 0; 
      //yes = random(1) > blackpercentage;
    }
    if (x < 0) {
      x = width;
      //yes = random(1) > blackpercentage;
    }


    location.x = (x + 0.2) * width/nwidth;
    location.y = (y - 0.5) * height/nheight;
    
  }

  void write() {
    if (yes) {
      fill(40, green, 0);
      /*
      if (x + y % 7 == 0) {
        fill(100, 100, 100) ;
      }*/
      text(digit, location.x, location.y);
    }
  }
}
void updateVariables(){
  int value5 = tr.getValue();
  speed = value5/100.0;
  blackpercentage = (100-value5*2)/100.0;
}