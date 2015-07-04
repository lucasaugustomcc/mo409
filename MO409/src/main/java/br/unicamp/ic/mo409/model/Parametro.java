package br.unicamp.ic.mo409.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the tb_presenca database table.
 * 
 */
@Entity
@Table(name = "tb_parametro")
@NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p")
public class Parametro implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_parametro", unique = true, nullable = false)
	private int idParametro;

	@Column(name = "porcentagem", nullable = false)
	private float porcentagem;
	
	@Column(name = "duracao", nullable = false)
	private int duracao;
	
	public Parametro()
	{
		
	}
	
	public Parametro(int duracao, float porcentagem)
	{
		this.duracao = duracao;
		this.porcentagem = porcentagem;
	}

	public int getIdParametro()
	{
		return idParametro;
	}

	public void setIdParametro(int idParametro)
	{
		this.idParametro = idParametro;
	}

	public float getPorcentagem()
	{
		return porcentagem;
	}

	public void setPorcentagem(float porcentagem)
	{
		this.porcentagem = porcentagem;
	}

	public int getDuracao()
	{
		return duracao;
	}

	public void setDuracao(int duracao)
	{
		this.duracao = duracao;
	}
}
