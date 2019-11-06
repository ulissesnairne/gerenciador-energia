package br.com.ulisses.igti.gerenciador.service.menu;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.ulisses.igti.gerenciador.entity.menu.MenuItem;
import br.com.ulisses.igti.gerenciador.menu.dao.MenuDao;

/**
 * Serviço que provê as funcionalidades referentes ao menu do sistema.
 */
@Service
public class MenuServiceBean implements MenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuServiceBean.class);

    private MenuDao menuDao;

    @Inject
    public MenuServiceBean(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @Override
    public List<MenuItem> findMenusByUsernameAndGroups(String username, List<String> groups) {
        try {
            return menuDao.findMenusByUsernameAndGroups(username, groups);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<MenuItem> findAllMenus() {
        try {
            return menuDao.findAllMenus();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        }
    }
}
