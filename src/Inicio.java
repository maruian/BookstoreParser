import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Inicio {

	public static void main(String[] args){
		
		Parser p = new Parser();
		
		try {
			p.parseFicheroXml("bookstore.xml");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.parseDocument();
		
		p.getResultado();
		
		ArrayList<Book> books = new ArrayList<Book>();
		Book b = new Book("science fiction","2001: A space odissey","en","Arthur C. Clarke","1968","28.10");
		Book c = new Book("science fiction","Dune","en","Frank Herbert","1965","14.15");
		books.add(b);
		books.add(c);
		
		Marshaller m = new Marshaller(books);
		
		m.crearDocumento();
		m.crearArbolDOM();
		
		File f = new File("bookstore3.xml");
		
		try {
			m.escribirDocumentAXml(f);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	
	}
	
}
