package com.prohanceapplication.common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select
public class ElementActions {

		
		   /* ================= CORE CHECK ================= */
		
		   @Keyword
		   boolean isElementReady(TestObject to, int timeout = GlobalVariable.Delay) {
		
			   if (!WebUI.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL)) {
				   KeywordUtil.logInfo("Element NOT present : ${to.getObjectId()}")
				   return false
			   }
		
			   if (!WebUI.waitForElementVisible(to, timeout, FailureHandling.OPTIONAL)) {
				   KeywordUtil.logInfo("Element NOT visible : ${to.getObjectId()}")
				   return false
			   }
		
			   if (!WebUI.waitForElementClickable(to, timeout, FailureHandling.OPTIONAL)) {
				   KeywordUtil.logInfo("Element NOT clickable : ${to.getObjectId()}")
				   return false
			   }
		
			   return true
		   }
		
		   /* ================= SAFE CLICK ================= */
		
		   @Keyword
		   def click(TestObject to, boolean stopOnFail = true) {
		
			   if (isElementReady(to)) {
				   try {
					   WebUI.click(to)
				   } catch (Exception e) {
					   KeywordUtil.logInfo("Normal click failed, trying JS click")
					   jsClick(to)
				   }
			   } else {
				   handleFailure("Unable to click element", to, stopOnFail)
			   }
		   }
		
		   /* ================= SAFE SET TEXT ================= */
		
		   @Keyword
		   def setText(TestObject to, String text, boolean stopOnFail = true) {
		
			   if (isElementReady(to)) {
				   try {
					   WebUI.clearText(to)
					   WebUI.setText(to, text)
				   } catch (Exception e) {
					   jsSetValue(to, text)
				   }
			   } else {
				   handleFailure("Unable to set text", to, stopOnFail)
			   }
		   }
		
		   /* ================= KEYBOARD ================= */
		
		   @Keyword
		   def pressEnter(TestObject to) {
			   if (isElementReady(to)) {
				   WebUI.sendKeys(to, Keys.chord(Keys.ENTER))
			   }
		   }
		
		   /* ================= JS FALLBACK ================= */
		
		   @Keyword
		   def jsClick(TestObject to) {
			   WebElement el = WebUI.findWebElement(to, GlobalVariable.Delay)
			   WebUI.executeJavaScript("arguments[0].click();", [el])
		   }
		
		   @Keyword
		   def jsSetValue(TestObject to, String value) {
			   WebElement el = WebUI.findWebElement(to, GlobalVariable.Delay)
			   WebUI.executeJavaScript("arguments[0].value='${value}'", [el])
		   }
		
		   /* ================= RETRY ================= */
		
		   @Keyword
		   def retryClick(TestObject to, int retry = 3) {
		
			   for (int i = 1; i <= retry; i++) {
				   try {
					   if (isElementReady(to, 5)) {
						   WebUI.click(to)
						   return
					   }
				   } catch (Exception e) {
					   KeywordUtil.logInfo("Retry ${i} failed for ${to.getObjectId()}")
					   WebUI.delay(1)
				   }
			   }
			   KeywordUtil.markFailedAndStop("Click failed after ${retry} retries : ${to.getObjectId()}")
		   }
		
		   /* ================= STATE CHECKS ================= */
		
		   @Keyword
		   boolean isPresent(TestObject to) {
			   return WebUI.waitForElementPresent(to, 3, FailureHandling.OPTIONAL)
		   }
		
		   @Keyword
		   boolean isVisible(TestObject to) {
			   return WebUI.waitForElementVisible(to, 3, FailureHandling.OPTIONAL)
		   }
		
		   /* ================= VERIFY ================= */
		
		   @Keyword
		   def verifyText(TestObject to, String expected) {
		
			   if (!isElementReady(to)) {
				   KeywordUtil.markFailedAndStop("Element not ready for text verification")
			   }
		
			   String actual = WebUI.getText(to).trim()
			   if (!actual.equals(expected)) {
				   KeywordUtil.markFailed(
					   "Text mismatch | Expected: ${expected} | Actual: ${actual}"
				   )
			   }
		   }
		
		   /* ================= COMMON FAILURE HANDLER ================= */
		
		   private def handleFailure(String action, TestObject to, boolean stop) {
		
			   String msg = "${action} : ${to.getObjectId()}"
			   if (stop) {
				   KeywordUtil.markFailedAndStop(msg)
			   } else {
				   KeywordUtil.markWarning(msg)
			   }
		   }
		   @Keyword
		   def waitForPageReady() {
			   WebUI.waitForPageLoad(GlobalVariable.Delay)
			   WebUI.executeJavaScript(
				   "return document.readyState", null
			   )
		   }
			
		   @Keyword
		   def switchToNewTab() {
			   WebUI.switchToWindowIndex(WebUI.getWindowIndex() + 1)
		   }
			
		   @Keyword
		   def closeChildAndBack() {
			   WebUI.closeWindowIndex(1)
			   WebUI.switchToWindowIndex(0)
		   }
			
		   @Keyword
		   def switchToFrame(TestObject frameTO) {
			   WebUI.waitForElementPresent(frameTO, 10)
			   WebUI.switchToFrame(frameTO, 10)
		   }
			
		   @Keyword
		   def switchToDefault() {
			   WebUI.switchToDefaultContent()
		   }
			
		   @Keyword
		   String getSelectedDropdownValue(TestObject dropdownObj) {
			   WebUI.waitForElementPresent(dropdownObj, GlobalVariable.Delay)
	   
			   WebElement dropdownElement = WebUI.findWebElement(dropdownObj)
			   Select select = new Select(dropdownElement)
	   
			   return select.getFirstSelectedOption().getText().trim()
		   }
		   
}
