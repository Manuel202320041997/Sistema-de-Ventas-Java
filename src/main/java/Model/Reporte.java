package Model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfWriter;
	
import Dao.Conexion;

public class Reporte {

	
	public void ReportesClientes() {
		
		Document documento = new Document();
		documento.setPageSize(new Rectangle(710, documento.getPageSize().getHeight()));
		try {
			String ruta = System.getProperty("user.home");
			PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reportes_Clientes.pdf"));
			Image header = Image.getInstance("src/main/resources/Img/header2.png");
			header.scaleToFit(730,980);
			header.setAlignment(Chunk.ALIGN_CENTER);
				
			//Formato Text
			
			Paragraph parrafo = new Paragraph();
			parrafo.setAlignment(Paragraph.ALIGN_CENTER);
			parrafo.setFont(FontFactory.getFont("Arial", 13, Font.ITALIC,BaseColor.DARK_GRAY));
			parrafo.add("Reporte generado por TGestiona © G.T.C  \n\n\n\n\n\n");
			parrafo.setFont(FontFactory.getFont("Bahnschrift SemiBold", 28, Font.BOLD,BaseColor.BLACK));
			parrafo.add("Reporte de Clientes \n\n\n");
			
			documento.open();
			
			documento.add(header);
			documento.add(parrafo);
			
			float[] columnsWidths = {3, 5, 5, 10, 7};
			PdfPTable tabla = new PdfPTable(columnsWidths);
			
			tabla.addCell("Codigo");
			tabla.addCell("Nombre");
			tabla.addCell("DNI");
			tabla.addCell("Correo");
			tabla.addCell("Telefono");
			
			try {
				Connection cn = Conexion.obtenerConexion();
				PreparedStatement pst = cn.prepareStatement(
			"SELECT id, nombre AS nombres, dni, correo, telefono FROM cliente"
						);
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					do {
						tabla.addCell(rs.getString(1));
						tabla.addCell(rs.getString(2));
						tabla.addCell(rs.getString(3));
						tabla.addCell(rs.getString(4));
						tabla.addCell(rs.getString(5));
						
						
					} while (rs.next());
					documento.add(tabla);
					
					JOptionPane.showMessageDialog(null, "Reporte creado exitosamente");
					
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("Error en " + e);
			}
			documento.close();
			
		} catch (DocumentException | IOException e) {
			// TODO: handle exception
			System.out.println("Error en: " +e);
		}
		//documento.close();
	}
	
public void ReportesProductos() {

		
		Document documento = new Document();
		
		documento.setPageSize(new Rectangle(830, documento.getPageSize().getHeight()));
		
		try {
			String ruta = System.getProperty("user.home");
			PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/ReportesProductos.pdf"));
			
			//FooterPageEvent event = new FooterPageEvent();
			//writer.setPageEvent(event);
			
			Image header = Image.getInstance("src/main/resources/Img/header2.png");
			header.scaleToFit(840,1000);
			header.setAlignment(Chunk.ALIGN_CENTER);
				
			//Formato Text
			
			Paragraph parrafo = new Paragraph();
			parrafo.setAlignment(Paragraph.ALIGN_CENTER);
			parrafo.setFont(FontFactory.getFont("Arial", 13, Font.ITALIC,BaseColor.DARK_GRAY));
			parrafo.add("Reporte generado por TGestiona © S.A.C \n\n\n\n\n\n");
			parrafo.setFont(FontFactory.getFont("Bahnschrift SemiBold", 28, Font.BOLD,BaseColor.BLACK));
			parrafo.add("Reporte de Productos \n\n\n");
			
			documento.open();
			
			documento.add(header);
			documento.add(parrafo);
			
			float[] columnsWidths = {3, 8, 3, 6, 3, 5, 3};

			PdfPTable tabla = new PdfPTable(columnsWidths);
			PdfPCell cell = new PdfPCell();
			cell.setBorderWidth(2f);
			
			
			tabla.addCell("Codigo");
			tabla.addCell("Nombre");
			tabla.addCell("Stock");
			tabla.addCell("P. Compra");
			tabla.addCell("P. Venta");
			tabla.addCell("Id Categoria");
			tabla.addCell("Num S");

			
			try {
				Connection cn = Conexion.obtenerConexion();
				PreparedStatement pst = cn.prepareStatement("SELECT * FROM producto");
				ResultSet rs = pst.executeQuery();
				if(rs.next()) {
					do {
						tabla.addCell(rs.getString(1));
						tabla.addCell(rs.getString(2));
						tabla.addCell(rs.getString(3));
						tabla.addCell(rs.getString(4));
						tabla.addCell(rs.getString(5));
						tabla.addCell(rs.getString(6));
						tabla.addCell(rs.getString(7));
									
			
					} while (rs.next());
					documento.add(tabla);
					
					JOptionPane.showMessageDialog(null, "Reporte creado exitosamente");
					
				}
				
			} catch (SQLException e) {
				// TODO: handle exception
				System.out.println("Error en " + e);
			}
			documento.close();
			
		} catch (DocumentException | IOException e) {
			// TODO: handle exception
			System.out.println("Error en: " +e);
		}
		//documento.close();
	}

public class FooterPageEvent implements PdfPageEvent {
    public void onOpenDocument(PdfWriter writer, Document document) {}

    public void onStartPage(PdfWriter writer, Document document) {}

