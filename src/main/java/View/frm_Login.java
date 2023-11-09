package View;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.UsuarioController;
import Model.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class frm_Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frm_Login frame = new frm_Login();
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
	public frm_Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setTitle("Sistema de Ventas");
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		setResizable(false);
		setUndecorated(true);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);


		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(67,16,58));
		panel.setBounds(0, -10, 274, 510);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblSistemaDeVentas = new JLabel("SISTEMA DE VENTAS");
		lblSistemaDeVentas.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSistemaDeVentas.setForeground(new Color(255, 255, 255));
		lblSistemaDeVentas.setBounds(24, 96, 240, 40);
		panel.add(lblSistemaDeVentas);
		
		JLabel lblSubtitulo = new JLabel();
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		Font font = new Font("Meiryo", Font.PLAIN, 22);
		lblSubtitulo.setForeground(Color.WHITE);
		lblSubtitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSubtitulo.setBounds(24, 346, 228, 40);
		lblSubtitulo.setFont(font);
		lblSubtitulo.setText("Empresa TGestiona");
		panel.add(lblSubtitulo);
		
		JLabel lblProgramadorManuel_1 = new JLabel("");
		lblProgramadorManuel_1.setIcon(new ImageIcon(frm_Login.class.getResource("/Img/carrito1.png")));
		lblProgramadorManuel_1.setForeground(Color.WHITE);
		lblProgramadorManuel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblProgramadorManuel_1.setBounds(63, 213, 135, 122);
		panel.add(lblProgramadorManuel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(272, -10, 428, 524);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(frm_Login.class.getResource("/Img/user1.4.png")));
		lblNewLabel.setBounds(160, 68, 139, 137);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setIcon(new ImageIcon(frm_Login.class.getResource("/Img/user2.png")));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(80, 256, 106, 31);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setIcon(new ImageIcon(frm_Login.class.getResource("/Img/password.png")));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_1_1.setBounds(62, 329, 134, 31);
		panel_1.add(lblNewLabel_1_1);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtUsuario.setBounds(196, 259, 169, 31);
		panel_1.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(196, 332, 169, 31);
		panel_1.add(txtClave);
		
		JButton btnIngresar = new JButton("Iniciar Sesion");
		btnIngresar.setForeground(new Color(255, 255, 255));
		btnIngresar.setBackground(new Color(67,16,58));
		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 15));		
		btnIngresar.setBounds(79, 411, 285, 31);
		panel_1.add(btnIngresar);
		
		JButton btnCerrar = new JButton("");
		btnCerrar.setFocusable(false);
		btnCerrar.setFocusTraversalKeysEnabled(false);
		btnCerrar.setFocusPainted(false);		
		btnCerrar.setIcon(new ImageIcon(frm_Login.class.getResource("/Img/cerrar-sesion.png")));
		btnCerrar.setBorder(null);
		btnCerrar.setBackground(Color.WHITE);
		btnCerrar.setBounds(345, 24, 57, 40);
		panel_1.add(btnCerrar);
		
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UsuarioController controller = new UsuarioController();
				
				String usuario = txtUsuario.getText();
				String clave = txtClave.getText();
				
				Usuario usuariovalidado = controller.validarLogin(usuario, clave);
				
				if(usuariovalidado != null) {
					frm_Inicio form = new frm_Inicio(usuariovalidado);
										
					form.setVisible(true);
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Usuario y/o Clave incorrecta", "Aviso", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
	}
}
