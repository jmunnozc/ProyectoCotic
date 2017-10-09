package pe.com.cotic.test.dao;

import java.util.List;

import pe.com.cotic.test.modelo.Usuario;
import pe.com.cotic.test.modelo.Usuarioportafolio;

public interface UsuarioPortafolioDao {
	
	public List<Usuarioportafolio> ListarUsuariosPortafolios();
	public boolean grabarUsuarioPortafolio(Usuarioportafolio usuarioportafolio);
	public boolean eliminarUsuarioPortafolio(Usuarioportafolio usuarioportafolio);

}
