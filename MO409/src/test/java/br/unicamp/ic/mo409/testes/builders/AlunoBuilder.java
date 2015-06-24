package br.unicamp.ic.mo409.testes.builders;

import br.unicamp.ic.mo409.model.Aluno;
import br.unicamp.ic.mo409.model.Usuario;

public class AlunoBuilder  implements Builder<Aluno>{

    private int raAluno = 161255;
	private String nome = "Lucas Augusto Carvalho";
	private int idUsuario = 1;

	public AlunoBuilder() {}

    public AlunoBuilder withRaAluno(Integer raAluno) {
        this.raAluno = raAluno;
        return this;
    }

    public AlunoBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    } 
    
	public AlunoBuilder withIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
		return this;
	}

    public Aluno build() {
    	Aluno Aluno = new Aluno();
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuario.setPapel("ROLE_ALUNO");
    	usuario.setIdUsuario(idUsuario);
    	
    	Aluno.setRaAluno(raAluno);
    	Aluno.setUsuario(usuario);
    	usuario.setAluno(Aluno);
        return Aluno;
    }


}
