package clinica.especialidade.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import clinica.especialidade.dto.EspecialidadeDTO;
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
	public ResponseEntity<Optional<EspecialidadeDTO>> obterEmail(@RequestParam String email) {
		var buscaPorEmail = service.buscaPorEmail(email);
		if (buscaPorEmail.isEmpty()) {
			throw new RuntimeException("Email não encontrado");
		}
		return ResponseEntity.ok().body(buscaPorEmail);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<EspecialidadeDTO>> obterPorID(@PathVariable Long id) {
		var buscaPorEmail = service.buscaPorID(id);
		if (buscaPorEmail.isEmpty()) {
			throw new RuntimeException("Email não encontrado");
		}
		return ResponseEntity.ok().body(buscaPorEmail);
	}

	@PostMapping
	public ResponseEntity<EspecialidadeDTO> cadastrar(@Valid @RequestBody EspecialidadeDTO dto) {
		var cadastraEspecialidade = service.cadastraEspecialidade(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(cadastraEspecialidade.getKey()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<EspecialidadeDTO> atualizar(@Valid @RequestBody EspecialidadeDTO dto, @PathVariable Long id) {
		var atualiza = service.atualizaEspecialidade(dto, id);
		return ResponseEntity.ok().body(atualiza);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEspecialidade(@PathVariable Long id) {
		service.deletaEspecialidade(id);
		return ResponseEntity.noContent().build();
	}

}
