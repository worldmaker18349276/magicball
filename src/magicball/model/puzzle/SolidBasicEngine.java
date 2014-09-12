package magicball.model.puzzle;

import magicball.model.geometry.*;


public interface SolidBasicEngine < S extends Solid, R extends Region >
{
	public SolidBasicEngine clone();
	public S createSolidByRegion( R reg );
}
