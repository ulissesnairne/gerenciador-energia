package br.com.ulisses.igti.gerenciador.service.controle;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.ulisses.igti.gerenciador.controle.dao.ConfiguracoesDao;
import br.com.ulisses.igti.gerenciador.entity.controle.Configuracao;

@Service
public class ConfiguracoesServiceBean implements ConfiguracaoService {
	
	ConfiguracoesDao configuracoesDao;

	@Inject
	public ConfiguracoesServiceBean(ConfiguracoesDao configuracoesDao) {
		this.configuracoesDao = configuracoesDao;
	}

	@Override
	public Configuracao find() {
		return configuracoesDao.find();
	}

	@Override
	public Configuracao save(Configuracao configuracao) {
		return configuracoesDao.save(configuracao);
	}
	
	
	
	
	
}
