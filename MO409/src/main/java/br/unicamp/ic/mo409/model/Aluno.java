package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ALUNO database table.
 * 
 */
@Entity
@Table(name="ALUNO")
@NamedQuery(name="Aluno.findAll", query="SELECT a FROM Aluno a")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int ra;

	//bi-directional many-to-one association to Presenca
	@OneToMany(mappedBy="aluno")
	private List<Presenca> presencas;

	//bi-directional many-to-one association to Tick
	@OneToMany(mappedBy="aluno")
	private List<Tick> ticks;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="aluno")
	private List<Usuario> usuarios;

	public Aluno() {
	}

	public int getRa() {
		return this.ra;
	}

	public void setRa(int ra) {
		this.ra = ra;
	}

	public List<Presenca> getPresencas() {
		return this.presencas;
	}

	public void setPresencas(List<Presenca> presencas) {
		this.presencas = presencas;
	}

	public Presenca addPresenca(Presenca presenca) {
		getPresencas().add(presenca);
		presenca.setAluno(this);

		return presenca;
	}

	public Presenca removePresenca(Presenca presenca) {
		getPresencas().remove(presenca);
		presenca.setAluno(null);

		return presenca;
	}

	public List<Tick> getTicks() {
		return this.ticks;
	}

	public void setTicks(List<Tick> ticks) {
		this.ticks = ticks;
	}

	public Tick addTick(Tick tick) {
		getTicks().add(tick);
		tick.setAluno(this);

		return tick;
	}

	public Tick removeTick(Tick tick) {
		getTicks().remove(tick);
		tick.setAluno(null);

		return tick;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setAluno(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setAluno(null);

		return usuario;
	}

}