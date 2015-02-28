package magicball.model;


public interface CompositeEngine < O > extends Engine<O>
{
	public void add( Engine<O> engine );
	public Engine<O> get( Class<? extends Engine<O>> clazz );
}
