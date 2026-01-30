package com.prohancewebapplication.webtable

import org.openqa.selenium.*

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class Table {
	@Keyword
	public List<WebElement> webTableDataReader(TestObject table, TestObject rowsObj) {

		List<WebElement> rows = []

		if (table != null && rowsObj != null) {

			WebUI.waitForElementVisible(table, GlobalVariable.Delay)

			rows = WebUI.findWebElements(rowsObj, GlobalVariable.Delay)
		}

		return rows
	}

	@Keyword
	List<List<String>> getWebTableData(List<WebElement> rows) {

		List<List<String>> webData = []

		for (int i = 0; i < rows.size(); i++) {

			List<WebElement> cols = rows[i].findElements(By.tagName("td"))

			if (cols.size() == 0) {
				cols = rows[i].findElements(By.tagName("th"))
			}

			List<String> rowData = []

			for (WebElement col : cols) {
				if(col !=null || col !='') {
					rowData.add(col.getText().trim())
				}
			}

			webData.add(rowData)
			KeywordUtil.logInfo("Row ${i + 1}: ${rowData}")
		}
		System.out.println(webData);
		return webData
	}




	@Keyword
	public void  verifySorting(int colIndex, String order ,TestObject obj) {

		List<WebElement> rows = WebUI.findWebElements(obj, GlobalVariable.Delay)

		List<String> actual = []

		for (int i = 1; i < rows.size(); i++) {

			if (!rows.get(i).isDisplayed()) {
				continue
			}
			List<WebElement> cols = rows.get(i)
					.findElements(By.xpath(".//td[not(contains(@class,'hidden-lg'))]"));

			actual.add(cols.get(colIndex).getText().trim())
		}

		sortAndPrint( actual,  order,colIndex);
	}


	private void sortAndPrint(List actual, String order ,int colIndex) {


		List<String> expected = actual.sort { a, b ->
			boolean aNum = a.toString().isNumber()
			boolean bNum = b.toString().isNumber()

			int comparison = 0

			if (aNum && bNum) {
				comparison = a.toInteger() <=> b.toInteger()
			} else if (aNum) {
				comparison = -1
			} else if (bNum) {
				comparison = 1
			} else {
				comparison = a.toString().compareToIgnoreCase(b.toString())
			}

			return order.equalsIgnoreCase("ASC") ? comparison : -comparison
		}
		assert actual == expected :
		" ${order} sorting failed for column index ${colIndex}"

		KeywordUtil.markWarning("actual   : " + actual)
		KeywordUtil.markWarning("expected : " + expected)
	}
}


