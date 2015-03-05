package magicball.model.geometry;

import magicball.model.math.Num;
import magicball.model.*;


public class TransformationAdvanced
{
	static public interface Operator extends Engine<Transformation>
	{
		public Transformation pow( Transformation trans, int exp );
		public Transformation dividedBy( Transformation trans, Num divisor );
	}
}
