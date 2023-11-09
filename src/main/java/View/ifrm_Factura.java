package View;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Model.DetalleVenta;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import Controller.DetalleVentaController;
import Controller.FacturaController;
import Controller.ProductoController;
import Dao.Conexion;
import Dao.DetalleVentaDao;
import Controller.ClienteController;
import DaoImpl.DetalleVentaDaoImpl;
import DaoImpl.ClienteDaoImpl;
import Model.Producto;
import Model.Cliente;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

public class ifrm_Factura extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	ArrayList<DetalleVenta> listarProducto = new ArrayList<>();
	private DetalleVenta producto;
	private JTextField txtBuscar;
	private JTextField txtTotal;
	private JTextField txtIGV;
	private JTextField txtDescuento;
	private JTextField txtSubTotal;
	private JTextField txtCambio;
	private JTable tblFactura;
	private JSpinner spinnerCantidad;
	private JSpinner spinnerEfectivo;	
	private FacturaController facturaController;
	private ProductoController productoController;
	private ClienteController clienteController;
	private DetalleVentaController detalleventaController;
	private JComboBox<String> cboProducto;
	private JComboBox<String> cboCliente;
	DefaultTableModel modelo;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ifrm_Factura frame = new ifrm_Factura();
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
	public ifrm_Factura() {
		setBounds(-5, -5, 910, 500);
		setClosable(true); //cerrar frame
		setIconifiable(false); // minimizar frame
		getContentPane().setBackground(new Color(67,16,58));
		
		facturaController = new FacturaController();
		productoController = new ProductoController();
		clienteController = new ClienteController();
		detalleventaController = new DetalleVentaController();
		
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Facturas");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(417, 9, 139, 35);
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 33));
		getContentPane().add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(21, 227, 412, 219);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Efectivo:");
		lblNewLabel_2_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1_1_1_1.setBounds(21, 140, 81, 19);
		panel_1.add(lblNewLabel_2_1_1_1_1);
		
		txtCambio = new JTextField();
		txtCambio.setBackground(new Color(255, 255, 255));
		txtCambio.setEditable(false);
		txtCambio.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtCambio.setColumns(10);
		txtCambio.setBounds(112, 174, 94, 20);
		panel_1.add(txtCambio);
		
		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Cambio:");
		lblNewLabel_2_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1_1_1_1_1.setBounds(21, 172, 81, 19);
		panel_1.add(lblNewLabel_2_1_1_1_1_1);
		
		JSpinner spinnerEfectivo = new JSpinner();
		spinnerEfectivo.setFont(new Font("Tahoma", Font.BOLD, 10));
		spinnerEfectivo.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerEfectivo.setBounds(112, 141, 93, 21);
		panel_1.add(spinnerEfectivo);
		
		JLabel lblNewLabel_2 = new JLabel("Sub Total:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(9, 11, 93, 19);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Descuento:");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(9, 40, 93, 19);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("IGV:");
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1_1.setBounds(-1, 69, 93, 19);
		panel_1.add(lblNewLabel_2_1_1);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Total:");
		lblNewLabel_2_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1_1_1.setBounds(9, 98, 93, 19);
		panel_1.add(lblNewLabel_2_1_1_1);
		
		txtTotal = new JTextField();
		txtTotal.setBackground(new Color(255, 255, 255));
		txtTotal.setEditable(false);
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtTotal.setBounds(112, 101, 93, 20);
		panel_1.add(txtTotal);
		txtTotal.setColumns(10);
		
		txtIGV = new JTextField();
		txtIGV.setBackground(new Color(255, 255, 255));
		txtIGV.setEditable(false);
		txtIGV.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtIGV.setColumns(10);
		txtIGV.setBounds(112, 71, 93, 20);
		panel_1.add(txtIGV);
		
		txtDescuento = new JTextField();
		txtDescuento.setBackground(new Color(255, 255, 255));
		txtDescuento.setEditable(false);
		txtDescuento.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtDescuento.setColumns(10);
		txtDescuento.setBounds(112, 42, 93, 20);
		panel_1.add(txtDescuento);
		
		txtSubTotal = new JTextField();
		txtSubTotal.setBackground(new Color(255, 255, 255));
		txtSubTotal.setEditable(false);
		txtSubTotal.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtSubTotal.setColumns(10);
		txtSubTotal.setBounds(112, 13, 93, 20);
		panel_1.add(txtSubTotal);
		
	
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setForeground(new Color(255, 255, 255));
		btnCalcular.setBackground(new Color(0, 128, 255));
		btnCalcular.setIcon(new ImageIcon(ifrm_Factura.class.getResource("/Img/calculadora.png")));
		btnCalcular.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCalcular.setBounds(246, 141, 131, 41);
		panel_1.add(btnCalcular);
	
	
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(ifrm_Factura.class.getResource("/Img/factura.png")));
		lblNewLabel_4.setBounds(281, 37, 64, 86);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_6.setBounds(19, 129, 198, 76);
		panel_1.add(lblNewLabel_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(21, 55, 523, 175);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Cliente:");
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 29, 82, 25);
		//lblNewLabel.setIcon(new ImageIcon(ifrm_Factura.class.getResource("/Img/lupa.png")));
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
		
		JLabel lblNewLabel_1_1 = new JLabel("Producto:");
		lblNewLabel_1_1.setBounds(10, 83, 86, 20);
		panel_2.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
		
		cboCliente = new JComboBox<String>();
		cboCliente.setBackground(new Color(255, 255, 255));
		cboCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//DatosDelProducto();
				//registrarProducto();
				//CargarComboProducto();
			}
		});
		cboCliente.setBounds(106, 23, 186, 31);
		cboCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		cboCliente.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Cliente"}));
		actualizarComboBoxClientes();
		panel_2.add(cboCliente);
		
		
		cboProducto = new JComboBox<String>();
		cboProducto.setBackground(new Color(255, 255, 255));
		cboProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CargarComboProducto();
			}
		});
		cboProducto.setBounds(106, 75, 186, 31);
		cboProducto.setFont(new Font("Tahoma", Font.BOLD, 14));
		cboProducto.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Producto"}));
		actualizarComboBoxProductos();
		panel_2.add(cboProducto);
		
		
		txtBuscar = new JTextField();
		txtBuscar.setFont(new Font("Tahoma", Font.BOLD, 10));
		txtBuscar.setBounds(370, 11, 34, 25);
		panel_2.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("");		
		btnBuscar.setBackground(new Color(0, 128, 255));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setIcon(new ImageIcon(ifrm_Factura.class.getResource("/Img/lupachi.png")));
		btnBuscar.setBounds(414, 11, 51, 25);
		panel_2.add(btnBuscar);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_1_2 = new JLabel("Cantidad: ");
		lblNewLabel_1_2.setBounds(-21, 134, 113, 14);
		panel_2.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton btnAgregar = new JButton("Añadir");		
		btnAgregar.setBackground(new Color(0, 128, 255));
		btnAgregar.setForeground(new Color(255, 255, 255));
		btnAgregar.setIcon(new ImageIcon(ifrm_Factura.class.getResource("/Img/agregar.png")));
		btnAgregar.setBounds(327, 122, 180, 35);
		panel_2.add(btnAgregar);
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(ifrm_Factura.class.getResource("/Img/clientes.png")));
		lblNewLabel_3.setBounds(386, 45, 62, 69);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Cliente ID:");
		lblNewLabel_5.setBounds(302, 13, 61, 20);
		panel_2.add(lblNewLabel_5);
		
		JSpinner spinnerCantidad = new JSpinner();
		spinnerCantidad.setModel(new SpinnerNumberModel(Integer.valueOf(0), null, null, Integer.valueOf(1)));
		spinnerCantidad.setBounds(106, 129, 186, 28);
		panel_2.add(spinnerCantidad);		
		
		ImageIcon iconofondo = new ImageIcon(getClass().getResource("/Img/ventachiqui.png"));
		JButton btnNuevaVenta = new JButton("Nueva Venta", iconofondo);
		btnNuevaVenta.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNuevaVenta.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNuevaVenta.setBackground(new Color(240, 240, 240));
		btnNuevaVenta.setBounds(433, 227, 111, 77);
		btnNuevaVenta.setIcon(iconofondo);
		btnNuevaVenta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(btnNuevaVenta);
		
		ImageIcon iconofondo1 = new ImageIcon(getClass().getResource("/Img/cambio.png"));
		JButton btnCambio = new JButton("Cambio", iconofondo1);
		btnCambio.setBackground(new Color(255, 255, 255));
		btnCambio.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCambio.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCambio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCambio.setBounds(433, 303, 111, 77);
		btnCambio.setIcon(iconofondo1);
		getContentPane().add(btnCambio);
		
		ImageIcon iconofondo2 = new ImageIcon(getClass().getResource("/Img/registrarVenta.png"));
		JButton btnRegistrarVenta = new JButton("Vender", iconofondo2);
		btnRegistrarVenta.setBackground(new Color(255, 255, 255));
		btnRegistrarVenta.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRegistrarVenta.setHorizontalTextPosition(SwingConstants.CENTER);		
		btnRegistrarVenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegistrarVenta.setBounds(433, 375, 111, 71);
		btnRegistrarVenta.setIcon(iconofondo2);		
		getContentPane().add(btnRegistrarVenta);

		
		modelo = new DefaultTableModel(
	            new Object[][] {},
	            new String[] {
	                "Nombre","Cantidad", "P. Unit."
	            }
	        );
	
		tblFactura = new JTable(modelo);
		tblFactura.getColumnModel().getColumn(0).setPreferredWidth(100); // Nombre
		tblFactura.getColumnModel().getColumn(1).setPreferredWidth(10); // Cantidad
		tblFactura.getColumnModel().getColumn(2).setPreferredWidth(10); // PrecioUnitario
		//mostrarTabla();
		 JScrollPane scrollPane = new JScrollPane(tblFactura);
		 scrollPane.setBackground(new Color(255, 255, 255));
		 scrollPane.setBounds(570, 55, 300, 391);
		 getContentPane().add(scrollPane);
		 
		 btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Object spinnerValue = spinnerCantidad.getValue();

			        if (spinnerValue instanceof Integer) {
			            int cantidad = (int) spinnerValue;
			            if (cantidad > 0) {  // Validar que la cantidad sea mayor que cero
			                String nombreProducto = (String) cboProducto.getSelectedItem();
			                Producto productoInfo = productoController.obtenerStockyPrecioPorNombre(nombreProducto);
			                int stockProducto = productoInfo.getStock();
			                double precioProducto = productoInfo.getPrecio_venta();

			                if (stockProducto >= cantidad) {
			                    Object[] fila = {nombreProducto, cantidad, precioProducto};
			                    modelo.addRow(fila);
			                } else {
			                    JOptionPane.showMessageDialog(ifrm_Factura.this, "No contamos con el suficiente Stock para la Cantidad ingresada.", "Error", JOptionPane.ERROR_MESSAGE);
			                }
			            } else {
			                JOptionPane.showMessageDialog(ifrm_Factura.this, "La cantidad debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        } else {
			            JOptionPane.showMessageDialog(ifrm_Factura.this, "Por favor, ingrese una cantidad válida en el Spinner.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			});
		 
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						int idCliente = Integer.parseInt(txtBuscar.getText());
						String nombreCliente = clienteController.buscarClientePorId(idCliente);
						// Paso 2: Verifica si se encontró el nombre del cliente
					    if (nombreCliente != null) {
					        // Paso 3: Recorre los elementos del JComboBox para encontrar el índice del nombre del cliente
					        for (int i = 0; i < cboCliente.getItemCount(); i++) {
					            String item = (String) cboCliente.getItemAt(i);
					            if (item.equals(nombreCliente)) {
					                // Paso 4: Utiliza setSelectedIndex para seleccionar el cliente en el JComboBox
					            	cboCliente.setSelectedIndex(i);
					                break; // Rompe el bucle una vez que encuentres el índice
					            }
					        }
					    } else {
					        // Mensaje de error si no se encuentra el cliente
					        //JOptionPane.showMessageDialog(ifrm_Factura.this, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
					        JOptionPane.showMessageDialog(ifrm_Factura.this, "Este cliente no existe.", "Error", JOptionPane.ERROR_MESSAGE);
					    }						
						
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			});		 
			
			btnCalcular.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String clienteSeleccionado = (String) cboCliente.getSelectedItem();
					
					List<DetalleVenta> detalleProductos = new ArrayList<>();
					
					for (int i = 0; i < modelo.getRowCount(); i++) {
					    String nombreProducto = (String) modelo.getValueAt(i, 0);
					    int idProducto = productoController.obtenerIdProductoPorNombre(nombreProducto);
					    int cantidad = (int) modelo.getValueAt(i, 1);
					    double precioUnitario = (double) modelo.getValueAt(i, 2);					    

					    DetalleVenta detalle = new DetalleVenta(idProducto, cantidad, precioUnitario);
					    detalleProductos.add(detalle);
					}			

			        // Realizar el cálculo y obtener los resultados
					 Map<String, Double> resultados = detalleventaController.calcularVenta(clienteSeleccionado, detalleProductos);

			        // Mostrar los resultados en los campos de texto
			        txtSubTotal.setText(String.valueOf(resultados.get("subTotal")));
			        txtDescuento.setText(String.valueOf(resultados.get("descuento")));
			        txtIGV.setText(String.valueOf(resultados.get("igv")));
			        txtTotal.setText(String.valueOf(resultados.get("totalPagar")));
					
				}
			});
			
			btnCambio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Object spinnerValue = spinnerEfectivo.getValue();
					double total = Double.parseDouble(txtTotal.getText());
			        if (spinnerValue instanceof Double) {
			        	
			            double efectivo = (double) spinnerValue;
			            
			            if (efectivo > 0) {  // Validar que la cantidad sea mayor que cero
			            	
			            	if(efectivo >= total) {
			            		
			            		double cambio = efectivo - total;
			            		double cambioRedondeado = Math.round(cambio * 100.0) / 100.0;
				            	txtCambio.setText(String.valueOf(cambioRedondeado));
			            	}
			            	else {
			            		JOptionPane.showMessageDialog(ifrm_Factura.this, "El efectivo ingresado es menor al total a Pagar", "Error", JOptionPane.ERROR_MESSAGE);
			            	}			            	
			            }
			            else {
			            	JOptionPane.showMessageDialog(ifrm_Factura.this, "Ingresa efectivo del Cliente", "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        }
				}
			});
			
			btnRegistrarVenta.addActionListener(new ActionListener() {
				private List<DetalleVenta> detallesVenta = new ArrayList<>();
				public void actionPerformed(ActionEvent e) {
					
					String nombreCliente = (String) cboCliente.getSelectedItem();
			        int idCliente = clienteController.obtenerIdClientePorNombre(nombreCliente);
			        double totalVenta = Double.parseDouble(txtTotal.getText());

			        if (totalVenta <= 0) {
			            JOptionPane.showMessageDialog(ifrm_Factura.this, "No hay productos en la factura para vender.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        else {		        	
			        	
			        	String numeroFactura = facturaController.generarNumeroFactura();
				        int idFactura = facturaController.registrarVenta(numeroFactura, idCliente, totalVenta);

			            int rowCount = modelo.getRowCount();
			            for (int i = 0; i < rowCount; i++) {
			                String nombreProducto = (String) modelo.getValueAt(i, 0);
			                int idProducto = productoController.obtenerIdProductoPorNombre(nombreProducto);
			                int cantidad = (int) modelo.getValueAt(i, 1);
			                double precioUnitario = (double) modelo.getValueAt(i, 2);
			                
			                DetalleVenta detalle = new DetalleVenta(idFactura, idProducto, cantidad, precioUnitario);
			                detallesVenta.add(detalle);
			            }
				        
				        for (DetalleVenta detalle : detallesVenta) {
				            detalleventaController.registrarDetalleVenta(idFactura, detalle.getId_producto(), detalle.getCantidad(), detalle.getPrecioUnitario());
				        }

				        JOptionPane.showMessageDialog(ifrm_Factura.this, "Venta registrada con éxito. Número de factura: " + numeroFactura, "Éxito", JOptionPane.INFORMATION_MESSAGE);
			        }
				}
			});
			
			btnNuevaVenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					limpiar();
				}
			});
	}
	
	public void actualizarComboBoxProductos() {
        // Llama al controlador para obtener la lista de productos
        List<Producto> listaProductos = productoController.listarProducto();

        // Limpia el ComboBox
        cboProducto.removeAllItems();

        // Agrega los nombres de los productos al ComboBox
        for (Producto producto : listaProductos) {
            cboProducto.addItem(producto.getNombre());
        }
    }

	public void actualizarComboBoxClientes() {
        List<Cliente> listarCliente = clienteController.listarCliente();

        cboCliente.removeAllItems();

        for (Cliente cliente : listarCliente) {
            cboCliente.addItem(cliente.getNombre());
        }
    }
	
	private void limpiar() {
	    // Restablece los ComboBox
	    cboCliente.setSelectedIndex(0);
	    cboProducto.setSelectedIndex(0);

	    // Limpia los JTextField
	    txtSubTotal.setText("");
	    txtDescuento.setText("");
	    txtIGV.setText("");
	    txtTotal.setText("");
	    txtCambio.setText("");
	    txtBuscar.setText("");

	    // Limpia el JSpinner
	    spinnerCantidad.setValue("0");
	    spinnerEfectivo.setValue("0.0");

	    // Limpia el modelo de la tabla
	    DefaultTableModel modelo = (DefaultTableModel) tblFactura.getModel();
	    modelo.setRowCount(0); // Elimina todas las filas de la tabla
	}
}
