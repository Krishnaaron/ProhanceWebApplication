import java.text.SimpleDateFormat as SimpleDateFormat
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.time.LocalDateTime as LocalDateTime
import java.time.format.DateTimeFormatter as DateTimeFormatter

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.waitForPageLoad(GlobalVariable.Delay, FailureHandling.CONTINUE_ON_FAILURE)

WebUI.navigateToUrl(GlobalVariable.URL)

//login page 
try {
    WebUI.setEncryptedText(findTestObject('Object Repository/Login/userName'), userName)

    WebUI.setEncryptedText(findTestObject('Object Repository/Login/Password'), password)

    WebUI.waitForElementClickable(findTestObject('Object Repository/Login/login_btn'), GlobalVariable.Delay)

    WebUI.click(findTestObject('Object Repository/Login/login_btn'))

    if (WebUI.verifyElementPresent(findTestObject('Object Repository/Login/session_Distroysession'), GlobalVariable.Delay,FailureHandling.OPTIONAL)) {
        WebUI.waitForElementClickable(findTestObject('Object Repository/Login/session_Distroysession'), GlobalVariable.Delay,FailureHandling.OPTIONAL)

        WebUI.click(findTestObject('Object Repository/Login/session_Distroysession'))
    }
    
    if (WebUI.verifyElementPresent(findTestObject('Object Repository/Common/Accept Cookies'), GlobalVariable.Delay,FailureHandling.OPTIONAL)) {
        WebUI.waitForElementClickable(findTestObject('Object Repository/Common/Accept Cookies'), GlobalVariable.Delay,FailureHandling.OPTIONAL)

        WebUI.click(findTestObject('Object Repository/Common/Accept Cookies'))
    }
}
catch (Exception e) {
    System.out.println(e.getMessage())

    //String timestamp = new Date().format('yyyyMMdd_HHmmss')
} 
// WebUI.takeScreenshot("D:\\KatalonScreenshots\\ldt_${timestamp}.png");
finally { 
    String timestamp = new Date().format('yyyyMMdd_HHmmss') //	WebUI.takeScreenshot("D:\\KatalonScreenshots\\ldt_${timestamp}.png");
}