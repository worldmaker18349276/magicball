package magicball.model.math.basic;

import magicball.model.math.*;


public class NumberDoubleExpression extends Num
{
	protected double value;

	public NumberDoubleExpression( double v ) {
		this.value = v;
	}

	public double getDoubleValue() {
		return this.value;
	}
}
