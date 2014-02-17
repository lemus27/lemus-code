import org.jdesktop.beansbinding.Converter;

/** Convert a string to a positive integer, including zero,
 * by removing all non-digit characters */
public class PositiveIntegerConverter extends Converter<Integer,String> {

    public String convertForward(Integer i) {
	if(i == null) return "0";
	return i.toString();
    }

    public Integer convertReverse(String s) {
	if(s == null) return 0;

	final StringBuilder sb = new StringBuilder();
	for(int i = 0; i < s.length(); i++) 
	    if(Character.isDigit(s.charAt(i))) sb.append(s.charAt(i));

	int i = 0;
	try {
	    i = Integer.parseInt(sb.toString());
	} catch(NumberFormatException nfe) {
	    return null;
	}
	if(i < 0) return -1 * i;
	return i;
    }
}
