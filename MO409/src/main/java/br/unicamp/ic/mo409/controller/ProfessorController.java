package br.unicamp.ic.mo409.controller;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.unicamp.ic.mo409.dao.AlunoDAO;
import br.unicamp.ic.mo409.dao.ChamadaDAO;
import br.unicamp.ic.mo409.dao.ParametroDAO;
import br.unicamp.ic.mo409.dao.PresencaDAO;
import br.unicamp.ic.mo409.dao.ProfessorDAO;
import br.unicamp.ic.mo409.dao.TurmaDAO;
import br.unicamp.ic.mo409.dao.UsuarioDAO;
import br.unicamp.ic.mo409.model.Aluno;
import br.unicamp.ic.mo409.model.Chamada;
import br.unicamp.ic.mo409.model.ChamadaState;
import br.unicamp.ic.mo409.model.Parametro;
import br.unicamp.ic.mo409.model.Presenca;
import br.unicamp.ic.mo409.model.PresencaState;
import br.unicamp.ic.mo409.model.Professor;
import br.unicamp.ic.mo409.model.Turma;
import br.unicamp.ic.mo409.model.Usuario;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@RestController
@Transactional
public class ProfessorController
{

	@Autowired
	UsuarioDAO usuarioDAO;

	@Autowired
	ChamadaDAO chamadaDAO;

	@Autowired
	ProfessorDAO professorDAO;

	@Autowired
	TurmaDAO turmaDAO;
	
	@Autowired
	AlunoDAO alunoDAO;

	@Autowired
	private ParametroDAO parametroDAO;

	@Autowired
	private PresencaDAO presencaDAO;

