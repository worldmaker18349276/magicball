package magicball.model.geometry.func;

import magicball.model.geometry.*;


public abstract class SurfaceFuncExpression < F extends Function<Number[],Number> > extends Surface
{
	public abstract F getFunction();
}
