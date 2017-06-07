package ldg.library;

import processing.core.PApplet;
import controlP5.*;

public class TwitterRequest {
	PApplet parent;
	int pValue = 0;
	String query;
	int maxValue = Integer.MAX_VALUE;
	int minValue = 0;
	int first = 0;
	SecondApplet sa;
	
	public TwitterRequest(PApplet parent, String query) {
		this.parent = parent;
		parent.registerMethod("dispose", this);
		this.query = query;
	}
	
	
	public int getValue(){
		if (first == 0){
			String[] args = {"matrix_Simulator"};
			sa = new SecondApplet();
			PApplet.runSketch(args, sa);
			first = 1;
		}
		return pValue;
	}
	
	public void setMaxValue(int v){
		this.maxValue = v;
	}
	
	public void setMinValue(int v){
		this.minValue = v;
	}
	
	public void dispose() {
		
	}
	
	class SecondApplet extends PApplet {

		  ControlP5 myController;
		  Slider s;
		  
		  public void settings() {
		    size(250, (int) 375);
		  }
		  public void setup() {
		    myController = new ControlP5(this);
		    s = myController.addSlider(query,minValue,maxValue,minValue,100, 63, 50, 250);
		    s.setColorLabel(0);
		    s.setColorTickMark(0);
		    s.setColorValue(0);
		    s.setColorValueLabel(0);
		    s.setNumberOfTickMarks(maxValue-minValue+1);
		  }
		  public void draw() {
		    background(247);
		    fill(0);
		    pValue = (int) s.getValue();
		  }
		  
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

