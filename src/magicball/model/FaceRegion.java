package magicball.model;

import java.util.Set;
import java.util.HashSet;


public abstract class FaceRegion extends Region
{
	public abstract Face getFace();
	public abstract int getSide();
	public abstract int at( Solid sol );

	public boolean inside( Vector v ) {
		return getFace().at(v) == getSide();
	}
}
