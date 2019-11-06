package br.com.ulisses.igti.gerenciador.controle;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.ulisses.igti.gerenciador.entity.controle.Configuracao;
import br.com.ulisses.igti.gerenciador.service.controle.ConfiguracaoService;

@Controller
@SessionScoped
public class ConfiguracoesBean {

	private ConfiguracaoService configuracaoService;
	private Configuracao configuracao;

	@Inject
	public ConfiguracoesBean(ConfiguracaoService configuracaoService) {
		this.configuracaoService = configuracaoService;
	}

	@PostConstruct
	public void init() {
		this.configuracao = this.configuracaoService.find();
	}

	public void save() {
		this.configuracaoService.save(configuracao);
		FacesMessage msg = new FacesMessage("Configurações salvas com sucesso ! ");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public Configuracao getConfiguracao() {
		return configuracao;
	}
}
