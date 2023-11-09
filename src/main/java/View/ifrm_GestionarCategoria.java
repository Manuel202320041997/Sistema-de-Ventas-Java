package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Controller.CategoriaController;
import Controller.ReporteController;
import Model.Categoria;
import Model.Reporte;
import Model.ReporteExcel;

import java.awt.GridLayout;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ifrm_GestionarCategoria extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTable tblCategoria;
	private JTextField txtCategoria;	
	DefaultTableModel modelo;
	private CategoriaController categoriaController;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ifrm_GestionarCategoria frame = new ifrm_GestionarCategoria();
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
	public ifrm_GestionarCategoria() {
		setBounds(-5, -5, 910, 500);
		setClosable(true); //cerrar frame
		setIconifiable(false); // minimizar frame
		getContentPane().setBackground(new Color(67,16,58));
		getContentPane().setLayout(null);
		
		categoriaController = new CategoriaController();
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 89, 249, 286);
		panel.setBackground(new Color(255, 255, 255));
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion de Categoria");
		lblNewLabel.setBounds(10, 11, 248, 39);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Descripcion:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(0, 101, 84, 26);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblNewLabel_1);
		
		txtCategoria = new JTextField();
		txtCategoria.setBounds(94, 106, 132, 20);
		txtCategoria.setColumns(10);
		panel.add(txtCategoria);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(new Color(255, 255, 255));
		btnGuardar.setBackground(new Color(0, 146, 1));
		btnGuardar.setBounds(18, 167, 146, 23);
		btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBackground(new Color(247, 0, 47));
		btnEliminar.setBounds(20, 242, 144, 23);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.add(btnEliminar);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnLimpiar.setBackground(new Color(254, 202, 2));
		btnLimpiar.setBounds(20, 208, 144, 23);
		panel.add(btnLimpiar);
		
		ImageIcon iconoExcel = new ImageIcon(getClass().getResource("/Img/sobresalir.png"));
		JButton btnExcel = new JButton("");
		btnExcel.setIcon(iconoExcel);
		
		btnExcel.setBackground(new Color(128, 255, 128));
		btnExcel.setBounds(174, 197, 65, 36);
		panel.add(btnExcel);
		
		
		modelo = new DefaultTableModel(
	            new Object[][] {},
	            new String[] {
	                "ID", "Descripcion"
	            }
	        );
	
		tblCategoria = new JTable(modelo);
		tblCategoria.getColumnModel().getColumn(0).setPreferredWidth(20); // ID
		tblCategoria.getColumnModel().getColumn(1).setPreferredWidth(100); // DNI

	 mostrarTabla();
	 // Crear un JScrollPane y agregar la JTable a él
	 JScrollPane scrollPane = new JScrollPane(tblCategoria);
	 scrollPane.setBounds(269, 11, 615, 448);
	 getContentPane().add(scrollPane);
	 
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 Categoria categoria = new Categoria();
						 categoria.setDescripcion(txtCategoria.getText());						 

			            categoriaController.registrarCategoria(categoria);
			            mostrarTabla();
			            limpiar();
			            JOptionPane.showMessageDialog(ifrm_GestionarCategoria.this, "Categoria agregada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
				       
				    } catch (Exception ex) {
				        JOptionPane.showMessageDialog(ifrm_GestionarCategoria.this, "Ocurrió un error al registrar la Categoria", "Error", JOptionPane.ERROR_MESSAGE);
				    }
				}		
		});
		
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		
		btnExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 ReporteController controller = new ReporteController();
	        	 List<Reporte> listarReporte = controller.listarReporte();
	        	 ReporteExcel.reportecat();

			}
		});
		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tblCategoria.getSelectedRow();
				
				if(filaSeleccionada >= 0) {
					try {
						Object idObject = tblCategoria.getValueAt(filaSeleccionada, 0); // Almacena el valor de la primera columna de la tabla(ID) y la guarda en IDObject, con esto sabe el id exacto de la categoria 
						
						int idAlumno = Integer.parseInt(idObject.toString());
						int confirmacion = JOptionPane.showConfirmDialog(ifrm_GestionarCategoria.this, "Seguro que deseas eliminar esta Categoria?", "¿Seguro?",JOptionPane.YES_NO_OPTION);
						
						if(confirmacion == JOptionPane.YES_OPTION) {
							categoriaController.eliminarCategoria(idAlumno);
							mostrarTabla();
						}
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(ifrm_GestionarCategoria.this, "Error de conversión: El valor no es un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				else {
			        JOptionPane.showMessageDialog(ifrm_GestionarCategoria.this, "Por favor, seleccione un alumno para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
			    }  
				
			}
		});
	 
	 }
	
	
	
	private void mostrarTabla() {
	    modelo.setRowCount(0); // Limpiar la tabla antes de agregar datos	    
	    CategoriaController controller = new CategoriaController();
	    List<Categoria> listarCategoria = controller.listarCategoria();
	    
	    for (Categoria categoria : listarCategoria) {
	        Object[] fila = {
	        		categoria.getId(),
	        		categoria.getDescripcion()

	        };
	        modelo.addRow(fila);
	    }
	}
	
	private void limpiar() {
		txtCategoria.setText("");
	}
	
	
}
