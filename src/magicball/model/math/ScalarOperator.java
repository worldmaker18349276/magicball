package magicball.model.math;

import magicball.model.*;


public interface ScalarOperator extends Engine<Num>
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
