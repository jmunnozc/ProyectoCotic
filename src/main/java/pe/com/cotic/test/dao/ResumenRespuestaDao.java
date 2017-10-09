package pe.com.cotic.test.dao;

import java.util.List;

import pe.com.cotic.test.modelo.Respuestacabecera;
import pe.com.cotic.test.modelo.Resumenrespuesta;

public interface ResumenRespuestaDao {
	
	public List<Resumenrespuesta> listarResumenRespuesta(Respuestacabecera respuestacabecera);
	
}
