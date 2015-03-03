package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;
import magicball.model.*;


public interface TransformationBehavior extends Engine<Transformation>
{
	public Num[] applies( Transformation trans, Num[] point );
}
