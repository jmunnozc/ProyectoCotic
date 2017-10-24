package pe.com.cotic.test.dao;

import java.util.List;

import pe.com.cotic.test.modelo.Portafolio;
import pe.com.cotic.test.modelo.Reportecurso;
import pe.com.cotic.test.modelo.Reportecursodetalle;

public interface ReporteCursoDao {

	public List<Reportecurso> ListarReporteCursos();
	
	public List<Reportecursodetalle> ListarReporteCursosDetalle(Reportecurso reportecurso);
	
}
