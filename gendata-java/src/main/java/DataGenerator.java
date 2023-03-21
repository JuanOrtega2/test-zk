

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.datafaker.Faker;
import net.datafaker.formats.Format;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;


@SuppressWarnings("deprecation")
public class DataGenerator {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdfFileName = new SimpleDateFormat("yyyyMMdd_HHmmss");
	static SimpleDateFormat sdfNice = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static Date tsFrom = null ;
	static Date tsTo = null ;
	static String path = "." ;
	static int maxFiles = 20 ;

	
	static Faker myFaker = new Faker();
	
	public static void main(String[] args) {
		
		Options options = new Options();
		
		int numRecords = 5000 ;
		int numFields= 7 ;
		
		options.addOption(new Option("h", "help", false, "Print this help"));
		options.addOption(new Option("n", "numrecords", true, "Number of records in file (default 5000)"));
		options.addOption(new Option("p", "path", true, "Path to write the file"));
		options.addOption(new Option("f", "numrecords", true, "Number of records in file (default 7)"));
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null ;
		try {
			cmd = parser.parse(options, args);
		} catch (org.apache.commons.cli.ParseException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
		
		if ( cmd.hasOption('h') || cmd.getOptions().length < 0 ) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("FileGenerator", options);
			System.exit(0) ;
		}
		
		
		if ( cmd.hasOption('n')  ) {
			try {
				numRecords = Integer.parseInt(cmd.getParsedOptionValue("numrecords").toString());
			} catch (Exception e) {
			}
		} 

		if ( cmd.hasOption('p')  ) {
			try {
				path = cmd.getParsedOptionValue("path").toString();
			} catch (Exception e) {}
		} 
		if ( cmd.hasOption('f')  ) {
			try {
				numFields = Integer.parseInt(cmd.getParsedOptionValue("numrecords").toString());
			} catch (Exception e) {}
		}

		String filename = "data_" + sdfFileName.format(new Date() ) ;
		generateFile( filename, numRecords, numFields ) ;
	}
	
	private static void generateFile(String filename, int numRecords,int numFields) {
		
		
		String fullFileName = path + "/" + filename + ".tmp" ;
		System.out.println (String.format( "%s - Generating file: %s, records: %d, fields: %d",
				sdfNice.format(new Date() ), 
				fullFileName, numRecords, numFields))  ;
		
		int printed = 0 ;
		BufferedOutputStream bfout = null ;
		
		File myFile = new File(fullFileName) ;
		
		try {
			bfout = new BufferedOutputStream (new FileOutputStream(myFile ) ) ;
		
			while ( printed < numRecords ) {
				printed ++ ;
				//String data = getData(numFields) ;
				String data=generateData(numFields);
				bfout.write( ("set /test_perf/node_" + String.format("%03d", myFaker.number().numberBetween(1, 100)) + " '" + data + "'\n").getBytes() );
			}
			bfout.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println () ;
		}
		
		myFile.renameTo(new File(path + "/" + filename+ ".cmd")) ;
	}

	public static String generateRandomStrings(int numFields) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numFields; i++) {
			String randomString =  myFaker.lorem().sentence();
			/*for (int j = 0; j < 10; j++) {
				int randomInt = (int) (Math.random() * 26 + 97);
				char randomChar = (char) myFaker.lorem().sentence();
				randomString += randomChar;
			}*/
			sb.append(randomString);
    	}
    	return sb.toString();
	}
	private static String getData(int numFields) {
	
		return  Format.toJson()
        .set("firstName", () -> myFaker.name().firstName())
        .set("lastName", () -> myFaker.name().lastName())
        .set("addCountry", () -> myFaker.address().country())
        .set("addCity", () -> myFaker.address().city())
        .set("addStreet", () -> myFaker.address().streetAddress())
        .set("addZipcode", () -> myFaker.address().zipCode())
        .set("telephone", () ->myFaker.phoneNumber().phoneNumber())
        .build().generate() ;
		
	}
	public static String generateData(int numFields) {
        StringBuilder builder = new StringBuilder();
        builder.append("{");

        for (int i = 0; i < numFields; i++) {
            String[] fieldNames = {
			"firstName", "lastName", "address", "city", "country", "streetAddress",
			"zipCode", "phoneNumber", "email", "username", "password", "company",
			"jobTitle", "funnyName", "musicGenre", "color", "university",
			"bookTitle", "beerStyle", "catName"};
			String fieldName = fieldNames[myFaker.random().nextInt(fieldNames.length)];
            String fieldValue = getFieldValue(fieldName);
            builder.append('"').append(fieldName).append('"').append(": ").append('"').append(fieldValue).append('"').append(", ");
        }

        // Eliminar coma sobrante del final
        builder.deleteCharAt(builder.length() - 2);
		// Eliminar el espacio del final
		builder.deleteCharAt(builder.length() - 1);
        builder.append("}");
        return builder.toString();
    }

    private static String getFieldValue(String fieldName) {
        switch (fieldName) {
            case "firstName":
                return myFaker.name().firstName();
            case "lastName":
                return myFaker.name().lastName();
            case "address":
                return myFaker.address().fullAddress();
            case "city":
                return myFaker.address().city();
            case "country":
                return myFaker.address().country();
            case "streetAddress":
                return myFaker.address().streetAddress();
            case "zipCode":
                return myFaker.address().zipCode();
            case "phoneNumber":
                return myFaker.phoneNumber().phoneNumber();
            case "email":
                return myFaker.internet().emailAddress();
            case "username":
                return myFaker.name().username();
            case "company":
                return myFaker.company().name();
            case "jobTitle":
                return myFaker.job().title();
            case "funnyName":
                return myFaker.funnyName().name();
            case "musicGenre":
                return myFaker.music().genre();
            case "color":
                return myFaker.color().name();
            case "university":
                return myFaker.university().name();
            case "bookTitle":
                return myFaker.book().title();
            case "beerStyle":
                return myFaker.beer().style();
            case "catName":
                return myFaker.cat().name();
            default:
                return "PasswordUnknown";
        }
    }

}
