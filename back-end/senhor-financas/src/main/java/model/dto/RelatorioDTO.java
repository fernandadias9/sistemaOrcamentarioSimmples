package model.dto;

public class RelatorioDTO {

	private int mes;
	private double somaReceitas;
	private double somaDespesas;
	
	public RelatorioDTO(int mes, double somaReceitas, double somaDespesas) {
		super();
		this.mes = mes;
		this.somaReceitas = somaReceitas;
		this.somaDespesas = somaDespesas;
	}

	public RelatorioDTO() {
		super();
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public double getSomaReceitas() {
		return somaReceitas;
	}

	public void setSomaReceitas(double somaReceitas) {
		this.somaReceitas = somaReceitas;
	}

	public double getSomaDespesas() {
		return somaDespesas;
	}

	public void setSomaDespesas(double somaDespesas) {
		this.somaDespesas = somaDespesas;
	}
}
