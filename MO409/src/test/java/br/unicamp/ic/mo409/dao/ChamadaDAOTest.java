package br.unicamp.ic.mo409.dao;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import br.unicamp.ic.mo409.model.Usuario;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration (locations = {"file:src/test/resources/applicationContext.xml"}) 
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false) 
@Transactional 
public class ChamadaDAOTest {
	
	@Autowired
	protected UsuarioDAO user;

	@Test
	public void test2() {
		Usuario user = new Usuario();
		user.setNome("lucas");
		assertEquals("lucas", user.getNome());
	}
}
