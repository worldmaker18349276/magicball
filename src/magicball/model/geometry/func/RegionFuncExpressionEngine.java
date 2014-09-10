package magicball.model.geometry.func;

import magicball.model.geometry.*;


public class RegionFuncExpressionEngine implements RegionEngine
{
	private static final RegionFuncExpression UNIVERSE = new UniverseRegionFuncExpression();
	private static final RegionFuncExpression EMPTY = new EmptyRegionFuncExpression();

	private static RegionFuncExpression cast( Region reg ) {
		return (RegionFuncExpression) reg;
	}

	private static RegionFuncExpression [] cast( Region [] regs ) {
		RegionFuncExpression [] regs_ = new RegionFuncExpression [ regs.length ];
		for ( int i=0; i<regs.length; i++ )
			regs_[i] = (RegionFuncExpression) regs[i];
		return regs_;
	}

	public Region intersect( Region... regs ) {
		return new IntersectionRegionFuncExpression(cast(regs));
	}

	public Region union( Region... regs ) {
		return new UnionRegionFuncExpression(cast(regs));
	}

	public Region complement( Region reg1, Region reg2 ) {
		return new ComplementRegionFuncExpression(cast(reg1),cast(reg1));
	}

	public Region createUniverseRegion() {
		return RegionFuncExpressionEngine.UNIVERSE;
	}

	public Region createEmptyRegion() {
		return RegionFuncExpressionEngine.EMPTY;
	}

	public Region createRegionByFace( Surface face, int side ) {
		return new RegionBelowSurfaceFuncExpression((SurfaceFuncExpression)face,side);
	}
}
