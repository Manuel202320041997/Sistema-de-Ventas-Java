package Controller;

import java.util.ArrayList;
import java.util.List;

import DaoImpl.ProductoDaoImpl;
import Model.Producto;
import Model.Usuario;

public class ProductoController {
private ProductoDaoImpl productoDaoImpl = null;
private CategoriaController categoriaController = new CategoriaController();
    
    public ProductoController(){
        productoDaoImpl = new ProductoDaoImpl();
    }
    
    public List<Producto> listarProducto(){
    	 List<Producto> listaCompleta = productoDaoImpl.listarProducto();
    	    List<Producto> listaFiltrada = new ArrayList<>();

    	    for (Producto producto : listaCompleta) {
    	        if (producto.getEstado()) { // Filtra solo los productos con estado true
    	            listaFiltrada.add(producto);
    	        }
    	    }
    	    return listaFiltrada;
    }
    
    public void agregarProducto(Producto producto){
   	 try {
   	        // Crear un objeto Alumno con los valores numéricos
   	     Producto productoObj = new Producto();
		   	productoObj.setNombre(producto.getNombre());
		   	productoObj.setStock(producto.getStock());
		   	productoObj.setPrecio_compra(producto.getPrecio_compra());
		   	productoObj.setPrecio_venta(producto.getPrecio_venta());
		   	productoObj.setId_categoria(producto.getId_categoria());

   	        // Llamar a tu DAO para agregar el alumno a la base de datos
   	        productoDaoImpl.registrarProducto(productoObj);
   	    } catch (Exception e) {
   	        e.printStackTrace(); // Manejo de excepciones, puedes personalizarlo según tus necesidades
   	    }
   }
   
   public Producto obtenerProductoPorId(int idProducto) {
	   List<Producto> productos = listarProducto(); // Obtener la lista filtrada

	    // Buscar el producto por ID en la lista filtrada
	    for (Producto producto : productos) {
	        if (producto.getId() == idProducto && producto.getEstado()) {
	            return producto;
	        }
	    }

	    return null; 
   }   


   public void actualizarStock(int idProducto, int nuevoStock, int anteriorStock) {
	   try {
		int stockSuma = nuevoStock + anteriorStock;   
		productoDaoImpl.actualizarStock(idProducto, stockSuma);
	} catch (Exception e) {
		e.printStackTrace();
	}
   }
   
   public void eliminarProducto(int idProducto) {
	   productoDaoImpl.eliminarProducto(idProducto);
   }

   public Producto obtenerStockyPrecioPorNombre(String nombreProducto){
		Producto producto = productoDaoImpl.obtenerStockyPrecioPorNombre(nombreProducto);

		if(producto != null){
			int stock = producto.getStock();
			double precio = producto.getPrecio_venta();

			producto.setStock(stock);
			producto.setPrecio_compra(precio);
			
			return producto;
		}

		else{
			return null;
		}
   }
   
   public int obtenerIdProductoPorNombre(String nombreProducto){
		return productoDaoImpl.obtenerIdProductoPorNombre(nombreProducto);
   }
   
   public String obtenerNombreProductoPorId(int idProducto) {
		try {
			//System.out.println("Intentando obtener Nombre de Categoria de: " + idProducto);
			String nombreProducto = productoDaoImpl.obtenerNombreProductoPorId(idProducto);
			
			if (nombreProducto != null) {
	            System.out.println("Nombre de categoria encontrado: " + nombreProducto);
	            return nombreProducto;
	        } else {
	           System.err.println("La categoria no se encuentra en la base de datos.");
	            return null;
	        }
			
		} catch (Exception e) {
			 e.printStackTrace(); // Imprime la información de la excepción en la consola para depuración
	
		     return null;
		}
	}
    
}
