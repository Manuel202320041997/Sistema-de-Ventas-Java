package Dao;

import java.util.List;
import Model.Usuario;
import DaoImpl.UsuarioDaoImpl;

public interface UsuarioDao {
	public List<Usuario> listarUsuario();
	
	public void agregarUsuario(Usuario usuario);
	
    public void eliminarUsuario(int idUsuario);
}
