/**
 * Created: 25.06.2012
 */

package de.freese.addressbook.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

import de.freese.addressbook.views.AddressList;

/**
 * @author Thomas Freese
 */
public class RefreshHandler extends AbstractHandler
{
	/**
	 * Erstellt ein neues {@link RefreshHandler} Object.
	 */
	public RefreshHandler()
	{
		super();
	}

	/**
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException
	{
		IWorkbenchPart part = HandlerUtil.getActivePart(event);

		if (part instanceof AddressList)
		{
			((AddressList) part).refresh();
		}

		return null;
	}
}
