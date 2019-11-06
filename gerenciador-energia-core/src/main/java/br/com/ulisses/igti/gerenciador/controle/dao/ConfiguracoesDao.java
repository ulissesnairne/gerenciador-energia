package br.com.ulisses.igti.gerenciador.controle.dao;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ulisses.igti.gerenciador.entity.controle.Configuracao;
import br.com.ulisses.igti.gerenciador.qualifier.GerenciadorDataSource;

@Repository
@Transactional
public class ConfiguracoesDao {

	private NamedParameterJdbcTemplate jdbcTemplate;
	private ConfiguracoesResulSetExtractor resultSetExtractor;

	@Inject
	public ConfiguracoesDao(@GerenciadorDataSource NamedParameterJdbcTemplate jdbcTemplate,
			ConfiguracoesResulSetExtractor resultSetExtractor) {
		this.jdbcTemplate = jdbcTemplate;
		this.resultSetExtractor = resultSetExtractor;
	}

	public Configuracao find() {
		String sql = "SELECT * FROM CONFIGURACAO";
		List<Configuracao> retorno = jdbcTemplate.query(sql, resultSetExtractor);

		return retorno.get(0);
	}

	public Configuracao save(Configuracao configuracao) {
		
		MapSqlParameterSource parameter = new MapSqlParameterSource();
		parameter.addValue("assistantApiKey", configuracao.getWatsonAssistantApiKey());
		parameter.addValue("assistantId", configuracao.getWatsonAssistantId());
		parameter.addValue("speechApiKey", configuracao.getWatsonSpeechApiKey());
		parameter.addValue("textApiKey", configuracao.getWatsonTextApiKey());
		parameter.addValue("valorKWH", configuracao.getValorKWH());

		if (find() != null) {

			StringBuilder sql = new StringBuilder("UPDATE CONFIGURACAO SET ");
			sql.append("   CON_WATSON_ASSISTANT_API_KEY = :assistantApiKey, ");
			sql.append("   CON_WATSON_ASSISTANT_ID = :assistantId, ");
			sql.append("   CON_WATSON_SPEECH_API_KEY = :speechApiKey, ");
			sql.append("   CON_WATSON_TEXT_API_KEY = :textApiKey, ");
			sql.append("   CON_VALOR_KWH = :valorKWH ");
			sql.append("  WHERE CON_COD = :codigo");
			parameter.addValue("codigo", configuracao.getCodigo());
			

			jdbcTemplate.update(sql.toString(), parameter);
		} else {
			StringBuilder sql = new StringBuilder("INSERT INTO CONFIGURACAO (CON_WATSON_ASSISTANT_API_KEY, CON_WATSON_ASSISTANT_ID, CON_WATSON_SPEECH_API_KEY, CON_WATSON_TEXT_API_KEY, CON_VALOR_KWH) ");
			sql.append("VALUES(:assistantApiKey, :assistantId, :speechApiKey, :textApiKey, :valorKWH)");
			
			jdbcTemplate.update(sql.toString(), parameter);
		}
		return configuracao;
	}

}