	@ModelAttribute("usuario")
	public Usuario getUsuario()
	{

		Authentication auth = (Authentication) SecurityContextHolder
				.getContext().getAuthentication();

		UserDetails user = (UserDetails) auth.getPrincipal();
		Usuario usuario = usuarioDAO.loadUsuarioByUsername(user.getUsername());

		return usuario;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/turmas", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray turmasChamada(@ModelAttribute("usuario") Usuario usuario)
	{

		List<Turma> turmas = turmaDAO.listarTurmasProfessor(Integer
				.valueOf(usuario.getProfessor().getRaProfessor()));

		// construir resposta JSON
		JSONArray array = new JSONArray();
		for (Turma turma : turmas)
		{
			JSONObject obj = new JSONObject();
			obj.put("idTurma", turma.getIdTurma());
			obj.put("codTurma", turma.getCodTurma());
			obj.put("codDisciplina", turma.getDisciplina().getCodDisciplina());
			obj.put("nomeDisciplina", turma.getDisciplina().getNomeDisciplina());
			obj.put("numAlunos", turma.getAlunos().size());
			array.add(obj);
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/abertas", method = RequestMethod.GET)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray chamadaAbertas(@ModelAttribute("usuario") Usuario usuario) throws Exception
	{
		Professor professor = usuario.getProfessor();	
		List<Chamada> chamadas = chamadaDAO.listChamadasAbertasProfessor(professor.getRaProfessor());
				
		// construindo JSON de resposta
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		
		for (Chamada chamada : chamadas)
		{
			Turma turma = chamada.getTurma();								

			// construir resposta JSON
			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
			obj.put("professorChamada", chamada.getProfessor().getUsuario().getNome());
			obj.put("duracao", chamada.getParametro().getDuracao());
			obj.put("porcentagem", chamada.getParametro().getPorcentagem());
			obj.put("numMinTicks", chamada.calcularNumMinTicks());
			
			// dados da turma
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);
			array.add(obj);
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/criadas", method = RequestMethod.GET)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray chamadasCriadas(@ModelAttribute("usuario") Usuario usuario) throws Exception
	{
		Professor professor = usuario.getProfessor();	
		List<Chamada> chamadas = chamadaDAO.listChamadasCriadasProfessor(professor.getRaProfessor());
				
		// construindo JSON de resposta
		JSONArray array = new JSONArray();
		
		for (Chamada chamada : chamadas)
		{
			Turma turma = chamada.getTurma();								

			// construir resposta JSON
			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("professorChamada", chamada.getProfessor().getUsuario().getNome());

			// dados da turma
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);
			array.add(obj);
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/criar", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray criarChamada(@ModelAttribute("usuario") Usuario usuario,
			@RequestBody List<Turma> turmas) throws Exception
	{
			
		for (Turma turma : turmas)
		{
			if (chamadaDAO.hasChamadaAbertaTurma(turma.getIdTurma()))
			{
				throw new Exception("Já existe uma chamada aberta para esta turma");
			}
		}
		
		Professor professor = usuario.getProfessor();

		// construindo JSON de resposta
		JSONArray array = new JSONArray();
		
		for (Turma turma : turmas)
		{
			turma = turmaDAO.find(turma.getIdTurma());
			if (turma == null)
			{
				throw new NoResultException("Turma não existente");
			}
			if (!turma.getProfessores().contains(professor))
			{
				throw new AccessDeniedException(
						"Professor não associado a turma.");
			}

			// abrir chamada
			Chamada chamada = new Chamada(turma, professor);
			Parametro parametro = chamada.getParametro();
			parametroDAO.persist(parametro);
			chamadaDAO.persist(chamada);			

			// construir resposta JSON
			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());

			// dados da turma
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);
			array.add(obj);
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/localizacao", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray localizacaoChamadas(@ModelAttribute("usuario") Usuario usuario,
			@RequestBody ProfessorLocalizacaoWrapper localizacao) throws Exception
	{			
		Professor professor = usuario.getProfessor();

		// construindo JSON de resposta
		JSONArray array = new JSONArray();
		
		List<Chamada> chamadas = localizacao.getChamadas();
		
		for (Chamada chamada : chamadas)
		{
			chamada = chamadaDAO.find(chamada.getIdChamada());
			
			if (!chamada.getTurma().getProfessores().contains(professor))
			{
				throw new AccessDeniedException(
						"Professor não associado a turma.");
			}
			
			chamada.atribuirLocalizacao(Float.valueOf(localizacao.getLatitude()),Float.valueOf(localizacao.getLongitude()));
			
			Turma turma = chamada.getTurma();
			// construir resposta JSON
			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("latitude", chamada.getLatitude());
			obj.put("longitude", chamada.getLongitude());

			// dados da turma
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);
			array.add(obj);
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/alterar-parametros", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray parametrosChamadas(@ModelAttribute("usuario") Usuario usuario,
			@RequestBody ProfessorParametrosWrapper parametros) throws Exception
	{			
		Professor professor = usuario.getProfessor();

		// construindo JSON de resposta
		JSONArray array = new JSONArray();
		
		List<Chamada> chamadas = parametros.getChamadas();
		
		for (Chamada chamada : chamadas)
		{
			
			chamada = chamadaDAO.find(chamada.getIdChamada());
			
			if (!chamada.getTurma().getProfessores().contains(professor))
			{
				throw new AccessDeniedException(
						"Professor não associado a turma.");
			}
			
			chamada.atribuirParametros(parametros.getDuracao(),parametros.getPorcentagem());			
			
			Turma turma = chamada.getTurma();
			// construir resposta JSON
			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("duracao", chamada.getParametro().getDuracao());
			obj.put("porcentagem", chamada.getParametro().getPorcentagem());

			// dados da turma
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);
			array.add(obj);
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/visualizar-parametros", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray visualizarParametrosChamadas(@ModelAttribute("usuario") Usuario usuario,
			@RequestBody List<Chamada> chamadas) throws Exception
	{			
		Professor professor = usuario.getProfessor();

		// construindo JSON de resposta
		JSONArray array = new JSONArray();
				
		for (Chamada chamada : chamadas)
		{
			
			chamada = chamadaDAO.find(chamada.getIdChamada());
			
			if (!chamada.getTurma().getProfessores().contains(professor))
			{
				throw new AccessDeniedException(
						"Professor não associado a turma.");
			}
			
			Parametro parametros = chamada.visualizarParametros();			
			
			// construir resposta JSON
			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("duracao", parametros.getDuracao());
			obj.put("porcentagem", parametros.getPorcentagem());
			
			array.add(obj);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/abrir", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray abrirChamada(@ModelAttribute("usuario") Usuario usuario,
			@RequestBody List<Chamada> chamadas) throws Exception
	{			
		Professor professor = usuario.getProfessor();

		// construindo JSON de resposta
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		
		for (Chamada chamada : chamadas)
		{
			chamada = chamadaDAO.find(chamada.getIdChamada());
			if (chamada == null)
			{
				throw new NoResultException("Turma não existente");
			}
			if (!chamada.getTurma().getProfessores().contains(professor))
			{
				throw new AccessDeniedException(
						"Professor não associado a turma.");
			}

			// abrir chamada			
			chamada.abrirChamada(
					new Date(System.currentTimeMillis()),
					new Time(System.currentTimeMillis()));
			
			
			Turma turma = chamada.getTurma();
			// construir resposta JSON
			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
			obj.put("duracao", chamada.getParametro().getDuracao());
			obj.put("porcentagem", chamada.getParametro().getPorcentagem());
			obj.put("numMinTicks", chamada.calcularNumMinTicks());

			// dados da turma
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);
			array.add(obj);
		}
		return array;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/chamada/encerrar", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray encerrarChamada(
			@ModelAttribute("usuario") Usuario usuario,
			@RequestBody List<Chamada> chamadas)
	{

		Professor professor = usuario.getProfessor();

		// construir resposta JSON
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();
		for (Chamada chamada : chamadas)
		{
			chamada = chamadaDAO.find(chamada.getIdChamada());
			chamada.encerrarChamada(new Time(System.currentTimeMillis()));
			chamada.calcularPresenca();

			// dados da chamada
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
			obj.put("horaFim", shf.format(chamada.getHoraFim()));

			// obtendo dados da turma
			Turma turma = chamada.getTurma();
			if (!turma.getProfessores().contains(professor))
			{
				throw new AccessDeniedException(
						"Professor não associado a turma.");
			}
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);
			array.add(obj);
		}
		return array;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/professor/chamada/presenca", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray relatorioChamada(
			@ModelAttribute("usuario") Usuario usuario,
			@RequestBody List<Chamada> chamadas) throws Exception
	{

		Professor professor = usuario.getProfessor();

		// construir resposta JSON
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		JSONArray array = new JSONArray();

		for (Chamada chamada : chamadas)
		{
			// obtendo objeto chamada do banco
			
			chamada = chamadaDAO.find(chamada.getIdChamada());
			if (null == chamada)
			{
				throw new NoResultException("Chamada inexistente");
			}	
			if (ChamadaState.encerrada != chamada.getState())
			{
				throw new Exception("Chamada não encerrada ainda. Resultado da presença inexistente");
			}	
			
			JSONObject obj = new JSONObject();
			obj.put("idChamada", chamada.getIdChamada());
			obj.put("numMinTicks", chamada.calcularNumMinTicks());
			obj.put("duracao", chamada.getParametro().getDuracao());
			obj.put("porcentagem", chamada.getParametro().getPorcentagem());
			obj.put("dataChamada", sdf.format(chamada.getDataChamada()));
			obj.put("horaInicio", shf.format(chamada.getHoraInicio()));
			obj.put("horaFim", shf.format(chamada.getHoraFim()));

			// obtendo dados da turma
			Turma turma = chamada.getTurma();
			if (!turma.getProfessores().contains(professor))
			{
				throw new AccessDeniedException(
						"Professor não associado a turma.");
			}
			JSONObject objTurma = new JSONObject();
			objTurma.put("idTurma", turma.getIdTurma());
			objTurma.put("codTurma", turma.getCodTurma());
			objTurma.put("codDisciplina", turma.getDisciplina()
					.getCodDisciplina());
			objTurma.put("nomeDisciplina", turma.getDisciplina()
					.getNomeDisciplina());
			obj.put("turma", objTurma);

			JSONArray arrayPresencas = new JSONArray();

			// obtendo relatorio de presença dos alunos de cada chamada
			Set<Presenca> presencas = chamada.getPresencas();
			for (Presenca presenca : presencas)
			{
				Aluno aluno = presenca.getAluno();
				JSONObject objPresenca = new JSONObject();
				objPresenca.put("raAluno", aluno.getRaAluno());
				objPresenca.put("nomeAluno", aluno.getUsuario().getNome());
				objPresenca.put("resultado", presenca.visualizarPresenca());
				objPresenca.put("horaInicio", shf.format(presenca.getHoraInicio() != null ? presenca.getHoraInicio() : new Time(0,0,0)));
				objPresenca.put("horaFim", shf.format(presenca.getHoraFim() != null ? presenca.getHoraFim() : new Time(0,0,0)));
				objPresenca.put("numTicks", presenca.getNumTicks());
				arrayPresencas.add(objPresenca);
			}
			obj.put("alunos", arrayPresencas);

			array.add(obj);
		}
		return array;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/turma/consultar-alunos", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONArray consultarAlunos(@ModelAttribute("usuario") Usuario usuario,
			@RequestBody ConsultarAlunoWrapper alunoConsulta) throws Exception
	{						
		Professor professor = usuario.getProfessor();
		
		
		List<Aluno> alunos = alunoDAO.consultarAlunosTurmasProfessor(
				alunoConsulta.getRaAluno(),
				alunoConsulta.getNomeAluno(), 
				professor.getRaProfessor());
		
		JSONArray array = new JSONArray();
		for (Aluno aluno : alunos)
		{
			JSONObject objAluno = new JSONObject();
			objAluno.put("raAluno", aluno.getRaAluno());
			objAluno.put("nomeAluno", aluno.getUsuario().getNome());
			
			JSONArray arrayTurmas = new JSONArray();
			List<Turma> turmas = turmaDAO.listarTurmasAlunosProfessor(aluno.getRaAluno(),professor.getRaProfessor());
			
			for (Turma turma : turmas)
			{
				// construindo JSON de resposta		
				// dados da turma
				JSONObject obj = new JSONObject();
				obj.put("idTurma", turma.getIdTurma());
				obj.put("codTurma", turma.getCodTurma());
				obj.put("codDisciplina", turma.getDisciplina()
						.getCodDisciplina());
				obj.put("nomeDisciplina", turma.getDisciplina()
						.getNomeDisciplina());
				arrayTurmas.add(obj);
			}
			objAluno.put("turmas", arrayTurmas);
			array.add(objAluno);
		}
				
		return array;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/professor/turma/alunos", method = RequestMethod.POST)
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONObject alunosTurma(@ModelAttribute("usuario") Usuario usuario,
			@RequestBody Turma turma) throws Exception
	{						
		Professor professor = usuario.getProfessor();

		turma = turmaDAO.find(turma.getIdTurma());
		if (turma == null)
		{
			throw new NoResultException("Turma não existente");
		}
		if (!turma.getProfessores().contains(professor))
		{
			throw new AccessDeniedException(
					"Professor não associado a turma.");
		}
		
		Long numChamadas = chamadaDAO.quantidadeChamadasEncerradasTurma(turma.getIdTurma());
		
		// construindo JSON de resposta		
		// dados da turma
		JSONObject obj = new JSONObject();
		obj.put("idTurma", turma.getIdTurma());
		obj.put("codTurma", turma.getCodTurma());
		obj.put("codDisciplina", turma.getDisciplina()
				.getCodDisciplina());
		obj.put("nomeDisciplina", turma.getDisciplina()
				.getNomeDisciplina());
		obj.put("numChamadas", numChamadas);
		obj.put("numAlunos", turma.getAlunos().size());
		

		JSONArray array = new JSONArray();
		
		for (Aluno aluno : turma.getAlunos())
		{
			Long numFaltas = presencaDAO.quantidadePresencasAlunoTurma(aluno.getRaAluno(), turma.getIdTurma(), PresencaState.ausente);
			Long numPresencas = presencaDAO.quantidadePresencasAlunoTurma(aluno.getRaAluno(), turma.getIdTurma(), PresencaState.presente);

			Integer porcentagemPresencas = (int) (numPresencas.intValue() * 100 / numChamadas);
			Integer porcentagemFaltas = (int) (numFaltas.intValue() * 100 / numChamadas);
			
			JSONObject objAluno = new JSONObject();
			objAluno.put("raAluno", aluno.getRaAluno());
			objAluno.put("nomeAluno", aluno.getUsuario().getNome());
			objAluno.put("numPresencas", numPresencas);
			objAluno.put("porcentagemPresencas", porcentagemPresencas);
			objAluno.put("numFaltas", numFaltas);
			objAluno.put("porcentagemFaltas", porcentagemFaltas);
			array.add(objAluno);			
		}
		obj.put("alunos", array);
		return obj;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/professor/turma/aluno-frequencia", method = RequestMethod.POST, consumes = "application/json", produces = "application/json;charset=UTF-8")
	@Secured({ "ROLE_PROFESSOR" })
	@ResponseBody
	public JSONObject consultarPresencaAluno(
			@ModelAttribute("usuario") Usuario usuario,
			@RequestBody PresencaAlunoWrapper presencaAluno)
	{
		Aluno aluno = alunoDAO.find(presencaAluno.getRaAluno());
		Professor professor = usuario.getProfessor();
		Turma turma = turmaDAO.find(presencaAluno.getIdTurma());

		if (!turma.getAlunos().contains(aluno))
		{
			throw new NoResultException(
					"Aluno não está matriculado na turma!");
		}
		if (!turma.getProfessores().contains(professor))
		{
			throw new AccessDeniedException(
					"Professor não associado a turma.");
		}
		// construir resposta JSON
		SimpleDateFormat shf = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				
		
		List<Presenca> presencas = presencaDAO.presencasAlunoChamadasEncerradas(presencaAluno.getRaAluno(), presencaAluno.getIdTurma());
		Long numFaltas = presencaDAO.quantidadePresencasAlunoTurma(presencaAluno.getRaAluno(), presencaAluno.getIdTurma(), PresencaState.ausente);
		Long numPresencas = presencaDAO.quantidadePresencasAlunoTurma(presencaAluno.getRaAluno(), presencaAluno.getIdTurma(), PresencaState.presente);
		
		Integer porcentagemPresencas = (int) (numPresencas.intValue()* 100 / presencas.size());
		Integer porcentagemFaltas = (int) (numFaltas.intValue()* 100 / presencas.size());
		
		JSONObject obj = new JSONObject();
		obj.put("numChamadas", presencas.size());
		obj.put("numPresencas", numPresencas);
		obj.put("numFaltas", numFaltas);
		obj.put("numPresencas", numPresencas);
		obj.put("porcentagemPresencas", porcentagemPresencas);
		obj.put("numFaltas", numFaltas);
		obj.put("porcentagemFaltas", porcentagemFaltas);
		
		// dados do aluno
		JSONObject objAluno = new JSONObject();
		objAluno.put("raAluno", aluno.getRaAluno());
		objAluno.put("nomeAluno", aluno.getUsuario().getNome());
		obj.put("aluno", objAluno);
		
		// dados da turma
		JSONObject objTurma = new JSONObject();
		objTurma.put("idTurma", turma.getIdTurma());
		objTurma.put("codTurma", turma.getCodTurma());
		objTurma.put("codDisciplina", turma.getDisciplina()
				.getCodDisciplina());
		objTurma.put("nomeDisciplina", turma.getDisciplina()
				.getNomeDisciplina());
		obj.put("turma", objTurma);
		
		JSONArray array = new JSONArray();
		for (Presenca presenca : presencas)
		{			
			if (presenca.getState() == PresencaState.presente || presenca.getState() == PresencaState.ausente)
			{
				JSONObject objFrequencia = new JSONObject();
				
				JSONObject objPresenca = new JSONObject();
				objPresenca.put("idPresenca", presenca.getIdPresenca());
				objPresenca.put("horaInicio", shf.format(presenca.getHoraInicio() != null ? presenca.getHoraInicio() : new Time(0,0,0)));
				objPresenca.put("horaFim", shf.format(presenca.getHoraFim() != null ? presenca.getHoraFim() : new Time(0,0,0)));
				objPresenca.put("numTicks", presenca.getNumTicks());
				objPresenca.put("resultado", presenca.visualizarPresenca());
				objFrequencia.put("presenca", objPresenca);
				
				Chamada chamada = presenca.getChamada();
				
				// construir resposta JSON
				// dados da chamada
				JSONObject objChamada = new JSONObject();
				objChamada.put("idChamada", chamada.getIdChamada());
				objChamada.put("dataChamada", sdf.format(chamada.getDataChamada()));
				objChamada.put("horaInicio", shf.format(chamada.getHoraInicio()));
				objChamada.put("horaFim", shf.format(chamada.getHoraFim()));
				objChamada.put("duracao", chamada.getParametro().getDuracao());
				objChamada.put("porcentagem", chamada.getParametro().getPorcentagem());
				objChamada.put("professorChamada", chamada.getProfessor().getUsuario().getNome());
				objFrequencia.put("chamada", objChamada);
				array.add(objFrequencia);
			}
		}
		obj.put("frequencia", array);
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.CONFLICT)  // 409
	public JSONObject handleError(HttpServletRequest req, Exception exception)
	{
		JSONObject obj = new JSONObject();
		obj.put("error", "exception");
		obj.put("message", exception.getMessage());
		return obj;
	}
}
class ProfessorAbrirChamadaWrapper  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2262553828925378441L;
	
	@JsonProperty( "latitude" )
	String latitude;
	
	@JsonProperty( "longitude" )
	String longitude;
	
	@JsonProperty("chamadas")
    List<Chamada> chamadas;
    
    public String getLatitude()
	{
		return latitude;
	}
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	public String getLongitude()
	{
		return longitude;
	}
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
	public List<Chamada> getChamadas()
	{
		return chamadas;
	}
	public void setChamadas(List<Chamada> chamadas)
	{
		this.chamadas = chamadas;
	}
}

class ProfessorLocalizacaoWrapper  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2262553828925378441L;
	
	@JsonProperty("chamadas")
    List<Chamada> chamadas;
	
	@JsonProperty( "latitude" )
	String latitude;
	
	@JsonProperty( "longitude" )
	String longitude;
    
    public String getLatitude()
	{
		return latitude;
	}
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	public String getLongitude()
	{
		return longitude;
	}
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
	public List<Chamada> getChamadas()
	{
		return chamadas;
	}
	public void setChamadas(List<Chamada> chamadas)
	{
		this.chamadas = chamadas;
	}
}
class ProfessorParametrosWrapper  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2262553828925378441L;
	
	@JsonProperty("chamadas")
    List<Chamada> chamadas;
	
	@JsonProperty( "duracao" )
	private
	Integer duracao;
	
	@JsonProperty( "porcentagem" )
	private
	Float porcentagem;
    
    
	public List<Chamada> getChamadas()
	{
		return chamadas;
	}
	public void setChamadas(List<Chamada> chamadas)
	{
		this.chamadas = chamadas;
	}
	public Integer getDuracao()
	{
		return duracao;
	}
	public void setDuracao(Integer duracao)
	{
		this.duracao = duracao;
	}
	public Float getPorcentagem()
	{
		return porcentagem;
	}
	public void setPorcentagem(Float porcentagem)
	{
		this.porcentagem = porcentagem;
	}
}
class PresencaAlunoWrapper  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2262553828925378441L;
	
	@JsonProperty("raAluno")
    Integer raAluno;
	
	@JsonProperty( "idTurma" )
	private
	Integer idTurma;
	
	public Integer getRaAluno()
	{
		return raAluno;
	}

	public void setRaAluno(Integer raAluno)
	{
		this.raAluno = raAluno;
	}

	public Integer getIdTurma()
	{
		return idTurma;
	}

	public void setIdTurma(Integer idTurma)
	{
		this.idTurma = idTurma;
	}      
}

class ConsultarAlunoWrapper  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2262553828925378441L;
	
	@JsonProperty("raAluno")
    Integer raAluno=0;
	
	@JsonProperty( "nomeAluno" )
	private
	String nomeAluno;
	
	public Integer getRaAluno()
	{
		return raAluno;
	}

	public void setRaAluno(Integer raAluno)
	{
		this.raAluno = raAluno;
	}

	public String getNomeAluno()
	{
		return nomeAluno;
	}

	public void setNomeAluno(String nomeAluno)
	{
		this.nomeAluno = nomeAluno;
	}

    
}