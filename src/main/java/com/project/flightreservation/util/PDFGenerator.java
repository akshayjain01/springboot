package com.project.flightreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.flightreservation.entities.Reservation;


@Component
public class PDFGenerator {
	public void generateItinerary(Reservation reservation,String filePath) {
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
			document.open();
			System.out.println(generateTable(reservation));
			document.add(generateTable(reservation));
			
			document.close();
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private PdfPTable generateTable(Reservation reservation) {
		PdfPTable table = new PdfPTable(2);
		
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Flight Itinerary"));
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Flight Details : "));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("Airlines");
		table.addCell(reservation.getFlight().getOperatingAirlines());
		
		table.addCell("Departure City");
		table.addCell(reservation.getFlight().getDepartureCity());
		
		table.addCell("Arrival City");
		table.addCell(reservation.getFlight().getArrivalCity());
		
		table.addCell("Flight Number");
		table.addCell(reservation.getFlight().getFlightNumber());
		
		table.addCell("Date Of Departure");
		table.addCell(reservation.getFlight().getDateOfDeparture().toString());
		
		table.addCell("Estimated Departure Time");
		table.addCell(reservation.getFlight().getEstimatedDepartureTime().toString());
		
		cell = new PdfPCell(new Phrase("Passenegr Details : "));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("First Name");
		table.addCell(reservation.getPassenger().getFirstName());
		
		table.addCell("Last Name");
		table.addCell(reservation.getPassenger().getLastName());
		
		table.addCell("Email");
		table.addCell(reservation.getPassenger().getEmail());
		
		table.addCell("Phone");
		table.addCell(reservation.getPassenger().getPhone());
		return table;
	}
}
