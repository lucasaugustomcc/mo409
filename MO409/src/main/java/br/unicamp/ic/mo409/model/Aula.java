package br.unicamp.ic.mo409.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_aula")
@NamedQuery(name="Aula.findAll", query="SELECT p FROM Aula p")
public class Aula implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_aula", unique=true, nullable=false)
	private int idAula;
	
	@Temporal(TemporalType.TIME)
	@Column(name="hora_inicio")
	private Date horaInicio;
	
	@Temporal(TemporalType.TIME)
	@Column(name="hora_fim")
	private Date horaFim;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_aula")
	private Date data;
}
