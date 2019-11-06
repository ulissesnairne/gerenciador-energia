package br.com.ulisses.igti.gerenciador.service.controle;

import br.com.ulisses.igti.gerenciador.entity.controle.Configuracao;

public interface ConfiguracaoService {

	public Configuracao find();
	
	public Configuracao save(Configuracao configuracao);
	
}
