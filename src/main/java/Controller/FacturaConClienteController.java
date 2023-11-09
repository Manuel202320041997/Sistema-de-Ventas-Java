package Controller;

import java.util.List;

import DaoImpl.FacturaConClienteDaoImpl;
import DaoImpl.FacturaDaoImpl;
import Model.FacturaConCliente;

public class FacturaConClienteController {
	
	
	private FacturaConClienteDaoImpl facturaConClienteDaoImpl = null;
	//private DetalleVentaDaoImpl detalleVentaDaoImpl = null; 
	
	public FacturaConClienteController(){
		facturaConClienteDaoImpl = new FacturaConClienteDaoImpl();
		
	}
	public List<FacturaConCliente> listarFacturaConCliente(){		
    	List<FacturaConCliente> FacturaConCliente = null;
    	FacturaConCliente = facturaConClienteDaoImpl.listarFacturasConCliente();
    	return FacturaConCliente;
}
}
