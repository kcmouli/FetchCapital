import org.junit.Before;
import org.junit.Test;

import fetchCapital.DisplayCapitalCity;
import org.junit.Assert;

public class DisplayCapitalCityTest {
	DisplayCapitalCity displayCapital;

	@Before
	public void Setup() throws Exception	{
		displayCapital = new DisplayCapitalCity();
	}

	@Test
	public void capitalCityUsingNameTest() throws Exception{
		String capital = displayCapital.getCapital("wash");
		Assert.assertEquals("Washington, D.C.", capital);
	}

	@Test
	public void capitalCityUsingCodeTest() throws Exception{
		String capital = displayCapital.getCapital("91");
		Assert.assertEquals("New Delhi", capital);
	}

	@Test
	public void capitalCityUsingAlphaNumericTest() throws Exception{
		String capital = displayCapital.getCapital("abc91");
		Assert.assertEquals("Please enter either name or code.", capital);
	}

	@Test
	public void capitalCityNoMatchTest() throws Exception{
		String capital = displayCapital.getCapital("abc");
		Assert.assertEquals("Didn't find any capital matching with given input.Try again.", capital);
	}

}
