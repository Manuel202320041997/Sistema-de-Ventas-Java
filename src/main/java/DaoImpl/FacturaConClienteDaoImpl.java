package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.Conexion;
import Dao.FacturaConClienteDao;
import Model.Cliente;
import Model.Factura;
import Model.FacturaConCliente;

public class FacturaConClienteDaoImpl implements FacturaConClienteDao {
	private PreparedStatement statement = null;
    private Connection conexion;
    
    public FacturaConClienteDaoImpl() {
    	 this.conexion = Conexion.obtenerConexion();
	};
	@Override
	public List<FacturaConCliente> listarFacturasConCliente() {
		List<FacturaConCliente> facturasConCliente = new ArrayList<>();
	    try {
	        String consulta = "SELECT f.fecha_registro, f.numero_factura, c.nombre, c.id, c.telefono, c.dni, f.total " +
	                         "FROM facturas f " +
	                         "LEFT JOIN cliente c ON f.id_cliente = c.id";
	        statement = conexion.prepareStatement(consulta);
	        ResultSet resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            // Obtener datos de la base de datos y almacenarlos en variables
	        	java.sql.Date fechaRegistro = resultSet.getDate("fecha_registro");
	            String numeroFactura = resultSet.getString("numero_factura");
	            String nombreCliente = resultSet.getString("nombre");
	            int idCliente = resultSet.getInt("id");
	            int telefonoCliente = resultSet.getInt("telefono");
	            String dniCliente = resultSet.getString("dni");
	            double total = resultSet.getDouble("total");

	            // Crear objetos de Factura y Cliente para almacenar los datos
	            Factura factura = new Factura();
	            factura.setFecha_registro(fechaRegistro);
	            factura.setNumero_factura(numeroFactura);
	            factura.setTotal(total);

	            Cliente cliente = new Cliente();
	            cliente.setNombre(nombreCliente);
	            cliente.setId(idCliente);
	            cliente.setTelefono(telefonoCliente);
	            cliente.setDni(dniCliente);

	            FacturaConCliente facturaConCliente = new FacturaConCliente(factura, cliente);
	            facturasConCliente.add(facturaConCliente);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return facturasConCliente;
	}

}
