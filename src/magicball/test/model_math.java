package magicball.test;

import magicball.model.math.*;


public class model_math
{
	public static void main( String [] args ) {
		try {

			System.out.println("====== Function test ======");
			System.out.println();
			{

				System.out.println("TEST 1:");
				{
					Function<Integer[],Integer> f = new Function<Integer[],Integer>() {
						public Integer apply( Integer[] v ) {
							return v[0]*2 + v[1]*5 - v[2];
						}
					};
					System.out.println("f(x,y,z) = 2x+5y-z");
					System.out.println("f(1,2,3) = "+f.apply(new Integer[]{1,2,3}));
					System.out.println("f(1,0,3,5) = "+f.apply(new Integer[]{1,0,3,5})); // no exception, but illegal
					// System.out.println("f(1,0) = "+f.apply(new Integer[]{1,0})); // error
				}
				System.out.println("TEST 1 END");
				System.out.println();


				System.out.println("TEST 2:");
				{
					Function<Integer,Boolean> g = new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n > 3);
						}
					};
					System.out.println("g(n) = n>3");
					System.out.println("g(9) = "+g.apply(9));
					System.out.println("g(-1) = "+g.apply(-1));
				}
				System.out.println("TEST 2 END");
				System.out.println();


				System.out.println("TEST 3:");
				{
					FunctionBasicEngine funcEngine = new FunctionBasicEngine();

					Function<Integer[],Integer> f = new Function<Integer[],Integer>() {
						public Integer apply( Integer[] v ) {
							return v[0]*2 + v[1]*5 - v[2];
						}
					};
					Function<Integer,Boolean> g = new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n > 3);
						}
					};
					Function<Integer[],Boolean> h = funcEngine.compose(f,g);

					System.out.println("h(x,y,z) = g(f(x,y,z))");
					System.out.println("h(1,2,3) = "+h.apply(new Integer[]{1,2,3}));
					System.out.println("h(1,0,3) = "+h.apply(new Integer[]{1,0,3}));
				}
				System.out.println("TEST 3 END");
				System.out.println();


				System.out.println("TEST 4:");
				{
					FunctionBasicEngine funcEngine = new FunctionBasicEngine();

					Function<Byte,Byte> s = funcEngine.<Byte>createIdentityFunction();

					System.out.println("s(b) = identity_function");
					System.out.println("s(3) = "+s.apply((byte)3));
					System.out.println("s(32) = "+s.apply((byte)32));
				}
				System.out.println("TEST 4 END");
				System.out.println();
			}

			System.out.println("====== Set test ======");
			System.out.println();
			{
				System.out.println("TEST 1:");
				{
					SetBasicEngine setEngine = new SetBasicEngine();

					Function<Integer,Boolean> g = new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n > 3);
						}
					};
					Set<Integer> I = setEngine.createSetByIntensionalDefinition(g);

					System.out.println("I = { n | n>3 }");
					System.out.println("I.isElement(4): "+I.isElement(4));
					System.out.println("I.isElement(-1): "+I.isElement(-1));
				}
				System.out.println("TEST 1 END");
				System.out.println();

				System.out.println("TEST 2:");
				{
					SetBasicEngine setEngine = new SetBasicEngine();

					Set<String> S = setEngine.<String>createUniverseSet();

					System.out.println("S = { s | s is String } (UniverseSet)");
					System.out.println("S.isElement(\"ha\"): "+S.isElement("ha"));
					System.out.println("S.isElement(\"false\"): "+S.isElement("false"));
				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{
					SetBasicEngine setEngine = new SetBasicEngine();

					Set<Boolean> S = setEngine.<Boolean>createEmptySet();

					System.out.println("S = {} (EmptySet)");
					System.out.println("S.isElement(true): "+S.isElement(true));
					System.out.println("S.isElement(false): "+S.isElement(false));
				}
				System.out.println("TEST 3 END");
				System.out.println();

				System.out.println("TEST 4:");
				{
					SetBasicEngine setEngine = new SetBasicEngine();

					Set<Integer> S1 = setEngine.createSetByIntensionalDefinition( new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n >= 3) && (n <= 11);
						}
					});

					Set<Integer> S2 = setEngine.createSetByIntensionalDefinition( new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n >= 6) && (n <= 20);
						}
					});

					Set<Integer> S3 = setEngine.union(S1,S2);

					System.out.println("S1 = [3,11]");
					System.out.println("S2 = [6,20]");
					System.out.println("S3 = union(S1,S2) = [3,20]");
					System.out.println("S3.isElement(4): "+S3.isElement(4));
					System.out.println("S3.isElement(9): "+S3.isElement(9));
					System.out.println("S3.isElement(30): "+S3.isElement(30));
				}
				System.out.println("TEST 4 END");
				System.out.println();

				System.out.println("TEST 5:");
				{
					SetBasicEngine setEngine = new SetBasicEngine();

					Set<Integer> S1 = setEngine.createSetByIntensionalDefinition( new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n >= 3) && (n <= 11);
						}
					});

					Set<Integer> S2 = setEngine.createSetByIntensionalDefinition( new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n >= 6) && (n <= 20);
						}
					});

					Set<Integer> S3 = setEngine.intersect(S1,S2);

					System.out.println("S1 = [3,11]");
					System.out.println("S2 = [6,20]");
					System.out.println("S3 = intersect(S1,S2) = [6,11]");
					System.out.println("S3.isElement(4): "+S3.isElement(4));
					System.out.println("S3.isElement(9): "+S3.isElement(9));
					System.out.println("S3.isElement(30): "+S3.isElement(30));
				}
				System.out.println("TEST 5 END");
				System.out.println();

				System.out.println("TEST 6:");
				{
					SetBasicEngine setEngine = new SetBasicEngine();

					Set<Integer> S1 = setEngine.createSetByIntensionalDefinition( new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n >= 3) && (n <= 11);
						}
					});

					Set<Integer> S2 = setEngine.createSetByIntensionalDefinition( new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n >= 6) && (n <= 20);
						}
					});

					Set<Integer> S3 = setEngine.complement(S1,S2);

					System.out.println("S1 = [3,11]");
					System.out.println("S2 = [6,20]");
					System.out.println("S3 = complement(S1,S2) = [3,6]");
					System.out.println("S3.isElement(4): "+S3.isElement(4));
					System.out.println("S3.isElement(9): "+S3.isElement(9));
					System.out.println("S3.isElement(30): "+S3.isElement(30));
				}
				System.out.println("TEST 6 END");
				System.out.println();

				System.out.println("TEST 7:");
				{
					SetBasicEngine setEngine = new SetBasicEngine();

					Set<Integer> S1 = setEngine.createSetByIntensionalDefinition( new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n >= 3) && (n <= 11);
						}
					});

					Set<Integer> S3 = setEngine.complement(S1);

					System.out.println("S1 = [3,11]");
					System.out.println("S3 = complement(S1) = (-inf,3),(11,inf)");
					System.out.println("S3.isElement(4): "+S3.isElement(4));
					System.out.println("S3.isElement(9): "+S3.isElement(9));
					System.out.println("S3.isElement(30): "+S3.isElement(30));
				}
				System.out.println("TEST 7 END");
				System.out.println();
			}

			System.out.println("====== Number test ======");
			System.out.println();
			{
				System.out.println("TEST 1:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number n1 = math.number(2.4);
					Number n2 = math.number1();
					Number n3 = math.number0();
					System.out.println("n1 = number(2.4) = " + n1);
					System.out.println("n2 = number1() = " + n2);
					System.out.println("n3 = number0() = " + n3);

				}
				System.out.println("TEST 1 END");
				System.out.println();
				
				System.out.println("TEST 2:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number n1 = math.number(1.0/3*3);
					System.out.println("n1 = number(1.0/3*3) = " + n1);
					System.out.println("n1 == number1(): " + math.equals(n1,math.number1()));
				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number n1 = math.number(0.5);
					Number n2 = math.number(2.0);
					System.out.println("n1 = number(0.5) = " + n1);
					System.out.println("n2 = number(2.0) = " + n2);
					System.out.println("n1 > n2: " + math.greaterThan(n1,n2));
					System.out.println("n1 < n2: " + math.lessThan(n1,n2));
				}
				System.out.println("TEST 3 END");
				System.out.println();

				System.out.println("TEST 4:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number n1 = math.number(0.4);
					Number n2 = math.number(32);
					System.out.println("n1 = number(0.4) = " + n1);
					System.out.println("n2 = number(32) = " + n2);

					System.out.println("-n1 = " + math.negate(n1));
					System.out.println("n1 + n2 = " + math.add(n1,n2));
					System.out.println("n1 - n2 = " + math.subtract(n1,n2));
					System.out.println("n1 * n2 = " + math.multiply(n1,n2));
					System.out.println("n1 / n2 = " + math.dividedBy(n1,n2));
					System.out.println("n1 ^ 3 = " + math.pow(n1,3));
					System.out.println("n1 ^ n2 = " + math.pow(n1,n2));
					System.out.println("n1 ^ 32 = " + math.pow(n1,n2));
					System.out.println("_/n1 = " + math.sqrt(n1));
				}
				System.out.println("TEST 4 END");
				System.out.println();
			}

			System.out.println("====== Vector test ======");
			System.out.println();
			{
				System.out.println("TEST 1:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[] v1 = math.vector(2.4,3,-0.5);
					Number[] v2 = math.vector0(3);
					System.out.println("v1 = vector(2.4,3,-0.5) = " + toString(v1));
					System.out.println("v2 = vector0(3) = " + toString(v2));
				}
				System.out.println("TEST 1 END");
				System.out.println();
				
				System.out.println("TEST 2:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[] v1 = math.vector(1.0/3*3-1,1.0/9*9-1);
					System.out.println("v1 = vector(1.0/3*3-1,1.0/9*9-1) = " + toString(v1));
					System.out.println("v1 == vector0(): " + math.equals(v1,math.vector0(2)));
				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[] v1 = math.vector(0.4,2,-1);
					Number[] v2 = math.vector(0,-1,1);
					Number n3 = math.number(32);
					System.out.println("v1 = vector(0.4,2,-1) = " + toString(v1));
					System.out.println("v2 = vector(0,-1,1) = " + toString(v2));
					System.out.println("n3 = number(32) = " + n3);

					System.out.println("-v1 = " + toString(math.negate(v1)));
					System.out.println("v1 + v2 = " + toString(math.add(v1,v2)));
					System.out.println("v1 - v2 = " + toString(math.subtract(v1,v2)));
					System.out.println("v1 * n3 = " + toString(math.multiply(v1,n3)));
					System.out.println("v1 / n3 = " + toString(math.dividedBy(v1,n3)));
				}
				System.out.println("TEST 3 END");
				System.out.println();

				System.out.println("TEST 4:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[] v1 = math.vector(1,0,1);
					Number[] v2 = math.vector(0,-1,1);
					System.out.println("v1 = vector(1,0,1) = " + toString(v1));
					System.out.println("v2 = vector(0,-1,1) = " + toString(v2));

					System.out.println("|v1| = " + math.norm(v1));
					System.out.println("v1^ = " + toString(math.normalize(v1)));
					System.out.println("v1 . v2 = " + math.dotProduct(v1,v2));
					System.out.println("v1 x v2 = " + toString(math.crossProduct(v1,v2)));
				}
				System.out.println("TEST 4 END");
				System.out.println();
			}

			System.out.println("====== Matrix test ======");
			System.out.println();
			{
				System.out.println("TEST 1:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{1,2},{3,4}});
					Number[][] m2 = math.matrix0(3,2);
					Number[][] m3 = math.matrix1(3);
					System.out.println("m1 = matrix({{1,2},{3,4}}) = " + toString(m1));
					System.out.println("m2 = matrix0(3,2) = " + toString(m2));
					System.out.println("m3 = matrix1(3) = " + toString(m3));
				}
				System.out.println("TEST 1 END");
				System.out.println();
				
				System.out.println("TEST 2:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{1.0/3*3,0.0},{0.0,1.0/9*9}});
					System.out.println("m1 = matrix({{1.0/3*3,0.0},{0.0,1.0/9*9}}) = " + toString(m1));
					System.out.println("m1 == matrix1(): " + math.equals(m1,math.matrix1(2)));
				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{0.4,2},{-1,5}});
					Number[][] m2 = math.matrix(new double[][]{{0,-1},{1,2}});
					Number n3 = math.number(32);
					System.out.println("m1 = matrix({{0.4,2},{-1,5}}) = " + toString(m1));
					System.out.println("m2 = matrix({{0,-1},{1,2}}) = " + toString(m2));
					System.out.println("n3 = number(32) = " + n3);

					System.out.println("-m1 = " + toString(math.negate(m1)));
					System.out.println("m1 + m2 = " + toString(math.add(m1,m2)));
					System.out.println("m1 - m2 = " + toString(math.subtract(m1,m2)));
					System.out.println("m1 * n3 = " + toString(math.multiply(m1,n3)));
					System.out.println("m1 / n3 = " + toString(math.dividedBy(m1,n3)));
				}
				System.out.println("TEST 3 END");
				System.out.println();

				System.out.println("TEST 4:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{0.4,2},{-1,5},{1,1}});
					Number[][] m2 = math.matrix(new double[][]{{0,-1,3,0.2},{1,-1,0,2}});
					System.out.println("m1 = matrix({{0.4,2},{-1,5},{1,1}}) = " + toString(m1));
					System.out.println("m2 = matrix({{0,-1,3,0.2},{1,-1,0,2}}) = " + toString(m2));

					System.out.println("m1^t = " + toString(math.transpose(m1)));
					System.out.println("m1 * m2 = " + toString(math.matrixMultiply(m1,m2)));
				}
				System.out.println("TEST 4 END");
				System.out.println();

				System.out.println("TEST 5:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{0.4,3,2},{-1,5,5},{0,1,1}});
					Number[] v2 = math.vector(2,3,4);
					System.out.println("m1 = matrix({{0.4,3,2},{-1,5,5},{0,1,1}}) = " + toString(m1));
					System.out.println("v2 = vector(2,3,4) = " + toString(v2));

					System.out.println("m1^3 = " + toString(math.pow(m1,3)));
					System.out.println("m1 * v2 = " + toString(math.matrixMultiply(m1,v2)));
					System.out.println("v2 * m1 = " + toString(math.matrixMultiply(v2,m1)));
					System.out.println("tr(m1) = " + math.trace(m1));
					System.out.println("det(m1) = " + math.determinant33(m1));
					System.out.println("m1^-1 = " + toString(math.invert33(m1)));
					System.out.println("m1^-1 * m1 = " + toString(math.matrixMultiply(math.invert33(m1),m1)));
				}
				System.out.println("TEST 5 END");
				System.out.println();

				System.out.println("TEST 6:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{0.4,3,2},{-1,5,5},{0,1,1}});
					Number[] v2 = math.vector(2,3,4);
					System.out.println("m1 = matrix({{0.4,3,2},{-1,5,5},{0,1,1}}) = " + toString(m1));
					System.out.println("v2 = vector(2,3,4) = " + toString(v2));

					Number[] v1 = math.matrixMultiply(m1,v2);
					System.out.println("m1 * v2 = v1 = " + toString(v1));
					
					Number[] v2_ = math.solveByLU(m1,v1);
					System.out.println("v2 - m1^-1 * v1 = " + toString(math.subtract(v2,v2_)));
				}
				System.out.println("TEST 6 END");
				System.out.println();

				System.out.println("TEST 7:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{0.4,3,2,5},{-1,5,5,2.3},{0,1,1,7}});
					System.out.println("m1 = matrix({{0.4,3,2,5},{-1,5,5,2.3},{0,1,1,7}}) = " + toString(m1));

					Number[][] m2 = math.solveByGauss(m1);
					System.out.println("m2 = solve(m1) = " + toString(m2));
				}
				System.out.println("TEST 7 END");
				System.out.println();

				System.out.println("TEST 8:");
				{
					NumberBasicEngine math = new NumberBasicEngine(1E-6);

					Number[] axis = math.normalize(math.vector(0,1,1));
					Number angle = (Double)(Math.PI/3);
					System.out.println("axis = (0,1,1)^ = " + toString(axis));
					System.out.println("angle = PI/3 = " + angle);

					Number[][] r = math.createRotationMatrix(axis,angle);
					System.out.println("r = rotation(axis,angle) = " + toString(r));
					
					Number[] axis_ = math.axisOfRotationMatrix(r);
					Number angle_ = math.angleOfRotationMatrix(r,axis_);
					System.out.println("r.axis = " + toString(axis_));
					System.out.println("r.angle = " + angle_);
				}
				System.out.println("TEST 8 END");
				System.out.println();
			}

		} catch ( Throwable e ) {
			e.printStackTrace();
		}
	}

	public static String toString( Number [] v ) {
		String str = "[ " + v[0];
		for ( int i=1; i<v.length; i++ )
			str = str + ", " + v[i];
		str = str + " ]";
		return str;
	}

	public static String toString( Number [][] m ) {
		String str = "";
		for ( int i=0; i<m.length; i++ )
			str = str + "\n" + toString(m[i]);
		return str;
	}
}
