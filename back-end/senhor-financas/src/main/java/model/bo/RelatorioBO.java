package model.bo;

import java.util.ArrayList;

import model.dao.RelatorioDAO;
import model.dto.RelatorioDTO;

public class RelatorioBO {

	public ArrayList<RelatorioDTO> gerarRelatorioBO(int idusuario, int ano) {
		RelatorioDAO relatorio = new RelatorioDAO();
		return relatorio.gerarRelatorioDAO(idusuario, ano);
	}
}
