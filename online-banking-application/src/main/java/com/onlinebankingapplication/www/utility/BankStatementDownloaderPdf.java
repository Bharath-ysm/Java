package com.onlinebankingapplication.www.utility;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.onlinebankingapplication.entity.BankAccountTransaction;

import jakarta.servlet.http.HttpServletResponse;

public class BankStatementDownloaderPdf 
{
	private List<BankAccountTransaction> transactions;
	
	private String startDate;

	private String EndDate;
	
	public BankStatementDownloaderPdf(String startDate,String EndDate, List<BankAccountTransaction> transactions)
	{
		this.transactions=transactions;
		this.startDate=startDate;
		this.EndDate=EndDate;
	}
	
	private void writeTableHeader(PdfPTable table)
	{
		PdfPCell cell=new PdfPCell();
		
		cell.setBackgroundColor(new Color(253,100,120));
		
		cell.setPadding(5);
		
		Font font=FontFactory.getFont(FontFactory.HELVETICA);
		
		font.setColor(new Color(27,31,40));
		
		cell.setPhrase(new Phrase("TransactionId",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("TransactionType",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Amount",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Narration",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("BeneficiaryAccount",font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("TransactionTime",font));
		table.addCell(cell);
		
	}
	
	private void writeTableData(PdfPTable table)
	{
		for(BankAccountTransaction transaction:transactions)
		{
			table.addCell(transaction.getTransactionId());
			
			table.addCell(transaction.getType());
			
			table.addCell(String.valueOf(transaction.getAmount()));
			
			
			table.addCell(transaction.getNarration());
			
			table.addCell(DateTimeUtils.getepoch(transaction.getTransactionTime()));
			
			table.addCell(transaction.getDestinationBankAccount()!= null? transaction.getDestinationBankAccount().getNumber():"--");
			
			
		}
	}
	
	public void export(HttpServletResponse response)throws DocumentException,IOException
	{
		Document d=new Document(PageSize.A4);
		
		PdfWriter.getInstance(d, response.getOutputStream());
		
		d.open(); //opening the document
		
		Font headerfont=FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		
		headerfont.setSize(25);
		
		headerfont.setColor(18, 18, 18);
		
		Paragraph pheader=new Paragraph("Customer BankStatement\n",headerfont);
		
		pheader.setAlignment(Paragraph.ALIGN_CENTER);
		
		d.add(pheader);
		
		//bank Details
		Font subfont=FontFactory.getFont(FontFactory.TIMES_ROMAN);
		
		subfont.setSize(19);
		
		subfont.setColor(18,18,18);
		
		Paragraph psub=new Paragraph("Bank Details:\n",subfont);
		
		psub.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		
		d.add(psub);
		
		//bank transactions
		Font fontTransaction=FontFactory.getFont(FontFactory.TIMES_BOLD);
		
		fontTransaction.setSize(18);
		
		fontTransaction.setColor(18,18,18);
		
		Paragraph ptransaction=new Paragraph("BankTranscations:\n",fontTransaction);
		
		ptransaction.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		
		d.add(ptransaction);
		
		//bank statement from start date to end date
		Font fontStatement=FontFactory.getFont(FontFactory.TIMES_BOLD);
		
		fontStatement.setSize(18);
		
		fontStatement.setColor(18,18,18);
		
		Paragraph pStatement=new Paragraph("BankStatements Form "+startDate+"To"+EndDate+":\n",fontStatement);
		
		pStatement.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		
		d.add(pStatement);
		
		///
		
		
		Font fontbank=FontFactory.getFont(FontFactory.TIMES_BOLD);
		
		fontbank.setSize(16);
		
		fontbank.setColor(18,18,18);
		
		Paragraph pBank= new Paragraph("Bank Name :+transactions.get(0).getBank().getName()\n",fontbank);
		
			
		
		
		pheader.add("AccountNo : +transactions.get(0).getBankAccount().getNumber()\n");
		
		pheader.add("BankCode : +transaction.get(0).getBank().getCode()\n");
		
		pheader.add("IfscCode : +transaction.get(0).getBankAccount.getIfscCode()\n");
		
		pheader.add("BankEmail : +transaction.get(0).getBank().getBankEmail()\n");
		
		pheader.add("MobileNo : +transaction.get(0).getUser().getContact()\n");
		
		pBank.setAlignment(Paragraph.ALIGN_JUSTIFIED);
		
		d.add(pBank);
		
		PdfPTable p = new PdfPTable(6);
		
		p.setWidthPercentage(100);
		
		p.setSpacingBefore(10);
		
		writeTableHeader(p);
		
		writeTableData(p);
		
		d.add(p);
		
		d.close();
		
		
		
		
		
		
		
	}
	
}

