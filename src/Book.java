
public class Book {
	private String category;
	private String title;
	private String lang;
	private String author;
	private String year;
	private String price;
	
	public Book(String category, String title, String lang, String author, String year, String price){
		this.category=category;
		this.title=title;
		this.lang=lang;
		this.author=author;
		this.year=year;
		this.price=price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void print(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Title: "+this.title+"\n");
		stringBuffer.append("Category: "+this.category+"\n");
		stringBuffer.append("Language: "+this.lang+"\n");
		stringBuffer.append("Author: "+this.author+"\n");
		stringBuffer.append("Year: "+this.year+"\n");
		stringBuffer.append("Price: "+this.price+"\n");
		System.out.println(stringBuffer.toString());
	}
	
	
}
