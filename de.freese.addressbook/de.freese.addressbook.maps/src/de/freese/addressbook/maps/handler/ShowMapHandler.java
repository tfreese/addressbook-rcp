/**
 * Created: 25.06.2012
 */

package de.freese.addressbook.maps.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import de.freese.addressbook.maps.views.MapView;

/**
 * @author Thomas Freese
 */
public class ShowMapHandler extends AbstractHandler
{
	/**
	 * Erstellt ein neues {@link ShowMapHandler} Object.
	 */
	public ShowMapHandler()
	{
		super();
	}

	/**
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException
	{
		try
		{
			// HandlerUtil.getCurrentSelection(event);

			IWorkbenchPage page = HandlerUtil.getActiveWorkbenchWindow(event).getActivePage();
			page.showView(MapView.ID);
		}
		catch (PartInitException ex)
		{
			throw new ExecutionException(ex.getMessage(), ex);
		}

		return null;
	}
}
