package br.unicamp.ic.mo409.model;

public class Presenca {

	public PresencaState state;
	public Integer idPresenca;
	public Integer numTicks;
	public Boolean isPresent;
	public Integer minTicks;

	public Presenca()
	{
		state = PresencaState.calculando;
	}

	public void calcularPresenca(Integer numTicks) {
		if (numTicks >= minTicks)
		{
			state = PresencaState.presente;
		}
		else
		{
			state = PresencaState.ausente;
		}
	}

	public void checkInPresenca() {
		state = PresencaState.em_branco;  ///??
	}

	public void checkOutPresenca() {
		state = PresencaState.em_aula;    ///??
	}
	
////////
	
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
				Integer numTicks;
				
				if (tempNumTicks > 0)
				{
				    numTicks = 1;
				}
				else
				{
					numTicks = 2;
				}
				calcularPresenca(numTicks);
			}
			
			if ((state == PresencaState.em_aula) 
					&& (sEventName.compareTo("calcularPresencaEvent") == 0))
			{
				Integer tempNumTicks    = (Integer) in_colObject[1];
				Integer numTicks;
				
				if (tempNumTicks > 0)
				{
				    numTicks = 1;
				}
				else
				{
					numTicks = 2;
				}
				calcularPresenca(numTicks);
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
				calcularPresenca(numTicks);
			}
			
//          Se estiver no estado "calculando", independentemente de acao de disparo, vai para:
//			"presente" se numTicks >= minTicks   ou
//			"ausente"  caso contrario
//          Deixei o esqueleto de codigo logo a seguir apenas para caso seja necessario 
//			tratar aqui estas condicoes.
//			
//			if ((state == PresencaState.calculando) 
//				&& (????))			
//		    {
//		    	
//		    }

		}
	}

	public void setNumTicks(Integer numTicks) {
		this.numTicks = numTicks;
	}

	public Integer getNumTicks() {
		return numTicks;
	}

	public void setMinTicks(Integer minTicks) {
		this.minTicks = minTicks;
	}

	public Integer getMinTicks() {
		return minTicks;
	}

}
