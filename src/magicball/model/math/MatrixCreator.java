package magicball.model.math;

import magicball.model.*;


public interface MatrixCreator extends Engine<Num>
{
	public Num[][] createMatrixByDoubles( double[][] ns );
	public Num[][] createZeroMatrixWithDim( int d1, int d2 );
	public Num[][] createIdentityMatrixWithDim( int d );
}
