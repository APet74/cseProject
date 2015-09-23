package unl.cse.assignments;

/* Phase-I */
import com.airamerica.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

// Include imports for XML/JSON libraries if needed
import com.thoughtworks.xstream.XStream;

public class DataConverter {

	public static void main(String[] args) throws FileNotFoundException {

		
		String test = "";
		Scanner sc1;
		
		String file = args[0];
		//String file = "cgislands_input_002.txt";
		
		//pull in new file
		try {
			sc1 = new Scanner(new File(file));
		
			String[] fileInput = new String[1000];
			fileInput[0] = "";
			
			while(sc1.hasNext()) {
				String a = sc1.next();
				fileInput[0] = fileInput[0] + a;
			}
		
			//convert to string
			test = fileInput[0];
			
		} catch (FileNotFoundException e) {	
			e.printStackTrace();
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
