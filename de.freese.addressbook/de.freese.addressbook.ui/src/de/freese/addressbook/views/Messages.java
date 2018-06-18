/**
 * Created: 25.06.2012
 */

package de.freese.addressbook.views;

import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Freese
 */
public class Messages extends NLS
{
	/**
	 * 
	 */
	private static final String BUNDLE_NAME = "de.freese.addressbook.views.messages"; //$NON-NLS-1$

	/**
	 * 
	 */
	public static String AddressView_land;

	/**
	 * 
	 */
	public static String AddressView_name;

	/**
	 * 
	 */
	public static String AddressView_plzort;

	/**
	 * 
	 */
	public static String AddressView_strasse;

	/**
	 * 
	 */
	static
	{
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	/**
	 * Erstellt ein neues {@link Messages} Object.
	 */
	private Messages()
	{
		super();
	}
}
