// This testing program stub creates a queue of HTML tags 
// in a valid sequence.
// You may use this as a starting point for testing
// your removeAll method.
import java.util.*;

public class HtmlValidatorTest {
	public static void main(String[] args) {
		// <b>Hi</b><br/>
		Queue<HtmlTag> tags = new LinkedList<HtmlTag>();
		tags.add(new HtmlTag("b", true));      // <b>
		tags.add(new HtmlTag("b", false));     // </b>
		tags.add(new HtmlTag("br"));           // <br/>
		
		HtmlValidator validator = new HtmlValidator(tags);
	}
}
