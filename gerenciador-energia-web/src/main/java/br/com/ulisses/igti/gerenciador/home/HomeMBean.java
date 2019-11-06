package br.com.ulisses.igti.gerenciador.home;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Classe responsável pela home, menus e login/logout do sistema.
 */
@Controller
@SessionScoped
public class HomeMBean {

	private MenuModel menuModel;
	private String currentPage = "Home";

	public void logout() {
		getExternalContext().invalidateSession();
	}

	public boolean isUserLoggedIn() {
		return getExternalContext().getSessionMap().containsKey("user");
	}

	public MenuModel getMenuModel() {
		if (menuModel == null) {
			menuModel = buildMenuModel();
		}

		return menuModel;
	}

	private MenuModel buildMenuModel() {
		MenuModel menu = new DefaultMenuModel();

		DefaultSubMenu firstSubmenu = new DefaultSubMenu("Controle de Energia");

		DefaultMenuItem item = new DefaultMenuItem("Configurações", null, "/configuracoes");
		item.setCommand("#{homeMBean.goConfiguracoes()}");
		firstSubmenu.addElement(item);

		DefaultMenuItem item1 = new DefaultMenuItem("Sobre", null, "/sobre");
		firstSubmenu.addElement(item1);

		menu.addElement(firstSubmenu);

		DefaultSubMenu secondSubmenu = new DefaultSubMenu("Cadastro");

		DefaultMenuItem item2 = new DefaultMenuItem("Tipo de Dispositivo", null, "/tipoDispositivo");
		secondSubmenu.addElement(item2);

		DefaultMenuItem item3 = new DefaultMenuItem("Tipo de Modelo", null, "");
		secondSubmenu.addElement(item3);

		DefaultMenuItem item4 = new DefaultMenuItem("Tipo de Marca", null, "");
		secondSubmenu.addElement(item4);

		DefaultMenuItem item5 = new DefaultMenuItem("Lugar", null, "");
		secondSubmenu.addElement(item5);

		menu.addElement(secondSubmenu);

		DefaultSubMenu thirdSubmenu = new DefaultSubMenu("Watson");

		DefaultMenuItem item6 = new DefaultMenuItem("Conversar", null, "/conversar");
		thirdSubmenu.addElement(item6);

		menu.addElement(thirdSubmenu);

		return menu;
	}

	public String getCurrentPageLabel() {
		return currentPage;
	}

	@RequestMapping("/configuracoes")
	public View goConfiguracoes() {
		RedirectView redirect = new RedirectView("pages/controle/configuracoes.xhtml", true);
		currentPage = "Configurações";
		redirect.setExposeModelAttributes(false);

		return redirect;
	}

	@RequestMapping("/tipoDispositivo")
	public View goTipoDispositivo() {
		RedirectView redirect = new RedirectView("pages/cadastro/tipoDispositivo.xhtml", true);
		currentPage = "Tipo de Dispositivo";
		redirect.setExposeModelAttributes(false);

		return redirect;
	}
	
	@RequestMapping("/tipoModelo")
	public View goTipoModelo() {
		RedirectView redirect = new RedirectView("pages/cadastro/tipoDispositivo.xhtml", true);
		currentPage = "Tipo de Dispositivo";
		redirect.setExposeModelAttributes(false);

		return redirect;
	}
	
	@RequestMapping("/lugar")
	public View goLugar() {
		RedirectView redirect = new RedirectView("pages/cadastro/tipoDispositivo.xhtml", true);
		currentPage = "Tipo de Dispositivo";
		redirect.setExposeModelAttributes(false);

		return redirect;
	}
	
	@RequestMapping("/conversar")
	public View goConversar() {
		RedirectView redirect = new RedirectView("pages/watson/conversa.xhtml", true);
		currentPage = "Conversa";
		redirect.setExposeModelAttributes(false);

		return redirect;
	}
	
	@RequestMapping("/sobre")
	public View goSobre() {
		RedirectView redirect = new RedirectView("pages/controle/about.xhtml", true);
		currentPage = "Sobre";
		redirect.setExposeModelAttributes(false);

		return redirect;
	}

	public View getHomePage() {
		RedirectView redirect = new RedirectView("/index.xhtml", true);
		currentPage = "Home";
		redirect.setExposeModelAttributes(false);

		return redirect;
	}

	private ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	@RequestMapping("/")
	public View baseUrlRedirect(HttpServletRequest request) {

		RedirectView redirect = new RedirectView("/index.xhtml", true);
		redirect.setExposeModelAttributes(false);
		currentPage = "Home";

		return redirect;
	}
}
