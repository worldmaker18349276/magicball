package magicball.model;


// physical puzzle abstraction layer
public interface SolidBuilder
{
	public Solid createSolidByRegion( Region reg );
}
