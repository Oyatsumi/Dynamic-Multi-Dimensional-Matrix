import java.util.TreeMap;

/**
 * This is a generic linked multidimensional matrix. This specific implementation converts the multidimensional matrix into a linked list or linked tree.
 * The downside of this approach it takes a significant amount of time to process (read and write) in the matrix.
 * However, the consumption of memory is reduced because only the elements that are manually set in the matrix consume memory in fact.
 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
 * @param <T> - the type of the object to be placed in the matrix cells
 */
public class LinkedMultidimensionalMatrix <T> {
	
	//the integer can be changed according to the range of the matrix coordinates
	private final TreeMap<Integer, LinkedMultidimensionalMatrix<T>> matrix = new TreeMap<Integer, LinkedMultidimensionalMatrix<T>>();
	private T object = null;
	
	
	
	/**
	 * Sets the value of a matrix cell at a given position or coordinate.
	 * That is, assuming we are working with 3D matrices, then we instantiate an array position of size 3.
	 * After that, we fill the array with the coordinate values, lets assume we would like to set the value of (x=10, y=20, z=15);
	 * then, position[0] = 10, position[1] = 20, position[2] = 15.
	 * This array is then passed as parameter to this function along with the desired value to be set.
	 * @param position - the position or coordinate
	 * @param object - the object to put at the position or coordinate
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public void set(final int[] position, final T object){
		LinkedMultidimensionalMatrix<T> aux, current = this;
		for (int k=0; k<position.length; k++){
			final int pos = position[k];
			if (!current.matrix.containsKey(pos)){
				aux = new LinkedMultidimensionalMatrix<T>();
				current.matrix.put(pos, aux);
				current = aux;
			}else{
				current = current.matrix.get(pos);
			}
		}
		current.object = object;
	}

	
	/**
	 * This function returns the value at the given position.
	 * That is, assuming we are working with 3D matrices, then we instantiate an array position of size 3.
	 * After that, we fill the array with the coordinate values, lets assume we would like to get the value from (x=10, y=20, z=15);
	 * then, position[0] = 10, position[1] = 20, position[2] = 15.
	 * This array is then passed as parameter to this function.
	 * @param position
	 * @return
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public T get(final int[] position){
		LinkedMultidimensionalMatrix<T> current = this;
		for (int k=0; k<position.length; k++){
			final int pos = position[k];
			if (!current.matrix.containsKey(pos)){
				return null;
			}else{
				current = current.matrix.get(pos);
			}
		}
		return current.object;
	}
	
	/**
	 * Counts how many objects within the matrix equals the object passed as parameter.
	 * @param object - the object to look for
	 * @return - the amount of objects found
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	public int getHowManyEquals(final T object){
		return recursiveVerification(this, object, 0);
	}
	
	/**
	 * Internal recursive function to compute the getHowManyEquals function.
	 * @param link - a list instance
	 * @param object - the object to look for
	 * @param howMany - the counter
	 * @return
	 * @author Érick Oliveira Rodrigues (erickr@id.uff.br)
	 */
	private int recursiveVerification(final LinkedMultidimensionalMatrix<T> link, final T object, int howMany){
		if (link.matrix.size() == 0) {
			return link.object.equals(object) ? 1 : 0;
		}
		else{
			for (final int key : link.matrix.keySet()){
				howMany += recursiveVerification(link.matrix.get(key), object, 0);
			}
		}
		return howMany;
	}
}
