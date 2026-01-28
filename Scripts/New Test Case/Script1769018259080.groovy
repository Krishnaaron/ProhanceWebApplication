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

WebUI.navigateToUrl('https://enhance.prohance.io/phxauth/login?reqJson=g+X%2FozY2ipGypjdR%2F1GwZM+v28Zm8v3tzg3MTDU0x2FriRFlYIRbKFVjT3ORELKjwKVUrk4QmNSyFfdGgXK7pA%3D%3D')

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/label_Username'))

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/div_Username'))

WebUI.setText(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/input_Username_tlogin'), 
    'gopaladmin')

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/div_Password'))

WebUI.setEncryptedText(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/input_Password_tpwdsaved'), 
    'tsL9urF6O/APwsPS+50KRA==')

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/div_Captcha Text_col-lg-12'))

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/div_Would you like to terminate the other s_d1016e'))

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/button_OK'))

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'))

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_ACTIVITY DASHBOARD'))

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/span_Top Applications For All Groups_drilld_ef368b'))

WebUI.switchToWindowTitle('ProHance')

WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/img__ph-dash-vertical-align-middle ph-dash-_e9ae0f'))

