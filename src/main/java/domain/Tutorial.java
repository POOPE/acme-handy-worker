
package domain;

import java.util.ArrayList;

public class Tutorial extends DomainEntity {

	//relations
	public Actor				author;
	public ArrayList<Section>	sections;
	//attributes
	public String				title;
	public String				description;
	public ArrayList<String>	photos;

}
