package magicball.model.math.sample;

import magicball.model.math.*;


// intensional definition
public class SetSampleExpression < E > extends Set<E>
{
	protected java.util.Set<E> sam;

	public SetSampleExpression( java.util.Set<E> sam ) {
		this.sam = sam;
	}

	public java.util.Set<E> getSample() {
		return this.sam;
	}
}
