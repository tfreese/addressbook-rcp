package de.freese.addressbook;

import java.text.DateFormat;
import java.util.Date;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import de.freese.addressbook.perspectives.AddressesPerspective;

/**
 * @author Thomas Freese
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor
{
	/**
	 * Erstellt ein neues {@link ApplicationWorkbenchAdvisor} Object.
	 */
	public ApplicationWorkbenchAdvisor()
	{
		super();
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#createWorkbenchWindowAdvisor(org.eclipse.ui.application.IWorkbenchWindowConfigurer)
	 */
	@Override
	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(	final IWorkbenchWindowConfigurer configurer)
	{
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
	 */
	@Override
	public String getInitialWindowPerspectiveId()
	{
		return AddressesPerspective.ID;
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#initialize(org.eclipse.ui.application.IWorkbenchConfigurer)
	 */
	@Override
	public void initialize(final IWorkbenchConfigurer configurer)
	{
		configurer.setSaveAndRestore(true);

		super.initialize(configurer);
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#postShutdown()
	 */
	@Override
	public void postShutdown()
	{
		System.out.println("ApplicationWorkbenchAdvisor.postShutdown()");
		super.postShutdown();
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#postStartup()
	 */
	@Override
	public void postStartup()
	{
		System.out.println("ApplicationWorkbenchAdvisor.postStartup()");
		super.postStartup();
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#preShutdown()
	 */
	@Override
	public boolean preShutdown()
	{
		System.out.println("ApplicationWorkbenchAdvisor.preShutdown()");
		return super.preShutdown();
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#preStartup()
	 */
	@Override
	public void preStartup()
	{
		System.out.println("ApplicationWorkbenchAdvisor.preStartup()");
		super.preStartup();
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#restoreState(org.eclipse.ui.IMemento)
	 */
	@Override
	public IStatus restoreState(final IMemento memento)
	{
		if (memento != null)
		{
			IMemento myAppMemento = memento.getChild("myApp");

			if (myAppMemento != null)
			{
				System.out.println("Last opened on: " + myAppMemento.getString("lastOpenedDate"));
			}
		}

		return super.restoreState(memento);
	}

	/**
	 * @see org.eclipse.ui.application.WorkbenchAdvisor#saveState(org.eclipse.ui.IMemento)
	 */
	@Override
	public IStatus saveState(final IMemento memento)
	{
		memento.createChild("myApp").putString("lastOpenedDate",
				DateFormat.getDateTimeInstance().format(new Date()));

		return super.saveState(memento);
	}
}
