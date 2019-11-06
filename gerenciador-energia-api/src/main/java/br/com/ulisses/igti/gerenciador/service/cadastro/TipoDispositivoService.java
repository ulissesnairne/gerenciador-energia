package br.com.ulisses.igti.gerenciador.service.cadastro;

import java.util.List;

import br.com.ulisses.igti.gerenciador.entity.cadastro.TipoDispositivo;

public interface TipoDispositivoService {
	
	public List<TipoDispositivo> findAll();
	
	public TipoDispositivo save(TipoDispositivo tipo);
	
	public void delete(TipoDispositivo tipo);
	
	public void update(TipoDispositivo tipo);

}
