package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;
import magicball.model.*;


public interface TransformationCreator extends Engine<Transformation>
{
	public Transformation createIdentityTransformation();
	
	public Transformation createTransformationByFunction( Func<Num[],Num[]> func );
}
