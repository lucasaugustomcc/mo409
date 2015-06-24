package br.unicamp.ic.mo409.model;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the tb_presenca database table.
 * 
 */
@Entity
@Table(name="tb_presenca")
@NamedQuery(name="Presenca.findAll", query="SELECT p FROM Presenca p")
public class Presenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_presenca", unique=true, nullable=false)
	private int idPresenca;

	@Column(name="is_presente")
	private boolean isPresente;
	
	PresencaState state;

	@Column(name="num_ticks", nullable=false)
	private int numTicks;

	//bi-directional many-to-one association to Aluno
	@ManyToOne
	@JoinColumn(name="tb_aluno_ra_aluno", nullable=false)
	private Aluno aluno;

	//bi-directional many-to-one association to Aula
	@ManyToOne
	@JoinColumn(name="tb_aula_id_aula", nullable=false)
	private Chamada chamada;

	public Presenca() {
		state = PresencaState.em_branco;
	}

	public int getIdPresenca() {
		return this.idPresenca;
	}

	public void setIdPresenca(int idPresenca) {
		this.idPresenca = idPresenca;
	}

	public boolean getIsPresente() {
		return this.isPresente;
	}

	public void setIsPresente(boolean isPresente) {
		this.isPresente = isPresente;
	}

	public int getNumTicks() {
		return this.numTicks;
	}

	public void setNumTicks(int numTicks) {
		this.numTicks = numTicks;
	}

	public Aluno getAluno() {
		return this.aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Chamada getChamada() {
		return this.chamada;
	}

	public void setChamada(Chamada chamada) {
		this.chamada = chamada;
	}

	public void calcularPresenca(Integer numTicksMinino) {
		state = PresencaState.calculando;
		
		if (numTicks >= numTicksMinino)
		{
			state = PresencaState.presente;
		}
		else
		{
			state = PresencaState.ausente;
		}
	}
	
	public void handleEvent(Object... in_colObject) {
		if (in_colObject.length > 0) 
		{
			String sEventName = (String) in_colObject[0];
			if ((state == PresencaState.em_branco)
					&& (sEventName.compareTo("checkInPresencaEvent") == 0)) 
			{
				checkInPresenca();
			}
			
			if ((state == PresencaState.em_aula)
					&& (sEventName.compareTo("checkOutPresencaEvent") == 0)) 
			{
				checkOutPresenca();
			}
			
			if ((state == PresencaState.em_branco) 
					&& sEventName.compareTo("calcularPresencaEvent") == 0 ) 
			{				
				Integer tempNumTicks    = (Integer) in_colObject[1];
				Integer numMinTicks;
				
				if (tempNumTicks > 0)
				{
				    numMinTicks = 1;
				}
				else
				{
					numMinTicks = 2;
				}
				setNumTicks(0);
				calcularPresenca(numMinTicks);
			}
			
			if ((state == PresencaState.em_aula) 
					&& (sEventName.compareTo("calcularPresencaEvent") == 0))
			{
				Integer tempNumTicks    = (Integer) in_colObject[1];
				Integer numMinTicks;
				
				if (tempNumTicks > 0)
				{
					numMinTicks = 1;
				}
				else
				{
					numMinTicks = 2;
				}
				setNumTicks(5);
				calcularPresenca(numMinTicks);
			}
			
			if ((state == PresencaState.fora_de_aula)
					&& (sEventName.compareTo("calcularPresencaEvent") == 0)) 
			{
				Integer tempNumTicks = (Integer) in_colObject[1];
				Integer numTicks;
				
				if (tempNumTicks > 0)
				{
				    numTicks = 1;
				}
				else
				{
					numTicks = 2;
				}
				setNumTicks(5);
				calcularPresenca(numTicks);
			}
			
			if ((state == PresencaState.calculando) 
					&& (sEventName.compareTo("calcularPresencaEvent") == 0)) 
			{
				Integer tempNumTicks = (Integer) in_colObject[1];
				Integer numTicks;
				
				if (tempNumTicks > 0)
				{
				    numTicks = 1;
				}
				else
				{
					numTicks = 2;
				}
				setNumTicks(5);
				calcularPresenca(numTicks);
			}
		}
	}

	public void checkOutPresenca() {
		state = PresencaState.fora_de_aula;
		
	}

	public void checkInPresenca() {
		state = PresencaState.em_aula;
		
	}

}