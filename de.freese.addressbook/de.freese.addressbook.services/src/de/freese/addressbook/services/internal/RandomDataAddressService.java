package de.freese.addressbook.services.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import de.freese.addressbook.entities.Address;
import de.freese.addressbook.entities.Country;
import de.freese.addressbook.services.IAddressChangeListener;
import de.freese.addressbook.services.IAddressService;

/**
 * RandomDataAddressService is an "for practive purpose only" implementation of
 * {@link IAddressService}. It returns randomly created addresses, no persistence happens at all.
 * Also the calls are slowed down using Thread.sleep to imitate the behavior of a service
 * implementation which loads data from a backend server.
 * 
 * @author Thomas Freese
 */
public class RandomDataAddressService implements IAddressService
{
	/**
	 * 
	 */
	private final AtomicInteger idSequence = new AtomicInteger(0);

	/**
	 * 
	 */
	private final List<Address> addresses;

	/**
	 * 
	 */
	private final LinkedHashSet<IAddressChangeListener> addressChangeListeners =
			new LinkedHashSet<>();

	/**
			 * 
			 */
	private final List<Country> countries;

	/**
	 * Erstellt ein neues {@link RandomDataAddressService} Object.
	 */
	public RandomDataAddressService()
	{
		super();

		this.countries = new ArrayList<>();

		for (String countryName : RandomData.COUNTRIES)
		{
			this.countries.add(new Country(countryName));
		}

		this.addresses = new ArrayList<>();

		for (int i = 1; i <= 50; i++)
		{
			RandomData rd = new RandomData(i);
			this.addresses.add(new Address(this.idSequence.incrementAndGet(), rd.somePersonName(),
					rd.someStreet(), rd.someZipCode(), rd.someCity(), this.countries.get(i
							% this.countries.size())));
			rd.newData();
		}
	}

	/**
	 * @see de.freese.addressbook.services.IAddressService#addAddressChangeListener(de.freese.addressbook.services.IAddressChangeListener)
	 */
	@Override
	public void addAddressChangeListener(final IAddressChangeListener listener)
	{
		this.addressChangeListeners.add(listener);
	}

	/**
	 * @see de.freese.addressbook.services.IAddressService#deleteAddress(int)
	 */
	@Override
	public void deleteAddress(final int id)
	{
		for (Iterator<Address> i = this.addresses.iterator(); i.hasNext();)
		{
			Address address = i.next();

			if (address.getID() == id)
			{
				i.remove();
				fireAddressChange();

				return;
			}
		}
	}

	/**
	 * 
	 */
	private void fireAddressChange()
	{
		for (IAddressChangeListener changeListener : this.addressChangeListeners)
		{
			changeListener.addressesChanged();
		}
	}

	/**
	 * @see de.freese.addressbook.services.IAddressService#getAddress(int)
	 */
	@Override
	public Address getAddress(final int id)
	{
		for (Address address : this.addresses)
		{
			if (address.getID() == id)
			{
				return address.copy();
			}
		}
		return null;
	}

	/**
	 * @see de.freese.addressbook.services.IAddressService#getAllAddresses()
	 */
	@Override
	public List<Address> getAllAddresses()
	{
		simulateSlowNetworkConnection();
		return this.addresses;
	}

	/**
	 * @see de.freese.addressbook.services.IAddressService#getAllCities()
	 */
	@Override
	public String[] getAllCities()
	{
		return RandomData.CITIES;
	}

	/**
	 * @see de.freese.addressbook.services.IAddressService#getAllCountries()
	 */
	@Override
	public List<Country> getAllCountries()
	{
		return Collections.unmodifiableList(this.countries);
	}

	/**
	 * @see de.freese.addressbook.services.IAddressService#removeAddressChangeListener(de.freese.addressbook.services.IAddressChangeListener)
	 */
	@Override
	public void removeAddressChangeListener(final IAddressChangeListener listener)
	{
		this.addressChangeListeners.remove(listener);
	}

	/**
	 * @see de.freese.addressbook.services.IAddressService#saveAddress(de.freese.addressbook.entities.Address)
	 */
	@Override
	public Address saveAddress(final Address changedOrNewAddress)
	{
		if (changedOrNewAddress.getID() < 0)
		{
			// create new address
			Address createdAddress =
					new Address(this.idSequence.incrementAndGet(), changedOrNewAddress.getName(),
							changedOrNewAddress.getStreet(), changedOrNewAddress.getZip(),
							changedOrNewAddress.getCity(), changedOrNewAddress.getCountry());
			this.addresses.add(createdAddress);
			fireAddressChange();

			return createdAddress.copy();
		}

		// change existing address
		for (Address address : this.addresses)
		{
			if (address.getID() == changedOrNewAddress.getID())
			{
				address.setName(changedOrNewAddress.getName());
				address.setStreet(changedOrNewAddress.getStreet());
				address.setZip(changedOrNewAddress.getZip());
				address.setCity(changedOrNewAddress.getCity());
				address.setCountry(changedOrNewAddress.getCountry());
				fireAddressChange();
				return getAddress(address.getID());
			}
		}

		throw new IllegalArgumentException("Address " + changedOrNewAddress.getID() + " not found!");

	}

	/**
	 * 
	 */
	private void simulateSlowNetworkConnection()
	{
		try
		{
			Thread.sleep(new RandomData().someNumber(500, 5000));
		}
		catch (InterruptedException ex)
		{
			// Ignore
		}
	}
}
