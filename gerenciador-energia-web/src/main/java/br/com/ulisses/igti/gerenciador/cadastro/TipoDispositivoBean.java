package br.com.ulisses.igti.gerenciador.cadastro;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import br.com.ulisses.igti.gerenciador.entity.cadastro.TipoDispositivo;
import br.com.ulisses.igti.gerenciador.service.cadastro.TipoDispositivoService;

@Controller
@SessionScoped
public class TipoDispositivoBean {

	private List<TipoDispositivo> listaTipoDispositivo;

	private TipoDispositivoService tipoDispositivoService;

	private TipoDispositivo tipoDispositivoSelecionado;

	private String descricaoInclusao;

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

	public String getDescricaoInclusao() {
		return descricaoInclusao;
	}

	public void setDescricaoInclusao(String descricaoInclusao) {
		this.descricaoInclusao = descricaoInclusao;
	}

	public void onEditRow() {
		if (tipoDispositivoSelecionado != null) {
			tipoDispositivoSelecionado.setDescricao(this.descricaoInclusao);
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
		} else {
			FacesMessage msg = new FacesMessage("Não foi selecionado nenhum tipo de dispositivo para exclusão. ");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}

	public void onAddNew() {
		TipoDispositivo tipo = new TipoDispositivo();
		tipo.setDescricao(this.descricaoInclusao);

		tipo = tipoDispositivoService.save(tipo);

		FacesMessage msg = new FacesMessage("Adicionado novo tipo ", tipo.getDescricao());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		this.descricaoInclusao = "";
	}

}
