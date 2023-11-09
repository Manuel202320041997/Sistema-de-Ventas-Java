package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.CategoriaController;
import Controller.ProductoController;
import Model.Producto;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ifrm_ActualizarStock extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	DefaultTableModel modelo;
	private JTextField txtProducto;
	private JTextField txtStock;
	private JTextField txtCompra;
	private JTextField txtVenta;
	private JTextField txtCategoria;
	private JTextField txtId;
	private CategoriaController categoriaController;
	private ProductoController productoController;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ifrm_ActualizarStock frame = new ifrm_ActualizarStock();
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
	public ifrm_ActualizarStock() {
		setBounds(-5, -5, 910, 500);
		setClosable(true); //cerrar frame
		setIconifiable(false); // minimizar frame
		getContentPane().setBackground(new Color(67,16,58));
		getContentPane().setLayout(null);
		
		categoriaController = new CategoriaController();
		productoController = new ProductoController();
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 140, 242, 223);
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Aumentar Stock");
		lblNewLabel.setBounds(10, 11, 222, 39);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Stock:");
		lblNewLabel_1_1_1.setBounds(10, 136, 80, 26);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1_1_1);
		
		JButton btnGuardar = new JButton("Guardar");	
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setBackground(new Color(0, 146, 1));
		btnGuardar.setBounds(22, 191, 195, 23);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(btnGuardar);
		
		JSpinner spinnerStock = new JSpinner();
		spinnerStock.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		spinnerStock.setBounds(100, 141, 132, 20);
		panel.add(spinnerStock);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel_2.setBounds(47, 67, 185, 31);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Buscar por ID:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(67, 74, 69, 20);
		panel.add(lblNewLabel_1_1);
		
		JTextField txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(146, 74, 36, 20);
		panel.add(txtBuscar);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setIcon(new ImageIcon(ifrm_ActualizarStock.class.getResource("/Img/lupachi.png")));
		btnBuscar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setBounds(192, 74, 36, 20);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(298, 47, 560, 373);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("ACTUALIZACION DE STOCK");
		lblNewLabel_3.setIcon(new ImageIcon(frm_Inicio.class.getResource("/Img/iconostock.png")));
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_3.setBounds(10, 11, 272, 35);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblInformacionDelProducto = new JLabel("INFORMACION DEL PRODUCTO");
		lblInformacionDelProducto.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformacionDelProducto.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblInformacionDelProducto.setBounds(193, 101, 330, 39);
		panel_1.add(lblInformacionDelProducto);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(34, 143, 489, 196);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		txtProducto = new JTextField();
		txtProducto.setEditable(false);
		txtProducto.setColumns(10);
		txtProducto.setBounds(100, 83, 132, 20);
		panel_2.add(txtProducto);
		
		JLabel lblNewLabel_1 = new JLabel("Producto:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 78, 80, 26);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Stock Act.:");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 121, 80, 26);
		panel_2.add(lblNewLabel_1_2);
		
		txtStock = new JTextField();
		txtStock.setEditable(false);
		txtStock.setColumns(10);
		txtStock.setBounds(100, 126, 132, 20);
		panel_2.add(txtStock);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("P. Compra:");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(257, 29, 80, 26);
		panel_2.add(lblNewLabel_1_2_1);
		
		txtCompra = new JTextField();
		txtCompra.setEditable(false);
		txtCompra.setColumns(10);
		txtCompra.setBounds(347, 34, 132, 20);
		panel_2.add(txtCompra);
		
		txtVenta = new JTextField();
		txtVenta.setEditable(false);
		txtVenta.setColumns(10);
		txtVenta.setBounds(347, 78, 132, 20);
		panel_2.add(txtVenta);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("P. Venta:");
		lblNewLabel_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2_1_1.setBounds(257, 73, 80, 26);
		panel_2.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_2_1_1_1 = new JLabel("Categoria:");
		lblNewLabel_1_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2_1_1_1.setBounds(257, 121, 80, 26);
		panel_2.add(lblNewLabel_1_2_1_1_1);
		
		txtCategoria = new JTextField();
		txtCategoria.setEditable(false);
		txtCategoria.setColumns(10);
		txtCategoria.setBounds(347, 126, 132, 20);
		panel_2.add(txtCategoria);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setBounds(100, 34, 132, 20);
		panel_2.add(txtId);
		
		JLabel lblNewLabel_1_3 = new JLabel("ID:");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(10, 29, 80, 26);
		panel_2.add(lblNewLabel_1_3);
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		            int idProducto = Integer.parseInt(txtBuscar.getText());                    
		            Producto producto = productoController.obtenerProductoPorId(idProducto);
		            String categoriaNombre = categoriaController.obtenerNombreCategoriaPorId(producto.getId_categoria());
		            
		            if (producto != null && producto.getEstado()) {
		                // Llena los campos de texto con los datos del producto
		                txtId.setText(String.valueOf(producto.getId()));
		                txtProducto.setText(producto.getNombre());
		                txtStock.setText(String.valueOf(producto.getStock()));
		                txtCompra.setText(String.valueOf(producto.getPrecio_compra()));
		                txtVenta.setText(String.valueOf(producto.getPrecio_venta()));
		                txtCategoria.setText(categoriaNombre);
		            } else {
		                JOptionPane.showMessageDialog(ifrm_ActualizarStock.this, "No se encontró el Producto Seleccionado, por favor selecciona uno existente", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		            
		        } catch (Exception e2) {
		            JOptionPane.showMessageDialog(ifrm_ActualizarStock.this, "No se encontró el Producto Seleccionado, por favor selecciona uno existente", "Error", JOptionPane.ERROR_MESSAGE);
		        }
				
				
			}
		});
		
		txtBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtBuscar.setText("");
			}
		});
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idProducto = Integer.parseInt(txtBuscar.getText());
				int nuevoStock = (int) spinnerStock.getValue();
				int anteriorStock = Integer.parseInt(txtStock.getText());
				
				int stockSuma = nuevoStock + anteriorStock;
				txtStock.setText(Integer.toString(stockSuma));				
				
				productoController.actualizarStock(idProducto, nuevoStock, anteriorStock);
				JOptionPane.showMessageDialog(ifrm_ActualizarStock.this, "Stock actualizado con éxito!!", "Ingreso", JOptionPane.INFORMATION_MESSAGE);
				
				
			}
		});
		
	}
}
