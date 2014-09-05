package magicball.model;

import java.util.List;


public class SimultaneousRegionalTransform implements ContinuousOperator<ValidatedOperator<SequenceOperator<Displacement>>>
{
	RegionalTransform [] rtransforms;
	public SimultaneousRegionalTransform( RegionalTransform... rtrans ) {
		this.rtransforms = rtrans;
	}
	public List<ValidatedOperator<Displacement>> dividedBy( int divisor ) {
		this.
	}
	public List<ValidatedOperator<Displacement>> divided();
}

