package de.freese.addressbook;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * @author Thomas Freese
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor
{
	/**
	 * Erstellt ein neues {@link ApplicationWorkbenchWindowAdvisor} Object.
	 * 
	 * @param configurer {@link IWorkbenchWindowConfigurer}
	 */
	public ApplicationWorkbenchWindowAdvisor(final IWorkbenchWindowConfigurer configurer)
	{
		super(configurer);
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#createActionBarAdvisor(org.eclipse.ui.application.IActionBarConfigurer)
	 */
	@Override
	public ActionBarAdvisor createActionBarAdvisor(final IActionBarConfigurer configurer)
	{
		return new ApplicationActionBarAdvisor(configurer);
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowOpen()
	 */
	@Override
	public void preWindowOpen()
	{
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(800, 600));
		configurer.setTitle("Addressbuch");
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(true);
		configurer.setShowPerspectiveBar(true);
		configurer.setShowFastViewBars(false);
		configurer.setShowMenuBar(true);
		configurer.setShowProgressIndicator(true);
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#preWindowShellClose()
	 */
	@Override
	public boolean preWindowShellClose()
	{
		getWindowConfigurer().getWindow().getActivePage().closeAllEditors(true);

		return super.preWindowShellClose();
	}
}
