import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/** The ubiquitous Person bean class, used in a
 * Beans Binding demo.
 * Note especially that this class follows certain essential
 * requirements of the <i>bean</i> pattern:
 * <ul>
 * <li>Getter and setter methods for the properties</li>
 * <li><code>addPropertyChangeListener</code> method</li>
 * <li><code>removePropertyChangeListener</code> method</li>
 * </ul>
 * The <code>addPropertyChangeListener</code> and
 * <code>removePropertyChangeListener</code> methods are essential
 * to correct operation of this bean, and the beans binding.
 * Unless those methods are there, the AutoBindings will have
 * no way of knowing about changes to Person's properties.
 * This class uses PropertyChangeSupport to do the work
 *  */
public class Person {

    public String toString() { 
	return "Person: firstName=" + firstName + "," +
	    "lastName=" + lastName + "," + 
	    "yearOfBirth=" + yearOfBirth;
    }

    private String firstName;
    private String lastName;
    private Integer yearOfBirth;

    private final PropertyChangeSupport propertyChangeSupport =
	new PropertyChangeSupport(this);

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { 
	// The property value changes first, and then the property
	// change event is fired, so the old value must be stored in a
	// temporary variable
	final String old = this.firstName;
	this.firstName = firstName;

	propertyChangeSupport.
	    firePropertyChange(
			       "firstName", // the name of the property
			       old, // the old value
			       firstName // the new value
			       );
    }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { 
	final String old = this.lastName;
	this.lastName = lastName;

	propertyChangeSupport.
	    firePropertyChange(
			       "lastName", // the name of the property
			       old, // the old value
			       lastName // the new value
			       );
    }

    public Integer getYearOfBirth() { return yearOfBirth; }

    /** This value can't be in the future */
    public void setYearOfBirth(Integer yearOfBirth) { 
	final Integer old = this.yearOfBirth;
	this.yearOfBirth = yearOfBirth;

	propertyChangeSupport.
	    firePropertyChange(
			       "yearOfBirth", // the name of the property
			       old, // the old value
			       yearOfBirth // the new value
			       );
	
    }

    /** Essential Bean proeprty change handling events, so that Beans Binding
     * can use a Person as a source, not just a target */
    public void addPropertyChangeListener(PropertyChangeListener l) {
	propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
	propertyChangeSupport.removePropertyChangeListener(l);
    }

}
