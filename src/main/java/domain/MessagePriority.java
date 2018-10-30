
package domain;

public class MessagePriority {

	public final String	HIGH	= "HIGH";
	public final String	NEUTRAL	= "NEUTRAL";
	public final String	LOW		= "LOW";
	public String		priority;


	public String getPriority() {
		return this.priority;
	}

	public void setPriority(final String priority) {
		this.priority = priority;
	}

}
