package magicball.test;

import magicball.model.math.*;
import magicball.model.math.func.*;


public class model_sample
{
	public static void main( String [] args ) {
		try {

			System.out.println("====== Set test ======");
			System.out.println();
			{

				System.out.println("TEST 1:");
				{
					java.util.Set<Integer> sam = new java.util.HashSet<Integer>();
					for ( int i=-100; i<100; i++ )
						sam.add(i);
					SetEngine setEngine = new SetEngineSampleAlgorithm<Integer>(sam);

					Set<Integer> S1 = setEngine.createSetByFunction( new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n >= 6) && (n <= 11);
						}
					});

					Set<Integer> S2 = setEngine.createSetByFunction( new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n >= 3) && (n <= 20);
						}
					});

					Set<Integer> S3 = setEngine.union(S1,S2);

					System.out.println("S1 = [6,11]");
					System.out.println("S2 = [3,20]");
					System.out.println("S2.contain(S1): true");
					assert setEngine.containsAll(S2,S1) == true;
					System.out.println("S1.isEmpty(): false");
					assert setEngine.isEmpty(S1) == false;
					System.out.println("S2.isUniversal(): false");
					assert setEngine.isUniversal(S2) == false;
					System.out.println("S1.equal(S2): false");
					assert setEngine.equals(S1,S2) == false;
					System.out.println("S1.equal(S1): true");
					assert setEngine.equals(S1,S1) == true;
					System.out.println("empty.isEmpty(): true");
					assert setEngine.isEmpty(setEngine.<Integer>createEmptySet()) == true;
					System.out.println("universe.isUniversal(): true");
					assert setEngine.isUniversal(setEngine.<Integer>createUniversalSet()) == true;

				}
				System.out.println("TEST 1 END");
				System.out.println();

			}

		} catch ( Throwable e ) {
			e.printStackTrace();
		}
	}
}
