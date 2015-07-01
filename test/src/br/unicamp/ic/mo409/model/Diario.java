package br.unicamp.ic.mo409.model;


public class Diario {

	public DiarioState state;
	public Boolean isPreenchendoDiario;
	public Boolean hasPreenchidoDiario;
	public Boolean hasFrequenciaTurma;
	public Boolean hasFrequenciaAluno;
	public Integer idAluno;
	

	public Diario()
	{
		state = DiarioState.vazio;
	}

	public void preencherDiario(Integer listaPresencas) {
		state = DiarioState.sendo_preenchido;
	}

	public void preenchidoDiario(Integer listaPresencas) {
		state = DiarioState.nao_vazio;
	}

	public void getFrequenciaTurma(Integer idTurma) {
		state = DiarioState.nao_vazio;
	}

	public void getFrequenciaAluno(Integer idAluno, Integer idTurma) {
		state = DiarioState.nao_vazio;
	}

	public void handleEvent(Object... in_colObject) {
		if (in_colObject.length > 0) 
		{
			String sEventName = (String) in_colObject[0];
			if ((state == DiarioState.vazio) 
					&& (sEventName.compareTo("preencherDiarioEvent") == 0)) 
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
				preencherDiario(idTurma);
			}

			if ((state == DiarioState.nao_vazio) 
					&& (sEventName.compareTo("preenchidoDiarioEvent") == 0)) 
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
				preenchidoDiario(idTurma);
			}

			if ((state == DiarioState.frequencia_turma) 
					&& (sEventName.compareTo("getFrequenciaTurmaEvent") == 0)) 
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
				getFrequenciaTurma(idTurma);
			}

			if ((state == DiarioState.frequencia_aluno) 
					&& (sEventName.compareTo("getFrequenciaAlunoEvent") == 0)) 
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
				getFrequenciaAluno(idAluno, idTurma);
			}
		}
	}

}
