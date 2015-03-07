package magicball.model.geometry;

import java.util.Optional;

import magicball.model.math.Func;
import magicball.model.math.Num;


public interface TransformationAdvancedEngine extends
		ArbitraryTransformationBasicProperty.Behavior,
		ArbitraryTransformationBasicProperty.Creator,
		ArbitraryTransformationBasicProperty.Attribute,
		ArbitraryTransformationBasicProperty.Operator,
		ArbitraryTransformationBasicProperty.Predicate,
		ArbitraryTransformationAdvancedProperty.Operator,
		AffineTransformationAdvancedProperty.Creator,
		AffineTransformationAdvancedProperty.Attribute,
		AffineTransformationAdvancedProperty.Predicate
{
	// behavior
	@Override /* ArbitraryTransformationBasicProperty.Behavior */
	public Num[] applyTo( Transformation trans, Num[] point );


	// creater
	@Override /* ArbitraryTransformationBasicProperty.Creator */
	public Transformation createTransformationByFunction( Func<Num[],Num[]> func );
	@Override /* ArbitraryTransformationBasicProperty.Creator */
	public Transformation createIdentityTransformation();

	@Override /* AffineTransformationAdvancedProperty.Creator */
	public Transformation createAffineTransformationByAugmentedMatrix( Num[][] mat );
	@Override /* AffineTransformationAdvancedProperty.Creator */
	public Transformation createAffineTransformationByMatrixAndVector( Num[][] mat, Num[] vec );
	@Override /* AffineTransformationAdvancedProperty.Creator */
	public Transformation createLinearTransformationByMatrix( Num[][] mat );
	@Override /* AffineTransformationAdvancedProperty.Creator */
	public Transformation createRotationByVector( Num[] rvec );
	@Override /* AffineTransformationAdvancedProperty.Creator */
	public Transformation createReflectionByVector( Num[] fvec );
	@Override /* AffineTransformationAdvancedProperty.Creator */
	public Transformation createTranslationByVector( Num[] sh );
	@Override /* AffineTransformationAdvancedProperty.Creator */
	public Transformation createScalingByFactor( Num factor );
	@Override /* AffineTransformationAdvancedProperty.Creator */
	public Transformation createShearingByOffsets( Num a, Num b );


	// attribute
	@Override /* ArbitraryTransformationBasicProperty.Attribute */
	public Func<Num[],Num[]> getTransformationFunctionOf( Transformation trans );

	@Override /* AffineTransformationAdvancedProperty.Attribute */
	public Optional<Num[][]> getTransformationMatrixOf( Transformation trans );
	@Override /* AffineTransformationAdvancedProperty.Attribute */
	public Optional<Num[]> getRotationVectorOf( Transformation trans );
	@Override /* AffineTransformationAdvancedProperty.Attribute */
	public Optional<Num[]> getReflectionVectorOf( Transformation trans );
	@Override /* AffineTransformationAdvancedProperty.Attribute */
	public Optional<Num[]> getTranslationVectorOf( Transformation trans );


	// operator
	@Override /* ArbitraryTransformationBasicProperty.Operator */
	public Transformation compose( Transformation... trans );
	@Override /* ArbitraryTransformationBasicProperty.Operator */
	public Transformation invert( Transformation trans );
	@Override /* ArbitraryTransformationBasicProperty.Operator */
	public Transformation transformsBy( Transformation t, Transformation p );

	@Override /* ArbitraryTransformationAdvancedProperty.Operator */
	public Transformation pow( Transformation trans, int exp );
	@Override /* ArbitraryTransformationAdvancedProperty.Operator */
	public Transformation dividedBy( Transformation trans, Num divisor );


	// predicate
	@Override /* ArbitraryTransformationBasicProperty.Predicate */
	public boolean equals( Transformation trans1, Transformation trans2 );
	@Override /* ArbitraryTransformationBasicProperty.Predicate */
	public boolean isIdentity( Transformation trans );

	@Override /* AffineTransformationAdvancedProperty.Predicate */
	public boolean isAffine( Transformation trans );
	@Override /* AffineTransformationAdvancedProperty.Predicate */
	public boolean isLinear( Transformation trans );
	@Override /* AffineTransformationAdvancedProperty.Predicate */
	public boolean isSimilar( Transformation trans );
	@Override /* AffineTransformationAdvancedProperty.Predicate */
	public boolean isIsometric( Transformation trans );
	@Override /* AffineTransformationAdvancedProperty.Predicate */
	public boolean isRigid( Transformation trans );
	@Override /* AffineTransformationAdvancedProperty.Predicate */
	public boolean isTranslation( Transformation trans );
}
