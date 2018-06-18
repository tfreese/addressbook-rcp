package de.freese.addressbook.entities;


/**
 * @author Thomas Freese
 */
public class Address
{
	// /**
	// *
	// */
	// private final PropertyChangeSupport propertyChangeSupport;

	/**
	 * 
	 */
	private final int id;

	/**
	 * 
	 */
	private String name = null;

	/**
	 * 
	 */
	private String street = null;

	/**
	 * 
	 */
	private String zip = null;

	/**
	 * 
	 */
	private String city = null;

	/**
	 * 
	 */
	private Country country = null;

	/**
	 * Erstellt ein neues {@link Address} Object.
	 * 
	 * @param id int
	 * @param name String
	 * @param street String
	 * @param zip String
	 * @param city String
	 * @param country {@link Country}
	 */
	public Address(final int id, final String name, final String street, final String zip,
			final String city, final Country country)
	{
		super();

		this.id = id;
		this.name = name;
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.country = country;

		// this.propertyChangeSupport = new PropertyChangeSupport(this);
	}

	// /**
	// * @param listener {@link PropertyChangeListener}
	// */
	// public void addPropertyChangeListener(final PropertyChangeListener listener)
	// {
	// this.propertyChangeSupport.addPropertyChangeListener(listener);
	// }

	/**
	 * @return {@link Address}
	 */
	public Address copy()
	{
		return new Address(this.id, this.name, this.street, this.zip, this.city, this.country);
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

		if (!(obj instanceof Address))
		{
			return false;
		}

		Address other = (Address) obj;

		if (this.id != other.id)
		{
			return false;
		}

		return true;
	}

	/**
	 * @return String
	 */
	public String getCity()
	{
		return this.city;
	}

	/**
	 * @return {@link Country}
	 */
	public Country getCountry()
	{
		return this.country;
	}

	/**
	 * @return int
	 */
	public int getID()
	{
		return this.id;
	}

	/**
	 * @return String
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * @return String
	 */
	public String getStreet()
	{
		return this.street;
	}

	/**
	 * @return String
	 */
	public String getZip()
	{
		return this.zip;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + this.id;

		return result;
	}

	// /**
	// * @param listener {@link PropertyChangeListener}
	// */
	// public void removePropertyChangeListener(final PropertyChangeListener listener)
	// {
	// this.propertyChangeSupport.removePropertyChangeListener(listener);
	// }

	/**
	 * @param city String
	 */
	public void setCity(final String city)
	{
		this.city = city;
	}

	/**
	 * @param country {@link Country}
	 */
	public void setCountry(final Country country)
	{
		this.country = country;
	}

	/**
	 * @param name String
	 */
	public void setName(final String name)
	{
		this.name = name;

		// this.propertyChangeSupport.firePropertyChange("name", this.name, this.name = name);
	}

	/**
	 * @param street String
	 */
	public void setStreet(final String street)
	{
		this.street = street;
	}

	/**
	 * @param zip String
	 */
	public void setZip(final String zip)
	{
		this.zip = zip;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Address [id=" + this.id + ", name=" + this.name + ", street=" + this.street
				+ ", zip=" + this.zip + ", city=" + this.city + ", country=" + this.country + "]";
	}
}