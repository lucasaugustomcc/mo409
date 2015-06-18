package br.unicamp.ic.mo409.model;

import org.junit.*;
import static org.junit.Assert.*;

// JUnit 4.3
public class TestCases{
	
	public static junit.framework.Test suite(){
		return new junit.framework.JUnit4TestAdapter(TestCases.class);
	}
	
	@Test
	public void test1()
	{
		Presenca oTestObject = new Presenca();
		assertEquals(true, (oTestObject.state == PresencaState.em_branco));
		
	}
	
}