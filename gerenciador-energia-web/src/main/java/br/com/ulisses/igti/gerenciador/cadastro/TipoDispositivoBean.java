package br.com.ulisses.igti.gerenciador.cadastro;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.SelectEvent;
import org.springframework.stereotype.Controller;

import br.com.ulisses.igti.gerenciador.entity.cadastro.TipoDispositivo;
import br.com.ulisses.igti.gerenciador.service.cadastro.TipoDispositivoService;

@Controller
@ViewScoped
public class TipoDispositivoBean implements Serializable {

	private static final long serialVersionUID = -7144194779576955995L;

	private List<TipoDispositivo> listaTipoDispositivo;

	private TipoDispositivoService tipoDispositivoService;

	private TipoDispositivo tipoDispositivoSelecionado;

	@Inject
	public TipoDispositivoBean(TipoDispositivoService tipoDispositivoService) {
		this.tipoDispositivoService = tipoDispositivoService;
	}

	public List<TipoDispositivo> getListaTipoDispositivo() {
		this.listaTipoDispositivo = tipoDispositivoService.findAll();
		return this.listaTipoDispositivo;
	}

	public TipoDispositivo getTipoDispositivoSelecionado() {
		return tipoDispositivoSelecionado;
	}

	public void setTipoDispositivoSelecionado(TipoDispositivo tipoDispositivoSelecionado) {
		this.tipoDispositivoSelecionado = tipoDispositivoSelecionado;
	}

	public void onRowSelect(SelectEvent event) {
		if (event.getObject() != null) {
			this.setTipoDispositivoSelecionado((TipoDispositivo) event.getObject());
			System.out.println(tipoDispositivoSelecionado.getDescricao());
		}
	}

	public void onEditRow() {
		if (tipoDispositivoSelecionado != null) {
			tipoDispositivoService.update(tipoDispositivoSelecionado);

			FacesMessage msg = new FacesMessage(
					"Foi atualiza o tipo de dispositivo " + tipoDispositivoSelecionado.getDescricao());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} else {
			FacesMessage msg = new FacesMessage("Não foi selecionado nenhum tipo de dispositivo para exclusão. ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public void onDeleteRow() {
		if (tipoDispositivoSelecionado != null) {
			tipoDispositivoService.delete(tipoDispositivoSelecionado);

			FacesMessage msg = new FacesMessage(
					"Foi exceluído o tipo de dispositivo " + tipoDispositivoSelecionado.getDescricao());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			this.tipoDispositivoSelecionado = null;
		} else {
			FacesMessage msg = new FacesMessage("Não foi selecionado nenhum tipo de dispositivo para exclusão. ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	@PostConstruct
	public void init() {
//		this.tipoDispositivoSelecionado = new TipoDispositivo();
	}

	public void onAddNew() {
		tipoDispositivoService.save(tipoDispositivoSelecionado);

		FacesMessage msg = new FacesMessage("Adicionado novo tipo ", tipoDispositivoSelecionado.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

}
