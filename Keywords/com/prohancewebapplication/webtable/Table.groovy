package com.prohancewebapplication.webtable

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.ConditionType
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
	public void webTableDataWriter(List<WebElement> rows) {
		for (int i = 0; i < rows.size(); i++) {
			List<WebElement> cols = rows.get(i).findElements(By.tagName('td'))


			if (cols.size() == 0) {
				cols = rows.get(i).findElements(By.tagName('th'))
			}

			String rowText = ''

			for (WebElement col : cols) {
				rowText += (col.getText() + ' | ')
			}

			KeywordUtil.logInfo((('Row ' + i) + ': ') + rowText)
		}
	}
}
