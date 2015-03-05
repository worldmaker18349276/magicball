package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;


public interface TransformationBasicEngine extends
		ArbitraryTransformationBasicProperty.Behavior,
		ArbitraryTransformationBasicProperty.Creator,
		ArbitraryTransformationBasicProperty.Attribute,
		ArbitraryTransformationBasicProperty.Operator,
		ArbitraryTransformationBasicProperty.Predicate
{
	// behavior
	@Override /* ArbitraryTransformationBasicProperty.Behavior */
	public Num[] applyTo( Transformation trans, Num[] point );


	// creater
	@Override /* ArbitraryTransformationBasicProperty.Creator */
	public Transformation createTransformationByFunction( Func<Num[],Num[]> func );
	@Override /* ArbitraryTransformationBasicProperty.Creator */
	public Transformation createIdentityTransformation();


	// attribute
	@Override /* ArbitraryTransformationBasicProperty.Attribute */
	public Func<Num[],Num[]> getTransformationFunctionOf( Transformation trans );


	// operator
	@Override /* ArbitraryTransformationBasicProperty.Operator */
	public Transformation compose( Transformation... trans );
	@Override /* ArbitraryTransformationBasicProperty.Operator */
	public Transformation invert( Transformation trans );
	@Override /* ArbitraryTransformationBasicProperty.Operator */
	public Transformation transformsBy( Transformation t, Transformation p );


	// predicate
	@Override /* ArbitraryTransformationBasicProperty.Predicate */
	public boolean equals( Transformation trans1, Transformation trans2 );
	@Override /* ArbitraryTransformationBasicProperty.Predicate */
	public boolean isIdentity( Transformation trans );
}
