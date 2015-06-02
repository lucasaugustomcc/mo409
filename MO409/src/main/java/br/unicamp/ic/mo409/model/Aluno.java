package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_aluno database table.
 * 
 */
@Entity
@Table(name="tb_aluno")
@NamedQuery(name="Aluno.findAll", query="SELECT a FROM Aluno a")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ra_aluno", unique=true, nullable=false)
	private int raAluno;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="tb_usuario_id_usuario", nullable=false)
	private Usuario tbUsuario;

	//bi-directional many-to-many association to Turma
	@ManyToMany(mappedBy="tbAlunos")
	private List<Turma> tbTurmas;

	//bi-directional many-to-one association to Presenca
	@OneToMany(mappedBy="tbAluno")
	private List<Presenca> tbPresencas;

	//bi-directional many-to-one association to Tick
	@OneToMany(mappedBy="Aluno")
	private List<Tick> Ticks;

	public Aluno() {
	}

	public int getRaAluno() {
		return this.raAluno;
	}

	public void setRaAluno(int raAluno) {
		this.raAluno = raAluno;
	}

	public Usuario getTbUsuario() {
		return this.tbUsuario;
	}

	public void setTbUsuario(Usuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

	public List<Turma> getTbTurmas() {
		return this.tbTurmas;
	}

	public void setTbTurmas(List<Turma> tbTurmas) {
		this.tbTurmas = tbTurmas;
	}

	public List<Presenca> getTbPresencas() {
		return this.tbPresencas;
	}

	public void setTbPresencas(List<Presenca> tbPresencas) {
		this.tbPresencas = tbPresencas;
	}

	public Presenca addTbPresenca(Presenca tbPresenca) {
		getTbPresencas().add(tbPresenca);
		tbPresenca.setTbAluno(this);

		return tbPresenca;
	}

	public Presenca removeTbPresenca(Presenca tbPresenca) {
		getTbPresencas().remove(tbPresenca);
		tbPresenca.setTbAluno(null);

		return tbPresenca;
	}

	public List<Tick> getTicks() {
		return this.Ticks;
	}

	public void setTicks(List<Tick> Ticks) {
		this.Ticks = Ticks;
	}

	public Tick addTick(Tick Tick) {
		getTicks().add(Tick);
		Tick.setAluno(this);

		return Tick;
	}

	public Tick removeTick(Tick Tick) {
		getTicks().remove(Tick);
		Tick.setAluno(null);

		return Tick;
	}

}