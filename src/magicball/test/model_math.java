package magicball.test;

import magicball.model.math.*;
import magicball.model.math.func.*;


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
					System.out.println("f(1,2,3) = 9");
					assert f.apply(new Integer[]{1,2,3}) == 9;
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
					System.out.println("g(9) = true");
					assert g.apply(9) == true;
					System.out.println("g(-1) = false");
					assert g.apply(-1) == false;
				}
				System.out.println("TEST 2 END");
				System.out.println();


				System.out.println("TEST 3:");
				{
					FunctionBasicEngine funcEngine = new FunctionBasicEngineForFunc();

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
					System.out.println("h(1,2,3) = true");
					assert h.apply(new Integer[]{1,2,3}) == true;
					System.out.println("h(1,0,3) = false");
					assert h.apply(new Integer[]{1,0,3}) == false;
				}
				System.out.println("TEST 3 END");
				System.out.println();


				System.out.println("TEST 4:");
				{
					FunctionBasicEngine funcEngine = new FunctionBasicEngineForFunc();

					Function<Byte,Byte> s = funcEngine.<Byte>createIdentityFunction();

					System.out.println("s(b) = identity_function");
					System.out.println("s(3) = 3");
					assert s.apply((byte)3) == (byte)3;
					System.out.println("s(32) = 32");
					assert s.apply((byte)32) == (byte)32;
				}
				System.out.println("TEST 4 END");
				System.out.println();
			}

			System.out.println("====== Set test ======");
			System.out.println();
			{
				System.out.println("TEST 1:");
				{
					SetBasicEngine setEngine = new SetBasicEngineForFunc();

					Function<Integer,Boolean> g = new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n > 3);
						}
					};
					Set<Integer> I = setEngine.createSetByIntensionalDefinition(g);

					System.out.println("I = { n | n>3 }");
					System.out.println("I.isElement(4): true");
					assert I.isElement(4) == true;
					System.out.println("I.isElement(-1): false");
					assert I.isElement(-1) == false;
				}
				System.out.println("TEST 1 END");
				System.out.println();

				System.out.println("TEST 2:");
				{
					SetBasicEngine setEngine = new SetBasicEngineForFunc();

					Set<String> S = setEngine.<String>createUniverseSet();

					System.out.println("S = { s | s is String } (UniverseSet)");
					System.out.println("S.isElement(\"ha\"): true");
					assert S.isElement("ha") == true;
					System.out.println("S.isElement(\"false\"): true");
					assert S.isElement("false") == true;
				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{
					SetBasicEngine setEngine = new SetBasicEngineForFunc();

					Set<Boolean> S = setEngine.<Boolean>createEmptySet();

					System.out.println("S = {} (EmptySet)");
					System.out.println("S.isElement(true): false");
					assert S.isElement(true) == false;
					System.out.println("S.isElement(false): false");
					assert S.isElement(false) == false;
				}
				System.out.println("TEST 3 END");
				System.out.println();

				System.out.println("TEST 4:");
				{
					SetBasicEngine setEngine = new SetBasicEngineForFunc();

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

					@SuppressWarnings({"unchecked"})
					Set<Integer> S3 = setEngine.union(S1,S2);

					System.out.println("S1 = [3,11]");
					System.out.println("S2 = [6,20]");
					System.out.println("S3 = union(S1,S2) = [3,20]");
					System.out.println("S3.isElement(4): true");
					assert S3.isElement(4) == true;
					System.out.println("S3.isElement(9): true");
					assert S3.isElement(9) == true;
					System.out.println("S3.isElement(30): false");
					assert S3.isElement(30) == false;
				}
				System.out.println("TEST 4 END");
				System.out.println();

				System.out.println("TEST 5:");
				{
					SetBasicEngine setEngine = new SetBasicEngineForFunc();

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

					@SuppressWarnings({"unchecked"})
					Set<Integer> S3 = setEngine.intersect(S1,S2);

					System.out.println("S1 = [3,11]");
					System.out.println("S2 = [6,20]");
					System.out.println("S3 = intersect(S1,S2) = [6,11]");
					System.out.println("S3.isElement(4): false");
					assert S3.isElement(4) == false;
					System.out.println("S3.isElement(9): true");
					assert S3.isElement(9) == true;
					System.out.println("S3.isElement(30): false");
					assert S3.isElement(30) == false;
				}
				System.out.println("TEST 5 END");
				System.out.println();

				System.out.println("TEST 6:");
				{
					SetBasicEngine setEngine = new SetBasicEngineForFunc();

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
					System.out.println("S3.isElement(4): true");
					assert S3.isElement(4) == true;
					System.out.println("S3.isElement(9): false");
					assert S3.isElement(9) == false;
					System.out.println("S3.isElement(30): false");
					assert S3.isElement(30) == false;
				}
				System.out.println("TEST 6 END");
				System.out.println();

				System.out.println("TEST 7:");
				{
					SetBasicEngine setEngine = new SetBasicEngineForFunc();

					Set<Integer> S1 = setEngine.createSetByIntensionalDefinition( new Function<Integer,Boolean>() {
						public Boolean apply( Integer n ) {
							return (n >= 3) && (n <= 11);
						}
					});

					Set<Integer> S3 = setEngine.complement(S1);

					System.out.println("S1 = [3,11]");
					System.out.println("S3 = complement(S1) = (-inf,3),(11,inf)");
					System.out.println("S3.isElement(4): false");
					assert S3.isElement(4) == false;
					System.out.println("S3.isElement(9): false");
					assert S3.isElement(9) == false;
					System.out.println("S3.isElement(30): true");
					assert S3.isElement(30) == true;
				}
				System.out.println("TEST 7 END");
				System.out.println();
			}

			System.out.println("====== Number test ======");
			System.out.println();
			{
				System.out.println("TEST 1:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

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
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number n1 = math.number(1.0/3*3);
					System.out.println("n1 = number(1.0/3*3) = " + n1);
					System.out.println("n1 == number1(): " + math.equals(n1,math.number1()));
				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number n1 = math.number(0.5);
					Number n2 = math.number(2.0);
					System.out.println("0.5 > 2.0: false");
					assert math.greaterThan(n1,n2) == false;
					System.out.println("0.5 < 2.0: true");
					assert math.lessThan(n1,n2) == true;
				}
				System.out.println("TEST 3 END");
				System.out.println();

				System.out.println("TEST 4:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number n1 = math.number(0.4);
					Number n2 = math.number(32);
					System.out.println("n1 = number(0.4) = " + n1);
					System.out.println("n2 = number(32) = " + n2);

					System.out.println("-0.4 = -0.4");
					assert math.equals(math.negate(n1),-0.4);
					System.out.println("0.4 + 32 = 32.4");
					assert math.equals(math.add(n1,n2),32.4);
					System.out.println("0.4 - 32 = -31.6");
					assert math.equals(math.subtract(n1,n2),-31.6);
					System.out.println("0.4 * 32 = 12.8");
					assert math.equals(math.multiply(n1,n2),12.8);
					System.out.println("0.4 / 32 = 0.0125");
					assert math.equals(math.dividedBy(n1,n2),0.0125);
					System.out.println("0.4 ^ 3 = 0.064");
					assert math.equals(math.pow(n1,3),0.064);
					System.out.println("_/0.4 = 0.632455532");
					assert math.equals(math.sqrt(n1),0.632455532);
				}
				System.out.println("TEST 4 END");
				System.out.println();
			}

			System.out.println("====== Vector test ======");
			System.out.println();
			{
				System.out.println("TEST 1:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[] v1 = math.vector(2.4,3,-0.5);
					Number[] v2 = math.vector0(3);
					System.out.println("v1 = vector(2.4,3,-0.5) = " + toString(v1));
					System.out.println("v2 = vector0(3) = " + toString(v2));
				}
				System.out.println("TEST 1 END");
				System.out.println();
				
				System.out.println("TEST 2:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[] v1 = math.vector(1.0/3*3-1,1.0/9*9-1);
					System.out.println("v1 = vector(1.0/3*3-1,1.0/9*9-1) = " + toString(v1));
					System.out.println("v1 == vector0(): " + math.equals(v1,math.vector0(2)));
				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[] v1 = math.vector(0.4,2,-1);
					Number[] v2 = math.vector(0,-1,1);
					Number n3 = math.number(32);

					System.out.println("-[0.4,2,-1] = [-0.4,-2,1]");
					assert math.equals(math.negate(v1),new double[]{-0.4,-2,1});
					System.out.println("[0.4,2,-1] + [0,-1,1] = [0.4,1,0]");
					assert math.equals(math.add(v1,v2),new double[]{0.4,1,0});
					System.out.println("[0.4,2,-1] - [0,-1,1] = [0.4,3,-2]");
					assert math.equals(math.subtract(v1,v2),new double[]{0.4,3,-2});
					System.out.println("[0.4,2,-1] * 32 = [12.8,64,-32]");
					assert math.equals(math.multiply(v1,n3),new double[]{12.8,64,-32});
					System.out.println("[0.4,2,-1] / 32 = [0.0125,0.0625,-0.03125]");
					assert math.equals(math.dividedBy(v1,n3),new double[]{0.0125,0.0625,-0.03125});
				}
				System.out.println("TEST 3 END");
				System.out.println();

				System.out.println("TEST 4:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[] v1 = math.vector(1,0,1);
					Number[] v2 = math.vector(0,-1,1);

					System.out.println("|[1,0,1]| =  1.41421356");
					assert math.equals(math.norm(v1),1.41421356);
					System.out.println("[1,0,1]^ = [0.70710678,0,0.70710678]");
					assert math.equals(math.normalize(v1),new double[]{0.70710678,0,0.70710678});
					System.out.println("[1,0,1] . [0,-1,1] = 1");
					assert math.equals(math.dotProduct(v1,v2),1.0);
					System.out.println("[1,0,1] x [0,-1,1] = [1,-1,-1]");
					assert math.equals(math.crossProduct(v1,v2),new double[]{1,-1,-1});
				}
				System.out.println("TEST 4 END");
				System.out.println();
			}

			System.out.println("====== Matrix test ======");
			System.out.println();
			{
				System.out.println("TEST 1:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

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
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{1.0/3*3,0.0},{0.0,1.0/9*9}});
					System.out.println("m1 = matrix({{1.0/3*3,0.0},{0.0,1.0/9*9}}) = " + toString(m1));
					System.out.println("m1 == matrix1(): " + math.equals(m1,math.matrix1(2)));
				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{0.4,2},{-1,5}});
					Number[][] m2 = math.matrix(new double[][]{{0,-1},{1,2}});
					Number n3 = math.number(32);
					System.out.println("m1 = " + toString(m1));
					System.out.println("m2 = " + toString(m2));

					System.out.println("-m1 = " + toString(new double[][]{{-0.4,-2},{1,-5}}));
					assert math.equals(math.negate(m1),new double[][]{{-0.4,-2},{1,-5}});
					System.out.println("m1 + m2 = " + toString(new double[][]{{0.4,1},{0,7}}));
					assert math.equals(math.add(m1,m2),new double[][]{{0.4,1},{0,7}});
					System.out.println("m1 - m2 = " + toString(new double[][]{{0.4,3},{-2,3}}));
					assert math.equals(math.subtract(m1,m2),new double[][]{{0.4,3},{-2,3}});
					System.out.println("m1 * 32 = " + toString(new double[][]{{12.8,64},{-32,160}}));
					assert math.equals(math.multiply(m1,32),new double[][]{{12.8,64},{-32,160}});
					System.out.println("m1 / 32 = " + toString(new double[][]{{0.0125,0.0625},{-0.03125,0.15625}}));
					assert math.equals(math.dividedBy(m1,32),new double[][]{{0.0125,0.0625},{-0.03125,0.15625}});
				}
				System.out.println("TEST 3 END");
				System.out.println();

				System.out.println("TEST 4:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{0.4,2},{-1,5},{1,1}});
					Number[][] m2 = math.matrix(new double[][]{{0,-1,3,0.2},{1,-1,0,2}});
					System.out.println("m1 = " + toString(m1));
					System.out.println("m2 = " + toString(m2));

					System.out.println("m1^t = " + toString(new double[][]{{0.4,-1,1},{2,5,1}}));
					assert math.equals(math.transpose(m1),new double[][]{{0.4,-1,1},{2,5,1}});
					System.out.println("m1 * m2 = " + toString(new double[][]{{2,-2.4,1.2,4.08},{5,-4,-3,9.8},{1,-2,3,2.2}}));
					assert math.equals(math.matrixMultiply(m1,m2),new double[][]{{2,-2.4,1.2,4.08},{5,-4,-3,9.8},{1,-2,3,2.2}});
				}
				System.out.println("TEST 4 END");
				System.out.println();

				System.out.println("TEST 5:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{0.4,3,2},{-1,5,5},{0,1,1}});
					Number[] v2 = math.vector(2,3,4);
					System.out.println("m1 = " + toString(m1));
					System.out.println("v2 = " + toString(v2));

					System.out.println("m1^3 = " + toString(new double[][]{{-19.336,100.28,103.12},{-29.16,146.8,152.2},{-6.4,33.0,34.0}}));
					assert math.equals(math.pow(m1,3),new double[][]{{-19.336,100.28,103.12},{-29.16,146.8,152.2},{-6.4,33.0,34.0}});
					System.out.println("m1 * v2 = " + toString(new double[]{17.8,33.0,7.0}));
					assert math.equals(math.matrixMultiply(m1,v2),new double[]{17.8,33.0,7.0});
					System.out.println("v2 * m1 = " + toString(new double[]{-2.2,25.0,23.0}));
					assert math.equals(math.matrixMultiply(v2,m1),new double[]{-2.2,25.0,23.0});
					System.out.println("tr(m1) = 6.4");
					assert math.equals(math.trace(m1),6.4);
					System.out.println("det(m1) = 1");
					assert math.equals(math.determinant33(m1),1.0);
					System.out.println("m1^-1 = " + toString(new double[][]{{0.0,-1.0,5.0},{1.0,0.4,-4.0},{-1.0,-0.4,5.0}}));
					assert math.equals(math.invert33(m1),new double[][]{{0.0,-1.0,5.0},{1.0,0.4,-4.0},{-1.0,-0.4,5.0}});
				}
				System.out.println("TEST 5 END");
				System.out.println();

				System.out.println("TEST 6:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{0.4,3,2},{-1,5,5},{0,1,1}});
					Number[] v2 = math.vector(2,3,4);
					System.out.println("m1 = " + toString(m1));
					System.out.println("v2 = " + toString(v2));

					Number[] v1 = math.matrixMultiply(m1,v2);
					System.out.println("m1 * v2 = v1 = " + toString(v1));
					
					Number[] v2_ = math.solveByLU(m1,v1);
					System.out.println("v2_ = solve(m1,v1) = " + toString(v2_));
					assert math.equals(v2,v2_);
				}
				System.out.println("TEST 6 END");
				System.out.println();

				System.out.println("TEST 7:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[][] m1 = math.matrix(new double[][]{{0.4,3,2,5},{-1,5,5,2.3},{0,1,1,7}});
					System.out.println("m1 = " + toString(m1));

					Number[][] m2 = math.solveByGauss(m1);
					System.out.println("m2 = solve(m1) = " + toString(new double[][]{{1,0,0,32.7},{0,1,0,-22.08},{0,0,1,29.08}}));
					assert math.equals(m2,new double[][]{{1,0,0,32.7},{0,1,0,-22.08},{0,0,1,29.08}});
				}
				System.out.println("TEST 7 END");
				System.out.println();

				System.out.println("TEST 8:");
				{
					NumberBasicEngine math = new NumberBasicEngineForFunc(1E-6);

					Number[] axis = math.normalize(math.vector(0,1,1));
					Number angle = (Double)(Math.PI/3);
					Number[] rvec = math.multiply(axis,angle);
					System.out.println("rvec = PI/3*[0,1,1]^ = " + toString(rvec));

					Number[][] rmat = math.rotationVector2RotationMatrix(rvec);
					System.out.println("rmat(rvec) = " + toString(rmat));

					Number[] rvec_ = math.rotationMatrix2RotationVector(rmat);
					System.out.println("rvec(rmat) = " + toString(rvec_));
					assert math.equals(rvec,rvec_);
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

	public static String toString( double [] v ) {
		String str = "[ " + v[0];
		for ( int i=1; i<v.length; i++ )
			str = str + ", " + v[i];
		str = str + " ]";
		return str;
	}

	public static String toString( double [][] m ) {
		String str = "";
		for ( int i=0; i<m.length; i++ )
			str = str + "\n" + toString(m[i]);
		return str;
	}
}
