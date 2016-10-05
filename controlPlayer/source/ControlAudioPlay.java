import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import beads.*; 
import java.util.Arrays; 
import oscP5.*; 
import netP5.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class ControlAudioPlay extends PApplet {



//import hypermedia.net.*;



OscP5 oscP5;
NetAddress myRemoteLocation;
SamplePlayer p1;
SamplePlayer p2;
SamplePlayer p3;
SamplePlayer p4;
Envelope f1;
Envelope f2;
Envelope f3;
Envelope f4;
AudioContext ac;
float vol1=0.2f;
float vol2;
float vol3;
float vol4;


Sample s1;
Sample s2;
Sample s3;
Sample s4;


public void setup(){
  frameRate(200);
  
oscP5 = new OscP5(this,12000);
myRemoteLocation = new NetAddress("127.0.0.1", 12000);

oscP5.plug(this, "p1OSC", "/p1");
oscP5.plug(this, "p2OSC", "/p2");
oscP5.plug(this, "p3OSC", "/p3");
oscP5.plug(this, "p4OSC", "/p4");

ac = new AudioContext();

f1 = new Envelope(ac, 0.5f);
f2 =  new Envelope(ac, 0.5f);
f3 = new Envelope(ac, 0.5f);
f4 = new Envelope(ac, 0.5f);

Gain masterGain = new Gain(ac, 1, 1);
Gain p1G = new Gain(ac,1, f1);
Gain p2G = new Gain(ac,1, f2);
Gain p3G = new Gain(ac,1, f3);
Gain p4G = new Gain(ac,1, f4);

Sample s1 =  SampleManager.sample(dataPath("one.wav"));
Sample s2 = SampleManager.sample(dataPath("one.wav"));
Sample s3 = SampleManager.sample(dataPath("one.wav"));
Sample s4 = SampleManager.sample(dataPath("one.wav"));
p1 = new SamplePlayer(ac, s1);
p1.setKillOnEnd(false);
p2 = new SamplePlayer(ac, s2);
p2.setKillOnEnd(false);
p3 = new SamplePlayer(ac, s3);
p3.setKillOnEnd(false);
p4 = new SamplePlayer(ac, s4);
p4.setKillOnEnd(false);
p1.pause(true);
p4.pause(true);
p3.pause(true);
p2.pause(true);

masterGain.addInput(p1G);
masterGain.addInput(p2G);
masterGain.addInput(p3G);
masterGain.addInput(p4G);

 p1G.addInput(p1);
 p2G.addInput(p2);
 p3G.addInput(p3);
 p4G.addInput(p4);

ac.out.addInput(masterGain);
ac.start();
}

public void p1OSC(String command, String file){
 
  if(command.equals("p")){
    println("play");
    p1.pause(true);
    s1 =  SampleManager.sample(dataPath(file));
p1.setSample(s1);
p1.pause(false);
p1.setToLoopStart();
p1.start();
}else if(command.equals("s")){
  p1.pause(true);
  }else if(command.equals("v")){
    f1.addSegment(PApplet.parseFloat(file), 10);
  }

}
public void p2OSC(String command, String file){
  if(command.equals("p")){
    println("play2");
    p2.pause(true);
    s2 =  SampleManager.sample(dataPath(file));
p2.setSample(s2);
p2.pause(false);
  }else if(command.equals("s")){
  p2.pause(true);
  }else if(command.equals("v")){
    f2.addSegment(PApplet.parseFloat(file), 10);
  }
}

public void p3OSC(String command, String file){
  if(command.equals("p3")){
    println("play");
    p3.pause(true);
    s3 =  SampleManager.sample(dataPath(file));
p3.setSample(s3);
p3.pause(false);
  }else if(command.equals("s")){
  p3.pause(true);
  }else if(command.equals("v")){
    f3.addSegment(PApplet.parseFloat(file), 10);
  }
}

public void p4OSC(String command, String file){
  if(command.equals("p4")){
    println("play");
    p4.pause(true);
    s4 =  SampleManager.sample(dataPath(file));
p4.setSample(s4);
p4.pause(false);
  }else if(command.equals("s")){
  p4.pause(true);
  }else if(command.equals("v")){
    f4.addSegment(PApplet.parseFloat(file), 10);
  }
}

public void draw(){
}
  public void settings() {  size(10,10); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "ControlAudioPlay" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
