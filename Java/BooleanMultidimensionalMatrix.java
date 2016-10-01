package linear;

/**
 * This is a boolean linear multidimensional matrix. This specific implementation converts the multidimensional matrix into a linear array.
 * The downside of this approach is that elements that are not filled in the matrix would consume memory as well as those that are in the matrix.
 * 
 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
 */
public class BooleanMultidimensionalMatrix {
	
	/**
	 * The linear matrix
	 */
	private final boolean[] matrix; //there is a limit for the size of the array, which is Integer.MAX_VALUE - 5 I think.

	/**
	 * The sizes of each axis or dimension. 
	 */
	private final int[] sizes;
	
	
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
		matrix = new boolean[total];
	}
	
	/**
	 * Internal class that converts the coordinate dimensions into a linear index.
	 * @param position
	 * @return
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	private int getLinearId(final int[] position){
		int p = sizes.length - 1;
		int id = position[p];
		while (p - 1 >= 0){
			id = id * sizes[p - 1] + position[p - 1];
			p--;
		}
		return id;
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
	public boolean get(final int[] position){
		return matrix[getLinearId(position)];
	}
	
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
	public void set(final int[] position, final boolean value){
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
	public int getHowManyEquals(final boolean value){
		int counter = 0;
		for (int k=0; k<matrix.length; k++){
			if (matrix[k] == value) counter++;
		}
		return counter;
	}
}
