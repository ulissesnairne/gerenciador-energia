package br.com.ulisses.igti.gerenciador.service.watson;

public interface WatsonService {
	
	String conversar(String mensagem);
	String retornarTextoAudio(String arquivoAudio);
	void transformarTextoAudio(String texto);
	

}
