package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface SolidBasicEngine extends
		SolidBasicCreator,
		SolidBasicAttribute,
		SolidBasicOperator,
		SolidBasicPredicate
{
	// creater
	public Solid createSolidByRegion( Region reg );


	// attribute
	public Region getOccupiedRegion( Solid sol );


	// operator
	public void transformsBy( Solid sol, Transformation trans );
	public java.util.Set<Solid> filtersBy( java.util.Set<Solid> sols, Region reg ) throws IllegalOperationException;
	
	public boolean noDuplicateOccupyIn( java.util.Set<Solid> sols );
	public boolean areSameShape( Solid sol1, Solid sol2 );
	public boolean equals( Solid sol1, Solid sol2 );
}
