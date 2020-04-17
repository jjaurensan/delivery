package com.sandbox.delivery.print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.sandbox.delivery.entities.Delivery;

public class MyPdfGenerator {

	public static final String DEST = "results/pdf/hello_world.pdf";

	// 72 points = 1 inch = 25.4 mm

	private float convertMmtoPts(float measure) {
		float result = measure * (72f / 25.4f);
		return result;
	}

	public MyPdfGenerator(List<Delivery> allDelivery) throws IOException {

		final float MARGIN = convertMmtoPts(10f);

		try {
			File file = new File(DEST);
			file.getParentFile().mkdirs();
			// Initialize PDF writer
			PdfWriter writer = new PdfWriter(DEST);

			// Initialize PDF document
			PdfDocument pdf = new PdfDocument(writer);

			// Initialize document
			Document document = new Document(pdf, PageSize.A4);

			document.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);

			// Create a PdfFont
			PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
			PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
			
			//table
			Table tableHaut = new Table(UnitValue.createPercentArray(new float[] { 2 , 3})).useAllAvailableWidth();
			tableHaut.setFont(bold);
			tableHaut.addCell("AQUITAINE PAPETERIE\n"
					+"11 Rue Suffren\n"
					+"33083 BORDEAUX CEDEX");
			tableHaut.addCell("Numero Livraison : ");
			
			// Table
			Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 3, 4, 4, 1, 3 })).useAllAvailableWidth();

			// insert data
			table.addHeaderCell("id").setFont(bold);
			table.addHeaderCell("Date").setFont(bold);
			table.addHeaderCell("Livreur").setFont(bold);
			table.addHeaderCell("Client").setFont(bold);
			table.addHeaderCell("Nb Colis").setFont(bold);
			table.addHeaderCell("Signature").setFont(bold);
			

			for (Delivery delivery : allDelivery) {

				table.addCell(new Cell().add(new Paragraph(String.valueOf(delivery.getIdDelivery()))).setFont(font));
				table.addCell(new Cell().add(new Paragraph(delivery.getCreateDateDelivery().toString())).setFont(font));				
				table.addCell(new Cell().add(new Paragraph(delivery.getCarrier().getName())).setFont(font));
				
				table.addCell(new Cell().add(
						new Paragraph(
								delivery.getCustomer().getCustomerNumber() + "\n" 
								+ delivery.getCustomer().getContactName() + "\n"
								+ delivery.getCustomer().getCustomerListDeliveryAddress().get(0).getStreetOne()
								)).setFont(font));
				
				table.addCell(
						new Cell().add(new Paragraph(String.valueOf(delivery.getNumberOfPackage()))).setFont(font));
				table.addCell(new Cell().add(new Paragraph("")).setFont(font));
			}
			// end data
			table.addCell(tableHaut);
			

			document.add(table);
			document.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}