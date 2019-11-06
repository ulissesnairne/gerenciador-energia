package br.com.ulisses.igti.gerenciador.entity.controle;

import java.math.BigDecimal;

public class Configuracao {

	private Long codigo;
	private String watsonAssistantApiKey;
	private String watsonAssistantId;
	private String watsonSpeechApiKey;
	private String watsonTextApiKey;
	private BigDecimal valorKWH;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getWatsonAssistantApiKey() {
		return watsonAssistantApiKey;
	}

	public void setWatsonAssistantApiKey(String watsonAssistantApiKey) {
		this.watsonAssistantApiKey = watsonAssistantApiKey;
	}

	public String getWatsonAssistantId() {
		return watsonAssistantId;
	}

	public void setWatsonAssistantId(String watsonAssistantId) {
		this.watsonAssistantId = watsonAssistantId;
	}

	public String getWatsonSpeechApiKey() {
		return watsonSpeechApiKey;
	}

	public void setWatsonSpeechApiKey(String watsonSpeechApiKey) {
		this.watsonSpeechApiKey = watsonSpeechApiKey;
	}

	public String getWatsonTextApiKey() {
		return watsonTextApiKey;
	}

	public void setWatsonTextApiKey(String watsonTextApiKey) {
		this.watsonTextApiKey = watsonTextApiKey;
	}

	public BigDecimal getValorKWH() {
		return valorKWH;
	}

	public void setValorKWH(BigDecimal valorKWH) {
		this.valorKWH = valorKWH;
	}

}
