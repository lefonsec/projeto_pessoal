package clinica.especialidade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import clinica.especialidade.enuns.Modalidade;
import clinica.especialidade.model.Especialidade;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Long> {

	Optional<Especialidade> findByEmail(String email);

	Optional<Especialidade> findByCrm(String crm);

	@Modifying
	@Query("UPDATE Especialidade e SET e.nome = ?1, e.sobrenome = ?2, e.email = ?3, e.crm = ?4, e.modalidade = ?5 WHERE e.id = ?6")
	void updateEspecialidade(String nome, String sobrenome, String email, String crm, Modalidade modalidade, Long id);
}
