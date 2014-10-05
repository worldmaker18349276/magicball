package magicball.test;

import magicball.basic.*;
import magicball.model.*;
import magicball.model.math.*;
import magicball.model.geometry.*;
import magicball.model.puzzle.*;
import magicball.basic.puzzle.*;


public class RubiksCube
{
	public static void main( String [] args ) {
		try {

			EngineProvider provider = new BasicEngineProvider(1E-6,1.5,0.2/7);
			NumberEngine math = provider.getNumberEngine();
			SurfaceEngine faceEng = provider.getSurfaceEngine();
			RegionEngine regEng = provider.getRegionEngine();
			TransformationEngine transEng = provider.getTransformationEngine();
			MovementEngine moveEng = provider.getMovementEngine();

			Number[][] vecs = new Number [ 6 ][];

			vecs[0] = math.vector( 1.0, 0.0, 0.0);
			vecs[1] = math.vector(-1.0, 0.0, 0.0);
			vecs[2] = math.vector( 0.0, 1.0, 0.0);
			vecs[3] = math.vector( 0.0,-1.0, 0.0);
			vecs[4] = math.vector( 0.0, 0.0, 1.0);
			vecs[5] = math.vector( 0.0, 0.0,-1.0);
			Number d = math.number(1.0/3);
			Number t = math.number(Math.PI/2);


			Surface[] surfaces = new Surface [ 6 ];
			for ( int i=0; i<6; i++ )
				surfaces[i] = faceEng.createPlaneByVector(vecs[i]);

			Region whole = regEng.createUniversalRegion();
			for ( Surface face : surfaces )
				whole = regEng.intersect(whole,regEng.createRegionByFace(face,-1));


			Surface[] cutfaces = new Surface [ 6 ];
			for ( int i=0; i<6; i++ )
				cutfaces[i] = faceEng.createPlaneByVector(math.multiply(vecs[i],d));

			Region[] regions = new Region [ 6 ];
			for ( int i=0; i<6; i++ )
				regions[i] = regEng.createRegionByFace(cutfaces[i],1);
			

			Region[] x_layars = new Region [ 3 ];
			x_layars[0] = regions[0];
			x_layars[1] = regEng.intersect(regEng.createRegionByFace(cutfaces[0],-1),regEng.createRegionByFace(cutfaces[1],-1));
			x_layars[2] = regions[1];
			Region[] y_layars = new Region [ 3 ];
			y_layars[0] = regions[2];
			y_layars[1] = regEng.intersect(regEng.createRegionByFace(cutfaces[2],-1),regEng.createRegionByFace(cutfaces[3],-1));
			y_layars[2] = regions[3];
			Region[] z_layars = new Region [ 3 ];
			z_layars[0] = regions[4];
			z_layars[1] = regEng.intersect(regEng.createRegionByFace(cutfaces[4],-1),regEng.createRegionByFace(cutfaces[5],-1));
			z_layars[2] = regions[5];

			Solid[][][] parts = new Solid [ 3 ][ 3 ][ 3 ];
			java.util.Set<Solid> components = new java.util.HashSet<Solid>();
			for ( int x=0; x<3; x++ )
				for ( int y=0; y<3; y++ )
					for ( int z=0; z<3; z++ ) {
						parts[x][y][z] = new Solid(regEng.intersect(whole,x_layars[x],y_layars[y],z_layars[z]));
						components.add(parts[x][y][z]);
					}


			Movement[] whole_moves = new Movement [ 6 ];
			RegionalMovement[] moves = new RegionalMovement [ 6 ];
			for ( int i=0; i<6; i++ ) {
				Transformation trans = transEng.createRotationByVector(math.multiply(vecs[i],t));
				whole_moves[i] = moveEng.createSimpleMovementByTransformation(trans);
				moves[i] = new RegionalMovement(regions[i],whole_moves[i]);
			}


			PhysicalPuzzleEngine engine = new PhysicalPuzzleBasicEngine(provider);
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
								Region reg = parts[i][j][k].getRegion();
								if ( regEng.contains(reg,v) )
									System.out.println("("+(1-i)+","+(1-j)+","+(1-k)+")");
							}
					break;

				case "perm":
					for ( int xi=0; xi<3; xi++ ) for ( int yj=0; yj<3; yj++ ) for ( int zk=0; zk<3; zk++ ) {
						Number[] p = math.vector((1-xi)*0.5,(1-yj)*0.5,(1-zk)*0.5);
						System.out.print("("+(1-xi)+","+(1-yj)+","+(1-zk)+"): ");
						for ( int i=0; i<3; i++ ) for ( int j=0; j<3; j++ ) for ( int k=0; k<3; k++ ) {
							Region reg = parts[i][j][k].getRegion();
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
					rubiksCube.apply(moves[i]);
					break;

				case "rotate":
					System.out.print("input operation(1~6): ");
					input = console.readLine();
					int j = Integer.parseInt(input) - 1;
					rubiksCube.apply(whole_moves[j]);
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
