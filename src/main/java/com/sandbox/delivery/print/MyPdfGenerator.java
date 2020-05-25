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

	//private static final String DATE_FILE_NAME = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
	private static final String DESTPATH = "results/pdf/";
	private static final String SOCIETY = "AQUITAINE PAPETERIE\n" + "11 Rue Suffren\n" + "33083 BORDEAUX CEDEX";

	final Logger logger = LoggerFactory.getLogger(MyPdfGenerator.class);

	private float convertMmtoPts(float measure) {
		// 72 points = 1 inch = 25.4 mm
		return measure * (72f / 25.4f);
	}
	
	public MyPdfGenerator(LocalDate dateDelivery,List<DeliveryBO> allDelivery) throws IOException {
		// Create a PdfFont
		PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

		final float MARGIN = convertMmtoPts(10f);

		final String DEST = DESTPATH + dateDelivery.toString() + "_" + allDelivery.get(0).getCarrier().getName() + ".pdf";
		try (PdfWriter writer = new PdfWriter(DEST);
				PdfDocument pdf = new PdfDocument(writer);
				Document document = new Document(pdf, PageSize.A4);) {
			File file = new File(DEST);
			file.getParentFile().mkdirs();

			document.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);

			// Header
			Table tableHaut = new Table(UnitValue.createPercentArray(new float[] { 2, 3 })).useAllAvailableWidth();
			tableHaut.setFont(bold);
			tableHaut.addCell(SOCIETY);
			tableHaut.addCell("Vous etes livré par : \n" + allDelivery.get(0).getCarrier().toString());

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
						String.valueOf(delivery.getIdDelivery()) + "\n" + delivery.getCreateDateDelivery().toString()))
						.setFont(font));

				// Customer and Address
				AddressBO addressCurrent = delivery.getCustomer().getCustomerListDeliveryAddress().get(0);

				table.addCell(new Cell().add(new Paragraph(addressCurrent.toString())).setFont(font));

				// Package and Weight

				table.addCell(new Cell()
						.add(new Paragraph(
								String.valueOf(delivery.getNumberOfPackage() + "\n" + delivery.getWeight() + " kg")))
						.setFont(font));

				// Signature
				table.addCell(new Cell().add(new Paragraph("")).setFont(font));

				this.labelDelivery(delivery);
			}

			document.add(tableHaut);
			document.add(table.setFontSize(8));

		} catch (FileNotFoundException e) {
			logger.error("MyPDFGenerator", e);
		}

	}

	private void labelDelivery(DeliveryBO delivery) throws IOException {

		PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
		PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
		final float MARGIN = convertMmtoPts(0f);

		final String DEST = DESTPATH + "label/" + delivery.getCreateDateDelivery() + "_" + delivery.getIdDelivery() + ".pdf";

		try (PdfWriter writer = new PdfWriter(DEST);
				PdfDocument pdf = new PdfDocument(writer);
				Document document = new Document(pdf, PageSize.A4);) {
			File file = new File(DEST);
			file.getParentFile().mkdirs();

			document.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);

			Table tableGlobal = new Table(UnitValue.createPercentArray(new float[] { 1, 1 })).useAllAvailableWidth();

			for (int i = 0; i < delivery.getNumberOfPackage(); i++) {
				Table global = new Table(UnitValue.createPercentArray(new float[] { 1 })).useAllAvailableWidth();
				global.addCell(SOCIETY).setFont(bold);
				global.addCell(delivery.getCarrier().toString()).setFont(font);

				global.addCell(new Paragraph("Delivery : " + delivery.getIdDelivery() + " - Date : "
						+ delivery.getCreateDateDelivery().toString()));

				// Customer and Address
				AddressBO addressCurrent;
				if (delivery.getAddress() == null) {
					addressCurrent = delivery.getCustomer().getCustomerListDeliveryAddress().get(0);
				} else {
					addressCurrent = delivery.getAddress();
				}

				global.addCell(delivery.getCustomer().getContactName() + "\n" + addressCurrent.toString())
						.setFont(font);
				global.addCell("Colis " + (i + 1) + "/" + delivery.getNumberOfPackage()).setFont(font);

/*			//test QRcode
					BarcodeQRCode barcodeQRCode = new BarcodeQRCode(addressCurrent.toString());
					Image imageQR = new Image(barcodeQRCode.createFormXObject(pdf));
					global.addCell(new Cell().add(imageQR));
			//End Test
*/			
				tableGlobal.addCell(new Cell().add(global).setHeight(convertMmtoPts((float) 146)));
			}
			document.add(tableGlobal);
		} catch (FileNotFoundException e) {
			logger.error("label", e);
		}

	}
}