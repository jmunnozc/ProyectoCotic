package pe.com.cotic.test.modelo;

public class Usuarioscursos {

	private String nombreFull;
	private String grupo;
	private Integer totalCursos;
	private Integer ubicacionAlta;
	private Integer ubicacionBaja;
	private Integer cumplimiento;
	
	public String getNombreFull() {
		return nombreFull;
	}
	public void setNombreFull(String nombreFull) {
		this.nombreFull = nombreFull;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public Integer getTotalCursos() {
		return totalCursos;
	}
	public void setTotalCursos(Integer totalCursos) {
		this.totalCursos = totalCursos;
	}
	public Integer getUbicacionAlta() {
		return ubicacionAlta;
	}
	public void setUbicacionAlta(Integer ubicacionAlta) {
		this.ubicacionAlta = ubicacionAlta;
	}
	public Integer getUbicacionBaja() {
		return ubicacionBaja;
	}
	public void setUbicacionBaja(Integer ubicacionBaja) {
		this.ubicacionBaja = ubicacionBaja;
	}
	public Integer getCumplimiento() {
		return cumplimiento;
	}
	public void setCumplimiento(Integer cumplimiento) {
		this.cumplimiento = cumplimiento;
	}
	
}
