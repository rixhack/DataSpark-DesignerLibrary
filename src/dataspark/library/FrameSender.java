package dataspark.library;

import processing.core.PApplet;
import processing.core.PImage;

/*
 * Stub class with the only purpose to make both backend and designer libraries share
 * the same interface. This allows the designers to upload their sketches without having
 * to add or modify any code.
 */
public class FrameSender {

	public FrameSender(PApplet parent){
		
	}
	
	/*
	 * Function used by the backend library to convert and send frames to the web socket.
	 * It will have no functionality in the designer library.
	 * 
	 * @param img: The frame image to convert and send.
	 */
	public void send(PImage img){
		
	}
}
