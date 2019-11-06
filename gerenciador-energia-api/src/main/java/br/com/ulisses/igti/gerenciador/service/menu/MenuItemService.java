package br.com.ulisses.igti.gerenciador.service.menu;

import br.com.ulisses.igti.gerenciador.entity.menu.MenuItem;

/**
 * Interface do serviço que provê as funcionalidades referentes aos itens de menu do sistema.
 */
public interface MenuItemService {

    /**
     * Método que valida e persiste um {@link MenuItem} na base de dados.
     */
    void persist(MenuItem menuItem);

    /**
     * Método que valida e remove um {@link MenuItem} da base de dados.
     */
    void delete(Long idMenuItem);
}
