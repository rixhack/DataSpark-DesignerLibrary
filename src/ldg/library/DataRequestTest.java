package ldg.library;

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
		assertNull(tr.sa);
		tr.getValue();
		assertNotNull(tr.sa);
	}
	
	@Test
	public void testSlider() {
		assertEquals(0, tr.getValue());
		assertNotNull(tr.sa);
		tr.sa.setup();
		tr.sa.s.changeValue(5);
		tr.sa.draw();
		assertEquals(5.0,tr.sa.s.getValue(),0.0001);
		assertEquals(5,tr.getValue());
		tr.dispose();
	}
	

}
