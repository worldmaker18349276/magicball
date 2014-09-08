package magicball.model;


// geometry abstraction layer
public interface SolidBuilder
{
	public Solid createSolidByRegion( Region reg );
}
