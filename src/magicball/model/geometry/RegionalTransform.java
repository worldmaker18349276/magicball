package magicball.model.geometry;


// physical puzzle abstraction layer
public abstract class RegionalTransform
{
	public abstract Region getRegion(); // filter
	public abstract Transform getTransform();
}

