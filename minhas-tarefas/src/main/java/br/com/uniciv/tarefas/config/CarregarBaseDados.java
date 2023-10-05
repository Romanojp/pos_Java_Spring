package br.com.uniciv.tarefas.config;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.uniciv.tarefas.model.Tarefa;
import br.com.uniciv.tarefas.model.TarefaCategoria;
import br.com.uniciv.tarefas.model.TarefaStatus;
import br.com.uniciv.tarefas.model.Usuario;
import br.com.uniciv.tarefas.repository.TarefaCategoriaRepository;
import br.com.uniciv.tarefas.repository.TarefaRepository;
import br.com.uniciv.tarefas.repository.UsuarioRepository;

@Configuration
@Profile("dev")
public class CarregarBaseDados {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private TarefaCategoriaRepository tarefaCategoriaRepository;
	
	@Bean
	CommandLineRunner executar() {
		return args ->{
			
			Usuario usuario = new Usuario();
			usuario.setNome("Admin");
			usuario.setSenha("123456");
			
			usuarioRepository.save(usuario);
			
			TarefaCategoria categoria = new TarefaCategoria();
			categoria.setNome("Estudos");
			
			tarefaCategoriaRepository.save(categoria);
			
			
			Tarefa tarefa = new Tarefa();
			tarefa.setDescricao("Aprender Springboot");
			tarefa.setDataEntrega(LocalDate.now().plusDays(1));
			tarefa.setStatus(TarefaStatus.ABERTO);
			tarefa.setVisivel(true);
			tarefa.setCategoria(categoria);
			tarefa.setUsuario(usuario);
			
			tarefaRepository.save(tarefa);
			
		};
	}

}
