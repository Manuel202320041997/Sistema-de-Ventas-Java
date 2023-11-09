package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

//import com.mysql.cj.xdevapi.Statement;

import Dao.Conexion;
import Dao.FacturaDao;
import Model.Cliente;
import Model.Factura;

public class FacturaDaoImpl implements FacturaDao {
	private PreparedStatement statement = null;
    private Connection conexion;
    
    public FacturaDaoImpl() {
    	 this.conexion = Conexion.obtenerConexion();
	};
	@Override
	public int agregarFactura(Factura factura) {
		 int idGenerado = -1; // Valor predeterminado en caso de que no se obtenga un ID generado

		    try {
		        String consulta = "INSERT INTO facturas (numero_factura, id_cliente, total) VALUES (?, ?, ?)";
		        PreparedStatement statement = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);

		        statement.setString(1, factura.getNumero_factura());
		        statement.setInt(2, factura.getId_cliente_int());
		        statement.setDouble(3, factura.getTotal());

		        statement.executeUpdate();

		        // Obtener las claves generadas (en este caso, solo se espera una clave generada)
		        ResultSet generatedKeys = statement.getGeneratedKeys();
		        if (generatedKeys.next()) {
		            idGenerado = generatedKeys.getInt(1); // Obtener el valor del ID generado
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return idGenerado;
	}
	@Override
	public String obtenerUltimoNumeroFactura() {
		String ultimoNumeroFactura = null;
		
		try {
	        String consulta = "SELECT numero_factura FROM facturas ORDER BY id DESC LIMIT 1";
	        PreparedStatement statement = conexion.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);	       
	        ResultSet resultSet = statement.executeQuery();
	        

	        if (resultSet.next()) {
	            ultimoNumeroFactura = resultSet.getString("numero_factura");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return ultimoNumeroFactura;
	}
	@Override
	public void actualizarNumeroFactura(String nuevoNumeroFactura) {
		try {
			String consulta = "UPDATE facturas SET numero_factura = ? WHERE id = (SELECT MAX(id) FROM facturas)";
			//String consulta = "UPDATE facturas SET numero_factura = ? WHERE numero_factura = ? ORDER BY id DESC LIMIT 1";
	        PreparedStatement statement = conexion.prepareStatement(consulta);

	        statement.setString(1, nuevoNumeroFactura);

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}
	/*@Override
	public List<Factura> listarFactura() {
		 List<Factura> listaFacturas = new ArrayList<>();
		    try {
		        String consulta = "SELECT f.fecha_registro, f.numero_factura, c.nombre, c.id, c.telefono, c.dni, f.total FROM facturas f LEFT JOIN cliente c ON f.id_cliente = c.id";
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
		            // Puedes obtener más datos si los necesitas, como la fecha, el nombre del cliente, etc.

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
		            // Configurar los atributos del cliente (nombre, id, teléfono, dni) aquí

		            // Establecer la relación entre la factura y el cliente
		            factura.setId_cliente(cliente);

		            listaFacturas.add(factura);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return listaFacturas;
	}*/
	@Override
	public int obtenerIdFacturaPorNumeroFactura(String numeroFactura) {
		int id = 0;
		try {
			String consulta = "SELECT id FROM facturas WHERE numero_factura = ?";			
	        PreparedStatement statement = conexion.prepareStatement(consulta);

	        statement.setString(1, numeroFactura);	        
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            // Obtenemos datos de la base de datos y los almacenamos en variables
	            id = resultSet.getInt("id");
	            
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return id;

	}

}
