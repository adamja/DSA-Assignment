
import java.util.*;
import java.io.*;

public class StudentMain
{

/**********************************************************************************/
	// MAIN
	public static void main( String[] args )
	{
		displayMenu();
	}
	
/**********************************************************************************/
	// DISPLAY MENU
	public static void displayMenu()
	{
		int choice = 0;
		
		StudentEntry[] loadEntries = null;		//StudentEntry array used to pass the loaded list to search sub-routines
		String[] saveEntries = null;			//String array used to save user searches
		
		do {
			choice = menu();	// Call menu()
		
			switch (choice) {
				case 1:		loadEntries = load();
							break;

				case 2:		if (loadEntries != null) {
								saveEntries = searchSingle(loadEntries);
							}
							else {
								System.out.println("Please load a file containing students.\n");
							}
							break;

				case 3:		if (loadEntries != null) {
								saveEntries = searchList(loadEntries);
							}
							else {
								System.out.println("Please load a file containing students.\n");
							}
							break;

				case 4:		if (saveEntries != null) {
								save(saveEntries);
							}
							else {
								System.out.println("There are no searches to save.\n");
							}
							break;

				case 5:		System.out.println("Goodbye...");
							break;

				default:	break;
			}
		} while (choice != 5);
	}
	
/**********************************************************************************/
	// MENU
	private static int menu()
	{
		int choice = 0;
		
		// Input a choice from user
		do {
				// Read in the choice
				Scanner sc = new Scanner(System.in);
				System.out.print("***MENU***\n(1)  - Load Students\n(2)  - Search for Single Student\n(3)  - Search for List of Students\n(4)  - Save Matches\n(5)  - Quit\nChoice:> ");
				
				try {
					choice = sc.nextInt();		// catch invalid input exception before program crashes (eg. int out of range)
				}
				catch (Exception InputMismatchException) {
					choice = 0;
				}
			
			if ( !((choice >= 1) && (choice <= 5)) ) {
				System.out.println("Invalid option, please try again.\n");
			}
		// Try again selection was out of range
		} while (!((choice >= 1) && (choice <= 5)));
		
		return choice;
	}
	
/**********************************************************************************/
	// LOAD
	private static StudentEntry[] load()
	{
		StudentEntry[] loadEntries = null;
		
		loadEntries = FileIO.loadFile(); // Open the file and save to loadEntries array
		
		if (loadEntries != null) {	// Don't pass empty array to sort
			loadEntries = MergeSort.sort(loadEntries); // Sort the list
		}
		return loadEntries;
	}
	
/**********************************************************************************/

	// SINGLE SEARCH
	private static String[] searchSingle( StudentEntry[] loadEntries)
	{
		String[] saveEntry = null;
		StudentEntry student = null;
		int inID = 0;
		
		// Input student number
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter Student ID: ");
		try {
			inID = sc.nextInt();				// catch invalid input exception before program crashes (eg. int out of range)
		}
		catch (Exception InputMismatchException) {
			//System.out.println("The student number entered is invalid");
		}
		
		if ((inID >= 10000000) && (inID < 100000000)) {
			student = BinarySearch.search( inID, loadEntries);
		}
		else {
			System.out.println("The student number entered is invalid.\n");
		}
		
		if (student != null) {
			System.out.println("Student found:");
			saveEntry = new String[1];
			saveEntry[0] = student.printCSV();	//save student in single string array
			student.print();
		}
		
		return saveEntry;
	}

/**********************************************************************************/
	// LIST SEARCH
	private static String[] searchList( StudentEntry[] loadEntries )
	{
		BinarySearchTree tree = new BinarySearchTree();
		DSAQueue queue = new DSAQueue();
		String[] saveEntries = null;
		StudentEntry student = null;
		
		for ( int ii = 0; ii <= loadEntries.length-1; ii++ ) {			// copy loadEntries array into a binary tree
			tree.insert( loadEntries[ii].getName(), loadEntries[ii] );
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Please enter part of a student you would like to find: ");
		String search = sc.nextLine();
		
		queue = tree.traverseTree(search);
		
		if (queue.isEmpty() == false) {
			saveEntries = new String[queue.getCount()];
			System.out.println("The following students were found in the search:");
			
			int ii = 0;
			while (queue.isEmpty() == false) {
				student = new StudentEntry((StudentEntry)queue.dequeue());
				student.print();		// print to user
				saveEntries[ii] = new String(student.printCSV());		// save found entries into a string array in CSV format
				ii++;
			}
			System.out.println(saveEntries.length + " student(s) were found.\n");
		}
		else {
			System.out.println("No Students were found in the search.\n");
		}
		
		
		return saveEntries;
	}

/**********************************************************************************/
	// SAVE
	private static void save( String[] saveEntries )
	{
		FileIO.saveFile(saveEntries);
	}

/**********************************************************************************/
	
} // End of Class