package linear;

/**
 * This is a boolean linear multidimensional matrix. This specific implementation converts the multidimensional matrix into a linear array.
 * The downside of this approach is that elements that are not filled in the matrix would consume memory as well as those that are in the matrix.
 * IMPORTANT!!! This one is the fastest implementation among (LinkedMultidimensionalMatrix.java and MultidimensionalMatrix.java).
 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
 */
public class BooleanMultidimensionalMatrix {
	
	/**
	 * The linear matrix
	 */
	private final boolean[] matrix; //there is a limit for the size of the array, which is Integer.MAX_VALUE - 5 I think. You can change
	//the matrix to another type rather than boolean if you want. Just dont forget to change below also, where indicated.

	/**
	 * The sizes of each axis or dimension. 
	 */
	private final int[] sizes;
	
	/**
	 * The linear size of the matrix.
	 */
	private final int size;
	
	
	/**
	 * Constructs the multidimensional matrix.
	 * Lets suppose we are working with 3D matrices, then we should instantiate a int array of size 3 (3 dimensions, one for each dimension). 
	 * Lets suppose x has size 4 (that is, the range of the x
	 * coordinate goes from 0 to 3), y has size 9 (same analogy) and z has size 7 (again, same analogy).
	 * Then, size[0] should receive 4, size[1] = 9 and size[2] = 7.
	 * @param sizes
	 */
	public BooleanMultidimensionalMatrix(final int[] sizes){
		int total = 1;
		for (int k=0; k < sizes.length; k++){
			total *= sizes[k];
		}
		this.sizes = sizes;
		this.size = total;
		matrix = new boolean[total]; //change the type here also, if you change the matrix type
	}
	
	/**
	 * Internal function that converts the coordinate dimensions into a linear index.
	 * @param position
	 * @return
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public int getLinearId(final int[] position){
		int p = sizes.length - 1;
		int id = position[p];
		while (p - 1 >= 0){
			id = id * sizes[p - 1] + position[p - 1];
			p--;
		}
		return id;
	}
	
	private int nIndex = 0;
	/**
	 * Returns the next object of the matrix. Synchronous method. When finished the function returns null.
	 * @return - the next object
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public synchronized Boolean getNext(){
		if (nIndex == this.getSize()) {
			nIndex = 0;
			return null;
		}
		else{
			nIndex++;
			return this.matrix[nIndex - 1];
		}
	}
	
	/**
	 * Returns the number of dimensions of the matrix.
	 * @return - the number of dimensions
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public int getNumOfDimensions(){
		return this.sizes.length;
	}
	
	/**
	 * Given a linear index, this function returns the corresponding n dimensional position.
	 * @param linearIndex - the linear index
	 * @return - the corresponding n dimensional position
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public int[] getPosition(long linearIndex){
		int[] position = new int[sizes.length];
		long pSize = 0;
		for (int k=sizes.length - 1; k>0; k--){
			pSize = 1;
			for (int l=0; l<k; l++){
				pSize *= sizes[l];
			}
			position[k] = (int) (linearIndex / (pSize));
			linearIndex -= (position[k] * pSize);
		}
		position[0] = (int) (linearIndex % sizes[0]);
		return position;
	}

	
	/**
	 * This function returns the value at the given position.
	 * That is, assuming we are working with 3D matrices, then we instantiate an array position of size 3.
	 * After that, we fill the array with the coordinate values, lets assume we would like to get the value from (x=10, y=20, z=15);
	 * then, position[0] = 10, position[1] = 20, position[2] = 15.
	 * This array is then passed as parameter to this function.
	 * @param position - the position in the matrix
	 * @return
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public boolean get(final int[] position){//change the return type to another type if you want
		return matrix[getLinearId(position)];
	}
	
	/**
	 * Returns the linear size of the matrix (total amount of elements).
	 * @return
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public int getSize(){return this.size;}
	
	/**
	 * Sets the value of a matrix cell at a given position or coordinate.
	 * That is, assuming we are working with 3D matrices, then we instantiate an array position of size 3.
	 * After that, we fill the array with the coordinate values, lets assume we would like to set the value of (x=10, y=20, z=15);
	 * then, position[0] = 10, position[1] = 20, position[2] = 15.
	 * This array is then passed as parameter to this function along with the desired value to be set.
	 * @param position - the position of the matrix
	 * @param value - the value to be set
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public void set(final int[] position, final boolean value){ //change the boolean here also, if you change the matrix to another type
		matrix[getLinearId(position)] = value;
	}

	/**
	 * Prints the entire matrix as a linear array.
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public void printLinearMatrix(){
		System.out.printf("[");
		for (int k=0; k<matrix.length; k++){
			System.out.printf("%b, ", matrix[k]);
		}
		System.out.printf("]\n");
	}
	/**
	 * Counts how many values within the matrix equals the value passed as parameter.
	 * @param value - the value to look for
	 * @return - the amount of values found
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public int getHowManyEquals(final boolean value){ //change the boolean here if you want a matrix of another type
		int counter = 0;
		for (int k=0; k<matrix.length; k++){
			if (matrix[k] == value) counter++;
		}
		return counter;
	}
}
