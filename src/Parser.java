
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class Parser {
	// Definimos los atributos de la clase Parser
	// dom sera de tipo Document
	private Document dom = null;
	private ArrayList<Book> books = null;

	// Inicializamos books en el constructor
	public Parser() {
		books = new ArrayList<Book>();
	}

	public void parseFicheroXml(String fichero) throws ParserConfigurationException, SAXException, IOException {

		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// creamos un documentbuilder
		DocumentBuilder db;
		db = dbf.newDocumentBuilder();
		dom = db.parse(fichero);

	}

	public void parseDocument() {
		// obtenemos el elemento raiz acciones
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos book
		NodeList nl = docEle.getElementsByTagName("book");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (accion)
				Element el = (Element) nl.item(i);

				// obtenemos una accion
				Book b = getBook(el);
				
				// y la añadimos al array
				books.add(b);
			}
		}
	}

	private Book getBook(Element bookEle) {
		// para cada elemento accion, obtenemos sus datos
		String category = bookEle.getAttribute("category");
		String title="";
		String lang="";
		String author="";
		String year="";
		String price="";
		
		//obtenemos la lista todos los nodos del elemento book
		NodeList nlBook = bookEle.getChildNodes();
		
		if (nlBook!=null && nlBook.getLength()>0){
			// obtenemos el elemento titulo de la lista de nodos
			Element eleTitle = (Element)nlBook.item(1);
			
			// la instruccion anterior seria equivalente a realizar lo siguiente:
			// NodeList nlTitle = bookEle.getElementsByTagName("title");
			// if (nlTitle!=null & nlTitle.getLength()>0){
			//  	Element eleTitl = (Element)nlTitle.item(0);
			//  	title = eleTitle.getFirstChild().getNodeValue();
			// }
			
			// obtenemos el valor del atributlo del elemento titulo
			lang = eleTitle.getAttribute("lang");
			
			// obtenemos el valor del nodo texto del elemento titulo 
			title = eleTitle.getFirstChild().getNodeValue();
			
			// obtenemos el elemento autor de la lista de nodos
			Element eleAuthor = (Element)nlBook.item(3);
			
			// obtenemos el valor del nodo texto del elemento autor
			author=eleAuthor.getFirstChild().getNodeValue();
			
			// obtenemos el elemento year de la lista de nodos
			Element eleYear = (Element)nlBook.item(5);
			
			// obtenemos el valor del nodo texto del elemento year
			year=eleYear.getFirstChild().getNodeValue();
			
			// obtenemos el elemento precio de la lista de nodos
			Element elePrice = (Element)nlBook.item(7);
			
			// obtenemos el valor del nodo texto del elemento precio
			price = elePrice.getFirstChild().getNodeValue();
		}
		
		Book b = new Book(category,title,lang,author,year,price);
		return b;
	}

	public void getResultado() {
		StringBuffer cadena = new StringBuffer();
		Iterator it = books.iterator();
		while (it.hasNext()) {
			Book b = (Book) it.next();
			b.print();
		}		
	}
}
