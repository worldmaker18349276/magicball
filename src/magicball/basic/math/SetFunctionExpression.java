package magicball.basic.math;

import magicball.model.math.*;


// intensional definition
public class SetFunctionExpression < E > extends Set<E>
{
	protected Function<E,Boolean> function;

	public SetFunctionExpression( Function<E,Boolean> func ) {
		this.function = func;
	}

	public Function<E,Boolean> getFunction() {
		return this.function;
	}
}
