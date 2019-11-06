package br.com.ulisses.igti.gerenciador.controle.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import br.com.ulisses.igti.gerenciador.entity.controle.Configuracao;

@Component
public class ConfiguracoesResulSetExtractor implements RowMapper<Configuracao> {

	@Override
	public Configuracao mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Configuracao conf = new Configuracao();
		conf.setCodigo(resultSet.getLong("CON_COD"));
		conf.setWatsonAssistantApiKey(resultSet.getString("CON_WATSON_ASSISTANT_API_KEY"));
		conf.setWatsonAssistantId(resultSet.getString("CON_WATSON_ASSISTANT_ID"));
		conf.setWatsonSpeechApiKey(resultSet.getString("CON_WATSON_SPEECH_API_KEY"));
		conf.setWatsonTextApiKey(resultSet.getString("CON_WATSON_TEXT_API_KEY"));
		conf.setValorKWH(resultSet.getBigDecimal("CON_VALOR_KWH"));

		return conf;
	}

}
