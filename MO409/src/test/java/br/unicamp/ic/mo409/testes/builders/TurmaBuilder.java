package br.unicamp.ic.mo409.testes.builders;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ic.mo409.model.Aluno;
import br.unicamp.ic.mo409.model.Disciplina;
import br.unicamp.ic.mo409.model.Parametro;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;
import br.unicamp.ic.mo409.model.Usuario;

public class TurmaBuilder implements Builder<Turma>{

	private int ano = 2015;
	private String codTurma = "A";
	private int periodo = 1;
	private List<Professor> professores = new ArrayList<Professor>();
	private int idTurma = 1;
	private List<Aluno> alunos = new ArrayList<Aluno>();
	private Disciplina disciplina;
	private Parametro parametro = new Parametro(50, 50);

    public TurmaBuilder() {}

    public TurmaBuilder withIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
        return this;
    }
    
    public TurmaBuilder withParametro(Parametro parametro) {
        this.parametro = parametro;
        return this;
    }
    
    public TurmaBuilder withParametro(Integer duracao, float porcentagem) {
        this.parametro = new Parametro();
        return this;
    }

    public TurmaBuilder withCodTurma(String codTurma) {
        this.codTurma = codTurma;
        return this;
    }
    
    public TurmaBuilder withDisciplina(String nomeDisciplina, String codDisciplina) {
    	Disciplina disciplina = new Disciplina();
    	disciplina.setCodDisciplina(codDisciplina);
    	disciplina.setNomeDisciplina(nomeDisciplina);
        this.disciplina = disciplina;
        return this;
    }
    
    public TurmaBuilder withDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
        return this;
    }

    public TurmaBuilder withPeriodo(int periodo) {
        this.periodo = periodo;
        return this;
    }

    public TurmaBuilder withAno(int ano) {
        this.ano = ano;
        return this;
    }
    
    public TurmaBuilder withProfessor(int raProfessor, String nome) {
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuario.setPapel("ROLE_ALUNO");
    	
    	Professor professor = new Professor();
    	professor.setRaProfessor(raProfessor);
    	professor.setUsuario(usuario);
        this.professores.add(professor);
        return this;
    }

    public TurmaBuilder withProfessor(Professor professor) {
        this.professores.add(professor);
        return this;
    }
    
    public TurmaBuilder withProfessores(List<Professor> professores) {
        this.professores = professores;
        return this;
    }
    
    public TurmaBuilder withAluno(int raAluno, String nome) {
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuario.setPapel("ROLE_ALUNO");
    	Aluno aluno = new Aluno();
    	aluno.setRaAluno(raAluno);
    	aluno.setUsuario(usuario);
        this.alunos.add(aluno);
        return this;
    }

    public TurmaBuilder withAluno(Aluno aluno) {
        this.alunos.add(aluno);
        return this;
    }
    
    public TurmaBuilder withAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
        return this;
    }

    public Turma build() {
        Turma turma = new Turma();
        turma.setDisciplina(disciplina);
        turma.setAno(ano);
        turma.setParametro(parametro);
        turma.setCodTurma(codTurma);
        turma.setPeriodo(periodo);
        turma.setProfessores(professores);
        turma.setIdTurma(idTurma);
        turma.setAlunos(alunos);
        return turma;
    }
}
