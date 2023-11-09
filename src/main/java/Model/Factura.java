package Model;

public class Factura {
	private int id;
	private String numero_factura;
	private Cliente id_cliente;
	private int id_cliente_int;
	private double total;
	private java.sql.Date fecha_registro;
	
	public int getId() {
		return id;
	}	
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}
	public int getId_cliente_int() {
		return id_cliente_int;
	}
	public void setId_cliente_int(int id_cliente_int) {
		this.id_cliente_int = id_cliente_int;
	}
	public int getId_cliente() {
		 if (id_cliente_int != 0) {
	            return id_cliente_int;
	        } else if (id_cliente != null) {
	            return id_cliente.getId();
	        } else {
	            // Si ninguno de los atributos tiene un valor, puedes devolver un valor predeterminado o lanzar una excepción
	            return -1; // Valor predeterminado o cualquier otro valor que desees
	        }
	}
	public void setId_cliente(Cliente cliente) {
		this.id_cliente = cliente;
        this.id_cliente_int = cliente != null ? cliente.getId() : 0;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public java.sql.Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(java.sql.Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	
	
	
}
