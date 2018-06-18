package de.freese.addressbook.entities;

/**
 * @author Thomas Freese
 */
public class Country
{
	/**
	 * 
	 */
	private final String name;

	/**
	 * Erstellt ein neues {@link Country} Object.
	 * 
	 * @param name String
	 */
	public Country(final String name)
	{
		super();

		this.name = name;
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

		if (!(obj instanceof Country))
		{
			return false;
		}

		Country other = (Country) obj;

		if (this.name == null)
		{
			if (other.name != null)
			{
				return false;
			}
		}
		else if (!this.name.equals(other.name))
		{
			return false;
		}

		return true;
	}

	/**
	 * @return String
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.name == null) ? 0 : this.name.hashCode());

		return result;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "Country[" + this.name + "]";
	}
}