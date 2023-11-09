package Model;

public class Venta {
	private int id_venta;
	private int id_cliente;
	private double valorPagar;
	private String FechaVenta;
	private int estado;
	
	public Venta() {
		this.id_venta = 0;
		this.id_cliente = 0;
		this.valorPagar = 0.0;
		this.FechaVenta = "";
		this.estado = 0;
		
	}

	public Venta(int id_venta, int id_cliente, double valorPagar, String fechaVenta, int estado) {
		super();
		this.id_venta = id_venta;
		this.id_cliente = id_cliente;
		this.valorPagar = valorPagar;
		FechaVenta = fechaVenta;
		this.estado = estado;
	}

	public int getId_venta() {
		return id_venta;
	}

	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}

	public String getFechaVenta() {
		return FechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		FechaVenta = fechaVenta;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Venta [id_venta=" + id_venta + ", id_cliente=" + id_cliente + ", valorPagar=" + valorPagar
				+ ", FechaVenta=" + FechaVenta + ", estado=" + estado + "]";
	}
	
	
}
