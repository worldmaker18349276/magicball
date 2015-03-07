package magicball.model.math.spec;

import java.util.stream.Stream;
import java.util.stream.DoubleStream;
import java.util.Arrays;

import magicball.model.*;
import magicball.model.math.*;


public class VectorBasicPropertiesForDefault implements SpecEngine<Num,NumberDoubleExpression>,
		ArbitraryVectorBasicProperty.Creator,
		ArbitraryVectorBasicProperty.Attribute,
		ArbitraryVectorBasicProperty.Operator,
		ArbitraryVectorBasicProperty.Predicate
{
	protected ArbitraryScalarBasicProperty.Creator scaCreator;
	protected ArbitraryScalarBasicProperty.Attribute scaAttribute;
	protected ArbitraryScalarBasicProperty.Operator scaOperator;
	protected ArbitraryScalarBasicProperty.Predicate scaPredicate;


	public VectorBasicPropertiesForDefault() {
	}

	public VectorBasicPropertiesForDefault(
			ArbitraryScalarBasicProperty.Creator scaC,
			ArbitraryScalarBasicProperty.Attribute scaA,
			ArbitraryScalarBasicProperty.Operator scaO,
			ArbitraryScalarBasicProperty.Predicate scaP ) {
		setEngine(scaC);
		setEngine(scaA);
		setEngine(scaO);
		setEngine(scaP);
	}

	public void setEngine( ArbitraryScalarBasicProperty.Creator scaC ) {
		scaCreator = scaC;
	}

	public void setEngine( ArbitraryScalarBasicProperty.Attribute scaA ) {
		scaAttribute = scaA;
	}

	public void setEngine( ArbitraryScalarBasicProperty.Operator scaO ) {
		scaOperator = scaO;
	}

	public void setEngine( ArbitraryScalarBasicProperty.Predicate scaP ) {
		scaPredicate = scaP;
	}


	// vector ( Num[] )
	@Override /* ArbitraryVectorBasicProperty.Creator */
	public Num[] createVectorByDoubles( double... ns ) {
		return DoubleStream.of(ns)
			.mapToObj(scaCreator::createNumberByDouble)
			.toArray(Num[]::new);
	}

	@Override /* ArbitraryVectorBasicProperty.Creator */
	public Num[] createZeroVectorWithDim( int d ) {
		return DoubleStream.generate(() -> 0.0)
			.limit(d)
			.mapToObj(scaCreator::createNumberByDouble)
			.toArray(Num[]::new);
	}


	@Override /* ArbitraryVectorBasicProperty.Attribute */
	public double[] getDoubleValueOf( Num[] v ) {
		return Stream.of(v)
			.mapToDouble(scaAttribute::getDoubleValueOf)
			.toArray();
	}


	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] clone( Num[] v ) {
		return Arrays.copyOf(v,v.length);
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] subvectorOf( Num[] v, int i1, int i2 ) {
		return Arrays.copyOfRange(v,i1,i2);
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] augmentsWith( Num[] v, Num... ns ) {
		return Stream.concat(Stream.of(v), Stream.of(ns))
			.toArray(Num[]::new);
	}


	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] negate( Num[] v ) {
		return Stream.of(v)
			.map(scaOperator::negate)
			.toArray(Num[]::new);
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] plus( Num[] v1, Num[] v2 ) {
		if ( v1.length != v2.length )
			throw new ArithmeticException("v1.length != v2.length");
		Num[] result = new Num [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = scaOperator.plus(v1[i],v2[i]);
		return result;
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] plus( Num[]... vs ) {
		return Stream.of(vs)
			.reduce(this::plus)
			.get();
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] minus( Num[] v1, Num[] v2 ) {
		if ( v1.length != v2.length )
			throw new ArithmeticException("v1.length != v2.length");
		Num[] result = new Num [ v1.length ];
		for ( int i=0; i<result.length; i++ )
			result[i] = scaOperator.minus(v1[i],v2[i]);
		return result;
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] times( Num[] v1, Num n2 ) {
		return Stream.of(v1)
			.map(n1 -> scaOperator.times(n1,n2))
			.toArray(Num[]::new);
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] over( Num[] v1, Num n2 ) {
		return Stream.of(v1)
			.map(v -> scaOperator.over(v,n2))
			.toArray(Num[]::new);
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num norm( Num[] v ) {
		Num[] v2 = Stream.of(v)
			.map(n -> scaOperator.pow(n,2))
			.toArray(Num[]::new);
		return scaOperator.sqrt(scaOperator.plus(v2));
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] normalize( Num[] v ) {
		return over(v,norm(v));
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num dotProduct( Num[] v1, Num[] v2 ) {
		if ( v1.length != v2.length )
			throw new ArithmeticException("v1.length != v2.length");
		Num result = scaCreator.createZero();
			for ( int i=0; i<v1.length; i++ )
				result = scaOperator.plus(result,scaOperator.times(v1[i],v2[i]));
			return result;
	}

	@Override /* ArbitraryVectorBasicProperty.Operator */
	public Num[] crossProduct( Num[] v1, Num[] v2 ) {
		Num[] result = new Num [ 3 ];
		result[0] = scaOperator.minus(scaOperator.times(v1[1],v2[2]),scaOperator.times(v1[2],v2[1]));
		result[1] = scaOperator.minus(scaOperator.times(v1[2],v2[0]),scaOperator.times(v1[0],v2[2]));
		result[2] = scaOperator.minus(scaOperator.times(v1[0],v2[1]),scaOperator.times(v1[1],v2[0]));
		return result;
	}


	@Override /* ArbitraryVectorBasicProperty.Predicate */
	public boolean equals( Num[] v1, Num[] v2 ) {
		if ( v1.length != v2.length )
			return false;
		for ( int i=0; i<v1.length; i++ )
			if ( !scaPredicate.equals(v1[i],v2[i]) )
				return false;
		return true;
	}

	@Override /* ArbitraryVectorBasicProperty.Predicate */
	public boolean isZeroVector( Num[] v ) {
		return Stream.of(v)
			.allMatch(scaPredicate::isZero);
	}
}
