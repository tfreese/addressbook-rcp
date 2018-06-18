/**
 * Created: 24.06.2012
 */

package de.freese.addressbook.maps.views;

import java.net.URLEncoder;
import java.util.Iterator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import de.freese.addressbook.entities.Address;

/**
 * @author Thomas Freese
 */
public class MapView extends ViewPart implements ISelectionListener
{
	/**
	 * 
	 */
	public static final String ID = "de.freese.addressbook.maps.views.MapView"; //$NON-NLS-1$

	/**
	 * 
	 */
	private Browser browser = null;

	/**
	 * Erstellt ein neues {@link MapView} Object.
	 */
	public MapView()
	{
		super();
	}

	/**
	 * Create the actions.
	 */
	private void createActions()
	{
		// Create the actions
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void createPartControl(final Composite parent)
	{
		this.browser = new Browser(parent, SWT.FILL);

		showCity(null);

		createActions();
		initializeToolBar();
		initializeMenu();
		initializeSelection();
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose()
	{
		ISelectionService selectionService = getSite().getWorkbenchWindow().getSelectionService();
		selectionService.removePostSelectionListener(this);

		super.dispose();
	}

	/**
	 * Initialize the menu.
	 */
	private void initializeMenu()
	{
		// IMenuManager menuManager = getViewSite().getActionBars().getMenuManager();
	}

	/**
	 * 
	 */
	private void initializeSelection()
	{
		ISelectionService selectionService = getSite().getWorkbenchWindow().getSelectionService();
		selectionService.addPostSelectionListener(this);
	}

	/**
	 * Initialize the toolbar.
	 */
	private void initializeToolBar()
	{
		// IToolBarManager toolbarManager = getViewSite().getActionBars().getToolBarManager();
	}

	/**
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void selectionChanged(final IWorkbenchPart part, final ISelection selection)
	{
		if (selection.isEmpty())
		{
			showCity(null);
			return;
		}

		if (!(selection instanceof IStructuredSelection))
		{
			return;
		}

		Iterator<Object> iterator = ((IStructuredSelection) selection).iterator();

		while (iterator.hasNext())
		{
			Object object = iterator.next();

			if (object instanceof Address)
			{
				showCity(((Address) object).getCity());
				break;
			}
		}
	}

	/**
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus()
	{
		this.browser.setFocus();
	}

	/**
	 * @param city String
	 */
	private void showCity(final String city)
	{
		String url = null;

		try
		{
			if (city == null)
			{
				url = "http://maps.google.de/maps";
			}
			else
			{
				url =
						String.format("http://maps.google.de/maps?q=%s",
								URLEncoder.encode(city, "UTF-8"));
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		this.browser.setUrl(url);
	}
}
