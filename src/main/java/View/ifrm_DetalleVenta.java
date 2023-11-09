package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import Model.FacturaConCliente;
import Model.Venta;
import Model.Categoria;
import Model.Cliente;
import Model.DetalleVenta;
import Model.Factura;
import Controller.DetalleVentaController;
import Controller.CategoriaController;
import Controller.FacturaConClienteController;
import Controller.FacturaController;
import Controller.ProductoController;
import Controller.ProductoController;
import java.awt.BorderLayout;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ifrm_DetalleVenta extends JInternalFrame {
	
	
	private static final long serialVersionUID = 1L;
	DefaultTableModel modelo;
	DefaultTableModel modelo2;
	private JTable tblFactura;
	private JTable tblDetalle;
	ArrayList<DetalleVenta> listarProducto = new ArrayList<>();
	private DetalleVentaController detalleVentaController;
	private FacturaController facturaController;
	private ProductoController productoController;
	private FacturaConClienteController facturaConClienteController; 
	private JDialog dialog;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ifrm_DetalleVenta frame = new ifrm_DetalleVenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	
	/**
	 * Create the frame.
	 */
	public ifrm_DetalleVenta() {
		setBounds(-5, -5, 910, 500);
		setClosable(true); //cerrar frame
		setIconifiable(false); // minimizar frame
		getContentPane().setBackground(new Color(67,16,58));
		getContentPane().setLayout(null);
		
		detalleVentaController = new DetalleVentaController();
		facturaController = new FacturaController();
		facturaConClienteController = new FacturaConClienteController();
		productoController = new ProductoController();
		
		JLabel lblNewLabel = new JLabel("Detalle de Venta");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(309, 27, 262, 35);
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 33));
		getContentPane().add(lblNewLabel);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(888, 0, 10, 471);
		getContentPane().add(panel);
	
		
	
		modelo = new DefaultTableModel(
	            new Object[][] {},
	            new String[] {
	               "Fecha", "N° Factura", "Cliente","ID Cliente","Telefono","N° DNI","Total"
	            }
	        );
	
		tblFactura = new JTable(modelo);
		tblFactura.setDefaultEditor(Object.class, null);
		tblFactura.getColumnModel().getColumn(0).setPreferredWidth(20); // fecha
		tblFactura.getColumnModel().getColumn(1).setPreferredWidth(30); // n° factura
		tblFactura.getColumnModel().getColumn(1).setPreferredWidth(50); // Cliente
		tblFactura.getColumnModel().getColumn(1).setPreferredWidth(20); // id cliente
		tblFactura.getColumnModel().getColumn(1).setPreferredWidth(30); // telefono
		tblFactura.getColumnModel().getColumn(1).setPreferredWidth(30); // dni
		tblFactura.getColumnModel().getColumn(1).setPreferredWidth(20); // total
		
		mostrarTabla();
		 JScrollPane scrollPane = new JScrollPane(tblFactura);
		 scrollPane.setBounds(37, 85, 819, 346);
		 getContentPane().add(scrollPane);
		 
		 tblFactura.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			        if (e.getClickCount() == 2) {
			            int filaSeleccionada = tblFactura.getSelectedRow();
			            if (filaSeleccionada != -1) {
			                // Obtener la factura seleccionada
			                //Factura factura = obtenerFacturaDesdeLaFila(filaSeleccionada);
			            	Object columFactura = tblFactura.getValueAt(filaSeleccionada, 1);
			            	String numeroFactura = columFactura.toString();

			                // Aquí debes abrir una nueva vista con el detalle de la factura
			                // Puedes usar un JDialog, un JInternalFrame o cualquier otro contenedor
			                mostrarDetalleFactura(numeroFactura);
			            }
			        }
			    }
			});
	}
	
	private void mostrarTabla() {
	    modelo.setRowCount(0); // Limpiar la tabla antes de agregar datos	   
	    List<FacturaConCliente> facturasConCliente = facturaConClienteController.listarFacturaConCliente();
	    
	    for (FacturaConCliente facturaConCliente : facturasConCliente) {
	        Factura factura = facturaConCliente.getFactura();
	        Cliente cliente = facturaConCliente.getCliente();

	        Object[] fila2 = {
	        	factura.getFecha_registro(),
	            factura.getNumero_factura(),
	            cliente.getNombre(),
	            cliente.getId(),
	            cliente.getTelefono(),
	            cliente.getDni(),
	            factura.getTotal()
	        };

	        modelo.addRow(fila2);
	    }
	}
	
	public void mostrarDetalleFactura(String numeroFactura) {
	    dialog = new JDialog();
	    dialog.setTitle("Detalle de la Factura " + numeroFactura);
	    dialog.setSize(600, 480);

	    dialog.setLocationRelativeTo(null);
	    
	    JPanel contentPanel = new JPanel();
	    contentPanel.setBackground(new Color(67, 16, 58));
	    dialog.add(contentPanel);
	    
	    modelo2 = new DefaultTableModel(
	            new Object[][] {},
	            new String[] {
	               "Producto","Cantidad","Precio Unitario"
	            }
	        );
	
		tblDetalle = new JTable(modelo2);
		tblDetalle.setDefaultEditor(Object.class, null);		
		tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(100); // Producto
		tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(20); // Cantidad
		tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(20); // precio		
		
		mostrarTablaDetalle(numeroFactura);
		JScrollPane scrollPane = new JScrollPane(tblDetalle);
		scrollPane.setBounds(37, 85, 500, 290);
		contentPanel.add(scrollPane);
	
	    dialog.setVisible(true);
	}
	
	public void mostrarTablaDetalle(String numeroFactura) {
		modelo2.setRowCount(0);
		//modelo.setRowCount(0); // Limpiar la tabla antes de agregar datos
	    List<DetalleVenta> listarDetalle = detalleVentaController.listarDetalleVentaPorNumeroFactura(numeroFactura);
	    
	    for (DetalleVenta detalleVenta : listarDetalle) {
	    	String nombreProducto = productoController.obtenerNombreProductoPorId(detalleVenta.getId_producto());
	        Object[] fila = {
	        			        		
	        		nombreProducto,
	        		detalleVenta.getCantidad(),
	        		detalleVenta.getPrecioUnitario(),      		
	        };
	        modelo2.addRow(fila);
	    }
	    
	}
	/*public void cerrarDialog() {
		dialog.dispose();
	}*/

}
