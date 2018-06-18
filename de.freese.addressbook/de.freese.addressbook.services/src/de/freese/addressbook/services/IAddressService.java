package de.freese.addressbook.services;

import java.util.List;

import de.freese.addressbook.entities.Address;
import de.freese.addressbook.entities.Country;

/**
 * IAddressService is a simple data providing service which maintains a list of {@link Address}
 * objects.
 * 
 * @author Thomas Freese
 */
public interface IAddressService
{
	/**
	 * Adds an IAddressChangeListener which will be notified whenever addresses are changed, added
	 * or removed.
	 * 
	 * @param listener {@link IAddressChangeListener}
	 */
	public void addAddressChangeListener(IAddressChangeListener listener);

	/**
	 * Deletes the address with matching primary key id.
	 * 
	 * @param id int
	 */
	public void deleteAddress(int id);

	/**
	 * Returns a Address by its primary key id.
	 * 
	 * @param id int
	 * @return {@link Address}
	 */
	public Address getAddress(int id);

	/**
	 * Returns a List<Address> of all known addresses.
	 * 
	 * @return {@link List}
	 */
	public List<Address> getAllAddresses();

	/**
	 * Returns a list of all known cities as String[].
	 * 
	 * @return String[]
	 */
	public String[] getAllCities();

	/**
	 * Returns a list of all known countries as List<Country>.
	 * 
	 * @return {@link List}
	 */
	public List<Country> getAllCountries();

	/**
	 * Removes an IAddressChangeListener.
	 * 
	 * @param listener {@link IAddressChangeListener}
	 */
	public void removeAddressChangeListener(IAddressChangeListener listener);

	/**
	 * Saves a changed address. New addresses created with new Address() are created automatically
	 * and returned with a primary key.
	 * 
	 * @param changedOrNewAddress {@link Address}
	 * @return {@link Address}
	 */
	public Address saveAddress(Address changedOrNewAddress);
}