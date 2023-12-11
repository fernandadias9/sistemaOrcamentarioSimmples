package controller;

import java.util.ArrayList;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.bo.RelatorioBO;
import model.dto.RelatorioDTO;

@Path("/relatorio")
public class RelatorioRest {

	@GET
	@Path("/{idusuario}/{ano}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<RelatorioDTO> gerarRelatorioController(@PathParam("idusuario") int idusuario, @PathParam("ano") int ano) {
		RelatorioBO relatorio = new RelatorioBO();
		ArrayList<RelatorioDTO> lista =  relatorio.gerarRelatorioBO(idusuario, ano);
		return lista;
	}
}