package com.softwaresecurity.util;
import java.io.FileOutputStream;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.softwaresecurity.gringotts.HomeController;

import persistence.HibernateUtil;
import pojo.Transactions;
 
public class StatementGenerator {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	 
 
 public static void statementbyuniqid(String uniqId) {
	 Session session = HibernateUtil.getSessionFactory().openSession();
	 @SuppressWarnings("unchecked")
	List<Transactions> results = (List<Transactions>) session.createCriteria(Transactions.class)
			 .add( Restrictions.like("uniqId", uniqId)).addOrder(Order.desc("date")).list();
	if(results==null) 
	{
		try {
			logger.error(" No Transactions exist for the User");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	else
	{ 
  String pdfFilename = "Statement.pdf";
  StatementGenerator printReport = new StatementGenerator();
  printReport.createPDF(pdfFilename,results);
    }
 }
 private void createPDF (String pdfFilename, List<Transactions> results){
 
  Document doc = new Document();
  PdfWriter docWriter = null;
 
  try {
    
   //special font sizes
   Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
   Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 
 
   //file path
   String path = "C:\\Users\\PC\\Desktop\\" + pdfFilename;
   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
    
   //document header attributes
   doc.addAuthor("Gringotts Admin");
   doc.addCreationDate();
   doc.addProducer();
   doc.addCreator("Gringotts Bank");
   doc.addTitle("Transaction Statements");
   doc.setPageSize(PageSize.LETTER);
   
   //open document
   doc.open();
 
   //create a paragraph
   Paragraph paragraph = new Paragraph(" Transactions made:   ");
    
    
   //specify column widths
   float[] columnWidths = {0.9f, 1f, 1f, 1f, 1f};
   //create PDF table with the given widths
   PdfPTable table = new PdfPTable(columnWidths);
   // set table width a percentage of the page width
   table.setWidthPercentage(90f);
 
   //insert column headings
   ///insertCell(table, "Unique ID", Element.ALIGN_LEFT, 1, bfBold12);
   insertCell(table, "Date", Element.ALIGN_LEFT, 1, bfBold12);
   insertCell(table, "Transaction ID", Element.ALIGN_LEFT, 1, bfBold12);
   insertCell(table, "Transaction Type", Element.ALIGN_LEFT, 1, bfBold12);
   insertCell(table, "Description", Element.ALIGN_LEFT, 1, bfBold12);
   insertCell(table, "Balance", Element.ALIGN_LEFT, 1, bfBold12);
   table.setHeaderRows(1);
 
   //insert an empty row
   insertCell(table, "", Element.ALIGN_LEFT, 5, bfBold12);
   //create section heading by cell merging
   String str;
   //just some random data to fill 
   for(Transactions t1 : results){
       insertCell(table,t1.getDate().toString(), Element.ALIGN_LEFT, 1, bf12);
       insertCell(table,t1.getUniqId(), Element.ALIGN_LEFT, 1, bf12);
       insertCell(table,t1.getTransactionType(), Element.ALIGN_LEFT, 1, bf12);
       insertCell(table,t1.getDescription(), Element.ALIGN_LEFT, 1, bf12);
       str=Float.toString(t1.getBalance());
       insertCell(table,str, Element.ALIGN_LEFT, 1, bf12);
         }
   //add the PDF table to the paragraph 
   paragraph.add(table);
   // add the paragraph to the document
   doc.add(paragraph);
 
  }
  catch (DocumentException dex)
  {
   dex.printStackTrace();
  }
  catch (Exception ex)
  {
   ex.printStackTrace();
  }
  finally
  {
   if (doc != null){
    //close the document
    doc.close();
   }
   if (docWriter != null){
    //close the writer
    docWriter.close();
   }
  }
 }

private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
   
  //create a new cell with the specified Text and Font
  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
  //set the cell alignment
  cell.setHorizontalAlignment(align);
  //set the cell column span in case you want to merge two or more cells
  cell.setColspan(colspan);
  //in case there is no text and you wan to create an empty row
  if(text.trim().equalsIgnoreCase("")){
   cell.setMinimumHeight(10f);
  }
  //add the call to the table
  table.addCell(cell);
   
 }
 
}