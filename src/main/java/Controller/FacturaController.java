package Controller;

import DaoImpl.FacturaDaoImpl;

import java.util.List;

import DaoImpl.DetalleVentaDaoImpl;
import Model.Factura;
import Model.Cliente;
import Model.DetalleVenta;

public class FacturaController {
	
	private FacturaDaoImpl facturaDaoImpl = null;
	//private DetalleVentaDaoImpl detalleVentaDaoImpl = null; 
	
	public FacturaController(){
		facturaDaoImpl = new FacturaDaoImpl();
		
	}
	
	/*public List<Factura> listarFactura(){		
	    	List<Factura> listarFactura = null;
	    	listarFactura = facturaDaoImpl.listarFactura();
	    	return listarFactura;
	}*/
	
	public String generarNumeroFactura() {
		 String ultimoNumeroFactura = obtenerUltimoNumeroFactura();
		    
		    if (ultimoNumeroFactura != null) {
		        char letraActual = ultimoNumeroFactura.charAt(0);
		        int numeroActual = Integer.parseInt(ultimoNumeroFactura.substring(1));

		        // Aumenta el número actual y, si es necesario, cambia la letra
		        if (numeroActual < 9999) {
		            numeroActual++;
		        } else {
		            numeroActual = 1;
		            // Cambia a la siguiente letra del alfabeto (A, B, C, ...)
		            letraActual = (char) (letraActual + 1);
		        }

		        // Combina la letra actual con el número actual
		        String numeroFactura = letraActual + String.format("%05d", numeroActual);

		        return numeroFactura;
		    } else {
		        // Maneja el caso en el que no se pudo obtener un número de factura
		        return null;
		    }
	}
	
	private String obtenerUltimoNumeroFactura() {
	    return facturaDaoImpl.obtenerUltimoNumeroFactura();
	}

	private void actualizarNumeroFactura(String nuevoNumeroFactura) {
	    facturaDaoImpl.actualizarNumeroFactura(nuevoNumeroFactura);
	}
	
	public int registrarVenta(String numeroFactura, int idCliente, double TotalVenta) {
			
		Factura factura = new Factura();
		factura.setNumero_factura(numeroFactura);
		factura.setId_cliente_int(idCliente);
		factura.setTotal(TotalVenta);
		
		int idFactura = facturaDaoImpl.agregarFactura(factura);
		return idFactura;
	}
	
	public int obtenerIdFacturaPorNumeroFactura(String numeroFactura) {
		return facturaDaoImpl.obtenerIdFacturaPorNumeroFactura(numeroFactura);
	}
	
/*	public void registrarDetalleVenta(int idFactura, int idProducto, int cantidad, double precioUnitario) {
		DetalleVenta detalle = new DetalleVenta(idFactura, idProducto, cantidad, precioUnitario);
		
		detalle.setId_factura(idFactura);
		detalle.setId_producto(idProducto);
		detalle.setCantidad(cantidad);
		detalle.setPrecioUnitario(precioUnitario);
		
		detalleVentaDaoImpl.agregarDetalleFactura(detalle);
	}
*/
	
}
