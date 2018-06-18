package de.freese.addressbook;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import de.freese.addressbook.utils.ConsoleUtils;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication
{
	/**
	 * Erstellt ein neues {@link Application} Object.
	 */
	public Application()
	{
		super();
	}

	/**
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	@Override
	public Object start(final IApplicationContext context) throws Exception
	{
		Display display = PlatformUI.createDisplay();

		ConsoleUtils.init();

		// MessageConsole console = new MessageConsole("Console", null);
		// ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]
		// {
		// console
		// });
		// ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
		// MessageConsoleStream stream = console.newMessageStream();
		// stream.setActivateOnWrite(true);
		//
		// System.setOut(new PrintStream(stream));
		// System.setErr(new PrintStream(stream));

		try
		{
			int returnCode =
					PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());

			if (returnCode == PlatformUI.RETURN_RESTART)
			{
				return IApplication.EXIT_RESTART;
			}

			return IApplication.EXIT_OK;
		}
		finally
		{
			display.dispose();
		}
	}

	/**
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	@Override
	public void stop()
	{
		if (!PlatformUI.isWorkbenchRunning())
		{
			return;
		}

		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();

		display.syncExec(new Runnable()
		{
			/**
			 * @see java.lang.Runnable#run()
			 */
			@Override
			public void run()
			{
				if (!display.isDisposed())
				{
					workbench.close();
				}
			}
		});
	}
}
