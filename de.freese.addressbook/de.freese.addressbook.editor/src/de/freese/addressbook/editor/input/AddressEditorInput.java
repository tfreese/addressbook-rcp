/**
 * Created: 24.07.2012
 */

package de.freese.addressbook.editor.input;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import de.freese.addressbook.editor.Activator;
import de.freese.addressbook.entities.Address;

/**
 * @author Thomas Freese
 */
public class AddressEditorInput implements IEditorInput
{
	/**
	 * 
	 */
	private final Address address;

	/**
	 * Erstellt ein neues {@link AddressEditorInput} Object.
	 * 
	 * @param address {@link Address}
	 */
	public AddressEditorInput(final Address address)
	{
		super();

		this.address = address;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
		{
			return true;
		}

		if (obj == null)
		{
			return false;
		}

		if (!(obj instanceof AddressEditorInput))
		{
			return false;
		}

		AddressEditorInput other = (AddressEditorInput) obj;

		return (getID() == other.getID());
	}

	/**
	 * @see org.eclipse.ui.IEditorInput#exists()
	 */
	@Override
	public boolean exists()
	{
		// Ggf. prüfen ob Objekt noch existiert und false zurückgeben wenn nicht.
		// Vor allem relevant im Zusammenspiel mit getPersistable.
		return true;
	}

	/**
	 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(final Class adapterTarget)
	{
		// Editor-Input-Objekte können optional adaptierbar gestaltet werden
		return null;
	}

	/**
	 * @return int
	 */
	public int getID()
	{
		return this.address.getID();
	}

	/**
	 * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
	 */
	@Override
	public ImageDescriptor getImageDescriptor()
	{
		return Activator.getImageDescriptor("/icons/edit.gif");
	}

	/**
	 * @see org.eclipse.ui.IEditorInput#getName()
	 */
	@Override
	public String getName()
	{
		return "Editor: " + this.address.getName();
	}

	/**
	 * @see org.eclipse.ui.IEditorInput#getPersistable()
	 */
	@Override
	public IPersistableElement getPersistable()
	{
		// getPersistable muss nur implementiert werden, wenn das Editor-Inputobjekt
		// über mehrere Anwendungssessions hinweg gelten soll, z.B. wenn eine
		// "Zuletzt geöffnete Dokumente"-Liste verwendet wird.
		return null;
	}

	/**
	 * @see org.eclipse.ui.IEditorInput#getToolTipText()
	 */
	@Override
	public String getToolTipText()
	{
		return getName();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return getID();
	}
}
