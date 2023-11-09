package View;

import Model.Categoria;
import Model.Reporte;
import Model.Usuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.CategoriaController;
import Controller.ReporteController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import DaoImpl.ReporteDaoImpl;

public class frm_Inicio extends JFrame {
    private static Usuario usuariovalidado;
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frm_Inicio frame = new frm_Inicio(usuariovalidado);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public frm_Inicio(Usuario usuariovalidado) {
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        this.usuariovalidado = usuariovalidado;
        String nombreUsuario = usuariovalidado.getNombre();
        setUndecorated(true);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(67,16,58));
        panel.setBounds(0, 0, 900, 81);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("SISTEMA DE VENTAS TGestiona");
        //lblNewLabel.setIcon(new ImageIcon(frm_Inicio.class.getResource("/Img/logotgestionaicono.jpg")));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lblNewLabel.setBounds(29, 25, 438, 26);
        panel.add(lblNewLabel);

        ImageIcon iconoUsuarioLabel = new ImageIcon(getClass().getResource("/Img/user2.png"));
        JLabel lblNewLabel_1 = new JLabel("Usuario: " + nombreUsuario,iconoUsuarioLabel, JLabel.CENTER);
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_1.setBounds(692, 20, 198, 45);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setBounds(871, 67, -225, -62);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("New label");
        lblNewLabel_3.setIcon(new ImageIcon(frm_Inicio.class.getResource("/Img/fondoheader2.jpg")));
        lblNewLabel_3.setBounds(0, 0, 910, 81);
        panel.add(lblNewLabel_3);

        JMenuBar menuBar = new JMenuBar();
        
        // JMenu
        JMenu menuUsuario = new JMenu("Usuario");    
        JMenu menuProducto = new JMenu("Producto");
        JMenu menuCliente = new JMenu("Cliente");
        JMenu menuCategoria = new JMenu("Categoria");
        JMenu menuFacturas = new JMenu("Facturas");
        JMenu menuReportes = new JMenu("Reportes");
        // JMenu menuHistorial = new JMenu("Historial");
        JMenu menuCerrarSesion = new JMenu("Cerrar Sesion");
        
        // Iconos JMenu        
        ImageIcon iconoUsuario = new ImageIcon(getClass().getResource("/Img/usuario.png"));
        menuUsuario.setIcon(iconoUsuario);
        
        ImageIcon iconoProducto = new ImageIcon(getClass().getResource("/Img/producto.png"));
        menuProducto.setIcon(iconoProducto);
        
        ImageIcon iconoCliente = new ImageIcon(getClass().getResource("/Img/cliente.png"));
        menuCliente.setIcon(iconoCliente);
        
        ImageIcon iconoCategoria = new ImageIcon(getClass().getResource("/Img/categorias.png"));
        menuCategoria.setIcon(iconoCategoria);
        
        ImageIcon iconoFactura = new ImageIcon(getClass().getResource("/Img/carrito.png"));
        menuFacturas.setIcon(iconoFactura);
        
        ImageIcon iconoReporte = new ImageIcon(getClass().getResource("/Img/reportes.png"));
        menuReportes.setIcon(iconoReporte);
        
        // ImageIcon iconoHistorial = new ImageIcon(getClass().getResource("/Img/historial1.png"));
        // menuHistorial.setIcon(iconoHistorial);
        
        ImageIcon iconoCerrarSesion = new ImageIcon(getClass().getResource("/Img/cerrar-sesion.png"));
        menuCerrarSesion.setIcon(iconoCerrarSesion);        
        
        // JMenuItem
        JMenuItem submenuGestionarUsuario = new JMenuItem("Gestionar Usuario");

        JMenuItem submenuGestionarProductos = new JMenuItem("Gestionar Productos");
        JMenuItem submenuActualizarStock = new JMenuItem("Actualizar Stock");

        JMenuItem submenuGestionarCliente = new JMenuItem("Gestionar Cliente");
        
        JMenuItem submenuGestionarCategoria = new JMenuItem("Gestionar Categoria");
                
        JMenuItem submenuNuevaVenta = new JMenuItem("Nueva Venta");
        JMenuItem submenuDetalleVenta = new JMenuItem("Detalle Venta");
        
