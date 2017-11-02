package pe.com.cotic.test.dao;

import java.util.List;

import pe.com.cotic.test.modelo.Reportecurso;
import pe.com.cotic.test.modelo.Reportecursodetalle;
import pe.com.cotic.test.modelo.Reporteusuarioscursos;
import pe.com.cotic.test.modelo.Reporteusuarioscursospuestos;

public interface ReporteUsuariosCursosPuestosDao {

	public List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestosxxx();
	
	public List<Reporteusuarioscursospuestos> listarReporteUsuariosCursosPuestos();
	
	public List<Reporteusuarioscursos> listarReporteUsuariosCursos();
	
	public List<Reportecursodetalle> ListarReporteUsuariosCursosDetalle(int codigoUsuario);
}
