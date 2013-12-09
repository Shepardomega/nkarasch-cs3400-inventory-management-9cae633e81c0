package edu.wmich.cs3400.inventorymanagement.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import qrcode.Contents;
import qrcode.QRCodeEncoder;
import PDFWriter.PDFWriter;
import PDFWriter.PaperSize;
import PDFWriter.StandardFonts;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

/**
 * Generates QR codes representing the String value of the it of objects and
 * puts them in a PDF file. Can also send the PDF in an email if desired.
 * 
 * Not OO, it's simple and just runs.
 */
public class PDFGenerator {

	/**
	 * Handles the entire execution path of this PDF generating sub program.
	 * 
	 * @param context
	 *            applications context
	 * @param itemsToWrite
	 *            a list of the InventoryItems that will be added to the PDF
	 * @param email
	 *            a boolean indicating whether the PDF should be emailed or not
	 */
	public static void generatePDF(Context context,
			List<InventoryItem> itemsToWrite, boolean email) {

		String pdfData = generateData(itemsToWrite);

		File pdfFile = writeFile(pdfData);

		if (email) {
			emailFile(context, pdfFile);
		}
	}

	/**
	 * Handles generation of QR codes and formatting of the PDF file data.
	 * Returns the string representation of it.
	 * 
	 * @param itemsToWrite
	 *            a list of InventoryItems that need QR codes generated for the
	 *            PDF
	 * @return a string representation of the PDF file
	 */
	private static String generateData(List<InventoryItem> itemsToWrite) {

		PDFWriter pdfWriter = new PDFWriter(PaperSize.NORMAL_WIDTH,
				PaperSize.NORMAL_HEIGHT);
		pdfWriter.setFont(StandardFonts.SUBTYPE, StandardFonts.COURIER,
				StandardFonts.WIN_ANSI_ENCODING);

		int y = PaperSize.NORMAL_HEIGHT, pageInsert = 0;

		Iterator<InventoryItem> itemIterator = itemsToWrite.iterator();

		while (itemIterator.hasNext()) {

			if (itemIterator.hasNext()) {
				InventoryItem item = itemIterator.next();

				Bitmap bitmap = null;
				QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(
						String.valueOf(item.getHiddenId()), null, Contents.Type.TEXT,
						BarcodeFormat.QR_CODE.toString(), 21);

				try {
					bitmap = qrCodeEncoder.encodeAsBitmap();
				} catch (WriterException e) {
					e.printStackTrace();
				}

				pdfWriter.addImage(100, y - 350, 300, 300, bitmap);

				pdfWriter
						.addText(
								250 - (int) ((float) item.getName().length() / 2.0f) * 17,
								y - 60, 30, item.getName());
			}

			if (itemIterator.hasNext()) {
				InventoryItem item = itemIterator.next();

				Bitmap bitmap = null;
				QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(
						String.valueOf(item.getHiddenId()), null, Contents.Type.TEXT,
						BarcodeFormat.QR_CODE.toString(), 20);

				try {
					bitmap = qrCodeEncoder.encodeAsBitmap();
				} catch (WriterException e) {
					e.printStackTrace();
				}

				pdfWriter.addImage(450, y - 350, 300, 300, bitmap);

				pdfWriter
						.addText(
								600 - (int) ((float) item.getName().length() / 2.0f) * 17,
								y - 60, 30, item.getName());
			}

			y -= 350;
			pageInsert++;

			if (pageInsert > 2) {
				pdfWriter.newPage();
				pageInsert = 0;
				y = PaperSize.NORMAL_HEIGHT;
			}
		}

		return pdfWriter.asString();
	}

	/**
	 * Creates the PDF file on the external storage.
	 * 
	 * @param pdfData
	 *            a string representation of the PDF data
	 * @return the PDF file
	 */
	private static File writeFile(String pdfData) {

		String fileName = "QRCodes.pdf";
		String encoding = "ISO-8859-1";

		// create directory on sd card if it doesn't exist
		File directory = new File(Environment
				.getExternalStoragePublicDirectory("/inventory/").getPath());
		if (!(directory.exists() && directory.isDirectory())) {
			directory.mkdirs();
		}

		// create old file if it exists
		File newPDF = new File(directory, fileName);

		if (newPDF.exists()) {
			newPDF.delete();
		}

		try {
			newPDF.getParentFile().mkdirs();
			newPDF.createNewFile();

			FileOutputStream pdfFile = new FileOutputStream(newPDF);

			pdfFile.write(pdfData.getBytes(encoding));
			pdfFile.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return newPDF;
	}

	/**
	 * Allows the user to send the file wherever they want.
	 * 
	 * @param context
	 *            the applications context, needed for the email sending
	 *            activity
	 * @param pdfFile
	 *            the PDF file
	 */
	private static void emailFile(Context context, File pdfFile) {

		Uri uri = Uri.fromFile(pdfFile);

		Intent sendIntent = new Intent(Intent.ACTION_SEND);
		sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Inventory QR Code Sheet");
		sendIntent.putExtra(Intent.EXTRA_TEXT,
				"Sent from inventory manager project, PDF attached");
		sendIntent.putExtra(Intent.EXTRA_STREAM, uri);

		sendIntent.setType("application/pdf");

		context.startActivity(Intent.createChooser(sendIntent, ""));
	}
}
