package com.prohanceapplication.common

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

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
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import internal.GlobalVariable
import org.openqa.selenium.WebElement
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.io.FileHandler
import java.io.File
 
class ScreenshotUtils {
 
    /* ================= TAKE FULL PAGE SCREENSHOT ================= */
    @Keyword
    def takeScreenshot(String name) {
        String path = "Screenshots/${name}_${System.currentTimeMillis()}.png"
        WebUI.takeScreenshot(path)
        KeywordUtil.logInfo("Screenshot saved : " + path)
    }
 
    /* ================= SCREENSHOT ON FAILURE ================= */
    @Keyword
    def captureOnFailure(String stepName) {
        String path = "Screenshots/FAIL_${stepName}_${System.currentTimeMillis()}.png"
        WebUI.takeScreenshot(path)
        KeywordUtil.markFailed("Failure captured : " + path)
    }
 
    /* ================= ELEMENT LEVEL SCREENSHOT ================= */
    @Keyword
    def takeElementScreenshot(TestObject to, String name) {
        WebElement el = WebUI.findWebElement(to, GlobalVariable.Delay)
        File src = el.getScreenshotAs(OutputType.FILE)
        String path = "Screenshots/${name}_${System.currentTimeMillis()}.png"
        FileHandler.copy(src, new File(path))
        KeywordUtil.logInfo("Element screenshot saved : " + path)
    }
 
    /* ================= SCREENSHOT IF ELEMENT NOT VISIBLE ================= */
    @Keyword
    def screenshotIfNotVisible(TestObject to, String name) {
        if (!WebUI.verifyElementVisible(to, 2, FailureHandling.OPTIONAL)) {
            takeScreenshot("NOT_VISIBLE_" + name)
            KeywordUtil.logWarning("Element not visible : " + name)
        }
    }
 
    /* ================= SAFE CLICK WITH SCREENSHOT ON FAIL ================= */
    @Keyword
    def safeClickWithScreenshot(TestObject to, String name) {
        try {
            WebUI.waitForElementClickable(to, GlobalVariable.Delay)
            WebUI.click(to)
        } catch (Exception e) {
            takeScreenshot("CLICK_FAIL_" + name)
            throw e
        }
    }
 
    /* ================= BEFORE & AFTER ACTION SCREENSHOT ================= */
    @Keyword
    def beforeAfterScreenshot(String stepName) {
        takeScreenshot(stepName + "_BEFORE")
        WebUI.delay(0.5)
        takeScreenshot(stepName + "_AFTER")
    }
 
    /* ================= CAPTURE REPORT VIEW TOP & BOTTOM ================= */
    @Keyword
    def captureReportView(String reportName) {
        WebUI.executeJavaScript("window.scrollTo(0,0)", null)
        takeScreenshot(reportName + "_TOP")
        WebUI.executeJavaScript("window.scrollTo(0, document.body.scrollHeight)", null)
        takeScreenshot(reportName + "_BOTTOM")
    }
}