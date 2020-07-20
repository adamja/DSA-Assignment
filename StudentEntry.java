
public class StudentEntry
{
	private int studentID;
	private String studentName;


	// Alternate Constructor
	public StudentEntry( int inID, String inName )
	{
		if ( (inID >= 10000000) && (inID < 100000000) ) {		// don't add if inID is not 8 digits
			studentID = inID;
			studentName = inName;
		}
	}
	
	// Copy Constructor
	public StudentEntry( StudentEntry ent )
	{
		this.setID( ent.getID() );
		this.setName( ent.getName() );
	}
	
	// Accessors
	public int getID()
	{
		return studentID;
	}
	
	public String getName()
	{
		return studentName;
	}
	
	// Mutators
	public void setID( int inID )
	{
		if ( (inID >= 10000000) && (inID < 100000000) ) {
			studentID = inID;
		}
	}
	
	public void setName( String inName )
	{
		studentName = inName;
	}
	
	// PRINT - Prints student information to display
	public void print()
	{
		System.out.println( getID() + "\t" + getName() + "\n" );
	}
	
	//PRINTCSV - Returns a string in CSV format of StudentEntry
	public String printCSV()
	{
		return (getID() + "," + getName());
	}

}