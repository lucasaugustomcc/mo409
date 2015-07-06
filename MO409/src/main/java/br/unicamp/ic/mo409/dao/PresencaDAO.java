package br.unicamp.ic.mo409.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.unicamp.ic.mo409.model.ChamadaState;
import br.unicamp.ic.mo409.model.Presenca;
import br.unicamp.ic.mo409.model.PresencaState;

@Repository("PresencaDAO")
public class PresencaDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3922406143939849621L;
	
	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	public Presenca find(Integer id) 
	{
		return entityManager.find(Presenca.class, id);
	}

	public void persist(Presenca Presenca) {
		entityManager.persist(Presenca);
		entityManager.flush();
	}

	public void merge(Presenca Presenca) 
	{
		entityManager.merge(Presenca);
	}

	public void remove(Presenca Presenca) {
		entityManager.remove(Presenca);
	}

	@SuppressWarnings("unchecked")
	public List<Presenca> findAll() {		
		return entityManager.createQuery("SELECT c FROM Presenca c")
				.getResultList();
	}
	
	public Presenca findPresencaChamadaAbertaAluno(Integer idChamada, Integer raAluno) 
	{
		return (Presenca) entityManager.createQuery("SELECT p FROM Presenca p left join p.chamada c left join p.aluno a WHERE a.raAluno = :raAluno"
				+ " AND c.idChamada = :idChamada"
				+ " AND c.state = :chamadaState")
				.setParameter("raAluno", raAluno)
				.setParameter("idChamada", idChamada)
				.setParameter("chamadaState", ChamadaState.aberta)
				.getSingleResult();
	}
	
	public Presenca findPresencaChamadasAluno(Integer raAluno) 
	{
		return (Presenca) entityManager.createQuery("SELECT p FROM Presenca p left join p.aluno a WHERE a.raAluno = :raAluno AND p.state = :presencaState")
				.setParameter("raAluno", raAluno)
				.setParameter("presencaState", PresencaState.em_aula)
				.getSingleResult();
	}
	
	public Long quantidadePresencasAlunoTurma(Integer raAluno, Integer idTurma, PresencaState state) 
	{
		return (Long) entityManager.createQuery("SELECT COUNT(p.id) as quantidade FROM Presenca p left join p.aluno a left join p.chamada c left join c.turma t "
				+ "WHERE t.id = :idTurma AND a.raAluno = :raAluno AND p.state = :presencaState")
				.setParameter("raAluno", raAluno)
				.setParameter("idTurma", idTurma)
				.setParameter("presencaState", state)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Presenca> presencasAlunoChamadasEncerradas(Integer raAluno, Integer idTurma)
	{
		return (List<Presenca>) entityManager.createQuery("SELECT p FROM Presenca p left join p.aluno a left join p.chamada c left join c.turma t "
				+ "WHERE t.id = :idTurma AND a.raAluno = :raAluno AND c.state = :chamadaState AND (p.state = :presencaStatePresente OR p.state = :presencaStateAusente)")
				.setParameter("raAluno", raAluno)
				.setParameter("idTurma", idTurma)
				.setParameter("chamadaState", ChamadaState.encerrada)
				.setParameter("presencaStatePresente", PresencaState.presente)
				.setParameter("presencaStateAusente", PresencaState.ausente)
				.getResultList();
	}
}
