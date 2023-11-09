package Dao;

import java.util.List;
import Model.Factura;

public interface FacturaDao {
	//public List<Factura> listarFactura();
	
	public int agregarFactura(Factura factura);
	
	public String obtenerUltimoNumeroFactura();
	
	public void actualizarNumeroFactura(String nuevoNumeroFactura);
	
	public int obtenerIdFacturaPorNumeroFactura(String numeroFactura);
}
