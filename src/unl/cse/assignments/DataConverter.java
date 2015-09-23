package unl.cse.assignments;

/* Phase-I */
import com.airamerica.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import com.airamerica.*;

// Include imports for XML/JSON libraries if needed
import com.thoughtworks.xstream.XStream;



public class DataConverter {

	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner s = null;
    	try {
			s = new Scanner(new File("data/Airports.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	int counter = 0;
    	while(s.hasNext()) {
    		if(counter == 0){
    			counter++;
    			int sizeOfArray = s.nextInt();
    		}
    		/* Add each line to string array
    		 * create array of object size of the first index of string array
    		 * then split each line via delimeters
    		 * create objects and store in array of objects
    		 */
    		String line = s.nextLine();
    		String tokens[] = line.split(";");
    		String airportCode = tokens[0];
    		String airportName = tokens[1];
    		String address[] = tokens[2].split(" ");
    		Address addressVar = new Address(address[1], address[2], address[3], address[4], address[5]);
    		String isbn = tokens[2];
    		String publishDate = tokens[3];
    		Book b = new Book();
    		b.setTitle(title);
    		b.setAuthor(author);
    		b.setISBN(isbn);
    		b.setPublishDate(publishDate);
    		lib.addBook(b);
    	}
		
				// TODO: Add your code to read data from .dat files, create objects
		//and export them as XML or JSON 
		

		/*
		 * Uncomment the following line to see an example of XML implementation
		 * using XStream
		 */
<<<<<<< HEAD
		//XMLExample();
=======
		XMLExample();
>>>>>>> Test
	}

	/*
	 * An example of using XStream API It exports to "data/Person-example.xml"
	 * NOTE: It may be interesting to note how compositions (and relationships
	 * are exported. NOTE: Pay attention how to alias various properties of an
	 * object.
	 */
	/*
	public static void XMLExample() {
		XStream xstream = new XStream();

		Address address1 = new Address("Street1", "City1", null, null, null);
		Person p1 = new Person("PersonCode1", address1);
		p1.addEmail("Email1");
		p1.addEmail("Email2");
		Person p2 = new Person("PersonCode2", address1);
		p2.addEmail("Email3");
		p2.addEmail("Email4");
		xstream.alias("person", Person.class);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("data/Person-example.xml"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pw.print("<persons>\n");
		pw.print(xstream.toXML(p1) + "\n");
		pw.print(xstream.toXML(p2) + "\n");
		pw.print("</persons>" + "\n");
		pw.close();

		System.out.println("XML generated at 'data/Person-example.xml'");
	}
	*/
}
