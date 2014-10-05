package magicball.model.puzzle.basic;

import magicball.model.geometry.*;
import magicball.model.puzzle.*;


public class BasicSolid extends Solid
{
	protected Region region;
	protected SolidEngine solEngine;

	public BasicSolid( Region reg, SolidEngine solEng ) {
		setRegion(reg);
		setEngine(solEng);
	}

	@Override
	public BasicSolid clone() {
		return new BasicSolid(getRegion(),getEngine());
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion( Region reg ) {
		this.region = reg;
	}

	public SolidEngine getEngine() {
		return this.solEngine;
	}

	public void setEngine( SolidEngine solEng ) {
		this.solEngine = solEng;
	}


	public void apply( Transformation trans ) {
		solEngine.applies(this,trans);
	}
	
	public boolean isSameShape( Solid sol2 ) {
		return solEngine.isSameShape(this,sol2);
	}

	public boolean equals( Object sol2 ) {
		if ( sol2 instanceof Solid )
			return solEngine.equals(this,(Solid)sol2);
		else
			return false;
	}
}
