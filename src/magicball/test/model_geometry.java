package magicball.test;

import magicball.model.math.*;
import magicball.model.geometry.*;
import magicball.model.geometry.func.*;


public class model_geometry
{
	public static void main( String [] args ) {
		try {

			System.out.println("====== Transformation test ======");
			System.out.println();
			{

				System.out.println("TEST 1:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);

					Number [][] rot = mathEngine.createRotationMatrix(
						mathEngine.vector(new double[]{ 0, 0, 1 }),
						Math.PI/2);
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("rot = rotation([0,0,1],PI/2) = \n" + toString(rot));
					System.out.println("sh = " + toString(sh));

					TransformationMatrixExpression trans0 = transEngine.createIdentityTransformation();
					System.out.println("trans0 = Identity() = \n" + toString(trans0));
					System.out.println("isIdentity(trans0) = " + transEngine.isIdentity(trans0));
					System.out.println("isRotation(trans0) = " + transEngine.isRotation(trans0));
					System.out.println("isShift(trans0) = " + transEngine.isShift(trans0));

					TransformationMatrixExpression trans1 = new TransformationMatrixExpression(rot,mathEngine.vector0(3));
					System.out.println("trans1 = Trans(rot) = \n" + toString(trans1));
					System.out.println("isIdentity(trans1) = " + transEngine.isIdentity(trans1));
					System.out.println("isRotation(trans1) = " + transEngine.isRotation(trans1));
					System.out.println("isShift(trans1) = " + transEngine.isShift(trans1));

					TransformationMatrixExpression trans2 = new TransformationMatrixExpression(mathEngine.matrix1(3),sh);
					System.out.println("trans2 = Trans(sh) = \n" + toString(trans2));
					System.out.println("isIdentity(trans2) = " + transEngine.isIdentity(trans2));
					System.out.println("isRotation(trans2) = " + transEngine.isRotation(trans2));
					System.out.println("isShift(trans2) = " + transEngine.isShift(trans2));

				}
				System.out.println("TEST 1 END");
				System.out.println();

				System.out.println("TEST 2:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);

					Number [][] rot = mathEngine.createRotationMatrix(
						mathEngine.vector(new double[]{ 0, 0, 1 }),
						Math.PI/2);
					System.out.println("rot = rotation([0,0,1],PI/2) = \n" + toString(rot));

					TransformationMatrixExpression trans = new TransformationMatrixExpression(rot,mathEngine.vector0(3));
					System.out.println("trans = Trans(rot) = \n" + toString(trans));

					Function<Number[],Number[]> func = transEngine.createTransformationFunction(trans);
					System.out.println("trans([0,1,0]) = " + toString(func.apply(mathEngine.vector(new double[]{ 0, 1, 0 }))));

				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);

					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("sh = " + toString(sh));

					TransformationMatrixExpression trans = new TransformationMatrixExpression(mathEngine.matrix1(3),sh);
					System.out.println("trans = Trans(sh) = \n" + toString(trans));

					Function<Number[],Number[]> func = transEngine.createTransformationFunction(trans);
					System.out.println("trans([0,1,0]) = " + toString(func.apply(mathEngine.vector(new double[]{ 0, 1, 0 }))));

				}
				System.out.println("TEST 3 END");
				System.out.println();

				System.out.println("TEST 4:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);

					Number [][] rot = mathEngine.createRotationMatrix(
						mathEngine.vector(new double[]{ 0, 0, 1 }),
						Math.PI/2);
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("rot = rotation([0,0,1],PI/2) = \n" + toString(rot));
					System.out.println("sh = " + toString(sh));

					TransformationMatrixExpression trans = new TransformationMatrixExpression(rot,sh);
					System.out.println("trans = Trans(rot,sh) = \n" + toString(trans));

					Function<Number[],Number[]> func = transEngine.createTransformationFunction(trans);
					System.out.println("trans([0,1,0]) = " + toString(func.apply(mathEngine.vector(new double[]{ 0, 1, 0 }))));

				}
				System.out.println("TEST 4 END");
				System.out.println();

				System.out.println("TEST 5:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);
					FunctionBasicEngine funcEngine = new FunctionBasicEngine();

					Number [][] rot = mathEngine.createRotationMatrix(
						mathEngine.vector(new double[]{ 0, 0, 1 }),
						Math.PI/2);
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("rot = rotation([0,0,1],PI/2) = \n" + toString(rot));
					System.out.println("sh = " + toString(sh));

					TransformationMatrixExpression trans0 = new TransformationMatrixExpression(rot,sh);
					System.out.println("trans0 = Trans(rot,sh) = \n" + toString(trans0));

					TransformationMatrixExpression trans1 = new TransformationMatrixExpression(rot,mathEngine.vector0(3));
					System.out.println("trans1 = Trans(rot) = \n" + toString(trans1));

					TransformationMatrixExpression trans2 = new TransformationMatrixExpression(mathEngine.matrix1(3),sh);
					System.out.println("trans2 = Trans(sh) = \n" + toString(trans2));

					TransformationMatrixExpression trans12 = transEngine.compose(trans1,trans2);
					System.out.println("trans12 = trans1 o trans2 = \n" + toString(trans12));
					System.out.println("trans0 == trans12 = " + transEngine.equals(trans0,trans12));

				}
				System.out.println("TEST 5 END");
				System.out.println();

				System.out.println("TEST 6:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);
					FunctionBasicEngine funcEngine = new FunctionBasicEngine();

					Number [][] rot = mathEngine.createRotationMatrix(
						mathEngine.vector(new double[]{ 0, 0, 1 }),
						Math.PI/2);
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("rot = rotation([0,0,1],PI/2) = \n" + toString(rot));
					System.out.println("sh = " + toString(sh));

					TransformationMatrixExpression trans1 = new TransformationMatrixExpression(rot,mathEngine.vector0(3));
					System.out.println("trans1 = Trans(rot) = \n" + toString(trans1));

					TransformationMatrixExpression trans2 = new TransformationMatrixExpression(mathEngine.matrix1(3),sh);
					System.out.println("trans2 = Trans(sh) = \n" + toString(trans2));

					TransformationMatrixExpression trans21 = transEngine.compose(trans2,trans1);
					System.out.println("trans21 = trans2 o trans1 = \n" + toString(trans21));
					Function<Number[],Number[]> func21 = transEngine.createTransformationFunction(trans21);
					Number[] from = mathEngine.vector(new double[]{ 0, 1, 0 });
					Number[] to1 = func21.apply(from);
					System.out.println("trans21([0,1,0]) = " + toString(to1));
					Function<Number[],Number[]> func1 = transEngine.createTransformationFunction(trans1);
					Function<Number[],Number[]> func2 = transEngine.createTransformationFunction(trans2);
					Function<Number[],Number[]> func2func1 = funcEngine.compose(func2,func1);
					Number[] to2 = func2func1.apply(from);
					System.out.println("trans1(trans2([0,1,0])) = " + toString(to2));
					System.out.println("trans21(v) == trans1(trans2(v)) = " + mathEngine.equals(to1,to2));

				}
				System.out.println("TEST 6 END");
				System.out.println();

				System.out.println("TEST 7:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);

					Number [][] rot = mathEngine.createRotationMatrix(
						mathEngine.vector(new double[]{ 0, 0, 1 }),
						Math.PI/2);

					TransformationMatrixExpression trans1 = new TransformationMatrixExpression(rot,mathEngine.vector0(3));
					System.out.println("trans1 = Trans(rot) = \n" + toString(trans1));

					TransformationMatrixExpression trans2 = transEngine.pow(trans1,2);
					System.out.println("trans2 = trans1^2 = \n" + toString(trans2));

					TransformationMatrixExpression trans3 = transEngine.pow(trans1,3);
					System.out.println("trans3 = trans1^3 = \n" + toString(trans3));

					TransformationMatrixExpression trans4 = transEngine.pow(trans1,4);
					System.out.println("trans4 = trans1^4 = \n" + toString(trans4));
					System.out.println("isIdentity(trans4) = \n" + transEngine.isIdentity(trans4));

				}
				System.out.println("TEST 7 END");
				System.out.println();

				System.out.println("TEST 8:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);

					Number [][] rot = mathEngine.createRotationMatrix(
						mathEngine.vector(new double[]{ 0, 0, 1 }),
						Math.PI/2);
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					TransformationMatrixExpression trans1 = new TransformationMatrixExpression(rot,sh);
					System.out.println("trans1 = Trans(rot,sh) = \n" + toString(trans1));

					TransformationMatrixExpression trans_1 = transEngine.invert(trans1);
					System.out.println("trans_1 = invert(trans1) = \n" + toString(trans_1));

					TransformationMatrixExpression trans0 = transEngine.compose(trans1,trans_1);
					System.out.println("trans0 = trans1 o trans_1 = \n" + toString(trans0));
					System.out.println("isIdentity(trans0) = " + transEngine.isIdentity(trans0));

					TransformationMatrixExpression trans0_ = transEngine.compose(trans_1,trans1);
					System.out.println("trans0_ = trans_1 o trans1 = \n" + toString(trans0_));
					System.out.println("isIdentity(trans0_) = " + transEngine.isIdentity(trans0_));

				}
				System.out.println("TEST 8 END");
				System.out.println();

				System.out.println("TEST 9:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);

					Number [][] rot = mathEngine.createRotationMatrix(
						mathEngine.vector(new double[]{ 0, 0, 1 }),
						Math.PI/2);
					System.out.println("rot = rotation([0,0,1],PI/2) = \n" + toString(rot));
					TransformationMatrixExpression transr = new TransformationMatrixExpression(rot,mathEngine.vector0(3));
					System.out.println("transr = Trans(rot) = \n" + toString(transr));

					TransformationMatrixExpression transr_3 = transEngine.dividedBy(transr,mathEngine.number(3));
					System.out.println("transr_3 = divide(transr,3) = \n" + toString(transr_3));
					TransformationMatrixExpression transr_ = transEngine.pow(transr_3,3);
					System.out.println("transr_ = transr_3^3 =  \n" + toString(transr_));
					System.out.println("transr == transr_ = " + transEngine.equals(transr,transr_));

				}
				System.out.println("TEST 9 END");
				System.out.println();

				System.out.println("TEST 10:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);

					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("sh = " + toString(sh));

					TransformationMatrixExpression transs = new TransformationMatrixExpression(mathEngine.matrix1(3),sh);
					System.out.println("transs = Trans(sh) = \n" + toString(transs));

					TransformationMatrixExpression transs_3 = transEngine.dividedBy(transs,mathEngine.number(3));
					System.out.println("transs_3 = divide(transs,3) = \n" + toString(transs_3));
					TransformationMatrixExpression transs_ = transEngine.pow(transs_3,3);
					System.out.println("transs_ = transs_3^3 =  \n" + toString(transs_));
					System.out.println("transs == transs_ = " + transEngine.equals(transs,transs_));

				}
				System.out.println("TEST 10 END");
				System.out.println();

				System.out.println("TEST 11:");
				{
					NumberBasicEngine mathEngine = new NumberBasicEngine(1E-6);
					TransformationEngineForFunc transEngine = new TransformationEngineForFunc(mathEngine);

					Number [][] rot = mathEngine.createRotationMatrix(
						mathEngine.vector(new double[]{ 0, 0, 1 }),
						Math.PI/2);
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("rot = rotation([0,0,1],PI/2) = \n" + toString(rot));
					System.out.println("sh = " + toString(sh));

					TransformationMatrixExpression trans = new TransformationMatrixExpression(rot,sh);
					System.out.println("trans = Trans(rot,sh) = \n" + toString(trans));

					TransformationMatrixExpression trans_3 = transEngine.dividedBy(trans,mathEngine.number(3));
					System.out.println("trans_3 = divide(trans,3) = \n" + toString(trans_3));
					TransformationMatrixExpression trans_ = transEngine.pow(trans_3,3);
					System.out.println("trans_ = trans_3^3 =  \n" + toString(trans_));
					System.out.println("trans == trans_ = " + transEngine.equals(trans,trans_));

				}
				System.out.println("TEST 11 END");
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
		String str = toString(m[0]);
		for ( int i=1; i<m.length; i++ )
			str = str + "\n" + toString(m[i]);
		return str;
	}

	public static String toString( TransformationMatrixExpression trans ) {
		String str = "";
		str = str + "    rotation:\n";
		str = str + toString(trans.getRotationMatrix());
		str = str + "\n";
		str = str + "    shift:\n";
		str = str + toString(trans.getShiftVector());
		str = str + "\n";
		return str;
	}
}
