package magicball.model.math.basic;

import java.util.stream.Stream;

import magicball.model.*;
import magicball.model.math.*;


public class ScalarEngineForDouble implements SpecEngine<Num,NumberDoubleExpression>,
		ScalarCreator,
		ScalarAttribute,
		ScalarOperator,
		ScalarPredicate
{
	protected double epsilon;

	public ScalarEngineForDouble( double eps ) {
		this.epsilon = eps;
	}

	public double eps() {
		return this.epsilon;
	}


	// scalar ( Num )
	@Override /* ScalarCreator */
	public Num createNumberByDouble( double n ) {
		return new NumberDoubleExpression(n);
	}

	@Override /* ScalarCreator */
	public Num createZero() {
		return createNumberByDouble(0.0);
	}

	@Override /* ScalarCreator */
	public Num createOne() {
		return createNumberByDouble(1.0);
	}

	@Override /* ScalarCreator */
	public Num createPi() {
		return createNumberByDouble(Math.PI);
	}

	@Override /* ScalarCreator */
	public Num createE() {
		return createNumberByDouble(Math.E);
	}


	@Override /* ScalarAttribute */
	public double getDoubleValueOf( Num n ) {
		return cast(n).getDoubleValue();
	}


	@Override /* ScalarOperator */
	public Num negate( Num n ) {
		return createNumberByDouble(-getDoubleValueOf(n));
	}

	@Override /* ScalarOperator */
	public Num plus( Num n1, Num n2 ) {
		return createNumberByDouble( getDoubleValueOf(n1) + getDoubleValueOf(n2) );
	}

	@Override /* ScalarOperator */
	public Num plus( Num... ns ) {
		double sum = Stream.of(ns)
			.mapToDouble(this::getDoubleValueOf)
			.sum();
		return createNumberByDouble(sum);
	}

	@Override /* ScalarOperator */
	public Num minus( Num n1, Num n2 ) {
		return createNumberByDouble( getDoubleValueOf(n1) - getDoubleValueOf(n2) );
	}

	@Override /* ScalarOperator */
	public Num times( Num n1, Num n2 ) {
		return createNumberByDouble( getDoubleValueOf(n1) * getDoubleValueOf(n2) );
	}

	@Override /* ScalarOperator */
	public Num times( Num... ns ) {
		double mult = Stream.of(ns)
			.mapToDouble(this::getDoubleValueOf)
			.reduce((a,b) -> a*b)
			.getAsDouble();
		return createNumberByDouble(mult);
	}

	@Override /* ScalarOperator */
	public Num over( Num n1, Num n2 ) {
		return createNumberByDouble( getDoubleValueOf(n1) / getDoubleValueOf(n2) );
	}

	@Override /* ScalarOperator */
	public Num pow( Num n1, int n2 ) {
		return createNumberByDouble( Math.pow(getDoubleValueOf(n1),n2) );
	}

	@Override /* ScalarOperator */
	public Num pow( Num n1, Num n2 ) {
		return createNumberByDouble( Math.pow(getDoubleValueOf(n1),getDoubleValueOf(n2)) );
	}

	@Override /* ScalarOperator */
	public Num sqrt( Num n ) {
		return createNumberByDouble(Math.sqrt(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num abs( Num n ) {
		return createNumberByDouble(Math.abs(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num floor( Num n ) {
		return createNumberByDouble(Math.floor(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num ceil( Num n ) {
		return createNumberByDouble(Math.ceil(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num max( Num n1, Num n2 ) {
		return createNumberByDouble(Math.max(getDoubleValueOf(n1), getDoubleValueOf(n2)));
	}

	@Override /* ScalarOperator */
	public Num max( Num... ns ) {
		double maxn = Stream.of(ns)
			.mapToDouble(this::getDoubleValueOf)
			.max()
			.getAsDouble();
		return createNumberByDouble(maxn);
	}

	@Override /* ScalarOperator */
	public Num min( Num n1, Num n2 ) {
		return createNumberByDouble(Math.min(getDoubleValueOf(n1), getDoubleValueOf(n2)));
	}

	@Override /* ScalarOperator */
	public Num min( Num... ns ) {
		double minn = Stream.of(ns)
			.mapToDouble(this::getDoubleValueOf)
			.min()
			.getAsDouble();
		return createNumberByDouble(minn);
	}

	@Override /* ScalarOperator */
	public Num exp( Num n ) {
		return createNumberByDouble(Math.exp(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num ln( Num n ) {
		return createNumberByDouble(Math.log(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num sin( Num n ) {
		return createNumberByDouble(Math.sin(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num cos( Num n ) {
		return createNumberByDouble(Math.cos(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num tan( Num n ) {
		return createNumberByDouble(Math.tan(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num asin( Num n ) {
		return createNumberByDouble(Math.asin(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num acos( Num n ) {
		return createNumberByDouble(Math.acos(getDoubleValueOf(n)));
	}

	@Override /* ScalarOperator */
	public Num atan( Num n ) {
		return createNumberByDouble(Math.atan(getDoubleValueOf(n)));
	}


	@Override /* ScalarPredicate */
	public boolean equals( Num n1, Num n2 ) {
		return Math.abs(getDoubleValueOf(n1)-getDoubleValueOf(n2)) < this.epsilon;
	}

	@Override /* ScalarPredicate */
	public boolean isGreaterThan( Num n1, Num n2 ) {
		return (getDoubleValueOf(n1)-getDoubleValueOf(n2)) > this.epsilon;
	}

	@Override /* ScalarPredicate */
	public boolean isLessThan( Num n1, Num n2 ) {
		return (getDoubleValueOf(n1)-getDoubleValueOf(n2)) < -this.epsilon;
	}

	@Override /* ScalarPredicate */
	public boolean isZero( Num n ) {
		return equals(n,createZero());
	}

	@Override /* ScalarPredicate */
	public boolean isOne( Num n ) {
		return equals(n,createOne());
	}
}
