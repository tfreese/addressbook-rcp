package de.freese.addressbook.services.internal;

import java.util.Collection;
import java.util.Random;

/**
 * RandomData generates random values. This is helpful for generating test data for prototypes and test cases.
 * 
 * @author Thomas Freese
 */
public class RandomData
{
	/**
	 * 
	 */
	public static final String[] CITIES = new String[]
	{
			"Berlin",
			"Hamburg",
			"München",
			"Köln",
			"Frankfurt am Main",
			"Stuttgart",
			"Dortmund",
			"Essen",
			"Düsseldorf",
			"Bremen",
			"Hannover",
			"Leipzig",
			"Dresden",
			"Nürnberg",
			"Duisburg",
			"Bochum",
			"Wuppertal",
			"Bielefeld",
			"Bonn",
			"Mannheim",
			"Karlsruhe",
			"Wiesbaden",
			"Münster",
			"Gelsenkirchen",
			"Augsburg",
			"Mönchengladbach",
			"Aachen",
			"Braunschweig",
			"Chemnitz",
			"Kiel",
			"Krefeld",
			"Halle (Saale)",
			"Magdeburg",
			"Freiburg im Breisgau",
			"Oberhausen",
			"Lübeck",
			"Erfurt",
			"Rostock",
			"Mainz",
			"Kassel",
			"Hagen",
			"Hamm",
			"Saarbrücken",
			"Mülheim an der Ruhr",
			"Herne",
			"Ludwigshafen am Rhein",
			"Osnabrück",
			"Solingen",
			"Leverkusen",
			"Oldenburg",
			"Neuss",
			"Potsdam",
			"Heidelberg",
			"Paderborn",
			"Darmstadt",
			"Würzburg",
			"Regensburg",
			"Ingolstadt",
			"Heilbronn",
			"Göttingen",
			"Ulm",
			"Recklinghausen",
			"Wolfsburg",
			"Pforzheim",
			"Bottrop",
			"Offenbach am Main",
			"Bremerhaven",
			"Fürth",
			"Remscheid",
			"Reutlingen",
			"Moers",
			"Koblenz",
			"Bergisch Gladbach",
			"Salzgitter",
			"Siegen",
			"Erlangen",
			"Trier",
			"Hildesheim",
			"Cottbus",
			"Jena",
			"Gera"
	};

	/**
	 * 
	 */
	public static final String[] COUNTRIES = new String[]
	{
			"Deutschland", "Österreich", "Schweiz", "Polen", "Tschechische Republik", "Frankreich"
	};

	/**
	 * 
	 */
	public static final String[] GIVEN_NAMES = new String[]
	{
			"Alexander",
			"Andreas",
			"Angela",
			"Angelika",
			"Barbara",
			"Bernd",
			"Birgit",
			"Brigitte",
			"Bärbel",
			"Carl",
			"Christa",
			"Christiane",
			"Christine",
			"Dagmar",
			"Detlef",
			"Dieter",
			"Elisabeth",
			"Elke",
			"Frank",
			"Gabriele",
			"Gerd",
			"Gerhard",
			"Gisela",
			"Gudrun",
			"Günter",
			"Hans",
			"Heike",
			"Heinz",
			"Helga",
			"Helmut",
			"Holger",
			"Horst",
			"Ingrid",
			"Jens",
			"Joachim",
			"Jutta",
			"Jörg",
			"Jürgen",
			"Karin",
			"Klaus",
			"Kornelia",
			"Manfred",
			"Maria",
			"Marion",
			"Marlies",
			"Matthias",
			"Michael",
			"Monika",
			"Norbert",
			"Peter",
			"Petra",
			"Rainer",
			"Ralf",
			"Regina",
			"Reinhard",
			"Renate",
			"Rolf",
			"Rüdiger",
			"Sabine",
			"Sigrid",
			"Silvia",
			"Susanne",
			"Thomas",
			"Ulrich",
			"Ulrike",
			"Ursula",
			"Ute",
			"Uwe",
			"Werner",
			"Wolfgang"
	};

	/**
	 * 
	 */
	public static final String[] LAST_NAMES = new String[]
	{
			"Müller",
			"Schmidt",
			"Schneider",
			"Fischer",
			"Weber",
			"Meyer",
			"Wagner",
			"Becker",
			"Schulz",
			"Hoffmann",
			"Schäfer",
			"Koch",
			"Bauer",
			"Richter",
			"Klein",
			"Wolf",
			"Schröder",
			"Neumann",
			"Schwarz",
			"Zimmermann",
			"Braun",
			"Krüger",
			"Hofmann",
			"Hartmann",
			"Lange",
			"Schmitt",
			"Werner",
			"Schmitz",
			"Krause",
			"Meier",
			"Lehmann",
			"Schmid",
			"Schulze",
			"Maier",
			"Köhler",
			"Herrmann",
			"König",
			"Walter",
			"Mayer",
			"Huber",
			"Kaiser",
			"Fuchs",
			"Peters",
			"Lang",
			"Scholz",
			"Möller",
			"Weiß",
			"Jung",
			"Hahn",
			"Schubert",
			"Vogel",
			"Friedrich",
			"Keller",
			"Günther",
			"Frank",
			"Berger",
			"Winkler",
			"Roth",
			"Beck",
			"Lorenz",
			"Baumann",
			"Franke",
			"Albrecht",
			"Schuster",
			"Simon",
			"Ludwig",
			"Böhm",
			"Winter",
			"Kraus",
			"Martin",
			"Schumacher",
			"Krämer",
			"Vogt",
			"Stein",
			"Jäger",
			"Otto",
			"Sommer",
			"Groß",
			"Seidel",
			"Heinrich",
			"Brandt",
			"Haas",
			"Schreiber",
			"Graf",
			"Schulte",
			"Dietrich",
			"Ziegler",
			"Kuhn",
			"Kühn",
			"Pohl",
			"Engel",
			"Horn",
			"Busch",
			"Bergmann",
			"Thomas",
			"Voigt",
			"Sauer",
			"Arnold",
			"Wolff",
			"Pfeiffer"
	};

