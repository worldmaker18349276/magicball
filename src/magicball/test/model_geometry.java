package magicball.test;

import magicball.model.math.*;
import magicball.model.math.basic.*;
import magicball.model.geometry.*;
import magicball.model.geometry.basic.*;
import magicball.model.geometry.affine.*;


public class model_geometry
{
	public static void main( String [] args ) {
		try {

			System.out.println("====== Transformation test ======");
			System.out.println();
			{

				System.out.println("TEST 1:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);


					Number [] rvec = mathEngine.vector(new double[]{ 0, 0, Math.PI/2 });
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("rvec = " + toString(rvec));
					System.out.println("sh = " + toString(sh));

					Transformation trans0 = transEngine.createIdentityTransformation();
					System.out.println("trans0 = Identity() = \n" + toString(trans0));
					System.out.println("isIdentity(trans0) = true");
					assert transEngine.isIdentity(trans0) == true;
					System.out.println("isTranslation(trans0) = true");
					assert transEngine.isTranslation(trans0) == true;

					Transformation trans1 = transEngine.createRotationByVector(rvec);
					System.out.println("trans1 = Trans(rvec) = \n" + toString(trans1));
					System.out.println("isIdentity(trans1) = false");
					assert transEngine.isIdentity(trans1) == false;
					System.out.println("isTranslation(trans1) = false");
					assert transEngine.isTranslation(trans1) == false;

					Transformation trans2 = transEngine.createTranslationByVector(sh);
					System.out.println("trans2 = Trans(sh) = \n" + toString(trans2));
					System.out.println("isIdentity(trans2) = false");
					assert transEngine.isIdentity(trans2) == false;
					System.out.println("isTranslation(trans2) = true");
					assert transEngine.isTranslation(trans2) == true;

				}
				System.out.println("TEST 1 END");
				System.out.println();

				System.out.println("TEST 2:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);


					Number [] rvec = mathEngine.vector(new double[]{ 0, 0, Math.PI/2 });
					System.out.println("rvec = " + toString(rvec));

					Transformation trans = transEngine.createRotationByVector(rvec);
					System.out.println("trans = Trans(rvec) = \n" + toString(trans));

					Function<Number[],Number[]> func = transEngine.getTransformationFunction(trans);
					System.out.println("trans([0,1,0]) = [ -1, 0, 0 ]");
					Number[] vec = mathEngine.vector(new double[]{ -1, 0, 0 });
					Number[] vec_ = funcEngine.applies(func,mathEngine.vector(new double[]{ 0, 1, 0 }));
					assert mathEngine.equals(vec_,vec);

				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);


					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("sh = " + toString(sh));

					Transformation trans = transEngine.createTranslationByVector(sh);
					System.out.println("trans = Trans(sh) = \n" + toString(trans));

					Function<Number[],Number[]> func = transEngine.getTransformationFunction(trans);
					System.out.println("trans([0,1,0]) = [ 1, 1, 0 ]");
					Number[] vec = mathEngine.vector(new double[]{ 1, 1, 0 });
					Number[] vec_ = funcEngine.applies(func,mathEngine.vector(new double[]{ 0, 1, 0 }));
					assert mathEngine.equals(vec,vec_);

				}
				System.out.println("TEST 3 END");
				System.out.println();

				System.out.println("TEST 4:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);


					Number[] rvec = mathEngine.vector(new double[]{ 0, 0, Math.PI/2 });
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("rvec = " + toString(rvec));
					System.out.println("sh = " + toString(sh));

					Transformation trans = transEngine.compose(transEngine.createRotationByVector(rvec), transEngine.createTranslationByVector(sh));
					System.out.println("trans = Trans(rvec,sh) = \n" + toString(trans));

					Function<Number[],Number[]> func = transEngine.getTransformationFunction(trans);
					System.out.println("trans([0,1,0]) = [ 0, 0, 0 ]");
					Number[] vec = mathEngine.vector(new double[]{ 0, 0, 0 });
					Number[] vec_ = funcEngine.applies(func,mathEngine.vector(new double[]{ 0, 1, 0 }));
					assert mathEngine.equals(vec,vec_);

				}
				System.out.println("TEST 4 END");
				System.out.println();

				System.out.println("TEST 5:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);

					Number[] rvec = mathEngine.vector(new double[]{ 0, 0, Math.PI/2 });
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("rvec = " + toString(rvec));
					System.out.println("sh = " + toString(sh));

					Transformation trans0 = transEngine.compose(transEngine.createRotationByVector(rvec), transEngine.createTranslationByVector(sh));
					System.out.println("trans0 = Trans(rvec,sh) = \n" + toString(trans0));

					Transformation trans1 = transEngine.createRotationByVector(rvec);
					System.out.println("trans1 = Trans(rvec) = \n" + toString(trans1));

					Transformation trans2 = transEngine.createTranslationByVector(sh);
					System.out.println("trans2 = Trans(sh) = \n" + toString(trans2));

					Transformation trans12 = transEngine.compose(trans1,trans2);
					System.out.println("trans12 = trans1 o trans2 = \n" + toString(trans12));
					System.out.println("trans0 == trans12 = true");
					assert transEngine.equals(trans0,trans12) == true;

				}
				System.out.println("TEST 5 END");
				System.out.println();

				System.out.println("TEST 6:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);

					Number[] rvec = mathEngine.vector(new double[]{ 0, 0, Math.PI/2 });
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("rvec = " + toString(rvec));
					System.out.println("sh = " + toString(sh));

					Transformation trans1 = transEngine.createRotationByVector(rvec);
					System.out.println("trans1 = Trans(rvec) = \n" + toString(trans1));

					Transformation trans2 = transEngine.createTranslationByVector(sh);
					System.out.println("trans2 = Trans(sh) = \n" + toString(trans2));

					Transformation trans21 = transEngine.compose(trans2,trans1);
					System.out.println("trans21 = trans2 o trans1 = \n" + toString(trans21));
					Function<Number[],Number[]> func21 = transEngine.getTransformationFunction(trans21);
					Number[] from = mathEngine.vector(new double[]{ 0, 1, 0 });
					Number[] to = mathEngine.vector(new double[]{ -1, 1, 0 });
					Number[] to1 = funcEngine.applies(func21,from);
					System.out.println("trans21([0,1,0]) = [ -1, 1, 0 ]");
					assert mathEngine.equals(to,to1) == true;
					Function<Number[],Number[]> func1 = transEngine.getTransformationFunction(trans1);
					Function<Number[],Number[]> func2 = transEngine.getTransformationFunction(trans2);
					Function<Number[],Number[]> func2func1 = funcEngine.compose(func2,func1);
					Number[] to2 = funcEngine.applies(func2func1,from);
					System.out.println("trans1(trans2([0,1,0])) = [ -1, 1, 0 ]");
					assert mathEngine.equals(to,to2) == true;

				}
				System.out.println("TEST 6 END");
				System.out.println();

				System.out.println("TEST 7:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);


					Number[] rvec = mathEngine.vector(new double[]{ 0, 0, Math.PI/2 });

					Transformation trans1 = transEngine.createRotationByVector(rvec);
					System.out.println("trans1 = Trans(rvec) = \n" + toString(trans1));

					Transformation trans2 = transEngine.pow(trans1,2);
					System.out.println("trans2 = trans1^2 = \n" + toString(trans2));

					Transformation trans3 = transEngine.pow(trans1,3);
					System.out.println("trans3 = trans1^3 = \n" + toString(trans3));

					Transformation trans4 = transEngine.pow(trans1,4);
					System.out.println("trans4 = trans1^4 = \n" + toString(trans4));
					System.out.println("isIdentity(trans4) = true");
					assert transEngine.isIdentity(trans4) == true;

				}
				System.out.println("TEST 7 END");
				System.out.println();

				System.out.println("TEST 8:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);


					Number [] rvec = mathEngine.vector(new double[]{ 0, 0, Math.PI/2 });
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					Transformation trans1 = transEngine.compose(transEngine.createRotationByVector(rvec), transEngine.createTranslationByVector(sh));
					System.out.println("trans1 = Trans(rvec,sh) = \n" + toString(trans1));

					Transformation trans_1 = transEngine.invert(trans1);
					System.out.println("trans_1 = invert(trans1) = \n" + toString(trans_1));

					Transformation trans0 = transEngine.compose(trans1,trans_1);
					System.out.println("trans0 = trans1 o trans_1 = \n" + toString(trans0));
					System.out.println("isIdentity(trans0) = true");
					assert transEngine.isIdentity(trans0) == true;

					Transformation trans0_ = transEngine.compose(trans_1,trans1);
					System.out.println("trans0_ = trans_1 o trans1 = \n" + toString(trans0_));
					System.out.println("isIdentity(trans0_) = true");
					assert transEngine.isIdentity(trans0_) == true;

				}
				System.out.println("TEST 8 END");
				System.out.println();

				System.out.println("TEST 9:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);


					Number[] rvec = mathEngine.vector(new double[]{ 0, 0, Math.PI/2 });
					System.out.println("rvec = " + toString(rvec));
					Transformation transr = transEngine.createRotationByVector(rvec);
					System.out.println("transr = Trans(rvec) = \n" + toString(transr));

					Transformation transr_3 = transEngine.dividedBy(transr,mathEngine.number(3));
					System.out.println("transr_3 = divide(transr,3) = \n" + toString(transr_3));
					Transformation transr_ = transEngine.pow(transr_3,3);
					System.out.println("transr_ = transr_3^3 =  \n" + toString(transr_));
					System.out.println("transr == transr_ = true");
					assert transEngine.equals(transr,transr_) == true;

				}
				System.out.println("TEST 9 END");
				System.out.println();

				System.out.println("TEST 10:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);


					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("sh = " + toString(sh));

					Transformation transs = transEngine.createTranslationByVector(sh);
					System.out.println("transs = Trans(sh) = \n" + toString(transs));

					Transformation transs_3 = transEngine.dividedBy(transs,mathEngine.number(3));
					System.out.println("transs_3 = divide(transs,3) = \n" + toString(transs_3));
					Transformation transs_ = transEngine.pow(transs_3,3);
					System.out.println("transs_ = transs_3^3 =  \n" + toString(transs_));
					System.out.println("transs == transs_ = true");
					assert transEngine.equals(transs,transs_) == true;

				}
				System.out.println("TEST 10 END");
				System.out.println();

				System.out.println("TEST 11:");
				{
					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);


					Number [] rvec = mathEngine.vector(new double[]{ 0, 0, Math.PI/2 });
					Number [] sh = mathEngine.vector(new double[]{ 1, 0, 0 });
					System.out.println("rvec = " + toString(rvec));
					System.out.println("sh = " + toString(sh));

					Transformation trans = transEngine.compose(transEngine.createRotationByVector(rvec), transEngine.createTranslationByVector(sh));
					System.out.println("trans = Trans(rvec,sh) = \n" + toString(trans));

					Transformation trans_3 = transEngine.dividedBy(trans,mathEngine.number(3));
					System.out.println("trans_3 = divide(trans,3) = \n" + toString(trans_3));
					Transformation trans_ = transEngine.pow(trans_3,3);
					System.out.println("trans_ = trans_3^3 =  \n" + toString(trans_));
					System.out.println("trans == trans_ = true");
					assert transEngine.equals(trans,trans_) == true;

				}
				System.out.println("TEST 11 END");
				System.out.println();

			}


