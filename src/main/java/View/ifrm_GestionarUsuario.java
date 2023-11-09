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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.ReporteController;
import Controller.UsuarioController;
import Model.Producto;
import Model.Reporte;
import Model.ReporteExcel;
import Model.Usuario;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ifrm_GestionarUsuario extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtCorreo;
	private JTextField txtNombre;	
	private JTextField txtClave;
	private JTextField txtConfirmarClave;
	DefaultTableModel modelo;
	private UsuarioController usuarioController;
	private JTable tblUsuario;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ifrm_GestionarUsuario frame = new ifrm_GestionarUsuario();
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
	public ifrm_GestionarUsuario() {
		setBounds(-5, -5, 910, 500);
		setClosable(true); //cerrar frame
		setIconifiable(false); // minimizar frame
		getContentPane().setBackground(new Color(67,16,58));
		getContentPane().setLayout(null);
		
		usuarioController = new UsuarioController();
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 242, 448);
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion de Usuarios");
		lblNewLabel.setBounds(10, 11, 222, 39);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 80, 83, 26);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Correo:");
		lblNewLabel_1_1_1.setBounds(10, 130, 83, 26);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Clave:");
		lblNewLabel_1_1_1_1.setBounds(10, 180, 83, 26);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Confirmar");
		lblNewLabel_1_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1_1_2.setBounds(10, 230, 83, 26);
		panel.add(lblNewLabel_1_1_1_1_2);
		
		txtConfirmarClave = new JTextField();
		txtConfirmarClave.setColumns(10);
		txtConfirmarClave.setBounds(90, 235, 132, 20);
		panel.add(txtConfirmarClave);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(90, 135, 132, 20);
		txtCorreo.setColumns(10);
		panel.add(txtCorreo);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(90, 85, 132, 20);
		txtNombre.setColumns(10);
		panel.add(txtNombre);		
		
		txtClave = new JTextField();
		txtClave.setColumns(10);
		txtClave.setBounds(90, 185, 132, 20);
		panel.add(txtClave);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setBackground(new Color(0, 146, 1));
		btnGuardar.setBounds(14, 343, 149, 23);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(247, 0, 47));
		btnEliminar.setBounds(14, 414, 149, 23);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLimpiar.setBackground(new Color(254, 202, 2));
		btnLimpiar.setBounds(14, 377, 149, 23);
		panel.add(btnLimpiar);
		
		JButton btnExcel = new JButton("");
		ImageIcon iconoExcel = new ImageIcon(getClass().getResource("/Img/sobresalir.png"));		
		btnExcel.setIcon(iconoExcel);
		btnExcel.setBackground(new Color(128, 255, 128));
		btnExcel.setBounds(173, 364, 65, 36);
		panel.add(btnExcel);
		
		
		modelo = new DefaultTableModel(
	            new Object[][] {},
	            new String[] {
	                "ID", "Nombre", "Correo"
	            }
	        );

		tblUsuario = new JTable(modelo);
		tblUsuario.getColumnModel().getColumn(0).setPreferredWidth(20); // ID
		tblUsuario.getColumnModel().getColumn(1).setPreferredWidth(150); // Nombre
		tblUsuario.getColumnModel().getColumn(2).setPreferredWidth(150); // Correo	 
		mostrarTabla();
	 // Crear un JScrollPane y agregar la JTable a él
	 JScrollPane scrollPane = new JScrollPane(tblUsuario);
	 scrollPane.setBounds(262, 11, 622, 448);
	 getContentPane().add(scrollPane);
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					 try {
						 String clave1 = txtClave.getText();
						 String clave2 = txtConfirmarClave.getText();
						 
						 if(clave1.equals(clave2)) {
							 Usuario usuario = new Usuario();
							 usuario.setNombre(txtNombre.getText());
							 usuario.setCorreo(txtCorreo.getText());
							 usuario.setClave(txtClave.getText());

							 usuarioController.agregarUsuario(usuario);
					         mostrarTabla();
					         limpiar();
					            JOptionPane.showMessageDialog(ifrm_GestionarUsuario.this, "Usuario agregado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
						 }
						 else {
							 JOptionPane.showMessageDialog(ifrm_GestionarUsuario.this, "Las contraseñas no coinciden", "Advertencia", JOptionPane.WARNING_MESSAGE);
						 }
						 
						 
						 					       
					    } catch (Exception ex) {
					        JOptionPane.showMessageDialog(ifrm_GestionarUsuario.this, "Ocurrió un error al registrar el Usuario", "Error", JOptionPane.ERROR_MESSAGE);
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
			    int filaSeleccionada = tblUsuario.getSelectedRow();
			    
			    if (filaSeleccionada >= 0) {
			        try {
			            Object idObject = tblUsuario.getValueAt(filaSeleccionada, 0);
			            int idUsuario = Integer.parseInt(idObject.toString()); // Intenta la conversión

			            int confirmacion = JOptionPane.showConfirmDialog(ifrm_GestionarUsuario.this, "¿Está seguro de que desea eliminar este Usuario?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

			            if (confirmacion == JOptionPane.YES_OPTION) {
			                usuarioController.eliminarUsuario(idUsuario); // Llama al método del controlador para eliminar el alumno
			                mostrarTabla();
			            }
			        } catch (NumberFormatException ex) {
			            JOptionPane.showMessageDialog(ifrm_GestionarUsuario.this, "Error de conversión: El valor no es un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
			        }
			    } else {
			        JOptionPane.showMessageDialog(ifrm_GestionarUsuario.this, "Por favor, seleccione un Usuario para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    }   
			}
		});
		
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ReporteController controller = new ReporteController();
	        	 List<Reporte> listarReporte = controller.listarReporte();
	        	 ReporteExcel.reporteuser();
			}
		});
	 
	}
	private void mostrarTabla() {
	    modelo.setRowCount(0); // Limpiar la tabla antes de agregar datos
	    List<Usuario> listarusuario = usuarioController.listarUsuarios();
	    
	    for (Usuario usuario : listarusuario) {	        
	        Object[] fila = {
	        		
	        		usuario.getId(),
	        		usuario.getNombre(),
	        		usuario.getCorreo(),
	        		usuario.getClave()

	        };
	        modelo.addRow(fila);
	    }
	}
	
	private void limpiar() {
		
		txtNombre.setText("");
		txtCorreo.setText("");
		txtClave.setText("");
		txtConfirmarClave.setText("");
		
	}
	
}
