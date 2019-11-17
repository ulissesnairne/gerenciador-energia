package br.com.ulisses.igti.gerenciador.cadastro.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ulisses.igti.gerenciador.entity.cadastro.TipoDispositivo;
import br.com.ulisses.igti.gerenciador.qualifier.GerenciadorDataSource;

@Repository
@Transactional
public class TipoDispositivoDao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	private TipoDispositivoResultSetExtractor resultSetExtractor;

	@Inject
	public TipoDispositivoDao(@GerenciadorDataSource NamedParameterJdbcTemplate jdbcTemplate,
			TipoDispositivoResultSetExtractor resultSetExtractor) {
		this.jdbcTemplate = jdbcTemplate;
		this.resultSetExtractor = resultSetExtractor;
	}

	public List<TipoDispositivo> findAll() {
		String sql = "SELECT * FROM TIPO_DISPOSITIVO";
		return jdbcTemplate.query(sql, resultSetExtractor);
	}

	public TipoDispositivo save(TipoDispositivo tipo) {
		String sql = "INSERT INTO TIPO_DISPOSITIVO(TDI_DESCRICAO) VALUES(:descricao)";

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("descricao", tipo.getDescricao());

		jdbcTemplate.update(sql, parameter);
		return tipo;
	}

	public void delete(TipoDispositivo tipo) {

		String sql = "DELETE FROM TIPO_DISPOSITIVO WHERE TDI_COD = :codigo";

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", tipo.getCodigo());

		jdbcTemplate.update(sql, parameter);

	}
	
	public TipoDispositivo update(TipoDispositivo tipo) {
		String sql = "UPDATE TIPO_DISPOSITIVO SET TDI_DESCRICAO = :descricao WHERE TDI_COD = :codigo";

		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("codigo", tipo.getCodigo());
		parameter.addValue("descricao", tipo.getDescricao());

		jdbcTemplate.update(sql, parameter);
		return tipo;
	}

}
