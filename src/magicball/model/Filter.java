package magicball.model;

import java.util.Set;
import java.util.HashSet;


public abstract class Filter
{
	public abstract < Obj > Set<Obj> filter( Set<Obj> sols );
}
