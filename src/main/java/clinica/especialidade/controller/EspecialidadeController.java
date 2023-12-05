package clinica.especialidade.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import clinica.especialidade.dto.EspecialidadeDTO;
import clinica.especialidade.model.Especialidade;
import clinica.especialidade.service.EspecialidadeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/especialidade")
public class EspecialidadeController {

	@Autowired
	private final EspecialidadeService service;

	public EspecialidadeController(EspecialidadeService service) {
		this.service = service;
	}
	
	@GetMapping("e-mail")
	public ResponseEntity<Optional<Especialidade>> obterEmail(@RequestParam String email){
		var buscaPorEmail = service.buscaPorEmail(email);
		if(buscaPorEmail.isEmpty()) {
			throw new RuntimeException("Email não encontrado");
		}
		return ResponseEntity.ok().body(buscaPorEmail);
	}
	
	@PostMapping
	public ResponseEntity<Especialidade> cadastrar(@Valid @RequestBody EspecialidadeDTO dto){
		var cadastraEspecialidade = service.cadastraEspecialidade(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cadastraEspecialidade.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