        JMenuItem submenuReporteClientes = new JMenuItem("Reporte Clientes");
      
        JMenuItem submenuReporteCategorias = new JMenuItem("Reporte Categorias");
        
        JMenuItem submenuReporteProductos = new JMenuItem("Reporte Productos");
        
        //JMenuItem submenuReporteVentas = new JMenuItem("Reporte Productos");
        
        
        JMenuItem submenuVerHistorial = new JMenuItem("Ver Historial");
        
        JMenuItem submenuCerrarSesion = new JMenuItem("Cerrar Sesion");
        
        // Iconos JMenuItems
        ImageIcon iconosubmenuGestionarUsuario = new ImageIcon(getClass().getResource("/Img/anadir.png"));
        submenuGestionarUsuario.setIcon(iconosubmenuGestionarUsuario);
        
        ImageIcon iconosubmenuGestionarProductos = new ImageIcon(getClass().getResource("/Img/nuevo-producto.png"));
        submenuGestionarProductos.setIcon(iconosubmenuGestionarProductos);
        
        ImageIcon iconosubmenuActualizarStock = new ImageIcon(getClass().getResource("/Img/stock.png"));
        submenuActualizarStock.setIcon(iconosubmenuActualizarStock);

        ImageIcon iconosubmenuGestionarCliente = new ImageIcon(getClass().getResource("/Img/clientesgestor.png"));
        submenuGestionarCliente.setIcon(iconosubmenuGestionarCliente);
        
        ImageIcon iconosubmenuGestionarCategoria = new ImageIcon(getClass().getResource("/Img/categoriagestor.png"));
        submenuGestionarCategoria.setIcon(iconosubmenuGestionarCategoria);
        
        ImageIcon iconosubmenuNuevaVenta = new ImageIcon(getClass().getResource("/Img/ventagestor.png"));
        submenuNuevaVenta.setIcon(iconosubmenuNuevaVenta);
        
        ImageIcon iconosubmenuDetalleVenta = new ImageIcon(getClass().getResource("/Img/detalleventa.png"));
        submenuDetalleVenta.setIcon(iconosubmenuDetalleVenta);
        
        ImageIcon iconosubmenuReporteClientes = new ImageIcon(getClass().getResource("/Img/reportecliente.png"));
        submenuReporteClientes.setIcon(iconosubmenuReporteClientes);
        
        ImageIcon iconosubmenuReporteCategorias = new ImageIcon(getClass().getResource("/Img/reportecategoria.png"));
        submenuReporteCategorias.setIcon(iconosubmenuReporteCategorias);
        
        ImageIcon iconosubmenuReporteProductos = new ImageIcon(getClass().getResource("/Img/reprod.png"));
        submenuReporteProductos.setIcon(iconosubmenuReporteProductos);
        
        //ImageIcon iconosubmenuReporteVentas = new ImageIcon(getClass().getResource("/Img/reprod.png"));
        //submenuReporteVentas.setIcon(iconosubmenuReporteVentas);
        
        ImageIcon iconosubmenuVerHistorial = new ImageIcon(getClass().getResource("/Img/historialmenu.png"));
        submenuVerHistorial.setIcon(iconosubmenuVerHistorial);
        
        ImageIcon iconosubmenuCerrarSesion = new ImageIcon(getClass().getResource("/Img/salida-de-emergencia.png"));
        submenuCerrarSesion.setIcon(iconosubmenuCerrarSesion);
        
        /*ImageIcon iconosubmenuNuevoCliente = new ImageIcon(getClass().getResource("/Img/nuevo-cliente.png"));
        submenuNuevoCliente.setIcon(iconosubmenuNuevoCliente);*/        
        
        // Agregar los menús al JMenuBar
        menuBar.add(menuUsuario);
        menuBar.add(menuProducto);
        menuBar.add(menuCliente);
        menuBar.add(menuCategoria);
        menuBar.add(menuFacturas);
        menuBar.add(menuReportes);
       //  menuBar.add(menuHistorial);
        menuBar.add(menuCerrarSesion);
        
        // Agregamos submenus
        menuUsuario.add(submenuGestionarUsuario);

