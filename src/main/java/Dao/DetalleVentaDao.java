package Dao;

import java.util.List;

import Model.DetalleVenta;
import Model.Producto;


public interface DetalleVentaDao {
	public List<DetalleVenta> listarDetalleVenta();
	
	public List<DetalleVenta> listarDetalleVentaPorNumeroFactura(int idFactura);
	
	public DetalleVenta obtenerDetallePorNumeroFactura(String numeroFactura);
	
	public void agregarDetalleFactura(DetalleVenta detalle);
}
