package de.freese.addressbook;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * @author Thomas Freese
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor
{
	/**
	 * Erstellt ein neues {@link ApplicationActionBarAdvisor} Object.
	 * 
	 * @param configurer {@link IActionBarConfigurer}
	 */
	public ApplicationActionBarAdvisor(final IActionBarConfigurer configurer)
	{
		super(configurer);
	}

	/**
	 * @see org.eclipse.ui.application.ActionBarAdvisor#fillMenuBar(org.eclipse.jface.action.IMenuManager)
	 */
	@Override
	protected void fillMenuBar(final IMenuManager menuBar)
	{
		// Empty
	}

	/**
	 * @see org.eclipse.ui.application.ActionBarAdvisor#makeActions(org.eclipse.ui.IWorkbenchWindow)
	 */
	@Override
	protected void makeActions(final IWorkbenchWindow window)
	{
		// Empty
	}
}
