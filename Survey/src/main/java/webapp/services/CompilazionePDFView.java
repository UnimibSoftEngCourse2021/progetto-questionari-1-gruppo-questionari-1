package webapp.services;

import java.awt.Color;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import webapp.model.Compilazione;
import webapp.model.CompilazioneDomanda;
import webapp.model.Questionario;

public class CompilazionePDFView extends AbstractPDFView {
private String fileName;
	
	public CompilazionePDFView()
	{
		fileName = "compilazione.pdf";
		isLandScape = false;
	    mrgTop = 15;
	    mrgBottom = 15;
	    mrgLeft = 12;
	    mrgRight = 12;
	}
	
	public CompilazionePDFView(String NomeFile)
	{
		fileName = NomeFile;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Questionario q = (Questionario) model.get("questionario");
		final String Titolo = "Compilazioni questionario, titolo: "+q.getNome();
		
		
		//Impostazione del nome del file
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        
        Set<Compilazione> qCompilati = q.getCompilazioni();
        Iterator a = qCompilati.iterator();
        Compilazione c = new Compilazione();
        Set<CompilazioneDomanda> risposte = null;
        while(a.hasNext())
        {
        	c = (Compilazione) a.next();
        
	        risposte = c.getDomande();
	      
	        PdfPTable table = new PdfPTable(2);
	        table.setWidthPercentage(100.0f);
	        table.setSpacingBefore(10);
	      
	 
	        //impostazione  del colore e tipo di font
	        Font font = FontFactory.getFont(FontFactory.TIMES);
	        font.setColor(Color.WHITE);
	 
	        // impostazioni dell'intestazione
	        PdfPCell cell = new PdfPCell();
	        cell.setBackgroundColor(Color.BLUE);
	        cell.setPadding(5);
	 
	        // Intestazione del documento
	        cell.setPhrase(new Phrase("TestoDomanda", font));
	        table.addCell(cell);
	        
	        /*
	        cell.setPhrase(new Phrase("Immagine", font));
	        table.addCell(cell);
	        */
	 
	        cell.setPhrase(new Phrase("Risposta", font));
	        table.addCell(cell);
	        
	        Iterator<CompilazioneDomanda> i = risposte.iterator();
	        while(i.hasNext())
	        {
	            CompilazioneDomanda risposta = i.next();
	        	table.addCell(risposta.getDomanda().getTesto());
	            //table.addCell(risposta.getDomanda().getImmagine().toString());
	            table.addCell(risposta.getRisposta());
	        } 
	 
	        document.add(table);
       
        } 
        document.addTitle(Titolo);
        //document.setPageCount(0);
        document.addCreator("chiara");
	}
}

