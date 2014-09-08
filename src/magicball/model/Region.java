package magicball.model;

import java.util.Set;


// physical puzzle abstraction layer
public abstract class Region
{
	public abstract Set<Solid> filter( Set<Solid> sols ) throws IllegalOperationException;
}
