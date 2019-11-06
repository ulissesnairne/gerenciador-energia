package br.com.ulisses.igti.gerenciador.service.menu;

import br.com.ulisses.igti.gerenciador.entity.menu.Page;

/**
 * Interface do serviço que provê as funcionalidades referentes às páginas do sistema.
 */
public interface PageService {

    /**
     * Método que valida e persiste um {@link Page} na base de dados.
     */
    void persist(Page page);

    /**
     * Método que valida e remove um {@link Page} da base de dados.
     */
    void delete(Long idPage);
}
