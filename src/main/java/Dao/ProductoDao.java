package Dao;

import java.util.List;
import DaoImpl.ProductoDaoImpl;
import Model.Producto;

public interface ProductoDao {
	public List<Producto> listarProducto();
	
	public void registrarProducto(Producto producto);
	
	public Producto obtenerProductoPorId(int idProducto);
	
	public void actualizarStock(int idProducto, int nuevoStock);
		
	public void eliminarProducto(int idProducto);

	public Producto obtenerStockyPrecioPorNombre(String nombreProducto);

	public int obtenerIdProductoPorNombre(String nombreProducto);
	
	public String obtenerNombreProductoPorId(int idProducto);    
}
