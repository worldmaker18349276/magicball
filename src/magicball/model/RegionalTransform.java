package magicball.model;


// physical puzzle abstraction layer
public class RegionalTransform
{
	protected Region region;
	protected Transform transform;

	public RegionalTransform( Region reg, Transform trans ) {
		this.region = reg;
		this.transform = trans;
	}

	public Region getRegion() {
		return this.region;
	}

	public Transform getTransform() {
		return this.transform;
	}
}

