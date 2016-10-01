package linear;

import java.util.HashMap;

/**
 * This is a generic linear multidimensional matrix. This specific implementation converts the multidimensional matrix into a linear array.
 * The downside of this approach is that it takes a significant amount of time to process (read and write) in the matrix. However, it is unlimited, you do not have to worry about memory consumption.
 * 
 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
 * @param <T> - the type of the objects in the matrix
 */
public class MultidimensionalMatrix <T>{
	
	/**
	 * The linear matrix
	 */
	private final HashMap<Integer,T> matrix;

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
	public MultidimensionalMatrix(final int[] sizes){
		int total = 1;
		for (int k=0; k < sizes.length; k++){
			total *= sizes[k];
		}
		this.sizes = sizes;
		//matrix = new T[total];
		matrix = new HashMap<Integer,T>(total);
	
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
	public Object get(final int[] position){
		return matrix.get(getLinearId(position));
	}
	
	/**
	 * Sets the value of a matrix cell at a given position or coordinate.
	 * That is, assuming we are working with 3D matrices, then we instantiate an array position of size 3.
	 * After that, we fill the array with the coordinate values, lets assume we would like to set the value of (x=10, y=20, z=15);
	 * then, position[0] = 10, position[1] = 20, position[2] = 15.
	 * This array is then passed as parameter to this function along with the desired value to be set.
	 * @param position - the position of the matrix
	 * @param object - the object to be set
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public void set(final int[] position, final T object){
		final int pos = getLinearId(position);
		matrix.remove(pos);
		matrix.put(pos, object);
	}

	/**
	 * Counts how many objects within the matrix equals the object passed as parameter.
	 * @param object - the object to look for
	 * @return - the amount of objects found
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public int getHowManyEquals(final T object){
		int counter = 0;
		for (int key : matrix.keySet()){
			if (matrix.get(key).equals(object)) counter++;
		}
		return counter;
	}
}
