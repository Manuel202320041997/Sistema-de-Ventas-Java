package DaoImpl;
import java.util.List;

import Dao.ProductoDao;
import Model.Producto;
import Dao.Conexion;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductoDaoImpl implements ProductoDao{
	
	private PreparedStatement statement = null;
    private Connection conexion;
    
    public ProductoDaoImpl(){
        this.conexion = Conexion.obtenerConexion();
    }
	
	@Override
	public List<Producto> listarProducto() {
		List<Producto> listarProducto = new ArrayList<>();
		try {
			String consulta = "SELECT * FROM producto";
			statement = conexion.prepareStatement(consulta);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				/*Obtenemos datos de la base de datos y los almacenamos en variables*/
				int id = resultSet.getInt("id");
				String nombre = resultSet.getString("nombre");
				int stock = resultSet.getInt("stock");
				int precio_compra = resultSet.getInt("precio_compra");
				int precio_venta = resultSet.getInt("precio_venta");
				int id_categoria = resultSet.getInt("id_categoria");				
				Boolean estado = resultSet.getBoolean("estado");
				
				Producto producto = new Producto();
				producto.setId(id);
				producto.setNombre(nombre);
				producto.setStock(stock);
				producto.setPrecio_compra(precio_compra);
				producto.setPrecio_venta(precio_venta);
				producto.setId_categoria(id_categoria);
				producto.setEstado(estado);
				
				listarProducto.add(producto);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listarProducto;
	}

	@Override
	public void registrarProducto(Producto producto) {
		 try {
		        String consulta = "INSERT INTO producto (nombre, stock, precio_compra, precio_venta, id_categoria) VALUES (?, ?, ?, ?, ?)";
		        PreparedStatement statement = conexion.prepareStatement(consulta);
		        
		        statement.setString(1, producto.getNombre());
		        statement.setInt(2, producto.getStock());
		        statement.setDouble(3, producto.getPrecio_compra());
		        statement.setDouble(4, producto.getPrecio_venta());
		        statement.setInt(5, producto.getId_categoria());	        
		        
		        statement.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();        
		    }
		
	}

	@Override
	public Producto obtenerProductoPorId(int idProducto) {
		 Producto producto = null; // Inicializa el producto como nulo
		    
		    try {
		        String consulta = "SELECT * FROM producto WHERE id = ?";
		        statement = conexion.prepareStatement(consulta);
		        statement.setInt(1, idProducto); // Establece el valor del parámetro
		        ResultSet resultSet = statement.executeQuery();

		        if (resultSet.next()) {
		            // Obtenemos datos de la base de datos y los almacenamos en variables
		            int id = resultSet.getInt("id");
		            String nombre = resultSet.getString("nombre");
		            int stock = resultSet.getInt("stock");
		            int precio_compra = resultSet.getInt("precio_compra");
		            int precio_venta = resultSet.getInt("precio_venta");
		            int id_categoria = resultSet.getInt("id_categoria");
		            Boolean estado = resultSet.getBoolean("estado");

		            producto = new Producto();
		            producto.setId(id);
		            producto.setNombre(nombre);
		            producto.setStock(stock);
		            producto.setPrecio_compra(precio_compra);
		            producto.setPrecio_venta(precio_venta);
		            producto.setId_categoria(id_categoria);
		            producto.setEstado(estado);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return producto; // Devuelve un solo producto o null si no se encontró
	}

	@Override
	public void actualizarStock(int idProducto, int nuevoStock) {
		
		try {
	        String consulta = "UPDATE producto SET stock = ? WHERE id = ?";
	        PreparedStatement statement = conexion.prepareStatement(consulta);
	        
	        statement.setInt(1, nuevoStock);
	        statement.setInt(2, idProducto);        
	        
	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();        
	    }
	}

	@Override
	public void eliminarProducto(int idProducto) {
		try{
			String consulta = "UPDATE producto SET estado = 0 WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1,idProducto);
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
	}

	@Override
	public Producto obtenerStockyPrecioPorNombre(String nombreProducto) {
		Producto producto = null; 
		try{
			String consulta = "SELECT stock, precio_venta FROM producto WHERE nombre = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setString(1,nombreProducto);
            ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				producto = new Producto();
				producto.setStock(resultSet.getInt("stock"));
				producto.setPrecio_venta(resultSet.getDouble("precio_venta"));
			} 
			
        }
		catch(SQLException e){
			e.printStackTrace();
		}

		return producto;
	}

	@Override
	public int obtenerIdProductoPorNombre(String nombreProducto) {
		int idProducto = -1; // Valor predeterminado en caso de no encontrar el producto

		try {
			String consulta = "SELECT id FROM producto WHERE nombre = ?";
			PreparedStatement statement = conexion.prepareStatement(consulta);
			statement.setString(1, nombreProducto);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				idProducto = resultSet.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

    return idProducto;
	}

	@Override
	public String obtenerNombreProductoPorId(int idProducto) {
		try {
			String consulta = "SELECT nombre FROM producto WHERE id = ?";
		    statement = conexion.prepareStatement(consulta);
		    statement.setInt(1, idProducto); // Establece el valor del parámetro
		    ResultSet resultSet = statement.executeQuery();
			
		    if (resultSet.next()) {
		    	return resultSet.getString("nombre");			
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null; // En caso de error o si no se encuentra la categoría
	}

}
