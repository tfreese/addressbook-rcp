/**
 * Created: 19.07.2012
 */

package de.freese.addressbook.utils;

import java.io.OutputStream;
import java.io.PrintStream;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.IOConsoleOutputStream;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

/**
 * @author Thomas Freese
 */
public final class ConsoleUtils
{
	/**
	 * @author Thomas Freese
	 */
	public static enum ConsoleType
	{
		/**
		 * 
		 */
		DEFAULT,
		/**
		 * 
		 */
		ERRORS,
		/**
		 * 
		 */
		OTHER,
		/**
		 * 
		 */
		STDERR,
		/**
		 * 
		 */
		STDOUT
	}

	// /**
	// *
	// */
	// private static final ConsoleUtils INSTANCE = new ConsoleUtils();

	/**
	 * Show the application console. If the given console is null, attempt to find an existing
	 * console and use it. If the given console is null and no existing consoles exist, exit without
	 * doing anything.
	 * 
	 * @param myConsole An existing console.
	 */
	public static void displayConsole(final IConsole myConsole)
	{
		IConsole console = myConsole;

		// try to grab any old console and display it if given null
		if (console == null)
		{
			ConsolePlugin plugin = ConsolePlugin.getDefault();
			IConsoleManager conMan = plugin.getConsoleManager();
			IConsole[] existing = conMan.getConsoles();

			if (existing.length == 0)
			{
				return;
			}

			for (final IConsole element : existing)
			{
				console = element;
			}
		}

		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]
		{
			console
		});
		ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
	}

	/**
	 * Show the console view with the given ConsoleType. Will not create one if one does not already
	 * exist.
	 * 
	 * @param name Non-null enum of existing console (stdout/err probably safe)
	 */
	public static void displayConsole(final String name)
	{
		ConsolePlugin plugin = ConsolePlugin.getDefault();
		IConsoleManager conMan = plugin.getConsoleManager();
		IConsole[] existing = conMan.getConsoles();

		for (final IConsole element : existing)
		{
			if (name.compareTo(element.getName()) == 0)
			{
				ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[]
				{
					element
				});

				ConsolePlugin.getDefault().getConsoleManager().showConsoleView(element);

				return;
			}
		}
	}

	/**
	 * With the given ConsoleType enum, look for an existing/open console and return it, or if it
	 * does not exist, create a new one and return it.
	 * 
	 * @param name One of the members of the static enum defined in Console
	 * @return A non null message console
	 */
	private static MessageConsole findOrCreateConsole(final String name)
	{
		final ConsolePlugin plugin = ConsolePlugin.getDefault();
		final IConsoleManager conMan = plugin.getConsoleManager();
		final IConsole[] existing = conMan.getConsoles();

		for (final IConsole element : existing)
		{
			if (name.equals(element.getName()))
			{
				return (MessageConsole) element;
			}
		}

		// failed to find existing console, create one:
		final MessageConsole myConsole = new MessageConsole(name, null);
		conMan.addConsoles(new IConsole[]
		{
			myConsole
		});

		try
		{
			write(myConsole.newMessageStream(), "Initializing: " + name + "\n");
		}
		catch (Exception ex)
		{
			// Platform might not be up yet, silently ignore for now...
		}

		return myConsole;
	}

	// /**
	// * @return {@link ConsoleUtils}
	// */
	// public static ConsoleUtils getInstance()
	// {
	// return INSTANCE;
	// }

	/**
	 * Return a new output stream for a given console. If the console does not exist it will be
	 * created.
	 * 
	 * @param name A console type defined in Console's ConsoleType enum.
	 * @return An open stream for writing to.
	 */
	public static OutputStream getOutputStream(final String name)
	{
		MessageConsole ib = findOrCreateConsole(name);
		IOConsoleOutputStream mcs = ib.newOutputStream();
		mcs.setActivateOnWrite(true);

		return mcs;
	}

	/**
	 * Create consoles for STDERR and STDOUT, redirect all output to the in-application console.
	 * Should only be called once.
	 */
	public static void init()
	{
		MessageConsole outConsole = findOrCreateConsole(ConsoleType.STDOUT.name());
		IOConsoleOutputStream outStream = outConsole.newOutputStream();
		outStream.setActivateOnWrite(true);
		System.setOut(new PrintStream(outStream));

		LocalResourceManager resources = new LocalResourceManager(JFaceResources.getResources());
		MessageConsole errConsole = findOrCreateConsole(ConsoleType.STDERR.name());
		IOConsoleOutputStream errStream = errConsole.newOutputStream();
		errStream.setColor(resources.createColor(new RGB(255, 0, 0)));
		System.setErr(new PrintStream(errStream));

		// displayConsole(outConsole);
		// ConsolePlugin.getDefault().getConsoleManager().showConsoleView(outConsole);
	}

	/**
	 * Alternate way to bring up the console view. Don't know which one is better / the differences.
	 * 
	 * @param myConsole Console to show, must not be null.
	 */
	public static void showConsole(final IConsole myConsole)
	{
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();

		IWorkbenchPage page = window.getActivePage();
		String id = IConsoleConstants.ID_CONSOLE_VIEW;
		IConsoleView view;

		try
		{
			view = (IConsoleView) page.showView(id);
			view.display(myConsole);
		}
		catch (PartInitException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * @param stream {@link MessageConsoleStream}
	 * @param msg String
	 */
	public static void write(final MessageConsoleStream stream, final String msg)
	{
		stream.println(msg);
	}

	/**
	 * Used for quick writes to consoles, this is not in place of std err/out. If the given console
	 * does not exist it will be created.
	 * 
	 * @param name String
	 * @param msg String
	 */
	public static void write(final String name, final String msg)
	{
		MessageConsole myConsole = findOrCreateConsole(name);
		MessageConsoleStream out = myConsole.newMessageStream();

		write(out, msg);
	}

	/**
	 * Erstellt ein neues {@link ConsoleUtils} Object.
	 */
	private ConsoleUtils()
	{
		super();
	}
}
