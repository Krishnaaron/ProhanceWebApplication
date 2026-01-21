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

WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://enhance.prohance.io/prohance/')

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/div_Username'))

WebUI.setText(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/input_Username_tlogin'), 
    'gopaladmin')

WebUI.setText(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/input_Password_tpwdsaved'), 
    'gopaladmin@1')

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/input_Captcha Text_btn-login loginbtn'))

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/button_OK'))

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'))

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_ACTIVITY DASHBOARD'))

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/span_Top Applications For All Groups_drilld_ef368b'))

// Click to open second tab
WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/span_Top Applications For All Groups_drilld_ef368b'))

// Wait until new window opens
//WebUI.waitForWindowPresent(2, 10)

// Switch to second tab (index starts from 0)
WebUI.delay(2);
WebUI.switchToWindowIndex(1)
WebUI.maximizeWindow();

// Verify you are on second tab
WebUI.comment("Current tab title: " + WebUI.getWindowTitle())

// Scroll in second tab
WebUI.executeJavaScript('window.scrollTo(0, document.body.scrollHeight);', null)
WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/img__ph-dash-vertical-align-middle ph-dash-_e9ae0f'))

