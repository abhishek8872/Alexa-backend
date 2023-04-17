package com.bookmycon.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.bookmycon.model.User;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;


public class PdfGenerator {

	public void generate(List < User > userList, HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);

		Paragraph paragraph1 = new Paragraph("List of the Users", fontTiltle);

		paragraph1.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph1);

		PdfPTable table = new PdfPTable(5);

		table.setWidthPercentage(100f);
		table.setWidths(new int[] {3,3,3,3,3});
		table.setSpacingBefore(5);

		PdfPCell cell = new PdfPCell();

		cell.setBackgroundColor(CMYKColor.GRAY);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		font.setColor(CMYKColor.WHITE);

		cell.setPhrase(new Phrase("ID", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("User Name", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Email", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Mobile No", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Password", font));
		table.addCell(cell);

		for (User user: userList) {

			table.addCell(String.valueOf(user.getUserId()));

			table.addCell(user.getUserName());

			table.addCell(user.getUserEmail());

			table.addCell(user.getUserContact());

			table.addCell(user.getUserPassword());
		}

		document.add(table);

		document.close();
	}

}
