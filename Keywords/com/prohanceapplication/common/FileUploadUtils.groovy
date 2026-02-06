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
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.util.KeywordUtil
import internal.GlobalVariable
import org.openqa.selenium.WebElement
 
class FileUploadUtils {
 
    /* ================= BASIC INPUT TYPE FILE ================= */
 
    @Keyword
    def uploadUsingInput(TestObject fileInputTO, String filePath, boolean stopOnFail = true) {
 
        if (!new File(filePath).exists()) {
            KeywordUtil.markFailedAndStop("File not found : ${filePath}")
        }
 
        if (WebUI.waitForElementPresent(fileInputTO, GlobalVariable.Delay, FailureHandling.OPTIONAL)) {
            WebUI.uploadFile(fileInputTO, filePath)
            KeywordUtil.logInfo("File uploaded : ${filePath}")
        } else {
            handleFailure("File input not present", stopOnFail)
        }
    }
 
    /* ================= JS UPLOAD (HIDDEN INPUT) ================= */
 
    @Keyword
    def uploadUsingJS(TestObject fileInputTO, String filePath) {
 
        WebElement el = WebUI.findWebElement(fileInputTO, GlobalVariable.Delay)
 
        WebUI.executeJavaScript(
            "arguments[0].style.display='block';",
            [el]
        )
 
        WebUI.uploadFile(fileInputTO, filePath)
        KeywordUtil.logInfo("File uploaded using JS")
    }
 
    /* ================= CLICK + AUTO UPLOAD ================= */
    // When clicking upload opens OS dialog
 
    @Keyword
    def uploadUsingAutoIT(String uploadBtnExePath) {
 
        if (!new File(uploadBtnExePath).exists()) {
            KeywordUtil.markFailedAndStop("AutoIT exe not found")
        }
 
        WebUI.delay(1)
        Runtime.getRuntime().exec(uploadBtnExePath)
        KeywordUtil.logInfo("AutoIT upload executed")
    }
 
    /* ================= MULTIPLE FILE UPLOAD ================= */
 
    @Keyword
    def uploadMultipleFiles(TestObject fileInputTO, List<String> filePaths) {
 
        String combinedPaths = filePaths.join('\n')
        WebUI.uploadFile(fileInputTO, combinedPaths)
        KeywordUtil.logInfo("Multiple files uploaded")
    }
 
    /* ================= VERIFY FILE NAME ================= */
 
    @Keyword
    def verifyUploadedFileName(TestObject uploadedFileLabel, String expectedName) {
 
        String actual = WebUI.getText(uploadedFileLabel)
        if (!actual.contains(expectedName)) {
            KeywordUtil.markFailed(
                "Uploaded file mismatch | Expected: ${expectedName} | Actual: ${actual}"
            )
        }
    }
 
    /* ================= COMMON FAILURE HANDLER ================= */
 
    private def handleFailure(String msg, boolean stop) {
        if (stop) {
            KeywordUtil.markFailedAndStop(msg)
        } else {
            KeywordUtil.markWarning(msg)
        }
    }
}