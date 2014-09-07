package magicball.model;


public abstract class Face
{
	public boolean equals( Object face ) {
		if ( face instanceof Face )
			return equals((Face) face);
		else
			return false;
	}

	public abstract boolean equals( Face face );
	public abstract Face clone();
}
