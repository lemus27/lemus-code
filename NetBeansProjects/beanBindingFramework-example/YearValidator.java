import org.jdesktop.beansbinding.Validator;
import java.util.Calendar;

/** Validate the year-of-birth field.
 * This year can't be in the future. */
public final class YearValidator extends Validator<Integer> {

    @Override
    public Validator.Result validate(Integer year) {

	if(year == null) 
	    return new Result(null, "You must specify a year");

	final int currentYear = Calendar.getInstance().get(Calendar.YEAR);

	final int yearInt = year.intValue();

	if(yearInt > currentYear) 
	    return new Result(null, "Year: " + year + " is in the future");

        return null;    
    }

    public String toString() { return "YearValidator: " +
	    "currentYear=" + Calendar.getInstance().get(Calendar.YEAR);
    }
}
