
import java.util.*;
import java.io.*;

public class FileIO	// Static class
{
	// LOAD FILE
	public static StudentEntry[] loadFile()
	{
		// Declare Variables
		StudentEntry[] loadEntries = null;
		String csvFile = null;
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		boolean tryAgain;
		Scanner sc = new Scanner(System.in);
		
		do {
			tryAgain = false;	// Always set tryAgain to false at the start of each loop
			
			try {
				// Read in file name
				System.out.print("Please enter the file name (including path): ");
				csvFile = sc.nextLine();
				
				br = new BufferedReader(new FileReader(csvFile));	// Open reader stream
				
				int numOfEnts = 0;
				while ((line = br.readLine()) != null) {
					numOfEnts++;	// find number of entries in file
				}
				br.close();	// close the file
				
				System.out.println("A file containing " + numOfEnts + " entry(ies) has been found.\n");
				
				loadEntries = new StudentEntry[numOfEnts];	// create an array of student entry
				
				br = new BufferedReader(new FileReader(csvFile));	// open the file again to start at the beginning
				
				// Read through the file a second time and store the list into a StudentEntry array
				int ii = 0;
				while ((line = br.readLine()) != null) {
					String[] student = line.split(csvSplitBy);
					loadEntries[ii] = new StudentEntry( Integer.parseInt(student[0]), student[1] );
					ii++;
				}
				
			} 
			catch(FileNotFoundException e) {
				System.out.print("The file was not found. Would you like to try again? (y/n): ");
				String ans = sc.nextLine();
				
				if (ans.toLowerCase().equals("y")) {
					tryAgain = true;
				}
				else {
					tryAgain = false;
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			finally {
				if (br != null) {
					try {
						br.close();		// Always close open stream
					}
					catch (IOException e) {
					e.printStackTrace();
					}
				}
			}
			
		} while (tryAgain == true);	// repeat until file is found
		
		return loadEntries;
	}

	// SAVE FILE
	public static void saveFile( String[] saveEntries )
	{
		// Declare variables
		FileOutputStream fileStrm = null;
		PrintWriter pw = null;
		
		// Read in the file name
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter the file name you would like to save under: ");
		String fileName = sc.nextLine();
		
		try
		{
			// Opening and saving the file
			fileStrm = new FileOutputStream(fileName);
			pw = new PrintWriter(fileStrm);
			
			// Printing saved entries into designated file
			for (int ii = 0; ii <= saveEntries.length-1; ii++) {
				pw.println(saveEntries[ii]);
			}
			
			System.out.println("The file has been saved under: " + fileName+ "\n");
		}
		catch( Exception e ) {
			System.out.println("Unable to save file.\n"); // Let the user know if the file did not save
			e.printStackTrace();
		}
		
		finally {
			if (pw != null) {
				pw.close(); // Always close opened stream
			}
		}
		
	}
} // End FileIO class