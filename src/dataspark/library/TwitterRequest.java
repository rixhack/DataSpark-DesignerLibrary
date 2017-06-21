package dataspark.library;

import processing.core.PApplet;
import controlP5.*;

/*
 * Class which sends a data request to the data handler and receives values from it.
 * It returns this values on demand from the processing sketch.
 */

public class TwitterRequest {
	PApplet parent; // The processing script which runs the library.
	int pValue = 0; // The value returned to the script.
	String query; // The twitter query. (It will be used as a tag for the slider.)
	int maxValue = Integer.MAX_VALUE; // The maximum value for the slider.
	int minValue = 0; // The minimum value for the slider.
	int first = 0; // 1 if getValue has already been called once, 0 otherwise.
	data_simulator ds; // The Applet which contains the slider.
	
	public TwitterRequest(PApplet parent, String query) {
		this.parent = parent;
		parent.registerMethod("dispose", this);
		this.query = query;
	}
	
	
	public int getValue(){
		// If it is the first time, create and run the applet with the slider.
		if (first == 0){
			String[] args = {"twitter_Simulator"};
			ds = new data_simulator();
			PApplet.runSketch(args, ds);
			first = 1;
		}
		return pValue; // Returns the last value from the slider.
	}
	
	// Set maximum value for the slider.
	public void setMaxValue(int v){
		this.maxValue = v;
	}
	
	// Set minimum value for the slider.
	public void setMinValue(int v){
		this.minValue = v;
	}
	
	public void dispose() {
		
	}
	
	class data_simulator extends PApplet {

		  ControlP5 myController;
		  Slider s;
		  
		  public void settings() {
		    size(250, (int) 375); // Size of the slider window.
		  }
		  public void setup() {
		    myController = new ControlP5(this);
		    s = myController.addSlider(query,minValue,maxValue,minValue,100, 63, 50, 250);
		    // Set markers and labels colour to black.
		    s.setColorLabel(0);
		    s.setColorTickMark(0);
		    s.setColorValue(0);
		    s.setColorValueLabel(0);
		    // Set the slider to increase 1 by 1 with integer values.
		    s.setNumberOfTickMarks(maxValue-minValue+1);
		  }
		  public void draw() {
		    background(247); // Set the background to light gray.
		    fill(0);
		    pValue = (int) s.getValue(); // Store in pValue the value from the slider each frame.
		  }
		  
		  // Enable the slider to be modified with the keyboard.
		  public void keyPressed(){
			  if (keyCode == 38){
			    s.setValue(s.getValue()+1);
			  }
			  else if (keyCode == 40){
				  s.setValue(s.getValue()-1);
			  }
			  else if (keyCode == 37){
				  s.setValue(s.getValue()-10);
			  }
			  else if (keyCode == 39){
				  s.setValue(s.getValue()+10);
			  }
			}
		}
	
		

}

