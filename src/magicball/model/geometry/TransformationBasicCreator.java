package magicball.model.geometry;

import magicball.model.math.Function;
import magicball.model.*;


public interface TransformationBasicCreator extends Engine<Transformation>
{
	// creater
	public Transformation createIdentityTransformation();
	
	public Transformation createTransformationByFunction( Function<Number[],Number[]> func );
}
