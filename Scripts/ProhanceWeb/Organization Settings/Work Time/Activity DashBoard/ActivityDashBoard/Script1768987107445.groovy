import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.WebElement

import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

/* =========================================================
   COMMON REUSABLE METHODS
   ========================================================= */

def waitAndClick(TestObject to) {
    WebUI.waitForElementClickable(to, GlobalVariable.Delay)
    WebUI.click(to)
}

def safeScrollAndClick(TestObject to) {
    WebElement el = WebUI.findWebElement(to, GlobalVariable.Delay)
    WebUI.executeJavaScript('arguments[0].scrollIntoView(true);', [el])
    WebUI.delay(0.3)
    WebUI.executeJavaScript('arguments[0].click();', [el])
}

def navigateToActivityDashboard() {

    waitAndClick(findTestObject(
        'Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'))

    waitAndClick(findTestObject(
        'Object Repository/Organization Settings/New Folder/Page_ProHance/a_ACTIVITY DASHBOARD'))

    waitAndClick(findTestObject(
        'Object Repository/Organization Settings/New Folder/Page_ProHance/First_Container'))

    WebUI.waitForPageLoad(GlobalVariable.Delay)

    waitAndClick(findTestObject(
        'Object Repository/Organization Settings/New Folder/Page_ProHance/span_Top Applications For All Groups_drilld_ef368b'))

    WebUI.switchToWindowIndex(1)
    WebUI.waitForPageLoad(GlobalVariable.Delay)
}

def handleExportAndValidation() {

    CustomKeywords.'com.prohancewebapplication.file.FileSizeFinder.exisitingFileDelete'()

    String exportResolved = exportType ?: false
    String browserName = DriverFactory.getExecutedBrowser()
    KeywordUtil.logInfo("Browser Name : ${browserName}")

    String exportXpath = CustomKeywords.'com.prohanceebapplication.common.Export.export'(exportResolved)

    if (exportXpath) {
        TestObject exportBtn = new TestObject()
        exportBtn.addProperty('xpath', ConditionType.EQUALS, exportXpath)
        safeScrollAndClick(exportBtn)
    }

    if (isDataValion.toBoolean()) {

        def rows = CustomKeywords.'com.prohancewebapplication.webtable.Table.webTableDataReader'(
            findTestObject(
                'Object Repository/Organization Settings/Work Time/Activity DashBoard/TopApplicationWebTable/FirstDrillDown/table_Id'),
            findTestObject(
                'Object Repository/Organization Settings/Work Time/Activity DashBoard/TopApplicationWebTable/FirstDrillDown/table_tr')
        )

        if (rows) {
            CustomKeywords.'com.prohanceapplication.webtableexcelvalidation.DataValidator.validateWebTableWithDownloadedExcel'(rows)
        }

        CustomKeywords.'com.prohancewebapplication.file.FileSizeFinder.fileSize'()
    }
}

def performTableSorting(TestObject headerTO, TestObject rowTO) {
    List<WebElement> header =null;
    int headerCount = WebUI.findWebElements(headerTO, GlobalVariable.Delay).size()
    KeywordUtil.logInfo("Total headers : ${headerCount}")

    for (int i = 0; i < headerCount; i++) {

        // index-based header (stale safe)
        TestObject indexedHeader = new TestObject()
        indexedHeader.addProperty(
            "xpath",
            ConditionType.EQUALS,
            "(" + headerTO.findPropertyValue("xpath") + ")[" + (i + 1) + "]"
        )

        String headerText = WebUI.findWebElements(headerTO, GlobalVariable.Delay)
                                   .get(i).getText()
		header = WebUI.findWebElements(
    findTestObject('Object Repository/Organization Settings/Work Time/Activity DashBoard/TopApplicationWebTable/FirstDrillDown/TopApps_HeaderLinks'),
    GlobalVariable.Delay
)
        KeywordUtil.logInfo("Sorting ASC : ${headerText}")

        // ASC
        safeScrollAndClick(headers)
        WebUI.delay(1)

        if (isTSValidation.toBoolean()) {
            CustomKeywords.'com.prohancewebapplication.webtable.Table.verifySorting'(
                i, 'ASC', rowTO)
        }

        // DESC
        KeywordUtil.logInfo("Sorting DESC : ${headerText}")

        safeScrollAndClick(indexedHeader)
        WebUI.delay(1)

        if (isTSValidation.toBoolean()) {
            CustomKeywords.'com.prohancewebapplication.webtable.Table.verifySorting'(
                i, 'DESC', rowTO)
        }
    }
}

def cleanupWindow() {
    WebUI.switchToDefaultContent()
    WebUI.closeWindowIndex(1)
    WebUI.switchToWindowIndex(0)
}

/* =========================================================
   MAIN TEST EXECUTION
   ========================================================= */

if (!WebUI.waitForElementPresent(
        findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'),
        GlobalVariable.Delay)) {
    KeywordUtil.markFailedAndStop('WORK TIME link not found')
}

// Navigation
navigateToActivityDashboard()

// Export + Validation
if (isFileDownload.toBoolean()) {
    handleExportAndValidation()
}

// Table Sorting
if (isTableSorting.toBoolean()) {

    TestObject headerTO = findTestObject(
        'Object Repository/Organization Settings/Work Time/Activity DashBoard/TopApplicationWebTable/FirstDrillDown/TopApps_HeaderLinks')

    TestObject rowTO = findTestObject(
        'Object Repository/Organization Settings/Work Time/Activity DashBoard/TopApplicationWebTable/FirstDrillDown/table_tr')

    performTableSorting(headerTO, rowTO)
}

// Cleanup
cleanupWindow()
