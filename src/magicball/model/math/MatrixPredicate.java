package magicball.model.math;

import magicball.model.*;


public interface MatrixPredicate extends Engine<Num>
{
	public boolean equals( Num[][] m1, Num[][] m2 );
	public boolean isZeroMatrix( Num[][] m );
	public boolean isIdentityMatrix( Num[][] m );
}
