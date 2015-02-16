package magicball.model.puzzle.basic;

import magicball.model.geometry.*;
import magicball.model.puzzle.*;


public class BasicSolidRegionExpression extends Solid
{
	protected Region region;
	protected SolidBasicEngine solEngine;

	public BasicSolidRegionExpression( Region reg, SolidBasicEngine solEng ) {
		setRegion(reg);
		setEngine(solEng);
	}

	@Override
	public BasicSolidRegionExpression clone() {
		return new BasicSolidRegionExpression(getRegion(),getEngine());
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion( Region reg ) {
		this.region = reg;
	}

	public SolidBasicEngine getEngine() {
		return this.solEngine;
	}

	public void setEngine( SolidBasicEngine solEng ) {
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
