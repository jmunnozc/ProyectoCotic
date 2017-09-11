package pe.com.cotic.test.dao;

import java.util.List;

import pe.com.cotic.test.modelo.Usuario;

public interface UsuarioDao {
	
	public Usuario verificarDatos(Usuario usuario);
	public List<Usuario> ListarUsuarios();
	public boolean grabarUsuario(Usuario usuario);
	public boolean modificarUsuario(Usuario usuario);
	public boolean eliminarUsuario(Integer codigoUsuario);
}
