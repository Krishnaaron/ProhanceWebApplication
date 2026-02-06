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
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable
 
class AlertUtils {
 
    /* ================= CHECK ALERT ================= */
 
    @Keyword
    boolean isAlertPresent(int timeout = 3) {
        try {
            return WebUI.waitForAlert(timeout, FailureHandling.OPTIONAL)
        } catch (Exception e) {
            return false
        }
    }
 
    /* ================= ACCEPT ALERT ================= */
 
    @Keyword
    def acceptAlert(boolean stopOnFail = false) {
 
        if (isAlertPresent()) {
            WebUI.acceptAlert()
            KeywordUtil.logInfo("Alert accepted")
        } else {
            handleAlertFailure("Accept", stopOnFail)
        }
    }
 
    /* ================= DISMISS ALERT ================= */
 
    @Keyword
    def dismissAlert(boolean stopOnFail = false) {
 
        if (isAlertPresent()) {
            WebUI.dismissAlert()
            KeywordUtil.logInfo("Alert dismissed")
        } else {
            handleAlertFailure("Dismiss", stopOnFail)
        }
    }
 
    /* ================= GET ALERT TEXT ================= */
 
    @Keyword
    String getAlertText(boolean stopOnFail = false) {
 
        if (isAlertPresent()) {
            String text = WebUI.getAlertText()
            KeywordUtil.logInfo("Alert text : ${text}")
            return text
        } else {
            handleAlertFailure("Get text", stopOnFail)
            return null
        }
    }
 
    /* ================= VERIFY ALERT TEXT ================= */
 
    @Keyword
    def verifyAlertText(String expectedText, boolean stopOnFail = true) {
 
        if (isAlertPresent()) {
            String actual = WebUI.getAlertText()
            if (!actual.equals(expectedText)) {
                KeywordUtil.markFailed(
                    "Alert text mismatch | Expected: ${expectedText} | Actual: ${actual}"
                )
            }
        } else {
            handleAlertFailure("Verify text", stopOnFail)
        }
    }
 
    /* ================= SEND TEXT TO ALERT ================= */
    // For prompt alerts
 
    @Keyword
    def sendTextToAlert(String text, boolean stopOnFail = true) {
 
        if (isAlertPresent()) {
            WebUI.setAlertText(text)
            WebUI.acceptAlert()
            KeywordUtil.logInfo("Text sent to alert : ${text}")
        } else {
            handleAlertFailure("Send text", stopOnFail)
        }
    }
 
    /* ================= AUTO HANDLE ALERT ================= */
    // Accept if present, ignore if not
 
    @Keyword
    def acceptIfPresent() {
        if (isAlertPresent()) {
            WebUI.acceptAlert()
            KeywordUtil.logInfo("Alert auto-accepted")
        }
    }
 
    /* ================= COMMON FAILURE HANDLER ================= */
 
    private def handleAlertFailure(String action, boolean stop) {
 
        String msg = "Alert not present for action : ${action}"
        if (stop) {
            KeywordUtil.markFailedAndStop(msg)
        } else {
            KeywordUtil.markWarning(msg)
        }
    }
}