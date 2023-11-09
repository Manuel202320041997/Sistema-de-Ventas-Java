package DaoImpl;

import java.util.List;
import Dao.ClienteDao;
import Model.Cliente;
import Dao.Conexion;

import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDaoImpl implements ClienteDao {
	
	private PreparedStatement statement = null;
    private Connection conexion;
    
    public ClienteDaoImpl(){
        this.conexion = Conexion.obtenerConexion();
    }
	
	@Override
	public List<Cliente> listarCliente() {
		List<Cliente> listarCliente = new ArrayList<>();
		try {
			String consulta = "SELECT * FROM cliente";
			statement = conexion.prepareStatement(consulta);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				/*Obtenemos datos de la base de datos y los almacenamos en variables*/
				int id = resultSet.getInt("id");
				String dni = resultSet.getString("dni");
				String nombre = resultSet.getString("nombre");
				String correo = resultSet.getString("correo");
				int telefono = resultSet.getInt("telefono");				
				Boolean estado = resultSet.getBoolean("estado");
				
				Cliente cliente = new Cliente();
				cliente.setId(id);				
				cliente.setDni(dni);
				cliente.setNombre(nombre);
				cliente.setCorreo(correo);
				cliente.setTelefono(telefono);
				cliente.setEstado(estado);
				
				listarCliente.add(cliente);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listarCliente;
	}

	@Override
	public void agregarCliente(Cliente cliente) {
		try {
	        String consulta = "INSERT INTO cliente (dni, nombre, correo, telefono) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = conexion.prepareStatement(consulta);
	        
	        statement.setString(1, cliente.getDni());
	        statement.setString(2, cliente.getNombre());
	        statement.setString(3, cliente.getCorreo());
	        statement.setInt(4, cliente.getTelefono());	        
	        statement.executeUpdate();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();        
	    }
		
	}
	
	@Override
	public void eliminarCliente(int idCliente) {
		try{
			String consulta = "UPDATE cliente SET estado = 0 WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1,idCliente);
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
		
	}

	@Override
	public String buscarClientePorId(int idCliente) {
		try {
			String consulta = "SELECT nombre FROM cliente WHERE id = ?";
			statement = conexion.prepareStatement(consulta);
			statement.setInt(1, idCliente); // Establece el valor del parámetro
			ResultSet resultSet = statement.executeQuery();
	
			if (resultSet.next()) {
				// Obtenemos el nombre de la base de datos
				String nombre = resultSet.getString("nombre");
				return nombre; // Retorna el nombre del cliente
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return null; // Retorna null en caso de no encontrar un cliente con ese ID
	}

	@Override
	public int obtenerIdClientePorNombre(String nombreCliente) {
		int idCliente = -1; // Valor predeterminado en caso de no encontrar el producto

		try {
			String consulta = "SELECT id FROM cliente WHERE nombre = ?";
			PreparedStatement statement = conexion.prepareStatement(consulta);
			statement.setString(1, nombreCliente);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				idCliente = resultSet.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

    return idCliente;
	}

}
