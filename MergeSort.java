
public class MergeSort	// Static class
{
	// MERGE SORT
	public static StudentEntry[] sort( StudentEntry[] A )
    {
		if ((A.length == 0) || (A == null)) {	// if array is empty or 1 don't try to sort
			throw new IllegalArgumentException("Invalid Array to sort");
		}
			
		int leftIdx = 0;
		int rightIdx = A.length-1;
			
		if (leftIdx < rightIdx) {
			A = mergeSortRecurse(A, leftIdx, rightIdx);
		}
		return A;
    }
	
    private static StudentEntry[] mergeSortRecurse( StudentEntry[] A, int leftIdx, int rightIdx )
    {
		if (leftIdx < rightIdx) {
			int midIdx = (leftIdx + rightIdx) / 2;
			
			A = mergeSortRecurse(A, leftIdx, midIdx);	// split off left half of array
			
			A = mergeSortRecurse(A, midIdx+1, rightIdx);	// split off right half of array
			
			A = merge(A, leftIdx, midIdx, rightIdx);		// combine
		}
		return A;
    }
	
    private static StudentEntry[] merge( StudentEntry[] A, int leftIdx, int midIdx, int rightIdx )
    {
		StudentEntry[] tempArr = new StudentEntry[rightIdx-leftIdx+1];
		// initialise curser variables ii, jj and kk
		int ii = leftIdx;
		int jj = midIdx+1;
		int kk = 0;
		
		while ((ii <= midIdx) && (jj <= rightIdx)) {
			if (A[ii].getID() <= A[jj].getID()) {
				tempArr[kk] = new StudentEntry( A[ii] );	// if left is small than current right add to tempArr
				ii++;
			}
			else {
				tempArr[kk] = new StudentEntry(A[jj]);	// if right is smaller than current left add to tempArr
				jj++;
			}
			kk++;		// increment save position of tempArr
		}
		
		for (ii = ii; ii <= midIdx; ii++) {					// add remaining leftIdx array entries if they weren't all added above
			tempArr[kk] = new StudentEntry(A[ii]);
			kk++;
		}
		
		for (jj = jj; jj <= rightIdx; jj++) {				// add remaining rightIdx array entries if they weren't all added above
			tempArr[kk] = new StudentEntry( A[jj] );
			kk++;
		}
		
		for (kk = leftIdx; kk <= rightIdx; kk++) {			// combine sorted tempArr into whole array saving over previous unsorted positions
			A[kk] = new StudentEntry(tempArr[kk-leftIdx]);
		}
		return A;
    }
} // End BinarySearch class