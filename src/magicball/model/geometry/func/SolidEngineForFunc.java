package magicball.model.geometry;


public class SolidEngineForFunc implements SolidBasicEngine<SolidRegionExpression,RegionSetExpression>
{
	public SolidEngineForFunc clone();
	public SolidRegionExpression createSolidByRegion( RegionSetExpression reg );
}
