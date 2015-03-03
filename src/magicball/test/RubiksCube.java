package magicball.test;

import magicball.model.*;
import magicball.model.math.*;
import magicball.model.geometry.*;
import magicball.model.puzzle.*;
import magicball.model.puzzle.basic.*;


public class RubiksCube
{
	public static void main( String [] args ) {
		try {

			BasicEngineProvider provider = new DefaultEngineProvider(1E-6,1.5,100);
			NumberBasicEngine math = provider.getNumberEngine();
			FunctionBasicEngine funcEng = provider.getFunctionEngine();
			RegionBasicEngine regEng = provider.getRegionEngine();
			TransformationBasicEngine transEng = provider.getTransformationEngine();
			MotionBasicEngine moveEng = provider.getMotionEngine();
			SolidBasicEngine solEng = provider.getSolidEngine();

			java.util.function.Function< Number[], Function<Number[],Number> > createPlaneFunction;
			createPlaneFunction = ( fvec ) -> {
				Number dis = math.norm(fvec);
				Number[] nvec = math.normalize(fvec);
				return funcEng.createFunctionByLambda(
					vec -> math.subtract(math.dotProduct(vec,nvec),dis)
				);
			};

			java.util.function.BiFunction< Function<Number[],Number>, Integer, Region > createRegionByFace;
			createRegionByFace = ( face, side ) -> {
				return regEng.createRegionByFunction(funcEng.createFunctionByLambda(
					vec -> funcEng.applies(face,vec).doubleValue()*side > 0
				));
			};


			Number[][] vecs = new Number [ 6 ][];

			vecs[0] = math.vector( 1.0, 0.0, 0.0);
			vecs[1] = math.vector(-1.0, 0.0, 0.0);
			vecs[2] = math.vector( 0.0, 1.0, 0.0);
			vecs[3] = math.vector( 0.0,-1.0, 0.0);
			vecs[4] = math.vector( 0.0, 0.0, 1.0);
			vecs[5] = math.vector( 0.0, 0.0,-1.0);
			Number d = math.number(1.0/3);
			Number t = math.number(Math.PI/2);


			Function<Number[],Number>[] surfaces = new Function [ 6 ];
			for ( int i=0; i<6; i++ )
				surfaces[i] = createPlaneFunction.apply(vecs[i]);

			Region whole = regEng.createUniversalRegion();
			for ( Function<Number[],Number> face : surfaces )
				whole = regEng.intersect(whole,createRegionByFace.apply(face,-1));


			Function<Number[],Number>[] cutfaces = new Function [ 6 ];
			for ( int i=0; i<6; i++ )
				cutfaces[i] = createPlaneFunction.apply(math.multiply(vecs[i],d));

			Region[] regions = new Region [ 6 ];
			for ( int i=0; i<6; i++ )
				regions[i] = createRegionByFace.apply(cutfaces[i],1);
			

			Region[] x_layars = new Region [ 3 ];
			x_layars[0] = regions[0];
			x_layars[1] = regEng.intersect(createRegionByFace.apply(cutfaces[0],-1),createRegionByFace.apply(cutfaces[1],-1));
			x_layars[2] = regions[1];
			Region[] y_layars = new Region [ 3 ];
			y_layars[0] = regions[2];
			y_layars[1] = regEng.intersect(createRegionByFace.apply(cutfaces[2],-1),createRegionByFace.apply(cutfaces[3],-1));
			y_layars[2] = regions[3];
			Region[] z_layars = new Region [ 3 ];
			z_layars[0] = regions[4];
			z_layars[1] = regEng.intersect(createRegionByFace.apply(cutfaces[4],-1),createRegionByFace.apply(cutfaces[5],-1));
			z_layars[2] = regions[5];

			Solid[][][] parts = new Solid [ 3 ][ 3 ][ 3 ];
			java.util.Set<Solid> components = new java.util.HashSet<Solid>();
			for ( int x=0; x<3; x++ )
				for ( int y=0; y<3; y++ )
					for ( int z=0; z<3; z++ ) {
						parts[x][y][z] = solEng.createSolidByRegion(regEng.intersect(whole,x_layars[x],y_layars[y],z_layars[z]));
						components.add(parts[x][y][z]);
					}


			Motion[] whole_moves = new Motion [ 6 ];
			RegionalMotion[] moves = new RegionalMotion [ 6 ];
			for ( int i=0; i<6; i++ ) {
				Transformation trans = transEng.createRotationByVector(math.multiply(vecs[i],t));
				whole_moves[i] = moveEng.createSimpleMotionByTransformation(trans);
				moves[i] = new RegionalMotion(regions[i],whole_moves[i]);
			}


			PhysicalPuzzleBasicEngine engine = new PhysicalPuzzleEngineForBasic(provider);
			PhysicalPuzzle rubiksCube = new PhysicalPuzzle(components,engine);
			// rubiksCube.validate();

			java.io.Console console = System.console();
			String input = "";
			while ( !input.equals("exit") ) {
				System.out.print("input your option(test/perm/move/rotate/exit): ");
				input = console.readLine();
				switch ( input ) {
				case "test":
					System.out.println("input coordinate(seperated by newline): ");
					input = console.readLine();
					double x = Double.parseDouble(input);
					input = console.readLine();
					double y = Double.parseDouble(input);
					input = console.readLine();
					double z = Double.parseDouble(input);
					Number[] v = math.vector(x,y,z);
					for ( int i=0; i<3; i++ )
						for ( int j=0; j<3; j++ )
							for ( int k=0; k<3; k++ ) {
								Region reg = solEng.getOccupiedRegion(parts[i][j][k]);
								if ( regEng.contains(reg,v) )
									System.out.println("("+(1-i)+","+(1-j)+","+(1-k)+")");
							}
					break;

				case "perm":
					for ( int xi=0; xi<3; xi++ ) for ( int yj=0; yj<3; yj++ ) for ( int zk=0; zk<3; zk++ ) {
						Number[] p = math.vector((1-xi)*0.5,(1-yj)*0.5,(1-zk)*0.5);
						System.out.print("("+(1-xi)+","+(1-yj)+","+(1-zk)+"): ");
						for ( int i=0; i<3; i++ ) for ( int j=0; j<3; j++ ) for ( int k=0; k<3; k++ ) {
							Region reg = solEng.getOccupiedRegion(parts[i][j][k]);
							if ( regEng.contains(reg,p) )
								System.out.print("("+(1-i)+","+(1-j)+","+(1-k)+"); ");
						}
						System.out.println();
					}
					break;

				case "move":
					System.out.print("input operation(1~6): ");
					input = console.readLine();
					int i = Integer.parseInt(input) - 1;
					rubiksCube.applyBy(moves[i]);
					break;

				case "rotate":
					System.out.print("input operation(1~6): ");
					input = console.readLine();
					int j = Integer.parseInt(input) - 1;
					rubiksCube.applyBy(whole_moves[j]);
					break;

				case "exit":
					break;

				default:
					System.out.println("no such option!");
				}
			}

		} catch ( Throwable e ) {
			e.printStackTrace();
		}
	}
}
