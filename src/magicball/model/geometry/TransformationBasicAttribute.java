package magicball.model.geometry;

import magicball.model.math.Function;
import magicball.model.*;


public interface TransformationBasicAttribute extends Engine<Transformation>
{
	// attribute
	public Number[] applies( Transformation trans, Number[] point );

	public Function<Number[],Number[]> getTransformationFunction( Transformation trans );
}
