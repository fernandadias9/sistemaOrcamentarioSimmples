package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.dto.RelatorioDTO;

public class RelatorioDAO {
	
	public ArrayList<RelatorioDTO> gerarRelatorioDAO(int idusuario, int ano) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		RelatorioDTO[] listaRelatorio = new RelatorioDTO[12];
		String query = "select sum(receita), sum(despesa), relatorio.mes from (select sum(valor) as receita, 0 as despesa, month(datareceita) as mes from receita where idusuario = " 
					  + idusuario + "  and year(datareceita) = " 
					  + ano + " group by month(datareceita) union select 0 as receita, sum(valor) as despesa, month(datavencimento) as mes from despesa where idusuario = " 
					  + idusuario + " and year(datavencimento) = " 
					  + ano + " group by month(datavencimento)) relatorio group by mes order by mes";
		try {
			resultado = stmt.executeQuery(query);
			for(int i = 0; i < listaRelatorio.length; i++) {
				RelatorioDTO relatorio = new RelatorioDTO();
				relatorio.setSomaReceitas(0.00);
				relatorio.setSomaDespesas(0.00);
				relatorio.setMes(i+1);
				listaRelatorio[i] = relatorio;
			}
			while(resultado.next()) {
				RelatorioDTO relatorio = new RelatorioDTO();
				relatorio.setSomaReceitas(resultado.getDouble(1));
				relatorio.setSomaDespesas(resultado.getDouble(2));
				relatorio.setMes(resultado.getInt(3));
				listaRelatorio[relatorio.getMes() - 1] = relatorio;
			}
		} catch(SQLException erro) {
			System.out.println("\nErro ao executar a query do método somarReceitas.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
        List<RelatorioDTO> lista = Arrays.asList(listaRelatorio);
        ArrayList<RelatorioDTO> retorno = new ArrayList<RelatorioDTO>(lista);
		return retorno;
	}
}