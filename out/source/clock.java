import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class clock extends PApplet {

PFont myFont;
PFont myFont2;
float x=0.05f;
float y=0.04f;
float z=0.03f;
Number hour;
Number min;
Number sec;
float c = 0;

class Number{
  PShape[] obj;
  Number(String str){
    obj = new PShape[10];
    for (int i = 0; i < 10; i++) {
      String str2 = String.format("obj/%s/%d/tinker.obj",str,i);
      println(str2);
      obj[i] = loadShape(str2);
    }
  }
}


public void setup() {
   
  stroke(0);
  strokeWeight(2);
  frameRate(60.0f);
  myFont=loadFont("SnellRoundhand-Black-48.vlw");
  myFont2=loadFont("AmericanTypewriter-CondensedLight-48.vlw");
  hour = new Number("red");
  min = new Number("green");
  sec = new Number("blue");
  // camera(width/2, -height, 0, width/2, height/2, 0, 0, 0, 1); //上から見るとき
  camera(width/2.0f, 0, (height/2.0f) / tan(PI*30.0f / 180.0f), width/2.0f, height/2.0f, 0, 0, 1, 0);
}

public void draw() {
  background(255);
  Lighting();
  Haikei(); 
  Clock();
  drawFloor();
}

public void Lighting(){
  lights();
  spotLight(255, 0, 0,width/2-width/3, height*1/5, 0, 0, 1, 0, PI, 100);
  spotLight(0, 255, 0,width/2,height*1/5, 0, 0, 1, 0, PI, 100);
  spotLight(0, 0, 255,width/2+width/3, height*1/5, 0, 0, 1, 0, PI, 100);
}

public void Clock(){
  // ------------hour-----------------------
  pushMatrix();
  translate(width/2-width/3, height/2, 0);
  rotateX(c*x*0.9f);
  rotateY(c*y*0.9f);
  rotateZ(c*z*0.9f);

  if (hour() < 10) {
    // １のくらい
    pushMatrix();
    translate(0,-60,0);
    rotateX(PI);
    shape(hour.obj[hour()%10]);
    popMatrix();
  }
  if (hour() >= 10) {
    // １のくらい
    pushMatrix();
    translate(45,-60,0);
    rotateX(PI);
    shape(hour.obj[hour()%10]);
    popMatrix();
    // 10のくらい
    pushMatrix();
    translate(-45,-60,0);
    rotateX(PI);
    shape(hour.obj[hour()/10]);
    popMatrix();
  }
  // ---------end hour----------------------

  // ---------min----------------------
  popMatrix();

  pushMatrix();
  translate(width/2, height/2, 0);
  rotateX(c*x*1.9f);
  rotateY(c*y*1.9f);
  rotateZ(c*z*1.9f); 

  if (minute() < 10) {
    // １のくらい
    pushMatrix();
    translate(0,-60,0);
    rotateX(PI);
    shape(min.obj[minute()%10]);
    popMatrix();
  }

  if (minute() >= 10) {
    // １のくらい 
    pushMatrix();
    translate(45,-60,0);
    rotateX(PI);
    shape(min.obj[minute()%10]);
    popMatrix();
    // 10のくらい
    pushMatrix();
    translate(-45,-60,0);
    rotateX(PI);
    shape(min.obj[minute()/10]);
    popMatrix();
  }
  popMatrix();

  // ---------end min----------------------

  // ---------sec----------------------
  pushMatrix();
  translate(width/2+width/3, height/2, 0);
  rotateX(c*x);
  rotateY(c*y);
  rotateZ(c*z);

  if (second() < 10) {
    // １のくらい
    pushMatrix();
    translate(0,-60,0);
    rotateX(PI);
    shape(sec.obj[second()%10]);
    popMatrix();
  }

  if (second() >= 10) {
    // １のくらい
    pushMatrix();
    translate(45,-60,0);
    rotateX(PI);
    shape(sec.obj[second()%10]);
    popMatrix();
    // 10のくらい 
    pushMatrix();
    translate(-45,-60,0);
    rotateX(PI);
    shape(sec.obj[second()/10]);
    popMatrix();
  }
  popMatrix();
  // ---------end sec----------------------
  c = frameCount*100*PI/180;

  if (mousePressed==true) {
    if (x>0) {
      x=x*0.9f;
    } else
      x=0;
    if (y>0) {
      y=y*0.9f;
    } else
      y=0;
    if (z>0) {
      z=z*0.9f;
    } else
      z=0;
  }
  if (mousePressed==false) {
    x=0.05f;
    y=0.04f;
    z=0.03f;
  }
}

public void Haikei() {
  pushMatrix();
  if (hour()==6 || hour()==7 || hour()==8) {
    background(0xffFF66B3);
    textSize(width*1/6);
    fill(0);
    textFont(myFont);
    textAlign(CENTER);
    text("Break Fast", width*1/2, height*1/5);
    fill(0xffFFB366);
    ellipse(width*1/2, 0, width*1/2, height*1/5);
  }
  if (hour()==9 || hour()==10) {
    background(0xff66B3FF);
    textSize(width*1/6);
    fill(0);
    textFont(myFont);
    textAlign(CENTER);
    text("Walking time", width*1/2, height*1/5);
    fill(0xffFF6666);
    ellipse(width*1/2, 0, width*1/2, height*1/5);
  }
  if (hour()==11 || hour()==12 || hour()==13) {
    textSize(50);
    fill(0);
    textFont(myFont);
    textAlign(CENTER);
    text("lunch time", width*1/2, height*1/5);
    fill(255, 10, 0);
    ellipse(width*1/2, 0, width*1/2, height*1/5);
  }
  if (hour()==14 || hour()==15 || hour()==16) {
    background(255, 135, 0);
    textSize(width*1/6);
    fill(0);
    textFont(myFont2);
    textAlign(CENTER);
    text("Snack time", width*1/2, height*1/5);
    fill(255, 0, 0);
    ellipse(width*1/4, 0, width*1/2, height*1/5);
  }
  if (hour()==17 || hour()==18 || hour()==19) {
    background(0xff5d618c);
    textSize(width*1/6);
    fill(0);
    textFont(myFont2);
    textAlign(CENTER);
    text("Dinner time", width*1/2, height*1/5);
    fill(0xffFFFF00);
    ellipse(width*1/4, 0, width*1/2, height*1/5);
  }
  if (hour()==20 || hour()==21 || hour()==22) {
    background(0xff111188);
    textSize(width*1/6);
    fill(0);
    textFont(myFont2);
    textAlign(CENTER);
    text("Relax time", width*1/2, height*1/5);
    fill(0xffFFFF00);
    ellipse(width*1/4, 0, width*1/2, height*1/5);
  }
  if (hour()==23 || hour() >= 0 && hour() <= 5) {
    background(0xff333300);
    textSize(width*1/6);
    fill(0);
    textFont(myFont2);
    textAlign(CENTER);
    text("Sleeping time", width*1/2, height*1/5);
    fill(0xffFFFF00);
    ellipse(width*1/4, 0, width*1/2, height*1/5);
  }
  popMatrix();
}

public void drawFloor(){
  pushMatrix();
  translate(width/2, 3*height/4, 0);
  fill(126);
  noStroke();
  int s = 10;
  for(int z = -200; z < 200; z += s){
    for(int x = -width/2; x < width/2; x += s){
      beginShape(QUADS);
      vertex(x, 0, z);
      vertex(x, 0, z + s);
      vertex(x + s, 0, z + s);
      vertex(x + s, 0, z);
      endShape();
    }
  }
  popMatrix();
}
  public void settings() {  size(displayWidth, displayHeight, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "clock" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