			System.out.println("====== Region test ======");
			System.out.println();
			{

				System.out.println("TEST 1:");
				{

					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);
					RegionBasicEngine regEngine = new RegionEngineForFunc(funcEngine,transEngine);

					Region reg = regEngine.createRegionByFunction(funcEngine.createFunctionByLambda(
						( v ) -> (v[0].doubleValue()*2 + v[1].doubleValue()*5 - v[2].doubleValue() > 0)
					));
					System.out.println("reg = Region(2x+5y-z>0)");
					System.out.println("reg.contains([1,2,3]) = true");
					assert regEngine.contains(reg,new Number[]{ 1,2,3 }) == true;
					System.out.println("reg.contains([-1,0,5]) = false");
					assert regEngine.contains(reg,new Number[]{ -1,0,5 }) == false;

				}
				System.out.println("TEST 1 END");
				System.out.println();

				System.out.println("TEST 2:");
				{

					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);
					RegionBasicEngine regEngine = new RegionEngineForFunc(funcEngine,transEngine);

					Region reg1 = regEngine.createUniversalRegion();
					System.out.println("reg1 = universe()");
					System.out.println("reg1.contains([1,2,3]) = true");
					assert regEngine.contains(reg1,new Number[]{ 1,2,3 }) == true;
					System.out.println("reg1.contains([-1,0,5]) = true");
					assert regEngine.contains(reg1,new Number[]{ -1,0,5 }) == true;
					
