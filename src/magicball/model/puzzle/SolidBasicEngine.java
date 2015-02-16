package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface SolidBasicEngine
{
	public SolidBasicEngine clone();


	// creater
	public Solid createSolidByRegion( Region reg );


	// attribute
	public Region getOccupiedRegion( Solid sol );


	// operator
	public void applies( Solid sol, Transformation trans );
	
	public java.util.Set<Solid> filterBy( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException;
	public boolean noDuplicateOccupy( java.util.Set<Solid> sols );

	public boolean isSameShape( Solid sol1, Solid sol2 );
	public boolean equals( Solid sol1, Solid sol2 );
}
