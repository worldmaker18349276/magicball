package magicball.model.geometry;

import java.util.Optional;

import magicball.model.math.Num;
import magicball.model.*;


public class AffineTransformationAdvanced
{
	public interface Creator extends Engine<Transformation>
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

	public interface Attribute extends Engine<Transformation>
	{
		public Optional<Num[][]> getTransformationMatrixOf( Transformation trans );
		public Optional<Num[]> getRotationVectorOf( Transformation trans );
		public Optional<Num[]> getReflectionVectorOf( Transformation trans );
		public Optional<Num[]> getTranslationVectorOf( Transformation trans );
	}

	public interface Predicate extends Engine<Transformation>
	{
		public boolean isAffine( Transformation trans ); // f(x) = Mx+b
		public boolean isLinear( Transformation trans ); // f(x) = Mx
		public boolean isSimilar( Transformation trans ); // f(x) = Rfx+b
		public boolean isIsometric( Transformation trans ); // f(x) = Rx+b
		public boolean isRigid( Transformation trans ); // f(x) = Rx+b, |R|==1
		public boolean isTranslation( Transformation trans ); // f(x) = x+b
	}
}
