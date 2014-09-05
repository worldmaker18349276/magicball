package magicball.model;

import java.util.Set;
import java.util.List;
import java.util.HashSet;


public class PhysicalPuzzle
{
	protected Set<Solid> components;


	public PhysicalPuzzle( Set<Solid> sols ) {
		setComponents(sols);
	}

	public Set<Solid> getComponents() {
		return this.components;
	}

	public void setComponents( Set<Solid> sols ) {
		this.components = sols;
	}

	public PhysicalPuzzle clone() {
		Set<Solid> sols = new HashSet<Solid>();
		for ( Solid sol : sols ) {
			sols.add(sol.clone());
		}
		return new PhysicalPuzzle(sols);
	}

	public boolean equals( Object puzzle ) {
		if ( puzzle instanceof PhysicalPuzzle )
			return equals((PhysicalPuzzle) puzzle);
		else
			return false;
	}

	public boolean equals( PhysicalPuzzle puzzle ) {
		return getComponents().equals(puzzle.getComponents());
	}


	public boolean isValid() {
		// check no duplicate occupy
		Set<Solid> sols = getComponents();
		for ( Solid sol1 : sols ) {
			Region reg = sol1.getRegion();
			for ( Solid sol2 : sols ) {
				if ( sol1 != sol2 )
					if ( reg.at(sol2) != 1 )
						return false;
			}
		}
		return true;
		// TODO: more effecent algorithm
	}

	public void apply( Operator op ) throws IllegalStateException {
		apply(getComponents(),op);
	}

	protected void apply( Set<Solid> sols, Operator op ) throws IllegalStateException {
		if ( op instanceof SequenceOperator )
			apply(sols,(SequenceOperator<? extends Operator>) op);
		else if ( op instanceof PartialOperator )
			apply(sols,(PartialOperator<? extends Operator>) op);
		else if ( op instanceof ValidatedOperator )
			apply(sols,(ValidatedOperator<? extends Operator>) op);
		else if ( op instanceof Displacement )
			apply(sols,(Displacement) op);
		else
			throw new IllegalArgumentException();
	}

	protected void apply( Set<Solid> sols, SequenceOperator<? extends Operator> cop ) throws IllegalStateException {
		List<? extends Operator> op_list = cop.divided();
		for ( Operator op : op_list ) {
			apply(sols,op);
		}
	}

	protected void apply( Set<Solid> sols, PartialOperator<? extends Operator> pop ) throws IllegalStateException {
		Set<Solid> selected_sols = pop.getFilter().filter(sols);
		apply(selected_sols,pop.getOperator());
	}

	protected void apply( Set<Solid> sols, ValidatedOperator<? extends Operator> vop ) throws IllegalStateException {
		apply(sols,vop.getOperator());
		if ( !isValid() )
			throw new IllegalStateException();
	}

	protected void apply( Set<Solid> sols, Displacement dis ) throws IllegalStateException {
		for ( Solid sol : sols ) {
			sol.apply(dis);
		}
	}
}