    public void onEndPage(PdfWriter writer, Document document) {
        Phrase footer = new Phrase("Reporte generado por TGestiona © S.A.C", FontFactory.getFont("Arial", 15, Font.ITALIC));
        float x = (document.left() + document.right()) / 2;
        float y = document.bottom() - 10;
        ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, footer, x, y, 0);
    }
    
    

	@Override
	public void onChapter(PdfWriter arg0, Document arg1, float arg2, Paragraph arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onChapterEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCloseDocument(PdfWriter arg0, Document arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onGenericTag(PdfWriter arg0, Document arg1, Rectangle arg2, String arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onParagraph(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onParagraphEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSection(PdfWriter arg0, Document arg1, float arg2, int arg3, Paragraph arg4) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSectionEnd(PdfWriter arg0, Document arg1, float arg2) {
		// TODO Auto-generated method stub
		
	}

}


public void ReportesCategorias() {

	
	Document documento = new Document();
	
	
	documento.setPageSize(new Rectangle(830, documento.getPageSize().getHeight()));
	
	try {
		String ruta = System.getProperty("user.home");
		PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/ReportesCategorias.pdf"));
		
		//FooterPageEvent event = new FooterPageEvent();
		//.setPageEvent(event);
		
		
		Image header = Image.getInstance("src/main/resources/Img/header2.png");
		header.scaleToFit(840,1000);
		header.setAlignment(Chunk.ALIGN_CENTER);
			
		//Formato Text
		
		Paragraph parrafo = new Paragraph();
		parrafo.setAlignment(Paragraph.ALIGN_CENTER);
		parrafo.setFont(FontFactory.getFont("Arial", 13, Font.ITALIC,BaseColor.DARK_GRAY));
		parrafo.add("Reporte generado por TGestiona © S.A.C \n\n\n\n\n\n");
		parrafo.setFont(FontFactory.getFont("Bahnschrift SemiBold", 28, Font.BOLD,BaseColor.BLACK));
		parrafo.add("Reporte de Categorias \n\n\n");
		
		documento.open();
		
		documento.add(header);
		documento.add(parrafo);
		
		PdfPCell cell = new PdfPCell();
		cell.setBorderWidth(2f);
		float[] columnsWidths = {3, 5, 3};

		PdfPTable tabla = new PdfPTable(columnsWidths);
		
		
		tabla.addCell("Codigo");
		tabla.addCell("Descripcion");
		tabla.addCell("Estado");
				
		
		try {
			Connection cn = Conexion.obtenerConexion();
			PreparedStatement pst = cn.prepareStatement(
				"SELECT id, descripcion, estado FROM categoria" 
					);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				do {
					tabla.addCell(rs.getString(1));
					tabla.addCell(rs.getString(2));
					tabla.addCell(rs.getString(3));
					
					
				} while (rs.next());
				documento.add(tabla);
				
				JOptionPane.showMessageDialog(null, "Reporte creado exitosamente");
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error en " + e);
		}
		documento.close();
		
	} catch (DocumentException | IOException e) {
		// TODO: handle exception
		System.out.println("Error en: " +e);
	}
	//documento.close();
}

public void ReportesVentas() {

	
	Document documento = new Document();
	
	documento.setPageSize(new Rectangle(830, documento.getPageSize().getHeight()));
	
	try {
		String ruta = System.getProperty("user.home");
		PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/ReportesCategorias.pdf"));
		
		//FooterPageEvent event = new FooterPageEvent();
		//writer.setPageEvent(event);
		
		Image header = Image.getInstance("src/main/resources/Img/header2.png");
		header.scaleToFit(840,1000);
		header.setAlignment(Chunk.ALIGN_CENTER);
			
		//Formato Text
		
		Paragraph parrafo = new Paragraph();
		parrafo.setAlignment(Paragraph.ALIGN_CENTER);
		parrafo.setFont(FontFactory.getFont("Arial", 13, Font.ITALIC,BaseColor.DARK_GRAY));
		parrafo.add("Reporte generado por TGestiona © S.A.C \n\n\n\n\n\n");
		parrafo.setFont(FontFactory.getFont("Bahnschrift SemiBold", 28, Font.BOLD,BaseColor.BLACK));
		parrafo.add("Reporte de Categorias \n\n\n");
		
		documento.open();
		
		documento.add(header);
		documento.add(parrafo);
		
		PdfPCell cell = new PdfPCell();
		cell.setBorderWidth(2f);
		float[] columnsWidths = {3, 5, 3};

		PdfPTable tabla = new PdfPTable(columnsWidths);
		
		
		tabla.addCell("Codigo");
		tabla.addCell("Descripcion");
		tabla.addCell("Estado");
				
		
		try {
			Connection cn = Conexion.obtenerConexion();
			PreparedStatement pst = cn.prepareStatement(
				"SELECT id, descripcion, estado FROM categoria" 
					);
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				do {
					tabla.addCell(rs.getString(1));
					tabla.addCell(rs.getString(2));
					tabla.addCell(rs.getString(3));
					
					
				} while (rs.next());
				documento.add(tabla);
				
				JOptionPane.showMessageDialog(null, "Reporte creado exitosamente");
				
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Error en " + e);
		}
		documento.close();
		
	} catch (DocumentException | IOException e) {
		// TODO: handle exception
		System.out.println("Error en: " +e);
	}
	//documento.close();
}



}



   
