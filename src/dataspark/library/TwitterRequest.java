package dataspark.library;

import processing.core.PApplet;
import controlP5.*;

/*
 * Class which emulates the behaviour of the TwitterRequest class from the backend 
 * processing library allowing the designer of a processing sketch to test different 
 * data values with a slider tool.
 * 
 */

public class TwitterRequest {
	PApplet parent; // The processing script which runs the library.
	int pValue = 0; // The value returned to the script.
	String query; // The twitter query. (It will be used as a tag for the slider.)
	int maxValue = Integer.MAX_VALUE; // The maximum value for the slider.
	int minValue = 0; // The minimum value for the slider.
	int first = 0; // 1 if getValue has already been called once, 0 otherwise.
	data_simulator ds; // The Applet which contains the slider.
	
	public TwitterRequest(PApplet parent, String query, int tw) {
		this.parent = parent;
		parent.registerMethod("dispose", this);
		this.query = query;
	}
	
	/*
	 * Returns the current value of the silder. If it's the first time the Processing
	 * script calls the function, the Applet with the slider is created first.
	 */
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
	
	/*
	 * Sets the upper limit for the slider.
	 * 
	 * @param v: The value to be set as maximum.
	 */
	public void setMaxValue(int v){
		this.maxValue = v;
	}
	
	/*
	 * Sets the lower limit for the slider.
	 * 
	 * @param v: The value to be set as minimum.
	 */
	public void setMinValue(int v){
		this.minValue = v;
	}
	
	public void dispose() {
		
	}
	
	/*
	 * Inner class inside TwitterRequest. It consists of the Applet that contains the slider.
	 */
	class data_simulator extends PApplet {

		  ControlP5 myController;
		  Slider s;
		  	  
		  /*
		   * Sets the size of the window containing the slider.
		   */
		  public void settings() {
		    size(250, (int) 375); // Size of the slider window.
		  }
		  /*
		   * Creates and configures the slider.
		   */
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
		  /*
		   * Function called once every frame. It updates the variable which is sent to the
		   * sketch with the value of the slider.
		   */
		  public void draw() {
		    background(247); // Set the background to light gray.
		    fill(0);
		    pValue = (int) s.getValue(); // Store in pValue the value from the slider each frame.
		  }
		  
		  /*
		   * Function triggered when a key is pressed. It allows controlling the slider
		   * with the arrow keys.
		   */
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

