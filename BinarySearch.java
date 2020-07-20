	
public class BinarySearch	// Static class
{
	// BINARY SEARCH
	public static StudentEntry search( int inID, StudentEntry[] loadEntries )
	{
		StudentEntry saveEntry = null;
		boolean found = false;
		int first, last, middle, n, search, array[];
	 
		n = loadEntries.length;
		first  = 0;
		last   = n - 1;
		middle = (first + last)/2;

		
		while((first <= last) && (found == false))
		{
			if ( loadEntries[middle].getID() < inID ) {
				first = middle + 1;		// search in upper half
			}
			else if ( loadEntries[middle].getID() == inID ) {			// found ID
				saveEntry = new StudentEntry( loadEntries[middle] );	// save student
				found = true;			// stop searching
			}
			else {
				last = middle - 1;		// search in lower half
			}
			
			middle = (first + last)/2;	// move middle
		}
		
		if (first > last) {
			System.out.println(inID + " is not present in the list.\n");
		}
		return saveEntry;		// return saved student
	}
} // End BinarySearch class