package magicball.model.geometry;


public interface SolidEngine < S extends Solid, R extends Region >
{
	public S createSolidByRegion( R reg );
}
