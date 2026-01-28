import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

String explort = null

List<WebElement> rows = null

if (WebUI.verifyElementPresent(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'), 
    GlobalVariable.Delay)) {
    WebUI.waitForElementClickable(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'), 
        GlobalVariable.Delay)

    WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'))

    WebUI.waitForElementClickable(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_ACTIVITY DASHBOARD'), 
        GlobalVariable.Delay)

    WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_ACTIVITY DASHBOARD'))
	
	WebUI.waitForElementClickable(     findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/First_Container'), GlobalVariable.Delay, FailureHandling.STOP_ON_FAILURE)
	WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/First_Container'));
	WebUI.waitForPageLoad(GlobalVariable.Delay, FailureHandling.STOP_ON_FAILURE);
    WebUI.waitForElementClickable(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/span_Top Applications For All Groups_drilld_ef368b'), 
        GlobalVariable.Delay)

    WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/span_Top Applications For All Groups_drilld_ef368b'))

    WebUI.switchToWindowIndex(1)

    WebUI.waitForPageLoad(GlobalVariable.Delay, FailureHandling.STOP_ON_FAILURE)

    rows = CustomKeywords.'com.prohancewebapplication.webtable.Table.webTableDataReader'(findTestObject('Object Repository/Organization Settings/Activity DashBoard/TopApplicationWebTable/FirstDrillDown/table_Id'), 
        findTestObject('Object Repository/Organization Settings/Activity DashBoard/TopApplicationWebTable/FirstDrillDown/table_tr'))

    if (rows != null) {
        CustomKeywords.'com.prohancewebapplication.webtable.Table.webTableDataWriter'(rows)
    }
    
    WebUI.executeJavaScript('window.scrollTo(0, document.body.scrollHeight);', null)

    CustomKeywords.'com.prohancewebapplication.file.FileSizeFinder.exisitingFileDelete'()

    explort = exportType != null ? exportType : false

    String browserName = DriverFactory.getExecutedBrowser()

    KeywordUtil.logInfo(('Browser Name :' + ' ') + browserName)

    //Map result = CustomKeywords.'com.prohancewebapplication.file.DowloadLocation.createBrowserWithDownload'(browserName, "TopApplication")
    String xPath = CustomKeywords.'com.prohanceebapplication.common.Export.export'(explort)

    System.out.println(xPath)

    if (xPath != null) {
        TestObject exportSpan = new TestObject()

        exportSpan.addProperty('xpath', ConditionType.EQUALS, xPath)

        WebUI.waitForElementPresent(exportSpan, GlobalVariable.Delay)

        def el = WebUI.findWebElement(exportSpan, GlobalVariable.Delay)

        WebUI.executeJavaScript('arguments[0].scrollIntoView(true);', Arrays.asList(el))

        WebUI.executeJavaScript('arguments[0].click();', Arrays.asList(el))
    }
    
    CustomKeywords.'com.prohancewebapplication.file.FileSizeFinder.fileSize'()

    WebUI.switchToDefaultContent()

    WebUI.closeWindowIndex(1)

    WebUI.switchToWindowIndex(0)
}