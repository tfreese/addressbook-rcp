package de.freese.addressbook;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * @author Thomas Freese
 */
public class Activator implements BundleActivator
{
	/**
	 * 
	 */
	private static BundleContext context;

	/**
	 * @return {@link BundleContext}
	 */
	static BundleContext getContext()
	{
		return context;
	}

	/**
	 * Erstellt ein neues {@link Activator} Object.
	 */
	public Activator()
	{
		super();
	}

	/**
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(final BundleContext bundleContext) throws Exception
	{
		Activator.context = bundleContext;
		System.out.println("ActivatorServices.start()");
	}

	/**
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(final BundleContext bundleContext) throws Exception
	{
		Activator.context = null;
		System.out.println("ActivatorServices.stop()");
	}
}
