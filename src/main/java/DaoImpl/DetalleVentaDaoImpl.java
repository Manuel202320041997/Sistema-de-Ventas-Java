package DaoImpl;
import java.util.List;

import javax.swing.JComboBox;

import View.ifrm_Factura;
import Dao.Conexion;
import Dao.DetalleVentaDao;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Controller.DetalleVentaController;
import Model.DetalleVenta;
import Model.Producto;
import Model.Usuario;

public class DetalleVentaDaoImpl implements DetalleVentaDao{
	
	private PreparedStatement statement = null;
    private Connection conexion;
    
    public DetalleVentaDaoImpl() {
    	 this.conexion = Conexion.obtenerConexion();
	};
	
	@Override
	public List<DetalleVenta> listarDetalleVenta() {
		return null;
		
	}

	@Override
	public DetalleVenta obtenerDetallePorNumeroFactura(String numeroFactura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarDetalleFactura(DetalleVenta detalleVenta) {

		try {
	        String consulta = "INSERT INTO factura_detalle (id_factura, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";
	        PreparedStatement statement = conexion.prepareStatement(consulta);

	        statement.setInt(1, detalleVenta.getId_factura());
	        statement.setInt(2, detalleVenta.getId_producto());
	        statement.setInt(3, detalleVenta.getCantidad());
	        statement.setDouble(4, detalleVenta.getPrecioUnitario());

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public List<DetalleVenta> listarDetalleVentaPorNumeroFactura(int idFactura) {
		 List<DetalleVenta> listarDetalle = new ArrayList<>();
		    try {
		        String consulta = "SELECT id_producto, cantidad, precio_unitario FROM factura_detalle WHERE id_factura = ?";
		        statement = conexion.prepareStatement(consulta);
		        statement.setInt(1, idFactura);
		        ResultSet resultSet = statement.executeQuery();

		        while (resultSet.next()) {
		            // Dentro del bucle, se procesan todas las filas
		            int idProducto = resultSet.getInt("id_producto");
		            int cantidad = resultSet.getInt("cantidad");
		            double precioUnitario = resultSet.getDouble("precio_unitario");

		            // Crear un objeto DetalleVenta para cada fila y agregarlo a la lista
		            DetalleVenta detalle = new DetalleVenta();
		            detalle.setId_producto(idProducto);
		            detalle.setCantidad(cantidad);
		            detalle.setPrecioUnitario(precioUnitario);

		            listarDetalle.add(detalle);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return listarDetalle;
	}
	
	
}

   
