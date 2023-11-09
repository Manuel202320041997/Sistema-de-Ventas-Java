package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.ProductoController;
import Controller.ReporteController;
import Controller.CategoriaController;
import Model.Producto;
import Model.Reporte;
import Model.ReporteExcel;
import Model.Categoria;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ifrm_GestionarProducto extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;	
	private JComboBox <String> cboCategoria;
	DefaultTableModel modelo;
	private JTable tblProducto;
	private CategoriaController categoriaController;
	private ProductoController productoController;
	private JSpinner spinnerStock;
	private JSpinner spinnerVenta;
	private JSpinner spinnerCompra;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ifrm_GestionarProducto frame = new ifrm_GestionarProducto();
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
	public ifrm_GestionarProducto() {
		setBounds(-5, -5, 910, 500);
		setClosable(true); //cerrar frame
		setIconifiable(false); // minimizar frame
		getContentPane().setBackground(new Color(67,16,58));
		getContentPane().setLayout(null);
		
		categoriaController = new CategoriaController();
		productoController = new ProductoController();

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 242, 448);
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion de Productos");
		lblNewLabel.setBounds(10, 11, 222, 39);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 85, 80, 26);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Stock:");
		lblNewLabel_1_1_1.setBounds(10, 135, 80, 26);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("P. Compra:");
		lblNewLabel_1_1_1_1.setBounds(8, 185, 82, 26);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("P. Venta:");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_2.setBounds(10, 235, 80, 26);
		panel.add(lblNewLabel_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Categoria:");
		lblNewLabel_1_1_1_1_1.setBounds(10, 284, 80, 26);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1_1_1_1_1);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(100, 90, 132, 20);
		txtNombre.setColumns(10);
		panel.add(txtNombre);
		
		cboCategoria = new JComboBox();
		cboCategoria.setBackground(new Color(255, 255, 255));
		cboCategoria.setBounds(100, 289, 132, 21);
		cboCategoria.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar Categoria"}));
		actualizarComboBoxCategoria();		
		panel.add(cboCategoria);
		
		JButton btnGuardar = new JButton("Guardar");	
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setBackground(new Color(0, 146, 1));
		btnGuardar.setBounds(10, 343, 153, 23);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(247, 0, 47));
		btnEliminar.setBounds(10, 415, 153, 23);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLimpiar.setBackground(new Color(254, 202, 2));
		btnLimpiar.setBounds(10, 376, 153, 23);
		panel.add(btnLimpiar);
		
		JSpinner spinnerVenta = new JSpinner();
		spinnerVenta.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerVenta.setBounds(100, 240, 132, 20);
		panel.add(spinnerVenta);
		
		JSpinner spinnerCompra = new JSpinner();
		spinnerCompra.setModel(new SpinnerNumberModel(Double.valueOf(0), Double.valueOf(0), null, Double.valueOf(1)));
		spinnerCompra.setBounds(100, 190, 132, 20);
		panel.add(spinnerCompra);
		
		JSpinner spinnerStock = new JSpinner();
		spinnerStock.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinnerStock.setBounds(100, 140, 132, 20);
		panel.add(spinnerStock);
		
		JButton btnExcel = new JButton("");
		ImageIcon iconoExcel = new ImageIcon(getClass().getResource("/Img/sobresalir.png"));		
		btnExcel.setIcon(iconoExcel);
		btnExcel.setBackground(new Color(128, 255, 128));
		btnExcel.setBounds(177, 364, 55, 36);
		panel.add(btnExcel);
		
		
		modelo = new DefaultTableModel(
	            new Object[][] {},
	            new String[] {
	                "ID", "Nombre", "Stock", "P. Compra", "P. Venta", "Categoria"
	            }
	        );

		tblProducto = new JTable(modelo);
		tblProducto.getColumnModel().getColumn(0).setPreferredWidth(20); // ID
		tblProducto.getColumnModel().getColumn(1).setPreferredWidth(100); // Nombre
		tblProducto.getColumnModel().getColumn(2).setPreferredWidth(20); // Stock
		tblProducto.getColumnModel().getColumn(3).setPreferredWidth(20); // Compra
		tblProducto.getColumnModel().getColumn(4).setPreferredWidth(20); // Venta
		tblProducto.getColumnModel().getColumn(5).setPreferredWidth(100); // Categoria
		mostrarTabla();
	 // Crear un JScrollPane y agregar la JTable a él
	 JScrollPane scrollPane = new JScrollPane(tblProducto);
	 scrollPane.setBounds(268, 11, 616, 448);
	 getContentPane().add(scrollPane);

	 btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ReporteController controller = new ReporteController();
	        	 List<Reporte> listarReporte = controller.listarReporte();
	        	 ReporteExcel.reporteprod();
			}
		});
	 
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
	            String nombreCategoria = cboCategoria.getSelectedItem().toString();
	            int categoriaId = categoriaController.obtenerIdCategoriaPorNombre(nombreCategoria);
				
				Producto producto = new Producto();
				producto.setNombre(txtNombre.getText());
				producto.setStock((int) spinnerStock.getValue());
				producto.setPrecio_venta((double) spinnerVenta.getValue());
				producto.setPrecio_compra((double) spinnerCompra.getValue());
	            producto.setId_categoria(categoriaId);
	            	            
	            productoController.agregarProducto(producto);
	            mostrarTabla();
	            //limpiar();
	            JOptionPane.showMessageDialog(ifrm_GestionarProducto.this, "Alumno agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
		       
		    } catch (Exception ex) {
		        JOptionPane.showMessageDialog(ifrm_GestionarProducto.this, "Ocurrió un error al registrar el alumno", "Error", JOptionPane.ERROR_MESSAGE);
		    }
			}
			});
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    int filaSeleccionada = tblProducto.getSelectedRow();
			    
			    if (filaSeleccionada >= 0) {
			        try {
			            Object idObject = tblProducto.getValueAt(filaSeleccionada, 0);
			            int idProducto = Integer.parseInt(idObject.toString()); // Intenta la conversión

			            int confirmacion = JOptionPane.showConfirmDialog(ifrm_GestionarProducto.this, "¿Está seguro de que desea eliminar este Producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

			            if (confirmacion == JOptionPane.YES_OPTION) {
			                productoController.eliminarProducto(idProducto); // Llama al método del controlador para eliminar el alumno
			                mostrarTabla();
			            }
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(ifrm_GestionarProducto.this, "Error de conversión: El valor no es un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    } else {
			        JOptionPane.showMessageDialog(ifrm_GestionarProducto.this, "Por favor, seleccione un Usuario para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    }   
			}
		});
	 
}	
	
	public void actualizarComboBoxCategoria() {

        List<Categoria> listarCategoria = categoriaController.listarCategoria();

        // Limpia el ComboBox
        cboCategoria.removeAllItems();

        for (Categoria categoria : listarCategoria) {
        	cboCategoria.addItem(categoria.getDescripcion());
        }
    }
	
	private void mostrarTabla() {
	    modelo.setRowCount(0); // Limpiar la tabla antes de agregar datos
	    List<Producto> listarproducto = productoController.listarProducto();
	    
	    for (Producto producto : listarproducto) {
	    	String nombreCategoria = categoriaController.obtenerNombreCategoriaPorId(producto.getId_categoria());
	    	
	        Object[] fila = {
	        		producto.getId(),
	        		producto.getNombre(),
	        		producto.getStock(),
	        		producto.getPrecio_compra(),
	        		producto.getPrecio_venta(),
	        		nombreCategoria
	        };
	        modelo.addRow(fila);
	    }
	   
	}
	
	public void limpiar() {
		txtNombre.setText("");
		spinnerStock.setValue(0);
		spinnerVenta.setValue(0);
		spinnerCompra.setValue(0);
		cboCategoria.setSelectedIndex(0);
		
	}
	
}
