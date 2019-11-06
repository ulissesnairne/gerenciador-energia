package br.com.ulisses.igti.gerenciador.cadastro.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.ulisses.igti.gerenciador.entity.cadastro.TipoDispositivo;

@Component
public class TipoDispositivoResultSetExtractor implements RowMapper<TipoDispositivo> {

	@Override
	public TipoDispositivo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		TipoDispositivo tipo = new TipoDispositivo();
		tipo.setCodigo(resultSet.getInt("TDI_COD"));
		tipo.setDescricao(resultSet.getString("TDI_DESCRICAO"));

		return tipo;
	}
}
