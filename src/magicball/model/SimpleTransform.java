package magicball.model;

import java.util.List;
import java.util.ArrayList;


public class SimpleTransform extends Transform
{
	protected Displacement displacement;

	public SimpleTransform( Displacement dis ) {
		this.displacement = dis;
	}

	public Displacement getDisplacement() {
		return this.displacement;
	}

	public List<Displacement> divideIntoDisplacements( int divisor ) {
		Displacement sub_dis = getDisplacement().divide(divisor);
		List<Displacement> dis_list = new ArrayList<Displacement>(divisor);
		for ( int i=0; i<divisor; i++ )
			dis_list.add(sub_dis);
		return dis_list;
	}

	public List<Displacement> divideIntoDisplacements() {
		return divideIntoDisplacements(20);
	}
}

