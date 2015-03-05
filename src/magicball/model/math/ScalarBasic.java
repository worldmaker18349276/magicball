package magicball.model.math;

import magicball.model.*;


public class ScalarBasic
{
	static public interface Creator extends Engine<Num>
	{
		public Num createNumberByDouble( double n );
		public Num createZero();
		public Num createOne();
		public Num createPi();
		public Num createE();
	}

	static public interface Attribute extends Engine<Num>
	{
		public double getDoubleValueOf( Num n );
	}

	static public interface Operator extends Engine<Num>
	{
		public Num negate( Num n );
		public Num plus( Num n1, Num n2 );
		public Num plus( Num... ns );
		public Num minus( Num n1, Num n2 );
		public Num times( Num n1, Num n2 );
		public Num times( Num... ns );
		public Num over( Num n1, Num n2 );
		public Num pow( Num n1, int n2 );
		public Num pow( Num n1, Num n2 );
		public Num sqrt( Num n );
		public Num abs( Num n );
		public Num floor( Num n );
		public Num ceil( Num n );
		public Num max( Num n1, Num n2 );
		public Num max( Num... ns );
		public Num min( Num n1, Num n2 );
		public Num min( Num... ns );
		public Num exp( Num n );
		public Num ln( Num n );
		public Num sin( Num n );
		public Num cos( Num n );
		public Num tan( Num n );
		public Num asin( Num n );
		public Num acos( Num n );
		public Num atan( Num n );
	}

	static public interface Predicate extends Engine<Num>
	{
		public boolean equals( Num n1, Num n2 );
		public boolean isGreaterThan( Num n1, Num n2 );
		public boolean isLessThan( Num n1, Num n2 );
		public boolean isZero( Num n );
		public boolean isOne( Num n );
	}
}

