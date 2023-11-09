package Model;

public class FacturaConCliente {
    private Factura factura;
    private Cliente cliente;

    public FacturaConCliente(Factura factura, Cliente cliente) {
        this.factura = factura;
        this.cliente = cliente;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

