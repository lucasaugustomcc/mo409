package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the tb_aluno database table.
 * 
 */
@Entity
@Table(name = "tb_aluno")
@NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a")
public class Aluno implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ra_aluno", unique = true, nullable = false)
	private int raAluno;

	// bi-directional one-to-one association to Usuario
	@OneToOne
	@JoinColumn(name = "tb_usuario_id_usuario", nullable = false)
	private Usuario usuario;

	// bi-directional many-to-one association to Presenca
	@OneToMany(mappedBy = "aluno")
	private List<Presenca> presencas;

	// bi-directional many-to-one association to Tick
	@OneToMany(mappedBy = "aluno")
	private List<Tick> ticks;

	// bi-directional many-to-many association to Turma
	@ManyToMany(mappedBy = "alunos")
	private List<Turma> turmas;

	public Aluno()
	{
	}

	public int getRaAluno()
	{
		return this.raAluno;
	}

	public void setRaAluno(int raAluno)
	{
		this.raAluno = raAluno;
	}

	public Usuario getUsuario()
	{
		return this.usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public List<Presenca> getPresencas()
	{
		return this.presencas;
	}

	public void setPresencas(List<Presenca> presencas)
	{
		this.presencas = presencas;
	}

	public Presenca addPresenca(Presenca presenca)
	{
		getPresencas().add(presenca);
		presenca.setAluno(this);

		return presenca;
	}

	public Presenca removePresenca(Presenca presenca)
	{
		getPresencas().remove(presenca);
		presenca.setAluno(null);

		return presenca;
	}

	public List<Tick> getTicks()
	{
		return this.ticks;
	}

	public void setTicks(List<Tick> ticks)
	{
		this.ticks = ticks;
	}

	public Tick addTick(Tick tick)
	{
		getTicks().add(tick);
		tick.setAluno(this);

		return tick;
	}

	public Tick removeTick(Tick tick)
	{
		getTicks().remove(tick);
		tick.setAluno(null);

		return tick;
	}

	public List<Turma> getTurmas()
	{
		return this.turmas;
	}

	public void setTurmas(List<Turma> turmas)
	{
		this.turmas = turmas;
	}

	@Override
	public boolean equals(Object object)
	{
		boolean sameSame = false;

		if (object != null && object instanceof Aluno)
		{
			sameSame = this.raAluno == ((Aluno) object).getRaAluno();
		}

		return sameSame;
	}
}