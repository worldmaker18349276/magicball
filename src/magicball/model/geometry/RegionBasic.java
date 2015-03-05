package magicball.model.geometry;

import magicball.model.math.Func;
import magicball.model.math.Num;
import magicball.model.*;


public class RegionBasic
{
	static public interface Behavior extends Engine<Region>
	{
		public boolean contains( Region reg, Num[] point );
		public boolean containsAll( Region reg1, Region reg2 );
	}

	static public interface Creator extends Engine<Region>
	{
		public Region createRegionByFunction( Func<Num[],Boolean> func );
		public Region createUniversalRegion();
		public Region createEmptyRegion();
	}

	static public interface Operator extends Engine<Region>
	{
		public Region intersect( Region... regs );
		public Region union( Region... regs );
		public Region complement( Region reg1, Region reg2 );
		public Region complement( Region reg2 );
		public Region transformsBy( Region reg, Transformation trans );
	}

	static public interface Predicate extends Engine<Region>
	{
		public boolean equals( Region reg1, Region reg2 );
		public boolean isUniversal( Region reg );
		public boolean isEmpty( Region reg );
	}
}
