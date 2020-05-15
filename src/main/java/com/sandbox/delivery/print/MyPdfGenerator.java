package com.sandbox.delivery.print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.sandbox.delivery.services.bo.AddressBO;
import com.sandbox.delivery.services.bo.DeliveryBO;

public class MyPdfGenerator {

	public static final String DESTPATH = "results/pdf/";
	final Logger logger = LoggerFactory.getLogger(MyPdfGenerator.class);
	// 72 points = 1 inch = 25.4 mm

	private float convertMmtoPts(float measure) {
		return measure * (72f / 25.4f);
	}

	public MyPdfGenerator(List<DeliveryBO> allDelivery) throws IOException {

		final float MARGIN = convertMmtoPts(10f);
		 final String DEST = DESTPATH+LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)+"_"+ allDelivery.get(0).getCarrier().getName()+".pdf";
		try (PdfWriter writer = new PdfWriter(DEST);
				PdfDocument pdf = new PdfDocument(writer);
				Document document = new Document(pdf, PageSize.A4);) {
			File file = new File(DEST);
			file.getParentFile().mkdirs();

			document.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);

			// Create a PdfFont
			PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
			PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
			
			//Header
			Table tableHaut = new Table(UnitValue.createPercentArray(new float[] { 2, 3 })).useAllAvailableWidth();
				tableHaut.setFont(bold);
				tableHaut.addCell("AQUITAINE PAPETERIE\n" + "11 Rue Suffren\n" + "33083 BORDEAUX CEDEX");
				tableHaut.addCell("Vous etes livré par : \n" + allDelivery.get(0).getCarrier().getName() + "\n"
						+ allDelivery.get(0).getCarrier().getStreetOne() + "\n"
						+ allDelivery.get(0).getCarrier().getStreetTwo() + "\n" + allDelivery.get(0).getCarrier().getCity()
						+ " " + allDelivery.get(0).getCarrier().getCity());

			// Main
			Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 5, 1, 3 })).useAllAvailableWidth();

			// insert data
			table.addHeaderCell("id\n" + "Date").setFont(bold);
			
			table.addHeaderCell("Client").setFont(bold);
			table.addHeaderCell("Nb Colis\nPoids").setFont(bold);
			table.addHeaderCell("Signature").setFont(bold);

			for (DeliveryBO delivery : allDelivery) {

				// ID and Date
				table.addCell(new Cell().add(new Paragraph(
						String.valueOf(delivery.getIdDelivery()) 
						+"\n" +
						delivery.getCreateDateDelivery().toString()
						)).setFont(font));

				// Customer and Address
				StringBuilder addressStringBuffer = new StringBuilder();
				AddressBO addressCurrent = delivery.getCustomer().getCustomerListDeliveryAddress().get(0);

				addressStringBuffer.append(delivery.getCustomer().getCustomerNumber() + " "
						+ delivery.getCustomer().getContactName() + "\n");
				addressStringBuffer.append(addressCurrent.getStreetOne() + "\n");
				if (!addressCurrent.getStreetTwo().isEmpty()) {
					addressStringBuffer.append(addressCurrent.getStreetTwo() + "\n");
				}
				if (!addressCurrent.getStreetThree().isEmpty()) {
					addressStringBuffer.append(addressCurrent.getStreetThree() + "\n");
				}
				addressStringBuffer.append(addressCurrent.getZipCode() + " " + addressCurrent.getCity() + "\n");

				table.addCell(new Cell().add(new Paragraph(addressStringBuffer.toString())).setFont(font));
				
				// Package and Weight

				table.addCell(new Cell()
						.add(new Paragraph(
								String.valueOf(delivery.getNumberOfPackage() + "\n" + delivery.getWeight() + " kg")))
						.setFont(font));
				
				// Signature
				table.addCell(new Cell().add(new Paragraph("")).setFont(font));
			}

			document.add(tableHaut);
			document.add(table.setFontSize(8));

		} catch (FileNotFoundException e) {
			logger.error("MyPDFGenerator", e);
		}

	}
}