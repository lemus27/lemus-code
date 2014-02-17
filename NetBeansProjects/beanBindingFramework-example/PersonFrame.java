import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.WindowConstants;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.beansbinding.Validator;
import org.jdesktop.beansbinding.BindingListener;
import org.jdesktop.beansbinding.PropertyStateEvent;

/** A JFrame to edit a Person object,
 * using beans binding */
public class PersonFrame extends JFrame implements BindingListener {

    PersonFrame() {
	setSize(360,180);
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	final GridLayout gridLayout = new GridLayout(0,2);
	setLayout(gridLayout);
	add(new JLabel("First name:"));
	add(firstNameField);

	add(new JLabel("Last name:"));
	add(lastNameField);
	
	add(new JLabel("Year of birth:"));
	add(yearOfBirthField);

	add(new JLabel("Full name:"));
	add(fullName);
	
	add(new JLabel("Your year of birth:"));
	add(yearOfBirthLabel);

	add(new JLabel("Message:"));
	add(messageLabel);
	
	bindProperties();
    }

    /** This method does the work of creating several types
     * of binding, including one with a validator */
    private void bindProperties() {
	// Create a Property which gets the value
	// out of a JTextField
	final Property textProperty = BeanProperty.create("text");

	// Now do a simple binding of the UI elements to the
	// Person bean for firstName and lastName properties
	Binding binding =
	    Bindings.
	    createAutoBinding(AutoBinding.UpdateStrategy.READ, // one-way binding
			      firstNameField, // source of value
			      textProperty, // the property to get
			      person, // the "backing bean"
			      BeanProperty.create("firstName") // property to set
			      );
        binding.bind();

	// do the same for the last name
	binding =
	    Bindings.
	    createAutoBinding(AutoBinding.UpdateStrategy.READ, // one-way binding
			      lastNameField, // source of value
			      textProperty, // the property to get
			      person, // the "backing bean"
			      BeanProperty.create("lastName") // property to set
			      );
        binding.bind();

	binding =
	    Bindings.
	    createAutoBinding(AutoBinding.UpdateStrategy.READ,
			      person,
			      ELProperty.create("${firstName} ${lastName}"),
			      fullName,
			      textProperty
			      );
	binding.bind();

	// Bind the year-of-birth from the UI to the Person bean
	binding =
	    Bindings.
	    createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE, // two-way binding

			      person,
			      BeanProperty.create("yearOfBirth"), // property to set

			      yearOfBirthField, // source of value
			      textProperty, // the property to get

			      "yearOfBirth");
	// note that the converter and validators must be installed
	// before calling bind()
	binding.setConverter(new PositiveIntegerConverter());
	binding.setValidator(new YearValidator());
	// this allows us to get sync failure notifications,
	// so they can be displayed
	binding.addBindingListener(this);
        binding.bind();

	// and bind the year-of-birth from the Person back to the UI label
	binding =
	    Bindings.
	    createAutoBinding(AutoBinding.UpdateStrategy.READ,
			      person,
			      BeanProperty.create("yearOfBirth"),
			      yearOfBirthLabel,
			      textProperty
			      );
	// note that no converter is used; the Object.toString() method is used
	// final Validator validator = new YearValidator();
	binding.bind();

    }

    private final Person person = new Person();

    private final JTextField firstNameField = new JTextField();

    private final JTextField lastNameField = new JTextField();

    private final JTextField yearOfBirthField = new JTextField();

    private final JLabel fullName = new JLabel();

    private final JLabel yearOfBirthLabel = new JLabel();

    private final JLabel messageLabel = new JLabel();

    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {

		public void run() {
		    new PersonFrame().setVisible(true);
		}
	    });
    }

    /** Methods of BindingListener; only the synced() method is needed */
    public void bindingBecameBound(Binding binding) { }

    public void bindingBecameUnbound(Binding binding) { }

    public void sourceChanged(Binding binding, PropertyStateEvent event) { }

    public void synced(Binding binding) { }
    
    /** This is where the work happens */
    public void syncFailed(Binding binding, Binding.SyncFailure failure) { 
	messageLabel.setText(failure.getValidationResult().getDescription());
    }

    public void targetChanged(Binding binding, PropertyStateEvent event) { }

}
