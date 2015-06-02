package sessao;

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
		Sessao oTestObject = new Sessao();
		State state1 = State.nao_aberta;
		State state3 = State.nao_aberta;
		State state5 = State.nao_aberta;
		assertEquals(true, (oTestObject.state == State.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", state1);
		assertEquals(true, (oTestObject.state == State.aberta));
		oTestObject.handleEvent("abrirChamadaEvent", state3);
		assertEquals(true, (oTestObject.state == State.encerrada));
		oTestObject.handleEvent("encerrarChamadaEvent", state5);
		assertEquals(true, (oTestObject.state == State.encerrada));
		
	}
	
	@Test
	public void test2()
	{
		Sessao oTestObject = new Sessao();
		State state1 = State.nao_aberta;
		State state3 = State.nao_aberta;
		assertEquals(true, (oTestObject.state == State.nao_aberta));
		oTestObject.handleEvent("abrirChamadaEvent", state1);
		assertEquals(true, (state1 == State.nao_aberta));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() == true));
		assertEquals(true, (oTestObject.state == State.aberta));
		assertEquals(true, (oTestObject.recebendo_tick.booleanValue() == true));
		oTestObject.handleEvent("receberTickEvent", state3);
		assertEquals(true, (oTestObject.state == State.aberta));
		
	}
	
}