        menuProducto.add(submenuGestionarProductos);
        menuProducto.add(submenuActualizarStock);

        menuCliente.add(submenuGestionarCliente);

        menuCategoria.add(submenuGestionarCategoria);
        
        menuFacturas.add(submenuNuevaVenta);
        menuFacturas.add(submenuDetalleVenta);
        
        menuReportes.add(submenuReporteClientes);
        menuReportes.add(submenuReporteCategorias);
        menuReportes.add(submenuReporteProductos);
      
        
       // menuHistorial.add(submenuVerHistorial);
        
        menuCerrarSesion.add(submenuCerrarSesion);

        // Establecer las coordenadas para el JMenuBar
        menuBar.setBounds(0, 81, 1280, 30);

        // Agregar el JMenuBar al contenedor
        getContentPane().add(menuBar);
        
        JDesktopPane desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(67,16,58));
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;
        desktopPane.setBounds(0, 110, ancho, alto);
        getContentPane().add(desktopPane);
        
        submenuGestionarUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {        	
        		ifrm_GestionarUsuario ifrm_gestionarusuario =  new ifrm_GestionarUsuario();
        		desktopPane.add(ifrm_gestionarusuario);
        		ifrm_gestionarusuario.setVisible(true);
        	}
        });
        
        submenuGestionarProductos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {        	
        		ifrm_GestionarProducto ifrm_gestionarproducto =  new ifrm_GestionarProducto();
        		desktopPane.add(ifrm_gestionarproducto);
        		ifrm_gestionarproducto.setVisible(true);
        	}
        });
        
        submenuActualizarStock.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {        	
        		ifrm_ActualizarStock ifrm_actualizarStock =  new ifrm_ActualizarStock();
        		desktopPane.add(ifrm_actualizarStock);
        		ifrm_actualizarStock.setVisible(true);
        	}
        });
        
        submenuGestionarCategoria.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ifrm_GestionarCategoria ifrm_gestionarcategoria =  new ifrm_GestionarCategoria();
        		desktopPane.add(ifrm_gestionarcategoria);
        		ifrm_gestionarcategoria.setVisible(true);
        	}
        });
        
        submenuGestionarCategoria.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {        	
            		ifrm_GestionarCategoria ifrm_gestionarcategoria =  new ifrm_GestionarCategoria();
            		desktopPane.add(ifrm_gestionarcategoria);
            		ifrm_gestionarcategoria.setVisible(true);
            	}
        	
        });
        
        submenuGestionarCliente.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {        	
        		ifrm_GestionarCliente ifrm_gestionarcliente =  new ifrm_GestionarCliente();
            		desktopPane.add(ifrm_gestionarcliente);
            		ifrm_gestionarcliente.setVisible(true);
            	}
        	
        });
        
        submenuNuevaVenta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ifrm_Factura ifrm_factura =  new ifrm_Factura();
        		
        		desktopPane.add(ifrm_factura);
        		ifrm_factura.setVisible(true);
        	}
        });
        
        submenuDetalleVenta.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		ifrm_DetalleVenta ifrm_detalleventa =  new ifrm_DetalleVenta();
        		
        		desktopPane.add(ifrm_detalleventa);
        		ifrm_detalleventa.setVisible(true);
        	}
        });
        
        submenuReporteClientes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	    ReporteController controller = new ReporteController();
        	    List<Reporte> listarReporte = controller.listarReporte();
        		Reporte reporte = new Reporte();
        		reporte.ReportesClientes();
        	}
        });
        
        submenuReporteCategorias.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	    ReporteController controller = new ReporteController();
        	    List<Reporte> listarReporte = controller.listarReporte();
        		Reporte reporte = new Reporte();
        		reporte.ReportesCategorias();
        	}
        });
        
        
        submenuReporteProductos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	    ReporteController controller = new ReporteController();
        	    List<Reporte> listarReporte = controller.listarReporte();
        		Reporte reporte = new Reporte();
        		reporte.ReportesProductos();
        	

        	}
        });
        
       
        
        
        submenuCerrarSesion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);

        		if (confirm == JOptionPane.YES_OPTION) {
        		    
        		    System.exit(0);
        		}
        	}
        });
        
    }
}
