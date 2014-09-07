package magicball.model;

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

	public abstract void apply( Displacement dis );
	public abstract Set<Solid> filter( Set<Solid> sols ) throws IllegalOperationException;
	public abstract boolean noDuplicateOccupy( Set<Solid> sols );
}