					Region reg0 = regEngine.createEmptyRegion();
					System.out.println("reg0 = empty()");
					System.out.println("reg0.contains([1,2,3]) = false");
					assert regEngine.contains(reg0,new Number[]{ 1,2,3 }) == false;
					System.out.println("reg0.contains([-1,0,5]) = false");
					assert regEngine.contains(reg0,new Number[]{ -1,0,5 }) == false;

				}
				System.out.println("TEST 2 END");
				System.out.println();

				System.out.println("TEST 3:");
				{

					NumberBasicEngine mathEngine = new NumberEngineForDouble(1E-6);
					FunctionBasicEngine funcEngine = new FunctionEngineForLambda();
					TransformationAdvancedEngine transEngine = new AffineTransformationEngineForMatrix(mathEngine,funcEngine);
					RegionBasicEngine regEngine = new RegionEngineForFunc(funcEngine,transEngine);

					Region reg1 = regEngine.createRegionByFunction(funcEngine.createFunctionByLambda(
						( v ) -> (v[0].doubleValue()*2 + v[1].doubleValue()*5 - v[2].doubleValue() > 0)
					));
					System.out.println("reg1 = Region(2x+5y-z>0)");
					System.out.println("reg1.contains([1,2,3]) = true");
					assert regEngine.contains(reg1,new Number[]{ 1,2,3 }) == true;
					System.out.println("reg1.contains([-1,0,5]) = false");
					assert regEngine.contains(reg1,new Number[]{ -1,0,5 }) == false;
					System.out.println("reg1.contains([1,5,3]) = true");
					assert regEngine.contains(reg1,new Number[]{ 1,5,3 }) == true;
					System.out.println("reg1.contains([-5,1,1]) = false");
					assert regEngine.contains(reg1,new Number[]{ -5,1,1 }) == false;

					Region reg2 = regEngine.createRegionByFunction(funcEngine.createFunctionByLambda(
						( v ) -> (v[0].doubleValue()*2 - v[1].doubleValue()*4 + v[2].doubleValue() * 5 + 1 > 0)
					));
					System.out.println("reg2 = Region(2x-4y+5z+1>0)");
					System.out.println("reg2.contains([1,2,3]) = true");
					assert regEngine.contains(reg2,new Number[]{ 1,2,3 }) == true;
					System.out.println("reg2.contains([-1,0,5]) = true");
					assert regEngine.contains(reg2,new Number[]{ -1,0,5 }) == true;
					System.out.println("reg2.contains([1,5,3]) = false");
					assert regEngine.contains(reg2,new Number[]{ 1,5,3 }) == false;
					System.out.println("reg2.contains([-5,1,1]) = false");
					assert regEngine.contains(reg2,new Number[]{ -5,1,1 }) == false;

					Region reg3 = regEngine.intersect(reg1,reg2);
					System.out.println("reg3 = intersect(reg1,reg2)");
					System.out.println("reg3.contains([1,2,3]) = true");
					assert regEngine.contains(reg3,new Number[]{ 1,2,3 }) == true;
					System.out.println("reg3.contains([-1,0,5]) = false");
					assert regEngine.contains(reg3,new Number[]{ -1,0,5 }) == false;
					System.out.println("reg3.contains([1,5,3]) = false");
					assert regEngine.contains(reg3,new Number[]{ 1,5,3 }) == false;
					System.out.println("reg3.contains([-5,1,1]) = false");
					assert regEngine.contains(reg3,new Number[]{ -5,1,1 }) == false;

					Region reg4 = regEngine.union(reg1,reg2);
					System.out.println("reg4 = union(reg1,reg2)");
					System.out.println("reg4.contains([1,2,3]) = true");
					assert regEngine.contains(reg4,new Number[]{ 1,2,3 }) == true;
					System.out.println("reg4.contains([-1,0,5]) = true");
					assert regEngine.contains(reg4,new Number[]{ -1,0,5 }) == true;
					System.out.println("reg4.contains([1,5,3]) = true");
					assert regEngine.contains(reg4,new Number[]{ 1,5,3 }) == true;
					System.out.println("reg4.contains([-5,1,1]) = false");
					assert regEngine.contains(reg4,new Number[]{ -5,1,1 }) == false;

					Region reg5 = regEngine.complement(reg1,reg2);
					System.out.println("reg5 = complement(reg1,reg2)");
					System.out.println("reg5.contains([1,2,3]) = false");
					assert regEngine.contains(reg5,new Number[]{ 1,2,3 }) == false;
					System.out.println("reg5.contains([-1,0,5]) = false");
					assert regEngine.contains(reg5,new Number[]{ -1,0,5 }) == false;
					System.out.println("reg5.contains([1,5,3]) = true");
					assert regEngine.contains(reg5,new Number[]{ 1,5,3 }) == true;
					System.out.println("reg5.contains([-5,1,1]) = false");
					assert regEngine.contains(reg5,new Number[]{ -5,1,1 }) == false;

					Region reg6 = regEngine.complement(reg1);
					System.out.println("reg6 = complement(reg1)");
					System.out.println("reg6.contains([1,2,3]) = false");
					assert regEngine.contains(reg6,new Number[]{ 1,2,3 }) == false;
					System.out.println("reg6.contains([-1,0,5]) = true");
					assert regEngine.contains(reg6,new Number[]{ -1,0,5 }) == true;
					System.out.println("reg6.contains([1,5,3]) = false");
					assert regEngine.contains(reg6,new Number[]{ 1,5,3 }) == false;
					System.out.println("reg6.contains([-5,1,1]) = true");
					assert regEngine.contains(reg6,new Number[]{ -5,1,1 }) == true;

				}
				System.out.println("TEST 3 END");
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

	public static String toString( Transformation trans_ ) {
		AffineTransformationMatrixExpression trans = (AffineTransformationMatrixExpression) trans_;
		String str = "";
		str = str + "    rotation:\n";
		str = str + toString(trans.getMatrix());
		str = str + "\n";
		str = str + "    shift:\n";
		str = str + toString(trans.getVector());
		str = str + "\n";
		return str;
	}
}
