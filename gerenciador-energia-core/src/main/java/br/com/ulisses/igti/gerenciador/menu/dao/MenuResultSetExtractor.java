package br.com.ulisses.igti.gerenciador.menu.dao;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import br.com.ulisses.igti.gerenciador.entity.menu.MenuItem;
import br.com.ulisses.igti.gerenciador.entity.menu.Page;
import br.com.ulisses.igti.gerenciador.entity.permission.Permission;

/**
 * Classe que transforma um {@link ResultSet} em uma lista de {@link MenuItem}.
 */
@Component
public class MenuResultSetExtractor implements ResultSetExtractor<List<MenuItem>> {

    @Override
    public List<MenuItem> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        Map<Long, MenuItem> menuItemMap = new HashMap<>();
        Map<Long, Page> pageMap = new HashMap<>();

        while (resultSet.next()) {
            Long menuItemId = resultSet.getLong("menuItemId");
            MenuItem menuItem = menuItemMap.get(menuItemId);

            if (menuItem == null) {
                menuItem = extractMenuItemData(resultSet);
                menuItemMap.put(menuItem.getId(), menuItem);
            }

            Page page = getPage(resultSet, pageMap, menuItem);

            if (page != null) {
                if (menuItem.getPages().contains(page)) {
                    int pageIndex = menuItem.getPages().indexOf(page);
                    menuItem.getPages().set(pageIndex, page);
                } else {
                    menuItem.getPages().add(page);
                }

                pageMap.put(page.getId(), page);
            }
        }

        return new ArrayList<>(menuItemMap.values());
    }

    private MenuItem extractMenuItemData(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("menuItemId");
        String domain = resultSet.getString("menuItemDomain");
        String label = resultSet.getString("menuItemLabel");
        Long order = resultSet.getLong("menuItemOrder");
        List<Page> pages = new ArrayList<>();

        return new MenuItem(id, domain, label, order, pages);
    }

    private Page getPage(ResultSet resultSet, Map<Long, Page> pageMap, MenuItem menuItem) throws SQLException {
        if (hasColumn(resultSet, "pageId") && resultSet.getLong("pageId") > 0) {
            Long pageId = resultSet.getLong("pageId");
            Page page = pageMap.get(pageId);

            if (page == null) {
                page = extractPageData(resultSet, menuItem);
            }

            if (hasColumn(resultSet, "permissionId") && resultSet.getLong("permissionId") > 0) {
                Permission permission = extractPermissionData(resultSet, page);
                page.getPermissions().add(permission);
            }

            return page;
        }

        return null;
    }

    private boolean hasColumn(ResultSet resultSet, String columnName) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columns = rsmd.getColumnCount();

        for (int x = 1; x <= columns; x++) {
            if (columnName != null && columnName.equalsIgnoreCase(rsmd.getColumnName(x))) {
                return true;
            }
        }

        return false;
    }

    private Page extractPageData(ResultSet resultSet, MenuItem menuItem) throws SQLException {
        Long id = resultSet.getLong("pageId");
        String label = resultSet.getString("pageLabel");
        String url = resultSet.getString("pageUrl");
        boolean writeSupport = "S".equalsIgnoreCase(resultSet.getString("pageWriteSupport"));
        Long order = resultSet.getLong("pageOrder");
        List<Permission> permissions = new ArrayList<>();

        return new Page(id, label, url, writeSupport, order, menuItem, permissions);
    }

    private Permission extractPermissionData(ResultSet resultSet, Page page) throws SQLException {
        Long id = resultSet.getLong("permissionId");
        String groupLdap = resultSet.getString("permissionGroupLdap");
        String username = resultSet.getString("permissionUsername");
        String writeSupportStr = resultSet.getString("permissionWriteSupport");
        boolean writeSupport = "S".equalsIgnoreCase(writeSupportStr);

        return new Permission(id, page, groupLdap, username, writeSupport);
    }
}
