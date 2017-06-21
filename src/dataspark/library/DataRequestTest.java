package dataspark.library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.junit.Test;

import processing.core.PApplet;

public class DataRequestTest {

	TwitterRequest tr = new TwitterRequest(new PApplet(), "eindhoven");
	
	@Test
	public void testQuery() {
		assertEquals("eindhoven", tr.query);
	}
	
	@Test
	public void testMax() {
		assertEquals(Integer.MAX_VALUE, tr.maxValue);
		tr.setMaxValue(50);
		assertEquals(50, tr.maxValue);
	}
	
	@Test
	public void testMin() {
		assertEquals(0, tr.minValue);
		tr.setMinValue(1);
		assertEquals(1, tr.minValue);
	}
	
	@Test
	public void testSecondApplet() {
		assertNull(tr.ds);
		tr.getValue();
		assertNotNull(tr.ds);
	}
	
	@Test
	public void testSlider() throws InterruptedException {
		assertEquals(0, tr.getValue());
		assertNotNull(tr.ds);
		tr.ds.setup();
		Thread.sleep(1000);
		tr.ds.draw();
		Thread.sleep(1000);
		tr.ds.s.changeValue(5);
		tr.ds.draw();
		assertEquals(5.0,tr.ds.s.getValue(),0.0001);
		assertEquals(5,tr.getValue());
		tr.dispose();
	}
	

}
