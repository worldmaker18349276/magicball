package magicball.model.math;

import magicball.model.*;


public class VectorBasic
{
	static public interface Creator extends Engine<Num>
	{
		public Num[] createVectorByDoubles( double... ns );
		public Num[] createZeroVectorWithDim( int d );
	}

	static public interface Attribute extends Engine<Num>
	{
		public double[] getDoubleValueOf( Num[] v );
	}

	static public interface Operator extends Engine<Num>
	{
		public Num[] clone( Num[] v );
		public Num[] subvectorOf( Num[] v, int i1, int i2 );
		public Num[] augmentsWith( Num[] v, Num... ns );

		public Num[] negate( Num[] v );
		public Num[] plus( Num[] v1, Num[] v2 );
		public Num[] plus( Num[]... vs );
		public Num[] minus( Num[] v1, Num[] v2 );
		public Num[] times( Num[] v1, Num n2 );
		public Num[] over( Num[] v1, Num n2 );
		public Num norm( Num[] v );
		public Num[] normalize( Num[] v );
		public Num dotProduct( Num[] v1, Num[] v2 );
		public Num[] crossProduct( Num[] v1, Num[] v2 );
	}

	static public interface Predicate extends Engine<Num>
	{
		public boolean equals( Num[] v1, Num[] v2 );
		public boolean isZeroVector( Num[] v );
	}
}

