package pe.com.cotic.test.dao;

import java.util.List;

import pe.com.cotic.test.modelo.Nivel;
import pe.com.cotic.test.modelo.Portafolio;

public interface PortafolioDao {

	public List<Portafolio> ListarPortafolios();
	public List<Portafolio> ListarPortafoliosxNivel(int nivel);
	public boolean grabarPortafolio(Portafolio portafolio);
	public boolean modificarPortafolio(Portafolio portafolio);
	public boolean eliminarPortafolio(Portafolio portafolio);
	
	public List<Nivel> ListarNiveles(Portafolio portafolio);
	public int ultimoPortafolio();
}
