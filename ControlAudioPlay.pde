import beads.*;
import java.util.Arrays;
import hypermedia.net.*;
import oscP5.*;
import netP5.*;

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
float vol1=0.2;
float vol2;
float vol3;
float vol4;


Sample s1;
Sample s2;
Sample s3;
Sample s4;


void setup(){
  frameRate(200);
  size(10,10);
oscP5 = new OscP5(this,12000);
myRemoteLocation = new NetAddress("127.0.0.1", 12000);

oscP5.plug(this, "p1OSC", "/p1");
oscP5.plug(this, "p2OSC", "/p2");
oscP5.plug(this, "p3OSC", "/p3");
oscP5.plug(this, "p4OSC", "/p4");

ac = new AudioContext();
f1 = new Envelope(ac, 0.2);
f2 =  new Envelope(ac, 0.2);
f3 = new Envelope(ac, 0.3);
f4 = new Envelope(ac, 0.4);
Gain masterGain = new Gain(ac, 1, 1);
Gain p1G = new Gain(ac,1, f1);
Gain p2G = new Gain(ac,1, f2);
Gain p3G = new Gain(ac,1, f3);
Gain p4G = new Gain(ac,1, f4);
String holder = dataPath("4.mp3");
println(holder);
Sample s1 =  SampleManager.sample(dataPath("one.wav"));
Sample s2 = SampleManager.sample(dataPath("one.wav"));
Sample s3 = SampleManager.sample(dataPath("one.wav"));
Sample s4 = SampleManager.sample(dataPath("one.wav"));
p1 = new SamplePlayer(ac, s1);
p2 = new SamplePlayer(ac, s2);
p3 = new SamplePlayer(ac, s3);
p4 = new SamplePlayer(ac, s4);
p1.pause(true);
p4.pause(true);
p3.pause(true);
p2.pause(true);

masterGain.addInput(p1G);
masterGain.addInput(p2G);
masterGain.addInput(p3G);
masterGain.addInput(p4G);

 p1G.addInput(p1);
ac.out.addInput(masterGain);
ac.start();
}

void p1OSC(String command, String file){
  if(command.equals("p")){
    println("play");
    p1.pause(true);
    s2 =  SampleManager.sample(dataPath(file));
p1.setSample(s1);
p1.setToLoopStart();
p1.pause(false);
  }else if(command.equals("s")){
  p1.pause(true);
  }else if(command.equals("v")){
    f1.addSegment(float(file), 10);
  }

}
void p2OSC(String command, String file){

}

void p3OSC(String command, String file){

}

void p4OSC(String command, String file){

}

void draw(){
}