package clinica.especialidade.enuns;

public enum Modalidade {

	FISIOTERAPIA("fisioterapia") ,
	DERMATOLOGIA("dermatologia"), 
	PSICOLOGO("psicologo");
	
	private String descricao;

	private Modalidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
