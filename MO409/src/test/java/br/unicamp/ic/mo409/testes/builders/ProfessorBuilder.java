package br.unicamp.ic.mo409.testes.builders;

import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Usuario;

public class ProfessorBuilder {

    private int raProfessor = 1;
	private String nome = "Eliane Martins";
	private int idUsuario = 1;

	public ProfessorBuilder() {}

    public ProfessorBuilder withRaProfessor(Integer raProfessor) {
        this.raProfessor = raProfessor;
        return this;
    }

    public ProfessorBuilder withNome(String nome) {
        this.nome = nome;
        return this;
    } 
    
	public ProfessorBuilder withIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
		return this;
	}

    public Professor build() {
    	Professor professor = new Professor();
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuario.setPapel("ROLE_PROFESSOR");
    	usuario.setIdUsuario(idUsuario);
    	
    	professor.setRaProfessor(raProfessor);
    	professor.setUsuario(usuario);
    	usuario.setProfessores(professor);
        return professor;
    }


}
