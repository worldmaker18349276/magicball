package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;


public interface TransformationBasicEngine extends
		TransformationBasic.Behavior,
		TransformationBasic.Creator,
		TransformationBasic.Attribute,
		TransformationBasic.Operator,
		TransformationBasic.Predicate
{
	// behavior
	@Override /* TransformationBasic.Behavior */
	public Num[] applyTo( Transformation trans, Num[] point );


	// creater
	@Override /* TransformationBasic.Creator */
	public Transformation createTransformationByFunction( Func<Num[],Num[]> func );
	@Override /* TransformationBasic.Creator */
	public Transformation createIdentityTransformation();


	// attribute
	@Override /* TransformationBasic.Attribute */
	public Func<Num[],Num[]> getTransformationFunctionOf( Transformation trans );


	// operator
	@Override /* TransformationBasic.Operator */
	public Transformation compose( Transformation... trans );
	@Override /* TransformationBasic.Operator */
	public Transformation invert( Transformation trans );
	@Override /* TransformationBasic.Operator */
	public Transformation transformsBy( Transformation t, Transformation p );


	// predicate
	@Override /* TransformationBasic.Predicate */
	public boolean equals( Transformation trans1, Transformation trans2 );
	@Override /* TransformationBasic.Predicate */
	public boolean isIdentity( Transformation trans );
}
