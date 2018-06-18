/**
 * Created: 25.06.2012
 */

package de.freese.addressbook.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * @author Thomas Freese
 */
public class SomeCommandHandler extends AbstractHandler
{
	/**
	 * Erstellt ein neues {@link SomeCommandHandler} Object.
	 */
	public SomeCommandHandler()
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
		MessageDialog.openInformation(window.getShell(), "AddressBook", "Hello, Eclipse world");

		return null;
	}
}
