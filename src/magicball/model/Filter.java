package magicball.model;

import java.util.Set;
import java.util.HashSet;


public interface Filter
{
	public < Obj > Set<Obj> filter( Set<Obj> sols );
}
