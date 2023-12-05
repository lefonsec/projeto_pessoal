package clinica.especialidade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import clinica.especialidade.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {
	
	Optional<Especialidade> findByEmail(String email);
	Optional<Especialidade> findByCrm(String crm);
}
