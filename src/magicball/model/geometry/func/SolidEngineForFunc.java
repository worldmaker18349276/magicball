package magicball.model.geometry.func;

import magicball.model.geometry.*;


public class SolidEngineForFunc implements SolidBasicEngine<SolidRegionExpression<RegionSetExpression>,RegionSetExpression>
{
	public SolidEngineForFunc() {}

	public SolidEngineForFunc clone() {
		return new SolidEngineForFunc();
	}

	public SolidRegionExpression<RegionSetExpression> createSolidByRegion( RegionSetExpression reg ) {
		return new SolidRegionExpression<RegionSetExpression>(reg);
	}
}
