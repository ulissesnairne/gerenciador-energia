package br.com.ulisses.igti.gerenciador.service.menu;

import java.util.List;

import br.com.ulisses.igti.gerenciador.entity.menu.MenuItem;

/**
 * Interface do serviço que provê as funcionalidades referentes aos menus do
 * sistema.
 */
public interface MenuService {

	/**
	 * Método que busca todas as páginas de todos os itens de menu que o usuário
	 * tem acesso via permissão direta ou por grupo.
	 */
	List<MenuItem> findMenusByUsernameAndGroups(String username, List<String> groups);

	/**
	 * Método que busca todas as permissões de todas as páginas de todos os itens
	 * de menu.
	 */
	List<MenuItem> findAllMenus();
}
