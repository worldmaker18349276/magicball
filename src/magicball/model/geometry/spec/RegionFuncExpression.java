package magicball.model.geometry.spec;

import magicball.model.geometry.*;
import magicball.model.math.*;


public class RegionFuncExpression extends Region
{
	final private Function<Number[],Boolean> function;

	public RegionFuncExpression( Function<Number[],Boolean> function ) {
		this.function = function;
	}

	final public Function<Number[],Boolean> getFunction() {
		return this.function;
	}
}
