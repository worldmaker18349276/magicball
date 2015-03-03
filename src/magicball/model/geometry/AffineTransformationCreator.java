package magicball.model.geometry;

import magicball.model.math.Num;
import magicball.model.*;


public interface AffineTransformationCreator extends Engine<Transformation>
{
	public Transformation createAffineTransformationByAugmentedMatrix( Num[][] mat );
	public Transformation createAffineTransformationByMatrixAndVector( Num[][] mat, Num[] vec );
	public Transformation createLinearTransformationByMatrix( Num[][] mat );
	public Transformation createRotationByVector( Num[] rvec );
	public Transformation createReflectionByVector( Num[] fvec );
	public Transformation createTranslationByVector( Num[] sh );
	public Transformation createScalingByFactor( Num factor );
	public Transformation createShearingByOffsets( Num a, Num b ); // x'=x+az, y'=y+bz
}
