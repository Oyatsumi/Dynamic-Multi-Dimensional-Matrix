import linear.BooleanMultidimensionalMatrix;
import linear.MultidimensionalMatrix;

public class TestMatrix {

	public static void main(String[] args){
		
		final int K = 10;
		int[] sizes = new int[K];
		for (int k=0; k<K; k++){
			sizes[k] = k + 1;
		}
		
		int[] position = new int[K];
		
		double start = System.nanoTime();
		
		//change here to what matrix you want
		BooleanMultidimensionalMatrix m = new BooleanMultidimensionalMatrix(sizes);
		//LinkedMultidimensionalMatrix<Boolean> m = new LinkedMultidimensionalMatrix<Boolean>();
		//MultidimensionalMatrix<Boolean> m = new MultidimensionalMatrix<Boolean>(sizes);
		
		
		//a simple, small test
		for (int a=0; a<sizes[0]; a++){
			position[0] = a;
			for (int b=0; b<sizes[1]; b++){
				position[1] = b;
				for (int c=0; c<sizes[2]; c++){
					position[2] = c;
					for (int d=0; d<sizes[3]; d++){
						position[3] = d;
						for (int e=0; e<sizes[4]; e++){
							position[4] = e;
							for (int f=0; f<sizes[5]/3; f++){
								position[5] = f;
								for (int g=0; g<sizes[6]/2; g++){
									position[6] = g;
									for (int h=0; h<sizes[7]; h++){
										position[7] = h;
										for (int i=0; i<sizes[8]; i++){
											position[8] = i;
											for (int j=0; j <sizes[9]/2; j++){
												position[9] = j;
												
												try{
												m.set(position, true);
												}catch(Exception o){
													o.printStackTrace();
													System.out.println(a + "," + b + "," + c + "," + d + "," + e + "," + f + "," + g + "," + h + "," + i + "," + j);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
	
		
		System.out.println(m.getHowManyEquals(true));
		
		System.out.println((System.nanoTime() - start)/1000000000.0);
		
		//m.printLinearMatrix();
	}
	
	
	
}
