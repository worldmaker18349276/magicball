package magicball.model.geometry;

import magicball.model.math.*;
import magicball.model.*;


public class CompositeRegionBasicEngine extends DefaultCompositeEngine<Region> implements RegionBasicEngine
{
	// creater
	public Region createRegionByFunction( Function<Number[],Boolean> func ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Creator ) {

			try {
				return ((RegionBasic.Creator)engine).createRegionByFunction(func);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region createUniversalRegion() {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Creator ) {

			try {
				return ((RegionBasic.Creator)engine).createUniversalRegion();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region createEmptyRegion() {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Creator ) {

			try {
				return ((RegionBasic.Creator)engine).createEmptyRegion();
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// attribute
	public boolean contains( Region reg, Number[] point ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Behavior ) {

			try {
				return ((RegionBasic.Behavior)engine).contains(reg,point);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}


	// operator
	public Region intersect( Region... regs ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Operator ) {

			try {
				return ((RegionBasic.Operator)engine).intersect(regs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region union( Region... regs ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Operator ) {

			try {
				return ((RegionBasic.Operator)engine).union(regs);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region complement( Region reg1, Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Operator ) {

			try {
				return ((RegionBasic.Operator)engine).complement(reg1,reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region complement( Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Operator ) {

			try {
				return ((RegionBasic.Operator)engine).complement(reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public Region transformsBy( Region reg, Transformation trans ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Operator ) {

			try {
				return ((RegionBasic.Operator)engine).transformsBy(reg,trans);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}



	// predicate
	public boolean isEmpty( Region reg ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Predicate ) {

			try {
				return ((RegionBasic.Predicate)engine).isEmpty(reg);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean isUniversal( Region reg ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Predicate ) {

			try {
				return ((RegionBasic.Predicate)engine).isUniversal(reg);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean containsAll( Region reg1, Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Predicate ) {

			try {
				return ((RegionBasic.Predicate)engine).containsAll(reg1,reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

	public boolean equals( Region reg1, Region reg2 ) {
		for ( Engine<? extends Region> engine : engines ) if ( engine instanceof RegionBasic.Predicate ) {

			try {
				return ((RegionBasic.Predicate)engine).equals(reg1,reg2);
			} catch ( UnsupportedExpressionException | UnsupportedAlgorithmException e ) {
				continue;
			}

		}
		throw new UnsupportedAlgorithmException();
	}

}
