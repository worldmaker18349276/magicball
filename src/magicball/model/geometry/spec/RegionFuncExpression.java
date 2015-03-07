package magicball.model.geometry.spec;

import magicball.model.geometry.*;
import magicball.model.math.*;


public class RegionFuncExpression extends Region
{
	final private Func<Num[],Boolean> function;

	public RegionFuncExpression( Func<Num[],Boolean> function ) {
		this.function = function;
	}

	final public Func<Num[],Boolean> getFunction() {
		return this.function;
	}
}
