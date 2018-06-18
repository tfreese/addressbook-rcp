/**
 * Created: 24.07.2012
 */

package de.freese.addressbook.editor.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import de.freese.addressbook.editor.input.AddressEditorInput;
import de.freese.addressbook.editor.part.AddressEditorPart;
import de.freese.addressbook.entities.Address;

/**
 * @author Thomas Freese
 */
public class EditAddressHandler extends AbstractHandler
{
	/**
	 * Erstellt ein neues {@link EditAddressHandler} Object.
	 */
	public EditAddressHandler()
	{
		super();
	}

	/**
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException
	{
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		// IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);

		// IWorkbenchPart part = HandlerUtil.getActivePart(event);
		// System.out.println(part);

		// View view = (View) page.findView(View.ID);
		// ISelection selection = view.getSite().getSelectionProvider()
		// .getSelection();

		IWorkbenchPage page = window.getActivePage();
		ISelection selection = page.getSelection();

		if (selection.isEmpty())
		{
			return null;
		}

		if (!(selection instanceof IStructuredSelection))
		{
			return null;
		}

		try
		{
			// Iterator<Object> iterator = ((IStructuredSelection) selection).iterator();

			Address address = (Address) ((IStructuredSelection) selection).getFirstElement();

			page.openEditor(new AddressEditorInput(address), AddressEditorPart.class.getName());

			// EditorPart editor = page.findEditor(new FileEditorInput((IFile) res));
			// if (editor != null) {
			// page.activate(editor);
			// page.closeEditor(editor, false);
			// }
		}
		catch (Exception ex)
		{
			MessageDialog.openError(window.getShell(), "AddressBook", ex.toString());
		}

		return null;
	}
}
