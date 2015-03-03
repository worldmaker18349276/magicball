package magicball.model.math;

import magicball.model.*;


public interface ScalarPredicate extends Engine<Num>
{
	public boolean equals( Num n1, Num n2 );
	public boolean greaterThan( Num n1, Num n2 );
	public boolean lessThan( Num n1, Num n2 );
	public boolean isZero( Num n );
	public boolean isOne( Num n );
}
