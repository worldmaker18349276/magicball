package magicball.model.math;

import magicball.model.*;


public interface VectorOperator extends Engine<Num>
{
	public Num[] clone( Num[] v );
	public Num[] subvectorOf( Num[] v, int i1, int i2 );
	public Num[] augmentsWith( Num[] v, Num... ns );

	public Num[] negate( Num[] v );
	public Num[] add( Num[] v1, Num[] v2 );
	public Num[] add( Num[]... vs );
	public Num[] subtract( Num[] v1, Num[] v2 );
	public Num[] multiply( Num[] v1, Num n2 );
	public Num[] dividedBy( Num[] v1, Num n2 );
	public Num norm( Num[] v );
	public Num[] normalize( Num[] v );
	public Num dotProduct( Num[] v1, Num[] v2 );
	public Num[] crossProduct( Num[] v1, Num[] v2 );
}
