package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;
import magicball.model.*;


public class ArbitraryTransformationBasicProperty
{
	static public interface Behavior extends Engine<Transformation>
	{
		public Num[] applyTo( Transformation trans, Num[] point );
	}

	static public interface Creator extends Engine<Transformation>
	{
		public Transformation createTransformationByFunction( Func<Num[],Num[]> func );
		public Transformation createIdentityTransformation();
	}

	static public interface Attribute extends Engine<Transformation>
	{
		public Func<Num[],Num[]> getTransformationFunctionOf( Transformation trans );
	}

	static public interface Operator extends Engine<Transformation>
	{
		public Transformation compose( Transformation... trans );
		public Transformation invert( Transformation trans );
		public Transformation transformsBy( Transformation t, Transformation p ); // = p * t * p^-1
	}

	static public interface Predicate extends Engine<Transformation>
	{
		public boolean equals( Transformation trans1, Transformation trans2 );
		public boolean isIdentity( Transformation trans );
	}
}
