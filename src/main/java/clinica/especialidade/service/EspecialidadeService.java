package clinica.especialidade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinica.especialidade.controller.EspecialidadeController;
import clinica.especialidade.dto.EspecialidadeDTO;
import clinica.especialidade.model.Especialidade;
import clinica.especialidade.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {

	@Autowired
	private final EspecialidadeRepository repository;

	public EspecialidadeService(EspecialidadeRepository repository) {
		this.repository = repository;
	}

	@Transactional(readOnly = true)
	public Optional<EspecialidadeDTO> buscaPorEmail(String email) {
		var findByEmail = repository.findByEmail(email);
		return Optional.ofNullable(findByEmail.map(especialidade -> converteEmDto(especialidade))
				.orElseThrow(() -> new RuntimeException("Email não encontrado")));
	}

	@Transactional(readOnly = true)
	public Optional<EspecialidadeDTO> buscaPorID(Long id) {
		var findByEmail = repository.findById(id);
		return Optional.ofNullable(findByEmail.map(especialidade -> converteEmDto(especialidade))
				.orElseThrow(() -> new RuntimeException("Consulta não encontrado")));
	}

	@Transactional
	public EspecialidadeDTO cadastraEspecialidade(EspecialidadeDTO especialidade) {
		if (repository.findByCrm(especialidade.getCrm()).isPresent()) {
			throw new RuntimeException("Especialista Já cadastrado no sistema");
		}
		var doutorEspecilista = preparaDados(especialidade);
		var especialidadeSalva = repository.save(doutorEspecilista);
		var especialidadeSalvaDTO = new EspecialidadeDTO(especialidadeSalva.getKey(), especialidadeSalva.getNome(),
				especialidadeSalva.getSobrenome(), especialidadeSalva.getEmail(), especialidadeSalva.getCrm(),
				especialidadeSalva.getModalidade());

		this.adicionaHateos(especialidadeSalva, especialidadeSalvaDTO);

		return especialidadeSalvaDTO;
	}

	@Transactional
	public EspecialidadeDTO atualizaEspecialidade(EspecialidadeDTO especialidade, Long id) {
		var especialidadeSalva = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Especialidade não encontrada"));
		repository.updateEspecialidade(especialidade.getNome(), especialidade.getSobrenome(), especialidade.getEmail(),
				especialidade.getCrm(), especialidade.getModalidade(), id);
		var especialidadeSalvaDTO = new EspecialidadeDTO(especialidadeSalva.getKey(), especialidadeSalva.getNome(),
				especialidadeSalva.getSobrenome(), especialidadeSalva.getEmail(), especialidadeSalva.getCrm(),
				especialidadeSalva.getModalidade());

		this.adicionaHateos(especialidadeSalva, especialidadeSalvaDTO);

		return especialidadeSalvaDTO;
	}

	@Transactional
	public void deletaEspecialidade(Long id) {
		if (!buscaPorID(id).isPresent()) {
			throw new RuntimeException("Busca não encontrada");
		}
		repository.deleteById(id);
	}

	private Especialidade preparaDados(EspecialidadeDTO especialidade) {
		var doutorEspecilista = new Especialidade();
		doutorEspecilista.setNome(especialidade.getNome());
		doutorEspecilista.setSobrenome(especialidade.getSobrenome());
		doutorEspecilista.setEmail(especialidade.getEmail());
		doutorEspecilista.setCrm(especialidade.getCrm());
		doutorEspecilista.setModalidade(especialidade.getModalidade());
		return doutorEspecilista;
	}

	private void adicionaHateos(Especialidade x, EspecialidadeDTO especialidadeDTO) {
		Link selfLink = WebMvcLinkBuilder.linkTo(EspecialidadeController.class).slash(x.getKey()).withSelfRel();
		especialidadeDTO.add(selfLink);
	}

	private EspecialidadeDTO converteEmDto(Especialidade x) {
		var especialidadeDTO = new EspecialidadeDTO(null, x.getNome(), x.getSobrenome(), x.getEmail(), x.getCrm(),
				x.getModalidade());
		adicionaHateos(x, especialidadeDTO);
		return especialidadeDTO;
	}
}
