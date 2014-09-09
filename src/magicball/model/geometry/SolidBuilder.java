package magicball.model.geometry;


// geometry abstraction layer
public interface SolidBuilder
{
	public Solid createSolidByRegion( Region reg );
}
