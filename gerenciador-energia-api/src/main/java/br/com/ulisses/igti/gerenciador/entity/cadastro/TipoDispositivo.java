package br.com.ulisses.igti.gerenciador.entity.cadastro;

import java.io.Serializable;

public class TipoDispositivo implements Serializable {

	private static final long serialVersionUID = -7495207496036823638L;

	private Integer codigo;
	private String descricao;

	public TipoDispositivo() {

	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
