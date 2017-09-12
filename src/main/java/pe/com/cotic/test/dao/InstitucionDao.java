package pe.com.cotic.test.dao;

import java.util.List;

import pe.com.cotic.test.modelo.Institucion;

public interface InstitucionDao {
	
	public List<Institucion> ListarInstituciones();
	public boolean grabarInstitucion(Institucion institucion);
	public boolean modificarInstitucion(Institucion institucion);
	public boolean eliminarInstitucion(Institucion institucion);
}