	/**
	 * 
	 */
	public static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";

	/**
	 * 
	 */
	public static final String MIXED_CHARS = RandomData.UPPERCASE_CHARS + RandomData.LOWERCASE_CHARS;

	/**
	 * 
	 */
	public static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 
	 */
	private int seed = 0;

	/**
	 * Erstellt ein neues {@link RandomData} Object.
	 */
	public RandomData()
	{
		super();

		this.seed = (int) System.currentTimeMillis();
		newData();
	}

	/**
	 * Erstellt ein neues {@link RandomData} Object.
	 * 
	 * @param seed int
	 */
	public RandomData(final int seed)
	{
		super();

		this.seed = seed;
	}

	/**
	 * 
	 */
	public void newData()
	{
		this.seed = new Random(this.seed).nextInt(Integer.MAX_VALUE - 1);
	}

	/**
	 * @return boolean
	 */
	public boolean someBoolean()
	{
		return someNumber(0, 2) == 0;
	}

	/**
	 * @param count int
	 * @param characters String
	 * @return String
	 */
	public String someCharacters(final int count, final String characters)
	{
		StringBuilder string = new StringBuilder();
		Random random = new Random(this.seed);
		for (int i = 1; i <= count; i++)
		{
			string.append(characters.charAt(random.nextInt(characters.length())));
		}
		return string.toString();
	}

	/**
	 * @return String
	 */
	public String someCity()
	{
		return someElement(CITIES);
	}

	/**
	 * @param count int
	 * @return String
	 */
	public String someDigits(final int count)
	{
		StringBuilder string = new StringBuilder();
		Random random = new Random(this.seed);
		for (int i = 1; i <= count; i++)
		{
			string.append(String.valueOf(random.nextInt(10)));
		}
		return string.toString();
	}

	/**
	 * @param <E> Konkreter Typ
	 * @param elements {@link Collection}
	 * @return Ojject
	 */
	@SuppressWarnings("unchecked")
	public <E> E someElement(final Collection<E> elements)
	{
		return (E) someElement(elements.toArray());
	}

	/**
	 * @param <E> Konkreter Typ
	 * @param elements Object[]
	 * @return Object
	 */
	public <E> E someElement(final E[] elements)
	{
		return elements[someNumber(0, elements.length)];
	}

	/**
	 * @return String
	 */
	public String someEmail()
	{
		return someGivenName().toLowerCase() + "." + someLastName().toLowerCase() + "@localhost";
	}

	/**
	 * @return String
	 */
	public String someGivenName()
	{
		return someElement(GIVEN_NAMES);
	}

	/**
	 * @return String
	 */
	public String someLastName()
	{
		return someElement(LAST_NAMES);
	}

	/**
	 * @param fromInclusive double
	 * @param toExclusive double
	 * @return double
	 */
	public double someNumber(final double fromInclusive, final double toExclusive)
	{
		return fromInclusive + (this.seed % (toExclusive - fromInclusive));
	}

	/**
	 * @param fromInclusive int
	 * @param toExclusive int
	 * @return int
	 */
	public int someNumber(final int fromInclusive, final int toExclusive)
	{
		return fromInclusive + (this.seed % (toExclusive - fromInclusive));
	}

	/**
	 * @param fromInclusive long
	 * @param toExclusive long
	 * @return long
	 */
	public long someNumber(final long fromInclusive, final long toExclusive)
	{
		return fromInclusive + (this.seed % (toExclusive - fromInclusive));
	}

	/**
	 * @return String
	 */
	public String somePersonName()
	{
		return someGivenName() + " " + someLastName();
	}

	/**
	 * @return String
	 */
	public String somePhoneNumber()
	{
		return somePhoneNumber("/");
	}

	/**
	 * @param separator String
	 * @return String
	 */
	public String somePhoneNumber(final String separator)
	{
		return "0" + someDigits(3) + separator + someDigits(7);
	}

	/**
	 * @return String
	 */
	public String someStreet()
	{
		return someElement(LAST_NAMES) + "straße " + someNumber(1, 100);
	}

	/**
	 * @param <T> Konkreter Typ
	 * @param enumType {@link Class}
	 * @return {@link Enum}
	 */
	public <T extends Enum<T>> T someValue(final Class<T> enumType)
	{
		return someElement(enumType.getEnumConstants());
	}

	/**
	 * @return String
	 */
	public String someZipCode()
	{
		return someDigits(5);
	}
}