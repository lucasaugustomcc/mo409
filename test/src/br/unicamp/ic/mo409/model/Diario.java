package br.unicamp.ic.mo409.model;


public class Diario {

	public DiarioState state;
	public Boolean isPreenchendoDiario;
	public Boolean hasPreenchidoDiario;
	public Boolean hasFrequenciaTurma;
	public Boolean hasFrequenciaAluno;
	public Integer idTurma;
	public Integer idAluno;


	public Diario()
	{
		state = DiarioState.nao_aberto;   // ou inicia direto em "aberto"??? 
	}

	public void abrirDiario(Integer listaPresencas) {
		state = DiarioState.aberto;
	}

	public void encerrarDiario(Integer listaPresencas) {
		state = DiarioState.encerrado;
	}

	public void consultaFrequenciaTurma(Integer idTurma) {
		state = DiarioState.aberto;   // Este metodo tambem e' chamado no estado "encerrado" 
	}

	public void consultaFrequenciaAluno(Integer idAluno, Integer idTurma) {
		state = DiarioState.aberto;   // Este metodo tambem e' chamado no estado "encerrado" 
	}

	public void handleEvent(Object... in_colObject) {
		if (in_colObject.length > 0) 
		{
			String sEventName = (String) in_colObject[0];
			if ((state == DiarioState.nao_aberto) 
				&& (sEventName.compareTo("abrirDiarioEvent") == 0)) 
			{
				Integer tempIDTurma     = (Integer) in_colObject[1];
				Integer idTurma;

				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				abrirDiario(idTurma);
			}

			if ((state == DiarioState.aberto) 
				&& (sEventName.compareTo("encerrarDiarioEvent") == 0)) 
			{
				Integer tempIDTurma     = (Integer) in_colObject[1];
				Integer idTurma;

				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				encerrarDiario(idTurma);
			}

			if (((state == DiarioState.aberto) || (state == DiarioState.encerrado))
				&& (sEventName.compareTo("consultaFrequenciaTurmaEvent") == 0)) 
			{
				Integer tempIDTurma     = (Integer) in_colObject[1];
				Integer idTurma;

				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				consultaFrequenciaTurma(idTurma);
			}

			if (((state == DiarioState.aberto) || (state == DiarioState.encerrado))
				&& (sEventName.compareTo("consultaFrequenciaAlunoEvent") == 0)) 
			{
				Integer tempIDTurma     = (Integer) in_colObject[1];
				Integer idTurma;
				Integer tempIDAluno     = (Integer) in_colObject[2];
				Integer idAluno;

				if (tempIDTurma > 0)
				{
				    idTurma = 1;
				}
				else
				{
					idTurma = 2;
				}
				if (tempIDAluno > 0)
				{
				    idAluno = 1;
				}
				else
				{
					idAluno = 2;
				}
				consultaFrequenciaAluno(idAluno, idTurma);
			}
		}
	}

}
