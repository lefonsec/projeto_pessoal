package clinica.especialidade.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public Optional<Especialidade>  buscaPorEmail(String email) {
		return  repository.findByEmail(email);
	}

	@Transactional
	public Especialidade cadastraEspecialidade(EspecialidadeDTO especialidade) {
		if (repository.findByCrm(especialidade.crm()).isPresent()) {
			throw new RuntimeException("Especialista Já cadastrado no sistema");
		}
		var doutorEspecilista =  new Especialidade();
		doutorEspecilista.setNome(especialidade.nome());
		doutorEspecilista.setSobrenome(especialidade.sobrenome());
		doutorEspecilista.setEmail(especialidade.email());
		doutorEspecilista.setCrm(especialidade.crm());
		doutorEspecilista.setModalidade(especialidade.modalidade());		
		return repository.save(doutorEspecilista);
	}
	
}
