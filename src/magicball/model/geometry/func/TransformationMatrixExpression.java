package magicball.model.geometry.func;

import magicball.model.geometry.*;


public class TransformationMatrixExpression extends Transformation
{
	final private Number [][] rotation;
	final private Number [] shift;

	public TransformationMatrixExpression( Number [][] rot, Number [] sh ) {
		this.rotation = rot;
		this.shift = sh;
	}

	public TransformationMatrixExpression clone() {
		Number [][] rotation = getRotationMatrix();
		Number [] shift = getShiftVector();
		Number [][] rot = new Number [ 3 ][ 3 ];
		Number [] sh = new Number [ 3 ];

		for ( int i=0; i<3; i++ )
			for ( int j=0; j<3; j++ )
				rot[i][j] = rotation[i][j];
		for ( int k=0; k<3; k++ )
			sh[k] = shift[k];

		return new TransformationMatrixExpression(rot,sh);
	}
	
	final public Number [][] getRotationMatrix() {
		return this.rotation;
	}

	final public Number [] getShiftVector() {
		return this.shift;
	}
}

