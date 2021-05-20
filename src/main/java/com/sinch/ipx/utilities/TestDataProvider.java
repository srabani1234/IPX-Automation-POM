package com.sinch.ipx.utilities;

import org.testng.annotations.DataProvider;

import com.codoid.products.exception.FilloException;

public class TestDataProvider {

	@DataProvider
	public Object[][] getHomePageUrlTestData() throws FilloException {
		return DataUtil.getTestData("HomePage", "home_page_url_test");
	}
}
