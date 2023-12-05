package clinica.especialidade.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import clinica.especialidade.enuns.Modalidade;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EspecialidadeDTO extends RepresentationModel<EspecialidadeDTO> {

	@JsonIgnore
	Long key;
	@NotBlank
	String nome;
	@NotBlank
	String sobrenome;
	@Email
	@NotBlank
	String email;
	@NotBlank
	String crm;
	@NotNull
	Modalidade modalidade;

	public EspecialidadeDTO() {
	}

	public EspecialidadeDTO(Long key, @NotBlank String nome, @NotBlank String sobrenome, @Email @NotBlank String email,
			@NotBlank String crm, @NotNull Modalidade modalidade) {
		this.key = key;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
		this.crm = crm;
		this.modalidade = modalidade;
	}

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

}
