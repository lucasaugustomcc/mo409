package br.unicamp.ic.mo409.model;

import java.util.Date;


public class Chamada {

	public ChamadaState state;
	public Boolean recebendo_tick;
	public Date hora_inicio;
	public Date hora_fim;
	public Date data_aula;

	public Chamada()
	{
		state = ChamadaState.nao_aberta;
		setRecebendoTick(false);
	}

	public void abrirChamada(ChamadaState state) {
	}

	public void encerrarChamada(String hora_fim) {
	}

	public void calculaPresenca(ChamadaState state) {
	}

	public void receberTick(ChamadaState state) {
	}

	public void handleEvent(Object... in_colObject) {
		if (in_colObject.length > 0) 
		{
			String sEventName = (String) in_colObject[0];
			if ((state == ChamadaState.nao_aberta)
					&& (sEventName.compareTo("abrirChamadaEvent") == 0)) 
			{
					state = ChamadaState.aberta;
			}
			
			if (sEventName.compareTo("receberTickEvent") == 0) 
			{
				if (state == ChamadaState.aberta) 
				{
					receberTick(state);
					state = ChamadaState.aberta;
				}
			}
			
			if (sEventName.compareTo("encerrarChamadaEvent") == 0) 
			{
				if (state == ChamadaState.aberta) 
				{
					state = ChamadaState.encerrada;
				}
			}
			
			if (sEventName.compareTo("calcularPresencaEvent") == 0) 
			{
				if (state == ChamadaState.encerrada) 
				{
					state = ChamadaState.encerrada;
				}
			}
		}
	}

	public void setRecebendoTick(Boolean recebendo_tick) {
		this.recebendo_tick = recebendo_tick;
	}

	public Boolean getRecebendoTick() {
		return recebendo_tick;
	}

	public void setHoraInicio(Date hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public Date getHoraInicio() {
		return hora_inicio;
	}

	public void setHoraFim(Date hora_fim) {
		this.hora_fim = hora_fim;
	}

	public Date getHoraFim() {
		return hora_fim;
	}
}