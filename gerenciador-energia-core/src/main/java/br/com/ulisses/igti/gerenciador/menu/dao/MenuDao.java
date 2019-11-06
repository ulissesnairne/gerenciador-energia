package br.com.ulisses.igti.gerenciador.menu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ulisses.igti.gerenciador.entity.menu.MenuItem;
import br.com.ulisses.igti.gerenciador.qualifier.GerenciadorDataSource;

/**
 * Classe que faz a comunicação com a base de dados para os objetos referentes ao menu do sistema.
 */
@Repository
@Transactional
public class MenuDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private MenuResultSetExtractor resultSetExtractor;

    @Inject
    public MenuDao(@GerenciadorDataSource NamedParameterJdbcTemplate jdbcTemplate,
                   MenuResultSetExtractor resultSetExtractor) {
        this.jdbcTemplate = jdbcTemplate;
        this.resultSetExtractor = resultSetExtractor;
    }

    public List<MenuItem> findMenusByUsernameAndGroups(String username, List<String> groups) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT im.id_item_menu as menuItemId, " +
                "    im.domain as menuItemDomain, " +
                "    im.label as menuItemLabel, " +
                "    im.order_topic as menuItemOrder, " +
                "    pa.id_page as pageId," +
                "    pa.label as pageLabel," +
                "    pa.url as pageUrl," +
                "    pa.type as pageWriteSupport," +
                "    pa.order_page as pageOrder, " +
                "    pe.id_permission as permissionId, " +
                "    pe.group_ldap as permissionGroupLdap, " +
                "    pe.username as permissionUsername, " +
                "    pe.type as permissionWriteSupport " +
                " FROM item_menu im " +
                "    INNER JOIN page pa ON pa.id_item_menu = im.id_item_menu " +
                "    INNER JOIN permission pe ON pe.id_page = pa.id_page " +
                " WHERE im.domain = 'newPortal' ");

        if (!groups.isEmpty()) {
            sql.append(" AND (pe.username = :username OR pe.group_ldap IN (:groups)) ");
        } else {
            sql.append(" AND pe.username = :username ");
        }

        sql.append(" ORDER BY im.order_topic, pa.order_page ");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("username", username);
        parameters.put("groups", groups);

        return jdbcTemplate.query(sql.toString(), parameters, resultSetExtractor);
    }

    public List<MenuItem> findAllMenus() {
        String sql = "SELECT * FROM CONFIGURACAO";
        
        jdbcTemplate.query(sql, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				System.out.println(rs.getString("CON_WATSON_ASSISTANT_API_KEY"));
				rs.getString("CON_WATSON_ASSISTANT_API_KEY");
				return null;
			}
		});
        
        return null;
    }
}
