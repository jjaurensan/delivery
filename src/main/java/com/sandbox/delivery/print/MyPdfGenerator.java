package com.sandbox.delivery.print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.sandbox.delivery.services.bo.DeliveryBO;

public class MyPdfGenerator {

	public static final String DEST = "results/pdf/hello_world.pdf";
	final Logger logger = LoggerFactory.getLogger(MyPdfGenerator.class);
	// 72 points = 1 inch = 25.4 mm

	private float convertMmtoPts(float measure) {		
		return measure * (72f / 25.4f);
	}

	public MyPdfGenerator(List<DeliveryBO> allDelivery) throws IOException {

		final float MARGIN = convertMmtoPts(10f);

		try (	PdfWriter writer = new PdfWriter(DEST);
				PdfDocument pdf = new PdfDocument(writer);
				Document document = new Document(pdf, PageSize.A4);) 
			{
			File file = new File(DEST);
			file.getParentFile().mkdirs();

			document.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);

			// Create a PdfFont
			PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
			PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

			// table
			Table tableHaut = new Table(UnitValue.createPercentArray(new float[] { 2, 3 })).useAllAvailableWidth();
			tableHaut.setFont(bold);
			tableHaut.addCell("AQUITAINE PAPETERIE\n" + "11 Rue Suffren\n" + "33083 BORDEAUX CEDEX");
			tableHaut.addCell("Numero Livraison : ");

			// Table
			Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 3, 4, 4, 1, 3 }))
					.useAllAvailableWidth();

			// insert data
			table.addHeaderCell("id").setFont(bold);
			table.addHeaderCell("Date").setFont(bold);
			table.addHeaderCell("Livreur").setFont(bold);
			table.addHeaderCell("Client").setFont(bold);
			table.addHeaderCell("Nb Colis").setFont(bold);
			table.addHeaderCell("Signature").setFont(bold);

			for (DeliveryBO delivery : allDelivery) {

				table.addCell(new Cell().add(new Paragraph(String.valueOf(delivery.getIdDelivery()))).setFont(font));
				table.addCell(new Cell().add(new Paragraph(delivery.getCreateDateDelivery().toString())).setFont(font));
				table.addCell(new Cell().add(new Paragraph(delivery.getCarrier().getName())).setFont(font));

				table.addCell(
						new Cell()
								.add(new Paragraph(delivery.getCustomer().getCustomerNumber() + "\n"
										+ delivery.getCustomer().getContactName() + "\n" + delivery.getCustomer()
												.getCustomerListDeliveryAddress().get(0).getStreetOne()))
								.setFont(font));

				table.addCell(
						new Cell().add(new Paragraph(String.valueOf(delivery.getNumberOfPackage()))).setFont(font));
				table.addCell(new Cell().add(new Paragraph("")).setFont(font));
			}
			// end data
			table.addCell(tableHaut);

			document.add(table);

		} catch (FileNotFoundException e) {
			logger.error("MyPDFGenerator", e);
		}

	}
}