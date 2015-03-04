package magicball.model.math.basic;

import java.util.stream.Stream;
import java.util.stream.DoubleStream;
import java.util.Arrays;

import magicball.model.*;
import magicball.model.math.*;


public class DefaultVectorEngine implements SpecEngine<Num,NumberDoubleExpression>,
		VectorCreator,
		VectorAttribute,
		VectorOperator,
		VectorPredicate
{
	private ScalarCreator scaCreator;
	private ScalarAttribute scaAttribute;
	private ScalarOperator scaOperator;
	private ScalarPredicate scaPredicate;


	public DefaultVectorEngine() {
	}

	public DefaultVectorEngine( ScalarCreator scaC, ScalarAttribute scaAttr, ScalarOperator scaOp, ScalarPredicate scaPred ) {
		scaCreator = scaC;
		scaAttribute = scaAttr;
		scaOperator = scaOp;
		scaPredicate = scaPred;
	}

	public void setEngine( ScalarCreator scaC ) {
		scaCreator = scaC;
	}

	public void setEngine( ScalarAttribute scaAttr ) {
		scaAttribute = scaAttr;
	}

	public void setEngine( ScalarOperator scaOp ) {
		scaOperator = scaOp;
	}

	public void setEngine( ScalarPredicate scaPred ) {
		scaPredicate = scaPred;
	}


	// vector ( Num[] )
	@Override /* VectorCreator */
	public Num[] createVectorByDoubles( double... ns ) {
		return DoubleStream.of(ns)
			.mapToObj(scaCreator::createNumberByDouble)
			.toArray(Num[]::new);
	}

	@Override /* VectorCreator */
	public Num[] createZeroVectorWithDim( int d ) {
		return DoubleStream.generate(() -> 0.0)
			.limit(d)
			.mapToObj(scaCreator::createNumberByDouble)
			.toArray(Num[]::new);
	}


	@Override /* VectorAttribute */
	public double[] getDoubleValueOf( Num[] v ) {
		return Stream.of(v)
			.mapToDouble(scaAttribute::getDoubleValueOf)
			.toArray();
	}


	@Override /* VectorOperator */
	public Num[] clone( Num[] v ) {
		return Arrays.copyOf(v,v.length);
	}

	@Override /* VectorOperator */
	public Num[] subvectorOf( Num[] v, int i1, int i2 ) {
		return Arrays.copyOfRange(v,i1,i2);
	}

	@Override /* VectorOperator */
	public Num[] augmentsWith( Num[] v, Num... ns ) {
		return Stream.concat(Stream.of(v), Stream.of(ns))
			.toArray(Num[]::new);
	}


	@Override /* VectorOperator */
	public Num[] negate( Num[] v ) {
		return Stream.of(v)
			.map(scaOperator::negate)
			.toArray(Num[]::new);
	}

	@Override /* VectorOperator */
	public Num[] plus( Num[] v1, Num[] v2 ) {
		if ( v1.length != v2.length )
			throw new ArithmeticException("v1.length != v2.length");
		Num[] result = new Num [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = scaOperator.plus(v1[i],v2[i]);
		return result;
	}

	@Override /* VectorOperator */
	public Num[] plus( Num[]... vs ) {
		return Stream.of(vs)
			.reduce(this::plus)
			.get();
	}

	@Override /* VectorOperator */
	public Num[] minus( Num[] v1, Num[] v2 ) {
		if ( v1.length != v2.length )
			throw new ArithmeticException("v1.length != v2.length");
		Num[] result = new Num [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = scaOperator.minus(v1[i],v2[i]);
		return result;
	}

	@Override /* VectorOperator */
	public Num[] times( Num[] v1, Num n2 ) {
		return Stream.of(v1)
			.map(n1 -> scaOperator.times(n1,n2))
			.toArray(Num[]::new);
	}

	@Override /* VectorOperator */
	public Num[] over( Num[] v1, Num n2 ) {
		return Stream.of(v1)
			.map(v -> scaOperator.over(v,n2))
			.toArray(Num[]::new);
	}

	@Override /* VectorOperator */
	public Num norm( Num[] v ) {
		Num[] v2 = Stream.of(v)
			.map(n -> scaOperator.pow(n,2))
			.toArray(Num[]::new);
		return scaOperator.sqrt(scaOperator.plus(v2));
	}

	@Override /* VectorOperator */
	public Num[] normalize( Num[] v ) {
		return over(v,norm(v));
	}

	@Override /* VectorOperator */
	public Num dotProduct( Num[] v1, Num[] v2 ) {
		if ( v1.length != v2.length )
			throw new ArithmeticException("v1.length != v2.length");
		Num result = scaCreator.createZero();
			for ( int i=0; i<v1.length; i++ )
				result = scaOperator.plus(result,scaOperator.times(v1[i],v2[i]));
			return result;
	}

	@Override /* VectorOperator */
	public Num[] crossProduct( Num[] v1, Num[] v2 ) {
		Num[] result = new Num [ 3 ];
		result[0] = scaOperator.minus(scaOperator.times(v1[1],v2[2]),scaOperator.times(v1[2],v2[1]));
		result[1] = scaOperator.minus(scaOperator.times(v1[2],v2[0]),scaOperator.times(v1[0],v2[2]));
		result[2] = scaOperator.minus(scaOperator.times(v1[0],v2[1]),scaOperator.times(v1[1],v2[0]));
		return result;
	}


	@Override /* VectorPredicate */
	public boolean equals( Num[] v1, Num[] v2 ) {
		if ( v1.length != v2.length )
			return false;
		for ( int i=0; i<v1.length; i++ )
			if ( !scaPredicate.equals(v1[i],v2[i]) )
				return false;
		return true;
	}

	@Override /* VectorPredicate */
	public boolean isZeroVector( Num[] v ) {
		return Stream.of(v)
			.allMatch(scaPredicate::isZero);
	}
}
