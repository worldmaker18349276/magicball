package magicball.model.geometry;

import magicball.model.*;


public interface AffineTransformationAttribute extends Engine<Transformation>
{
	// attribute
	public Number[][] getTransformationMatrix( Transformation trans );
	public Number[] getRotationVector( Transformation trans );
	public Number[] getReflectionVector( Transformation trans );
	public Number[] getTranslationVector( Transformation trans );
}
