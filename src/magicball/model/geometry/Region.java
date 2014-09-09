package magicball.model.geometry;

import java.util.Set;


// physical puzzle abstraction layer
public abstract class Region
{
	public boolean equals( Object reg ) {
		if ( reg instanceof Region )
			return equals((Region) reg);
		else
			return false;
	}

	public abstract boolean equals( Region reg );
	public abstract Region clone();
}
