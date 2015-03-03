package magicball.model.geometry;

import magicball.model.*;


public interface TransformationPredicate extends Engine<Transformation>
{
	public boolean equals( Transformation trans1, Transformation trans2 );
	public boolean isIdentity( Transformation trans );
}
