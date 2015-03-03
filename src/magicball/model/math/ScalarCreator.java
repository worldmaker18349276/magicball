package magicball.model.math;

import magicball.model.*;


public interface ScalarCreator extends Engine<Num>
{
	public Num createNumberByDouble( double n );
	public Num createZero();
	public Num createOne();
	public Num createPi();
	public Num createE();
}
