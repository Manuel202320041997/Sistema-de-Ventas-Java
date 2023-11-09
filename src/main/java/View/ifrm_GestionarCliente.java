package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import Controller.ClienteController;
import Controller.ReporteController;
import Model.Cliente;
import Model.Reporte;
import Model.ReporteExcel;

public class ifrm_GestionarCliente extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtDNI;
	DefaultTableModel modelo;
	private JTable tblCliente;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JTextField txtTelefono;
	private ClienteController clienteController;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ifrm_GestionarCliente frame = new ifrm_GestionarCliente();
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
	public ifrm_GestionarCliente() {
		setBounds(-5, -5, 910, 500);
		setClosable(true); //cerrar frame
		setIconifiable(false); // minimizar frame
		getContentPane().setBackground(new Color(67,16,58));
		getContentPane().setLayout(null);
		
		clienteController = new ClienteController();
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 249, 448);
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion de Clientes");
		lblNewLabel.setBounds(0, 10, 248, 39);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DNI:");
		lblNewLabel_1.setBounds(18, 93, 40, 26);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nombre:");
		lblNewLabel_1_1_1.setBounds(10, 145, 65, 26);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Correo:");
		lblNewLabel_1_1_1_1.setBounds(18, 197, 57, 26);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Telefono:");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_2.setBounds(10, 247, 65, 26);
		panel.add(lblNewLabel_1_1_1_1_2);
		
		txtDNI = new JTextField();
		txtDNI.setBounds(85, 99, 132, 20);
		txtDNI.setColumns(10);
		panel.add(txtDNI);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setBackground(new Color(0, 146, 1));
		btnGuardar.setBounds(20, 307, 145, 23);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(247, 0, 47));
		btnEliminar.setBounds(20, 402, 145, 23);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLimpiar.setBackground(new Color(254, 202, 2));
		btnLimpiar.setBounds(20, 356, 145, 23);
		panel.add(btnLimpiar);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(85, 151, 132, 20);
		panel.add(txtNombre);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(85, 203, 132, 20);
		panel.add(txtCorreo);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(85, 253, 132, 20);
		panel.add(txtTelefono);
		
		JButton btnExcel = new JButton("");
		ImageIcon iconoExcel = new ImageIcon(getClass().getResource("/Img/sobresalir.png"));		
		btnExcel.setIcon(iconoExcel);
		btnExcel.setBackground(new Color(128, 255, 128));		
		btnExcel.setBounds(174, 356, 65, 36);
		panel.add(btnExcel);
		
		
		modelo = new DefaultTableModel(
	            new Object[][] {},
	            new String[] {
	                "ID", "DNI", "Nombre", "Correo", "Telefono"
	            }
	        );

		tblCliente = new JTable(modelo);
		tblCliente.getColumnModel().getColumn(0).setPreferredWidth(20); // ID
		tblCliente.getColumnModel().getColumn(1).setPreferredWidth(100); // DNI
		tblCliente.getColumnModel().getColumn(2).setPreferredWidth(20); // Nombre
		tblCliente.getColumnModel().getColumn(3).setPreferredWidth(20); // Correo
		tblCliente.getColumnModel().getColumn(4).setPreferredWidth(20); // Telefono

	 mostrarTabla();
	 // Crear un JScrollPane y agregar la JTable a él
	 JScrollPane scrollPane = new JScrollPane(tblCliente);
	 scrollPane.setBounds(269, 11, 615, 448);
	 getContentPane().add(scrollPane);
	 
	 btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Cliente cliente = new Cliente();
					cliente.setDni(txtDNI.getText());
					cliente.setNombre(txtNombre.getText());
					cliente.setCorreo(txtCorreo.getText());
					cliente.setTelefono(Integer.parseInt(txtTelefono.getText()));

		            	            
		            clienteController.agregarCliente(cliente);
		            mostrarTabla();
		            limpiar();
		            JOptionPane.showMessageDialog(ifrm_GestionarCliente.this, "Cliente agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			       
			    } catch (Exception ex) {
			        JOptionPane.showMessageDialog(ifrm_GestionarCliente.this, "Ocurrió un error al registrar el Cliente", "Error", JOptionPane.ERROR_MESSAGE);
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
				int filaSeleccionada = tblCliente.getSelectedRow();
			    
			    if (filaSeleccionada >= 0) {
			        try {
			            Object idObject = tblCliente.getValueAt(filaSeleccionada, 0);
			            int idCliente = Integer.parseInt(idObject.toString()); // Intenta la conversión

			            int confirmacion = JOptionPane.showConfirmDialog(ifrm_GestionarCliente.this, "¿Está seguro de que desea eliminar este Producto?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

			            if (confirmacion == JOptionPane.YES_OPTION) {
			                clienteController.eliminarCliente(idCliente); // Llama al método del controlador para eliminar el alumno
			                mostrarTabla();
			            }
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(ifrm_GestionarCliente.this, "Error de conversión: El valor no es un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    } else {
			        JOptionPane.showMessageDialog(ifrm_GestionarCliente.this, "Por favor, seleccione un Usuario para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    }   
			}
		});
	 
	 btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 ReporteController controller = new ReporteController();
	        	 List<Reporte> listarReporte = controller.listarReporte();
	        	 ReporteExcel.reporte();
				
			}
		});

	}
	
	private void mostrarTabla() {
	    modelo.setRowCount(0); // Limpiar la tabla antes de agregar datos
	    List<Cliente> listarcliente = clienteController.listarCliente();
	    
	    for (Cliente cliente : listarcliente) {
	        Object[] fila = {
	        		
	        		cliente.getId(),
	        		cliente.getDni(),
	        		cliente.getNombre(),
	        		cliente.getCorreo(),
	        		cliente.getTelefono()	        		
	        };
	        modelo.addRow(fila);
	    }
	}
	
	private void limpiar() {
		
		txtDNI.setText("");
		txtNombre.setText("");
		txtCorreo.setText("");
		txtTelefono.setText("");
	}
}
