package magicball.model.math.basic;

import java.util.stream.Stream;

import magicball.model.*;
import magicball.model.math.*;


public class ScalarEngineForDouble implements SpecEngine<Num,NumberDoubleExpression>,
		ScalarBasic.Creator,
		ScalarBasic.Attribute,
		ScalarBasic.Operator,
		ScalarBasic.Predicate
{
	protected double epsilon;

	public ScalarEngineForDouble( double eps ) {
		this.epsilon = eps;
	}

	public double eps() {
		return this.epsilon;
	}


	// scalar ( Num )
	@Override /* ScalarBasic.Creator */
	public Num createNumberByDouble( double n ) {
		return new NumberDoubleExpression(n);
	}

	@Override /* ScalarBasic.Creator */
	public Num createZero() {
		return createNumberByDouble(0.0);
	}

	@Override /* ScalarBasic.Creator */
	public Num createOne() {
		return createNumberByDouble(1.0);
	}

	@Override /* ScalarBasic.Creator */
	public Num createPi() {
		return createNumberByDouble(Math.PI);
	}

	@Override /* ScalarBasic.Creator */
	public Num createE() {
		return createNumberByDouble(Math.E);
	}


	@Override /* ScalarBasic.Attribute */
	public double getDoubleValueOf( Num n ) {
		return cast(n).getDoubleValue();
	}


	@Override /* ScalarBasic.Operator */
	public Num negate( Num n ) {
		return createNumberByDouble(-getDoubleValueOf(n));
	}

	@Override /* ScalarBasic.Operator */
	public Num plus( Num n1, Num n2 ) {
		return createNumberByDouble( getDoubleValueOf(n1) + getDoubleValueOf(n2) );
	}

	@Override /* ScalarBasic.Operator */
	public Num plus( Num... ns ) {
		double sum = Stream.of(ns)
			.mapToDouble(this::getDoubleValueOf)
			.sum();
		return createNumberByDouble(sum);
	}

	@Override /* ScalarBasic.Operator */
	public Num minus( Num n1, Num n2 ) {
		return createNumberByDouble( getDoubleValueOf(n1) - getDoubleValueOf(n2) );
	}

	@Override /* ScalarBasic.Operator */
	public Num times( Num n1, Num n2 ) {
		return createNumberByDouble( getDoubleValueOf(n1) * getDoubleValueOf(n2) );
	}

	@Override /* ScalarBasic.Operator */
	public Num times( Num... ns ) {
		double mult = Stream.of(ns)
			.mapToDouble(this::getDoubleValueOf)
			.reduce((a,b) -> a*b)
			.getAsDouble();
		return createNumberByDouble(mult);
	}

	@Override /* ScalarBasic.Operator */
	public Num over( Num n1, Num n2 ) {
		return createNumberByDouble( getDoubleValueOf(n1) / getDoubleValueOf(n2) );
	}

	@Override /* ScalarBasic.Operator */
	public Num pow( Num n1, int n2 ) {
		return createNumberByDouble( Math.pow(getDoubleValueOf(n1),n2) );
	}

	@Override /* ScalarBasic.Operator */
	public Num pow( Num n1, Num n2 ) {
		return createNumberByDouble( Math.pow(getDoubleValueOf(n1),getDoubleValueOf(n2)) );
	}

	@Override /* ScalarBasic.Operator */
	public Num sqrt( Num n ) {
		return createNumberByDouble(Math.sqrt(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num abs( Num n ) {
		return createNumberByDouble(Math.abs(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num floor( Num n ) {
		return createNumberByDouble(Math.floor(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num ceil( Num n ) {
		return createNumberByDouble(Math.ceil(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num max( Num n1, Num n2 ) {
		return createNumberByDouble(Math.max(getDoubleValueOf(n1), getDoubleValueOf(n2)));
	}

	@Override /* ScalarBasic.Operator */
	public Num max( Num... ns ) {
		double maxn = Stream.of(ns)
			.mapToDouble(this::getDoubleValueOf)
			.max()
			.getAsDouble();
		return createNumberByDouble(maxn);
	}

	@Override /* ScalarBasic.Operator */
	public Num min( Num n1, Num n2 ) {
		return createNumberByDouble(Math.min(getDoubleValueOf(n1), getDoubleValueOf(n2)));
	}

	@Override /* ScalarBasic.Operator */
	public Num min( Num... ns ) {
		double minn = Stream.of(ns)
			.mapToDouble(this::getDoubleValueOf)
			.min()
			.getAsDouble();
		return createNumberByDouble(minn);
	}

	@Override /* ScalarBasic.Operator */
	public Num exp( Num n ) {
		return createNumberByDouble(Math.exp(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num ln( Num n ) {
		return createNumberByDouble(Math.log(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num sin( Num n ) {
		return createNumberByDouble(Math.sin(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num cos( Num n ) {
		return createNumberByDouble(Math.cos(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num tan( Num n ) {
		return createNumberByDouble(Math.tan(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num asin( Num n ) {
		return createNumberByDouble(Math.asin(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num acos( Num n ) {
		return createNumberByDouble(Math.acos(getDoubleValueOf(n)));
	}

	@Override /* ScalarBasic.Operator */
	public Num atan( Num n ) {
		return createNumberByDouble(Math.atan(getDoubleValueOf(n)));
	}


	@Override /* ScalarBasic.Predicate */
	public boolean equals( Num n1, Num n2 ) {
		return Math.abs(getDoubleValueOf(n1)-getDoubleValueOf(n2)) < this.epsilon;
	}

	@Override /* ScalarBasic.Predicate */
	public boolean isGreaterThan( Num n1, Num n2 ) {
		return (getDoubleValueOf(n1)-getDoubleValueOf(n2)) > this.epsilon;
	}

	@Override /* ScalarBasic.Predicate */
	public boolean isLessThan( Num n1, Num n2 ) {
		return (getDoubleValueOf(n1)-getDoubleValueOf(n2)) < -this.epsilon;
	}

	@Override /* ScalarBasic.Predicate */
	public boolean isZero( Num n ) {
		return equals(n,createZero());
	}

	@Override /* ScalarBasic.Predicate */
	public boolean isOne( Num n ) {
		return equals(n,createOne());
	}
}
