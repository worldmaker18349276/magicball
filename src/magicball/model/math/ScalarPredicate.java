package magicball.model.math;

import magicball.model.*;


public interface ScalarPredicate extends Engine<Num>
{
	public boolean equals( Num n1, Num n2 );
	public boolean isGreaterThan( Num n1, Num n2 );
	public boolean isLessThan( Num n1, Num n2 );
	public boolean isZero( Num n );
	public boolean isOne( Num n );
}
