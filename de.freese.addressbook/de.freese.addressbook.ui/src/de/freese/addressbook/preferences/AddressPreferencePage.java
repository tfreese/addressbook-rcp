/**
 * Created: 27.06.2012
 */

package de.freese.addressbook.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.freese.addressbook.Activator;

/**
 * @author Thomas Freese
 */
public class AddressPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage
{
	/**
	 * 
	 */
	public final static String SOME_OPTION = "SomeOption";

	/**
	 * Erstellt ein neues {@link AddressPreferencePage} Object.
	 */
	public AddressPreferencePage()
	{
		super(GRID);

		// Zugriff
		// IPreferenceStore preferenceStore = Activator.getDefault().getPreferenceStore();
		// String value = preferenceStore.getString(AddressPreferencePage.SOME_OPTION);

		// Listener
		// this.preferenceStore.addPropertyChangeListener(new IPropertyChangeListener()
		// {
		// @Override
		// public void propertyChange(final PropertyChangeEvent event)
		// {
		// if (SomePreferencePage.SOME_OPTION.equals(event.getProperty()))
		// {
		// // handle change from event.getOldValue() to event.getNewValue()
		// }
		// }
		// });
	}

	/**
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	protected void createFieldEditors()
	{
		// Felder der Einstellungsseite hinzufügen
		addField(new StringFieldEditor(SOME_OPTION, "Some Option", getFieldEditorParent()));

	}

	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	@Override
	public void init(final IWorkbench workbench)
	{
		// Preference Store des eigenen Plug-ins setzen.
		setPreferenceStore(Activator.getDefault().getPreferenceStore());
	}
}
