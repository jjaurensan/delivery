package com.sandbox.delivery.print;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.barcodes.BarcodeQRCode;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.UnitValue;
import com.sandbox.delivery.services.bo.AddressBO;
import com.sandbox.delivery.services.bo.CarrierBO;
import com.sandbox.delivery.services.bo.DeliveryBO;

public class MyPdfGenerator {

	private static final String BREAK_LINE = "\n";
	private static final String FILE_EXTENSION = ".pdf";
	private static final String LABEL_CARRIER = "Transporteur";
	private static final String LABEL_SIGNATURE = "Signature";
	private static final String LABEL_WEIGHT = "Poids";
	private static final String LABEL_NUMBER_PACKAGE = "Nb Colis";
	private static final String LABEL_CUSTOMER = "Client";
	private static final String LABEL_DATE = "Date";
	private static final String LABEL_ID = "Id Liv.";
	private static final String DESTPATH = "results/pdf/";
	private static final String SOCIETY = "AQUITAINE PAPETERIE" + BREAK_LINE + "11 Rue Suffren" + BREAK_LINE
			+ "33083 BORDEAUX CEDEX";
	
	private static final String URL_LOGO = "./src/main/resources/templates/img/logo.jpg";
	private static final Logger LOGGER = LoggerFactory.getLogger(MyPdfGenerator.class);
	
	private PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
	private PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
	private PdfFont labelCarrier = PdfFontFactory.createFont(StandardFonts.HELVETICA);
	private final Image logo = new Image(ImageDataFactory.create(URL_LOGO));
	

	public MyPdfGenerator(LocalDate dateDelivery, List<DeliveryBO> allDelivery) throws IOException {
		// Create a PdfFont

		final float MARGIN = convertMmtoPts(10f);
		CarrierBO carrier = allDelivery.get(0).getCarrier();

		final String DEST = DESTPATH + dateDelivery.toString() + "_" + carrier.getName() + FILE_EXTENSION;

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
			tableHaut.addCell(LABEL_CARRIER + " : " + BREAK_LINE + carrier.toString());

			// Main
			Table table = new Table(UnitValue.createPercentArray(new float[] { 1, 5, 1, 3 })).useAllAvailableWidth();

			// insert data

			table.addHeaderCell(LABEL_ID + BREAK_LINE + LABEL_DATE).setFont(bold);
			table.addHeaderCell(LABEL_CUSTOMER).setFont(bold);
			table.addHeaderCell(LABEL_NUMBER_PACKAGE + BREAK_LINE + LABEL_WEIGHT).setFont(bold);
			table.addHeaderCell(LABEL_SIGNATURE).setFont(bold);

			for (DeliveryBO delivery : allDelivery) {

				// ID and Date
				table.addCell(new Cell().add(new Paragraph(String.valueOf(delivery.getIdDelivery()) + BREAK_LINE
						+ delivery.getCreateDateDelivery().toString())).setFont(font));

				// Customer and Address
				AddressBO addressCurrent = delivery.getAddress();
				table.addCell(new Cell()
						.add(new Paragraph(
								delivery.getCustomer().getContactName() + BREAK_LINE + addressCurrent.toString()))
						.setFont(font));

				// Package and Weight
				table.addCell(new Cell()
						.add(new Paragraph(String
								.valueOf(delivery.getNumberOfPackage() + BREAK_LINE + delivery.getWeight() + " kg")))
						.setFont(font));

				// Signature
				table.addCell(new Cell().add(new Paragraph("")).setFont(font));
			}

			document.add(tableHaut);
			document.add(table.setFontSize(8));

			// ---------------------------
			// LABEL GENERATION
			// ----------------------------
			document.setMargins(0, 0, 0, 0);

			for (DeliveryBO delivery : allDelivery) {

				document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
				Table tableGlobal = new Table(UnitValue.createPercentArray(new float[] { 1, 1 }))
						.useAllAvailableWidth();

				for (int i = 0; i < delivery.getNumberOfPackage(); i++) {
					Table global = new Table(UnitValue.createPercentArray(new float[] { 1 })).useAllAvailableWidth();
					
					global.addCell(logo.setAutoScale(true));
					global.addCell(new Paragraph(SOCIETY)).setFont(bold);
					global.addCell(new Paragraph(LABEL_DATE + " : " + delivery.getCreateDateDelivery().toString()));

					// Customer and Address
					AddressBO addressCurrent;
					if (delivery.getAddress() == null) {
						addressCurrent = delivery.getCustomer().getCustomerListDeliveryAddress().get(0);
					} else {
						addressCurrent = delivery.getAddress();
					}

					global.addCell(delivery.getCustomer().getContactName() + BREAK_LINE + addressCurrent.toString())
							.setFont(font);
					global.addCell(LABEL_ID+" : " + delivery.getIdDelivery() + " - "+LABEL_NUMBER_PACKAGE +" " + (i + 1) + "/"
							+ delivery.getNumberOfPackage()).setFont(font);

					// QRcode
					BarcodeQRCode barcodeQRCode = new BarcodeQRCode(addressCurrent.toString());
					Image imageQR = new Image(barcodeQRCode.createFormXObject(pdf)).setWidth(convertMmtoPts(25));
					
					global.addCell(new Cell().add(imageQR));
					// End Test
					global.addCell(new Cell().add(new Paragraph(carrier.toString())).setFont(labelCarrier)
							.setFontSize(6));
					tableGlobal.addCell(new Cell().add(global).setHeight(convertMmtoPts((float) 146)));
				}
				document.add(tableGlobal);
			}

		} catch (FileNotFoundException e) {
			LOGGER.error("MyPDFGenerator", e);
		}

	}
	private float convertMmtoPts(float measure) {
		// 72 points = 1 inch = 25.4 mm
		return measure * (72f / 25.4f);
	}

}