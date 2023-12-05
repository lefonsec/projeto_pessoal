package clinica.especialidade.model;

import java.io.Serializable;
import java.util.Objects;

import clinica.especialidade.enuns.Modalidade;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Especialidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long key;

	@Column(nullable = false, length = 150)
	private String nome;

	@Column(nullable = false, length = 150)
	private String sobrenome;

	@Column(nullable = false, length = 200, unique = true)
	private String email;
	
	@Column(nullable = false, length = 9, unique = true)
	private String crm;

	@Enumerated(EnumType.STRING)
	private Modalidade modalidade;

	public Especialidade() {
	}

	public Especialidade(Long key, String nome, String sobrenome, String email, Modalidade modalidade) {
		this.key = key;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.email = email;
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

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}
	

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, key);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Especialidade other = (Especialidade) obj;
		return Objects.equals(email, other.email) && Objects.equals(key, other.key);
	}

}
