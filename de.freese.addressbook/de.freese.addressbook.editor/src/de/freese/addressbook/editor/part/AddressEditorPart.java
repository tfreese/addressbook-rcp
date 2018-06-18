/**
 * Created: 24.07.2012
 */

package de.freese.addressbook.editor.part;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import de.freese.addressbook.editor.input.AddressEditorInput;

/**
 * @author Thomas Freese
 */
public class AddressEditorPart extends EditorPart
{
	/**
	 * 
	 */
	private boolean dirty = false;

	/**
	 * Erstellt ein neues {@link AddressEditorPart} Object.
	 */
	public AddressEditorPart()
	{
		super();
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(final Composite parent)
	{
		// Analog zu ViewParts sind in createPartControl die UI-Inhalte des
		// Editors zu erzeugen
		// ...

		// someTextField.addModifyListener(new ModifyListener() {
		// public void modifyText(ModifyEvent e) {
		// setDirty(true);
		// }
		// });
	}

	/**
	 * @see org.eclipse.ui.part.EditorPart#doSave(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void doSave(final IProgressMonitor monitor)
	{
		// doSave wird aufgerufen, sobald der Benutzer den Speichern-Command
		// auslöst und sollte die Änderungen des Editors persistieren.
	}

	/**
	 * @see org.eclipse.ui.part.EditorPart#doSaveAs()
	 */
	@Override
	public void doSaveAs()
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * @see org.eclipse.ui.part.EditorPart#getEditorInput()
	 */
	@Override
	public AddressEditorInput getEditorInput()
	{
		return (AddressEditorInput) super.getEditorInput();
	}

	/**
	 * @see org.eclipse.ui.part.EditorPart#init(org.eclipse.ui.IEditorSite,
	 *      org.eclipse.ui.IEditorInput)
	 */
	@Override
	public void init(final IEditorSite site, final IEditorInput input) throws PartInitException
	{
		// Sobald der Editor erzeugt wird, wird die init-Methode aufgerufen.
		// EditorPart verlangt, dass man die hereingereichte EditorSite und das
		// EditorInput-Objekt setzt. In dieser Methode könnte man auch
		// Nicht-UI bezogene Initialisierungen erledigen.
		setSite(site);
		setInput(input);

		// Sicherstellen, das das Input-Objekt vom erwarteten Typ ist
		Assert.isTrue(input instanceof AddressEditorInput,
				"Input object needs to be AddressEditorInput!");

		// Titel des Editors kann gesetzt werden mit:
		setPartName(getEditorInput().getName());
	}

	/**
	 * @see org.eclipse.ui.part.EditorPart#isDirty()
	 */
	@Override
	public boolean isDirty()
	{
		// Die Workbench erledigt das Handling des Speichern und die
		// Anzeige des aktuellen Änderungsstatus des Editors. Dazu muss isDirty
		// Auskunft darüber geben, ob der Editor ungesicherte Änderungen enthält
		// (= dirty ist). Dies ist gemäß der Inhalte des Editors herauszufinden und
		// zurückzugeben.
		return this.dirty;
	}

	/**
	 * @see org.eclipse.ui.part.EditorPart#isSaveAsAllowed()
	 */
	@Override
	public boolean isSaveAsAllowed()
	{
		return false;
	}

	/**
	 * @param dirty boolean
	 */
	@SuppressWarnings("unused")
	private void setDirty(final boolean dirty)
	{

		this.dirty = dirty;
		firePropertyChange(IEditorPart.PROP_DIRTY);
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus()
	{
		// setFocus muss implementiert werden: einem Control im Part
		// den Eingabefokus geben!
		// someControl.setFocus();
	}
}
