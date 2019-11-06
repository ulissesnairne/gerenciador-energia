package br.com.ulisses.igti.gerenciador.service.watson;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

@Service
public class WatsonServiceBean implements WatsonService {

	private AssistantService assistantService;
	private SpeechTextServices speechTextServices;
	private TextSpeechService textSpeechService;

	@Inject
	public WatsonServiceBean(AssistantService assistantService, SpeechTextServices speechTextServices,
			TextSpeechService textSpeechService) {
		this.assistantService = assistantService;
		this.speechTextServices = speechTextServices;
		this.textSpeechService = textSpeechService;
	}

	@Override
	public String conversar(String mensagem) {
		assistantService.sendMessage(mensagem);
		return assistantService.getLastMessageReturn();
	}

	@Override
	public String retornarTextoAudio(String arquivoAudio) {
		return this.speechTextServices.retornarTexto(arquivoAudio);
	}

	@Override
	public void transformarTextoAudio(String texto) {
		this.textSpeechService.transformar(texto, "retorno");
	}

}
