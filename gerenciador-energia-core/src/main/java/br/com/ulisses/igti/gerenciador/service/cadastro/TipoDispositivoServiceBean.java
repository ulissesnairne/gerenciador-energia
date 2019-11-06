package br.com.ulisses.igti.gerenciador.service.cadastro;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.ulisses.igti.gerenciador.cadastro.dao.TipoDispositivoDao;
import br.com.ulisses.igti.gerenciador.entity.cadastro.TipoDispositivo;

@Service
public class TipoDispositivoServiceBean implements TipoDispositivoService {

	TipoDispositivoDao tipoDispositivoDao;

	@Inject
	public TipoDispositivoServiceBean(TipoDispositivoDao tipoDispositivoDao) {
		this.tipoDispositivoDao = tipoDispositivoDao;
	}

	@Override
	public List<TipoDispositivo> findAll() {
		return this.tipoDispositivoDao.findAll();
	}

	@Override
	public TipoDispositivo save(TipoDispositivo tipo) {
		return this.tipoDispositivoDao.save(tipo);
	}

	@Override
	public void delete(TipoDispositivo tipo) {
		this.tipoDispositivoDao.delete(tipo);
	}

	@Override
	public void update(TipoDispositivo tipo) {
		this.update(tipo);
	}

}
