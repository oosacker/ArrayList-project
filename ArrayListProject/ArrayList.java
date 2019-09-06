import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<E> implements List <E>, Iterable<E>{

	 private E[] data;
	 private int elementNum = 0;	// number of items in array NOT array size!!!!
	 private static final int arraySize = 2;		// default array size (may get bigger if needed)
	 
	public ArrayList() {
		this.data = (E[])new Object[arraySize];	
		this.elementNum = 0;
	}
	
	/**
	 * Print out all elements
	 */
	public void printArray() {
		for(int i=0; i<data.length; i++) {
			System.out.println(i + " " + data[i]);
		}
		System.out.println();
	}
	
	/**
	 * Increase the size of the internal array
	 */
	private void increaseSize() {

		// double size
		E[] temp = (E[])new Object[data.length*2]; 
		
		// copy the data
		for(int i=0; i<data.length; i++) {
			temp[i] = data[i];
		}
		
		// rename it to data
		data = temp;
	}

	/**
	 * Appends the specified element to the end of this list (optional operation). 
	 */
	@Override
	public boolean add(Object e) {

		if(e == null) {
			throw new NullPointerException();
		}

		else{
			
			if(elementNum == data.length) {
				increaseSize();
			}

			this.data[elementNum] = (E) e;
			this.elementNum++;
			return true;
			
		}
	}

	/**
	 * Inserts the specified element at the specified position in this list 
	 * (optional operation). 
	 * Shifts the element currently at that position (if any) 
	 * and any subsequent elements to the right (adds one to their indices).
	 */
	@Override
	public void add(int index, E element) {
		// insert at index, shift all down by one
		
		if(element == null) {
			throw new NullPointerException();
		}
		
		if(index<0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index > 0 && elementNum==0) {
			// cannot insert in nonzero index if array empty
			throw new IllegalArgumentException();
		}
		
		if(elementNum == data.length) {
			increaseSize();
		}
		
		// make temp array to copy to
		E[] temp = (E[])new Object[data.length];
		
		// copy up to index
		for(int i=0; i<index; i++) {
			temp[i] = data[i];
		}
		
		// the new data is inserted
		temp[index] = element;
		elementNum++;
		
		//increaseSize();
		
		// the rest of the data NOTE data indexes need to be -1!!!!!
		for(int j=index+1; j<data.length; j++) {
			temp[j] = data[j-1];
		}
		
		// copy it back
		for(int k=0; k<data.length; k++) {
			data[k] = temp[k];
		}
		
	}

	/**
	 * Appends all of the elements in the specified collection to the end of this list, 
	 * in the order that they are returned by the specified collection's iterator (optional operation). 
	 * The behavior of this operation is undefined if the specified collection is modified 
	 * while the operation is in progress. 
	 * (Note that this will occur if the specified collection is this list, and it's nonempty.)
	 */
	@Override
	public boolean addAll(Collection c) {
		
		// find out the size of input
		int inputSize = c.size();
		
		// keep increasing the size until array big enough
		while( data.length < (elementNum + inputSize) ) {
			increaseSize();
			//System.out.println("increase size");
		}
		
		int j=0;	// counter for the input arraylist
		for(int i = elementNum; i < (elementNum + inputSize); i++) {
			//System.out.println("i="+i);
			data[i] = ((List<E>) c).get(j);
			j++;	
		}
		
		// MUT NOT DO THIS INSIDE THE LOOP OR CRASH!!!
		elementNum = elementNum+inputSize;
		return true;

	}

	@Override
	public boolean addAll(int index, Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Removes all of the elements from this list (optional operation). 
	 * The list will be empty after this call returns.
	 */
	@Override
	public void clear() {

		for(int i=0; i<data.length; i++) {
			data[i] = null;
			data = (E[])new Object[arraySize];	
		}
		elementNum = 0;
	}

	/**
	 * Returns true if this list contains the specified element. More formally, returns true if 
	 * and only if this list contains at least one element e such that (o==null ? e==null : o.equals(e)).
	 */
	@Override
	public boolean contains(Object o) {
		
		if(o == null) {
			throw new NullPointerException();
		}
		
		for(E item : data) {

			// need != null here or else will crash
			if(item != null && item.equals(o)) {
				
				return true;
				
			}
		}
		return false;
		
	}

	/**
	 * Returns true if this list contains all of the elements of the 
	 * specified collection.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {
		
		if(c.size() > elementNum) {
			return false;
		}

		for(int i=0; i<c.size(); i++) {
			
			E temp = ((List<E>) c).get(i);
			
			if( (data[i] != null) && !this.contains(temp) ) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the element at the specified position in this list.
	 */
	@Override
	public E get(int index) {
		
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index >= 0) {
			return data[index];
		}
		
		else 
			return null;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element. More formally, 
	 * returns the lowest index i such that 
	 * (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
	 */
	@Override
	public int indexOf(Object o) {
		
		if(o == null) {
			throw new NullPointerException();
		}
		
		for(int i=0; i<data.length; i++) {
			if(data[i] != null && data[i].equals(o)) {
				return i;
			}
		}
		
		return -1;
	}

	/**
	 * Returns true if this list contains no elements.
	 */
	@Override
	public boolean isEmpty() {
		if ( elementNum == 0 )
			return true;
		else
			return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element. More formally, 
	 * returns the highest index i such that 
	 * (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
	 */
	@Override
	public int lastIndexOf(Object o) {

		// search the array backwards
		for(int i=data.length-1; i>=0; i--) {
			if(data[i] != null && data[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, 
	 * if it is present (optional operation). If this list does not contain the element, 
	 * it is unchanged. More formally, removes the element with the lowest index i such that 
	 * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). 
	 * Returns true if this list contained the specified element 
	 * (or equivalently, if this list changed as a result of the call).
	 */
	@Override
	public boolean remove(Object o) {
		
		if(o == null) {
			throw new NullPointerException();
		}
		
		if(!this.contains(o)) {
			return false;
		}
		
		int index = this.indexOf(o);
		
		if ( this.remove(index) != null) {
			return true;
		}

		else 
			return false;
		
	}

	/**
	 * Removes the element at the specified position in this list (optional operation). 
	 * Shifts any subsequent elements to the left (subtracts one from their indices). 
	 * Returns the element that was removed from the list.
	 */
	@Override
	public E remove(int index) {
		
		if(index<0) {
			throw new IndexOutOfBoundsException();
		}
		
		// copy array up to index
		E[] temparr = (E[])new Object[data.length];	
		
		for(int i=0; i<index; i++) {
			temparr[i] = data[i];
		}
		
		// back up and delete it
		E oldData = data[index];
		data[index] = null;
		this.elementNum--;
		
		// copy from index+1 to end of data
		for(int j=index; j<data.length-1; j++) {
			temparr[j] = data[j+1];
		}
		
		//update the array
		data = temparr;

		return oldData;
	}

	/**
	 * Removes from this list all of its elements that are contained 
	 * in the specified collection (optional operation).
	 */
	@Override
	public boolean removeAll(Collection<?> c) {
		
		boolean ret = false;
		
		for(int i=0; i<c.size(); i++) {

			E temp = ((List<E>) c).get(i);
			
			int count=0;
			
			// count number of duplicates
			for(int j=0; j<elementNum; j++) {
				if(contains(temp))
					ret = true;
					count++;
			}
			
			int cur = 0;
			while(cur<count) {
				remove(temp);
				cur++;
			}
			
		}

		return ret;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Replaces the element at the specified 
	 * position in this list with the specified element (optional operation).
	 */
	@Override
	public E set(int index, E element) {
		
		// error case
		if(index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index > 0 && elementNum==0) {
			// cannot insert in nonzero index if empty
			throw new IllegalArgumentException();
		}
			
		if(element == null) {
			throw new NullPointerException();
		}
		
		// already something there -- back it up
		else if ( data[index] != null )  {
			E temp = data[index];
			data[index] = element;
			return temp;
		}
		
		else {
			data[index] = element;
			elementNum++;
			return null;
		}
		
	}

	/**
	 * Returns the number of elements in this list. 
	 * If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
	 */
	@Override
	public int size() {
		return this.elementNum;	
	}

	/**
	 * Returns a view of the portion of this list between the specified fromIndex, 
	 * inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, 
	 * the returned list is empty.) The returned list is backed by this list, 
	 * so non-structural changes in the returned list are reflected in this list, 
	 * and vice-versa. 
	 * The returned list supports all of the optional list operations supported by 
	 * this list.
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		
		List<E> ret = new java.util.ArrayList<E>();	
		
		for(int i=fromIndex; i<toIndex; i++) {
			ret.add(data[i]);
		}
		
		return ret;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element). 
	 */
	@Override
	public Object[] toArray() {
		
		E[] ret = (E[])new Object[elementNum];	
		
		for(int i=0; i<elementNum; i++) {
			ret[i] = data[i];
		}
		
		return ret;
	}

	@Override
	public Object[] toArray(Object[] a) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
