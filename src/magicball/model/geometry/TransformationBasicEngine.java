package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;


public interface TransformationBasicEngine extends
		TransformationBehavior,
		TransformationCreator,
		TransformationAttribute,
		TransformationOperator,
		TransformationPredicate
{
	// behavior
	@Override /* TransformationBehavior */
	public Num[] applies( Transformation trans, Num[] point );


	// creater
	@Override /* TransformationCreator */
	public Transformation createTransformationByFunction( Func<Num[],Num[]> func );
	@Override /* TransformationCreator */
	public Transformation createIdentityTransformation();


	// attribute
	@Override /* TransformationAttribute */
	public Func<Num[],Num[]> getTransformationFunctionOf( Transformation trans );


	// operator
	@Override /* TransformationOperator */
	public Transformation compose( Transformation... trans );
	@Override /* TransformationOperator */
	public Transformation invert( Transformation trans );


	// predicate
	@Override /* TransformationPredicate */
	public boolean equals( Transformation trans1, Transformation trans2 );
	@Override /* TransformationPredicate */
	public boolean isIdentity( Transformation trans );
}
