package DaoImpl;

import Dao.UsuarioDao;
import Model.Usuario;
import Dao.Conexion;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDaoImpl implements UsuarioDao {
	
	private PreparedStatement statement = null;
    private Connection conexion;
    
    public UsuarioDaoImpl(){
        this.conexion = Conexion.obtenerConexion();
    }
	
	@Override
	public List<Usuario> listarUsuario() {
		List<Usuario> listarUsuario = new ArrayList<>();
		try {
			String consulta = "SELECT * FROM usuario";
			statement = conexion.prepareStatement(consulta);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				/*Obtenemos datos de la base de datos y los almacenamos en variables*/
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				String correo = resultSet.getString("correo");
				String clave = resultSet.getString("clave");
				Boolean estado = resultSet.getBoolean("estado");
				
				Usuario usuario = new Usuario();
				usuario.setId(id);
				usuario.setNombre(nombre);
				usuario.setCorreo(correo);
				usuario.setClave(clave);
				usuario.setEstado(estado);
				
				listarUsuario.add(usuario);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listarUsuario;
	}

	@Override
	public void agregarUsuario(Usuario usuario) {
		try {
	        String consulta = "INSERT INTO usuario (nombre, correo, clave) VALUES (?, ?, ?)";
	        PreparedStatement statement = conexion.prepareStatement(consulta);
	        
	        statement.setString(1, usuario.getNombre());
	        statement.setString(2, usuario.getCorreo());
	        statement.setString(3, usuario.getClave());	        
	        statement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();        
	    }
	}

	@Override
	public void eliminarUsuario(int idUsuario) {
		try{
			String consulta = "UPDATE usuario SET estado = 0 WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1,idUsuario);
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
		
	}
	
}
