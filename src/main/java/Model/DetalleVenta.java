package Model;

import Controller.DetalleVentaController;

public class DetalleVenta {

	private int id;
	private int id_factura;
	private int id_producto;
	private int cantidad;
	private double precioUnitario;
	
    public DetalleVenta(int id_factura, int id_producto, int cantidad, double precioUnitario) {
        this.id_factura = id_factura;
    	this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

	public DetalleVenta(int idProducto, int cantidad2, double precioUnitario2) {
		this.id_producto = idProducto;
        this.cantidad = cantidad2;
        this.precioUnitario = precioUnitario2;
    }

	public DetalleVenta() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_factura() {
		return id_factura;
	}

	public void setId_factura(int id_factura) {
		this.id_factura = id_factura;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
    
    
}
