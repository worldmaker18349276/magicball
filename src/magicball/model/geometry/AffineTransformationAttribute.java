package magicball.model.geometry;

import java.util.Optional;

import magicball.model.math.Num;
import magicball.model.*;


public interface AffineTransformationAttribute extends Engine<Transformation>
{
	public Optional<Num[][]> getTransformationMatrixOf( Transformation trans );
	public Optional<Num[]> getRotationVectorOf( Transformation trans );
	public Optional<Num[]> getReflectionVectorOf( Transformation trans );
	public Optional<Num[]> getTranslationVectorOf( Transformation trans );
}
