package magicball.model;


public interface CompositeEngine < E > extends Engine<E>
{
	public void add( Engine<? extends E> engine );
	public Engine<? extends E> get( Class<? extends Engine<? extends E>> clazz );
}
