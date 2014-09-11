package magicball.model.geometry.func;

import magicball.model.geometry.*;


public abstract class TransformationMatrixExpression extends Transformation
{
	public abstract Number [][] getRotationMatrix();
	public abstract Number [] getShiftVector();

}

