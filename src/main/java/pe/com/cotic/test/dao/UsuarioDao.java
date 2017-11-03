package pe.com.cotic.test.dao;

import java.util.List;

import pe.com.cotic.test.modelo.Institucion;
import pe.com.cotic.test.modelo.Rol;
import pe.com.cotic.test.modelo.Rolusuario;
import pe.com.cotic.test.modelo.Usuario;

public interface UsuarioDao {
	
	public Usuario verificarDatos(Usuario usuario);
	public Usuario verificarDatosSP(Usuario usuario);
	public List<Usuario> ListarUsuarios();
	public boolean grabarUsuario(Usuario usuario);
	public boolean grabarRolUsuario(Rolusuario rolusuario);
	public boolean modificarUsuario(Usuario usuario);
	public boolean eliminarUsuario(Usuario usuario);
	public boolean modificarPerfilUsuario(Rolusuario rolusuario);
	public int buscarCodigoRolusuario(Usuario usuario);
	
	public List<Institucion> ListarInstituciones(Usuario usuario);
	public List<Rol> ListarPerfil(Usuario usuario);
}
