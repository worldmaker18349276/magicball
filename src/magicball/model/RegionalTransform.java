package magicball.model;


public class RegionalTransform implements PartialOperator<Transform>
{
	Region region;
	Transform transform;

	public RegionalTransform( Region reg, Transform trans ) {
		this.region = reg;
		this.transform = trans;
	}

	public Filter getFilter() {
		return this.region;
	}

	public Transform getOperator() {
		return this.transform;
	}
}

