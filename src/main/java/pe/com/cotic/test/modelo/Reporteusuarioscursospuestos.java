package pe.com.cotic.test.modelo;

public class Reporteusuarioscursospuestos {
	private Integer codigoUsuario;
	private String fullNombre;
	private Integer totalCuestionarios;
	private Integer ubicacionPrimera;
	private Integer ubicacionUltima;
	private Integer noCumplimiento;
	private Integer totalCuestionariosContestados;
	private String fechaUltimoCuestionario;

	public Integer getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(Integer codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	public String getFullNombre() {
		return fullNombre;
	}
	public void setFullNombre(String fullNombre) {
		this.fullNombre = fullNombre;
	}
	public Integer getTotalCuestionarios() {
		return totalCuestionarios;
	}
	public void setTotalCuestionarios(Integer totalCuestionarios) {
		this.totalCuestionarios = totalCuestionarios;
	}
	public Integer getUbicacionPrimera() {
		return ubicacionPrimera;
	}
	public void setUbicacionPrimera(Integer ubicacionPrimera) {
		this.ubicacionPrimera = ubicacionPrimera;
	}
	public Integer getUbicacionUltima() {
		return ubicacionUltima;
	}
	public void setUbicacionUltima(Integer ubicacionUltima) {
		this.ubicacionUltima = ubicacionUltima;
	}
	public Integer getNoCumplimiento() {
		return noCumplimiento;
	}
	public void setNoCumplimiento(Integer noCumplimiento) {
		this.noCumplimiento = noCumplimiento;
	}
	public Integer getTotalCuestionariosContestados() {
		return totalCuestionariosContestados;
	}
	public void setTotalCuestionariosContestados(
			Integer totalCuestionariosContestados) {
		this.totalCuestionariosContestados = totalCuestionariosContestados;
	}
	public String getFechaUltimoCuestionario() {
		return fechaUltimoCuestionario;
	}
	public void setFechaUltimoCuestionario(String fechaUltimoCuestionario) {
		this.fechaUltimoCuestionario = fechaUltimoCuestionario;
	}
	
}
