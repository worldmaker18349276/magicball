package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;
import magicball.model.*;


public interface TransformationAttribute extends Engine<Transformation>
{
	public Func<Num[],Num[]> getTransformationFunctionOf( Transformation trans );
}
