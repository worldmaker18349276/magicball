package magicball.model.geometry;

import magicball.model.*;


public interface TransformationOperator extends Engine<Transformation>
{
	public Transformation compose( Transformation... trans );
	public Transformation invert( Transformation trans );
}
