package magicball.model;

import java.util.Set;


public class FaceRegion extends Region
{
	protected Face face;
	protected int side;

	public FaceRegion( Face f, int s ) {
		this.face = f;
		this.side = s;
	}
	
	public FaceRegion clone() {
		return new FaceRegion(getFace().clone(),getSide());
	}

	public Face getFace() {
		return this.face;
	}

	public int getSide() {
		return this.side;
	}

	public boolean inside( Vector v ) {
		return getFace().at(v) == getSide();
	}

	public void apply( Displacement dis ) {
		getFace().apply(dis);
	}
}
