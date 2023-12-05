package clinica.especialidade.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import clinica.especialidade.enuns.Modalidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record EspecialidadeDTO(
		@JsonIgnore Long id,@NotBlank String nome, @NotBlank String sobrenome, @Email @NotBlank String email,
		@NotBlank String crm,
		 @NotNull Modalidade modalidade) {
}
