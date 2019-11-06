package br.com.ulisses.igti.gerenciador.watson;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.ulisses.igti.gerenciador.service.watson.WatsonServiceBean;
import br.com.ulisses.igti.gerenciador.util.Audio;

@Controller
@SessionScoped
public class ConversaBean {

	private WatsonServiceBean watsonServiceBean;
	private String mensagem;
	private StringBuilder mensagens;
	private String conversa;
	private Audio audio;

	@Inject
	public ConversaBean(WatsonServiceBean watsonServiceBean, Audio audio) {
		this.watsonServiceBean = watsonServiceBean;
		this.audio = audio;
	}

	@PostConstruct
	public void init() {
		mensagens = new StringBuilder();
	}

	public void conversar() {
		mensagens.append("<p align='left'><img src='../../img/person.jpg' width='50' /></p> <p align='left' style='word-break: break-word'>").append(mensagem).append("</p>");
		String retorno = watsonServiceBean.conversar(mensagem);
		
		mensagens.append("<p align='right'><img src='../../img/logo_sistema.png' width='50' /></p> <p align='right' style='word-break: break-word'>").append(retorno).append("</p>");
		mensagem = "";
		
		conversa = mensagens.toString();

	}
	
	public void gravarAudio() {
		try {
			byte[] audioCapturado = audio.capturar();
			audio.salvar("/Users/ulissesnairnedealmeida/Downloads/audios", "teste", audioCapturado);
			
			/* Speech to Text */
			mensagem = watsonServiceBean.retornarTextoAudio("/Users/ulissesnairnedealmeida/Downloads/audios/teste.flac");
			mensagens.append("<p align='left'><img src='../../img/person.jpg' width='50' /></p> <p align='left' style='word-break: break-word'>").append(mensagem).append("</p>");
			
			/* Assistant */ 
			String retorno = watsonServiceBean.conversar(mensagem);
			
			mensagens.append("<p align='right'><img src='../../img/logo_sistema.png' width='50' /></p> <p align='right' style='word-break: break-word'>").append(retorno).append("</p>");
			
			/* Text to Speech */
			System.out.println("utilizando o Watson Text to Speech");
			watsonServiceBean.transformarTextoAudio(retorno);
			audio.tocarSom("/Users/ulissesnairnedealmeida/Downloads/audios/retorno.wav");
			
			mensagem = "";
			
			conversa = mensagens.toString();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagens() {
		return mensagens.toString();
	}
	
	public String getConversa() {
		return conversa;
	}

	public void setConversa(String conversa) {
		this.conversa = conversa;
	}

}
