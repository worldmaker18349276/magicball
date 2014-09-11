package magicball.model.geometry.func;

import magicball.model.geometry.*;
import magicball.model.math.*;


public class SurfaceFuncExpression extends Surface
{
	final private Function<Number[],Number> function;

	public SurfaceFuncExpression( Function<Number[],Number> func ) {
		this.function = func;
	}

	final public Function<Number[],Number> getFunction() {
		return this.function;
	}
}
