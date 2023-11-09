package Controller;

import DaoImpl.UsuarioDaoImpl;
import Model.Categoria;
import Model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
	private UsuarioDaoImpl usuarioDaoImpl = null;
    
    public UsuarioController(){
        usuarioDaoImpl = new UsuarioDaoImpl();
    }
    
    public List<Usuario> listarUsuarios(){
    	List<Usuario> listaCompleta = usuarioDaoImpl.listarUsuario();
    	List<Usuario> listaFiltrada = new ArrayList<>();
    	
    	for (Usuario usuario : listaCompleta) {
            if (usuario.getEstado()) {//TRAE A TODOS LOS OBJETOS O FILAS DE DATOS QUE HAY EN LISTA COMPLETA, PERO SOLO ADMITE LOS QUE TIENEN DE ESTADO (TRUE)
                listaFiltrada.add(usuario);
            }
        }
    	return listaFiltrada;
    }
    
    public Usuario validarLogin(String nombreUsuario, String clave) {
    	List<Usuario> usuarios = listarUsuarios();
    	
    	for(Usuario usuario : usuarios) { //variable "usuario" toma valores de lista "usuarios"
    		if(usuario.getNombre().equals(nombreUsuario) && usuario.getClave().equals(clave)) {
    			return usuario;
    		}    		
    	}
    	return null;
    }
    
    public void agregarUsuario(Usuario usuario) {
    	try {
   	        // Crear un objeto Alumno con los valores numéricos
   	     Usuario usuarioObj = new Usuario();
		   	  usuarioObj.setNombre(usuario.getNombre());
		   	  usuarioObj.setCorreo(usuario.getCorreo());
		   	  usuarioObj.setClave(usuario.getClave());

   	        // Llamar a tu DAO para agregar el alumno a la base de datos
   	        usuarioDaoImpl.agregarUsuario(usuarioObj);
   	    } catch (Exception e) {
   	        e.printStackTrace(); // Manejo de excepciones, puedes personalizarlo según tus necesidades
   	    }
    }
    
    public void eliminarUsuario(int idUsuario) {
    	usuarioDaoImpl.eliminarUsuario(idUsuario);
    }
}
