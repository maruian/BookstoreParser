import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshaller {

	private Document dom = null;
	private ArrayList<Book> books = null;

	public Marshaller(ArrayList<Book> a) {
		books = a;
	}

	public void crearDocumento() {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// creamos una instancia de DOM
			dom = db.newDocument();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		}

	}

	public void crearArbolDOM() {

		// creamos el elemento raíz "acciones"
		Element docEle = dom.createElement("bookstore");
		dom.appendChild(docEle);

		// recorremos todas las acciones
		Iterator it = books.iterator();
		while (it.hasNext()) {
			Book b = (Book) it.next();
			// para cada objeto accion creamos el elemento <accion> y lo
			// añadimos a la raíz
			Element bookEle = setBook(b);
			docEle.appendChild(bookEle);
		}

	}

	private Element setBook(Book b) {

		// creamos el elemento accion
		Element bookEle = dom.createElement("book");
		bookEle.setAttribute("category", b.getCategory());
		
		Element title = dom.createElement("title");
		title.setTextContent(b.getTitle());
		title.setAttribute("lang", b.getLang());
		
		Element author = dom.createElement("author");
		author.setTextContent(b.getAuthor());
		
		Element year = dom.createElement("year");
		year.setTextContent(b.getYear());
		
		Element price = dom.createElement("price");
		price.setTextContent(b.getPrice());
		
		bookEle.appendChild(title);
		bookEle.appendChild(author);
		bookEle.appendChild(year);
		bookEle.appendChild(price);
	
		return bookEle;
	}
	
	public void escribirDocumentAXml(File file) throws TransformerException {
		// creamos una instacia para escribir el resultado
		Transformer trans = TransformerFactory.newInstance().newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT, "yes");

		// especificamos dónde escribimos y la fuente de datos
		StreamResult result = new StreamResult(file);
		DOMSource source = new DOMSource(dom);
		trans.transform(source, result);
	}
	
}
