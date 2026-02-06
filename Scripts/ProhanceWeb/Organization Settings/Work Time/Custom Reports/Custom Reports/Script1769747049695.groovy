import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject


import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

// --------------------
// Click WORK TIME menu
// --------------------

String obj1 ="Object Repository/Organization Settings/Work Time/Custom Reports/Filter By/FilterTypeHours_Select";
String obj2 ="Object Repository/Organization Settings/Work Time/Custom Reports/Filter By/FilterTypeCondition_Select";
String obj3 ="Object Repository/Organization Settings/Work Time/Custom Reports/Filter By/LoggedHoursFrom_Text";
String obj4 ="Object Repository/Organization Settings/Work Time/Custom Reports/Filter By/LoggedHoursTo_Text";
String obj5 ="Object Repository/Organization Settings/Work Time/Custom Reports/Filter By/FilterTypeCD1_Select";
String obj6 ="Object Repository/Organization Settings/Work Time/Custom Reports/Filter By/FilterTypeCD2_Select";
String obj7 ="Object Repository/Organization Settings/Work Time/Custom Reports/Filter By/Person DaysFrom_Text";
String obj8 ="Object Repository/Organization Settings/Work Time/Custom Reports/Filter By/Person DaysTo_Text";
if (WebUI.waitForElementPresent(
        findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'),
        GlobalVariable.Delay)) {

    WebUI.waitForElementClickable(
        findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'),
        GlobalVariable.Delay)

    WebUI.click(
        findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'))

    WebDriver driver = DriverFactory.getWebDriver()

    // --------------------
    // Click Efficiency Details Filter
    // --------------------
    List<WebElement> efficiencyLinks =
            driver.findElements(By.xpath("//a[@data-menulink='EfficiencyDetailsFilter']"))

    if (!efficiencyLinks.isEmpty()) {
        efficiencyLinks.get(0).click()
    } else {
        println("EfficiencyDetailsFilter link not found")
        return
    }
  
    WebUI.switchToWindowIndex(1)

    
    WebElement filterMenu =
            driver.findElement(By.xpath("//span[@id='show-content-menu-main']"))
    filterMenu.click()

   
    TestObject viewByDropdown = new TestObject('ViewByDropdown')
    viewByDropdown.addProperty(
            "xpath",
            ConditionType.EQUALS,
            "//select[@id='customReportViewByFilter']"
    )

    WebUI.waitForElementPresent(viewByDropdown, 20)

    WebElement dropdownElement = WebUI.findWebElement(viewByDropdown)
    Select select = new Select(dropdownElement)

    String selectedValue =
            select.getFirstSelectedOption().getText().trim()
    println("Selected View By Value: " + selectedValue)

    
    List<String> dropdownValues = []
	
    select.getOptions().each {
        dropdownValues.add(it.getText().trim())
    }
	
	 String reportDurationElementValue =
	 WebUI.findWebElement(findTestObject("Object Repository/Organization Settings/Work Time/Custom Reports/Custom Reports Hidden Element/Report Duration")).getAttribute("Value")
	 if(reportDuration != null && reportDuration !="" && reportDurationElementValue !=null && reportDurationElementValue !="")
	 { 
	    CustomKeywords.'com.prohanceapplication.customreports.CustomReports.selectReportDuration'(reportDuration,reportDurationElementValue)
	 }
	 println(UserType)
	 
	 if(UserType !=null && UserType !="")
	 {	 
	 CustomKeywords.'com.prohanceapplication.customreports.CustomReports.selectUserType'(UserType)
	 }
	
	TestObject checkbox = findTestObject(
    'Object Repository/Organization Settings/Work Time/Custom Reports/Common/Chk_ Display Overall Data'
)
TestObject isDDCheckedCheckbox = findTestObject(
	'Object Repository/Organization Settings/Work Time/Custom Reports/Common/Chk_Display data'
)
TestObject isDisplayOnlyActiveUsersDataWeb = findTestObject(
	'Object Repository/Organization Settings/Work Time/Custom Reports/Common/Chk_Display Only Active UsersData'
)
TestObject isDisplayWithinShiftOutsideShifDataWeb = findTestObject(
	'Object Repository/Organization Settings/Work Time/Custom Reports/Common/Chk_Display Within Shift Outside Shift'
)
TestObject isDisplayTimeZoneDataWeb = findTestObject(
	'Object Repository/Organization Settings/Work Time/Custom Reports/Common/Chk_Display time specific'
)
TestObject isDnableFHWeb = findTestObject(
	'Object Repository/Organization Settings/Work Time/Custom Reports/Filter By/EnableFilterCH_Chk'
)
TestObject isDnablePDWeb = findTestObject(
	'Object Repository/Organization Settings/Work Time/Custom Reports/Filter By/EnableFCDays_Chk'
)
WebUI.waitForElementPresent(checkbox, 0, FailureHandling.OPTIONAL)
WebUI.waitForElementPresent(isDisplayOnlyActiveUsersDataWeb, 0, FailureHandling.OPTIONAL)
boolean isChecked = WebUI.verifyElementChecked(checkbox, 1, FailureHandling.OPTIONAL)
boolean isDDChecked =WebUI.verifyElementChecked(isDDCheckedCheckbox, 1, FailureHandling.OPTIONAL);
boolean isDOAUData =WebUI.verifyElementChecked(isDisplayOnlyActiveUsersDataWeb, 1, FailureHandling.OPTIONAL);
boolean isDSOS =WebUI.verifyElementChecked(isDisplayWithinShiftOutsideShifDataWeb, 1, FailureHandling.OPTIONAL);
boolean isDTZW =WebUI.verifyElementChecked(isDisplayTimeZoneDataWeb, 1, FailureHandling.OPTIONAL);
boolean isDnableFH =WebUI.verifyElementChecked(isDnableFHWeb, 1, FailureHandling.OPTIONAL);
boolean isDnablePD =WebUI.verifyElementChecked(isDnablePDWeb, 1, FailureHandling.OPTIONAL);
if (dodCheck.toBoolean() && !isChecked) {
    WebUI.check(checkbox)  
} else if (!dodCheck.toBoolean() && isChecked) {
    WebUI.uncheck(checkbox) 
}

if (isDisplayOnlyActiveUsersData.toBoolean() && !isDOAUData) {
	WebUI.check(isDisplayOnlyActiveUsersDataWeb)
} else if (!isDisplayOnlyActiveUsersData.toBoolean() && isDOAUData) {
	WebUI.uncheck(isDisplayOnlyActiveUsersDataWeb)
}

if (isDWShOutsideShift.toBoolean() && !isDSOS) {
    WebUI.check(isDisplayWithinShiftOutsideShifDataWeb)
} else if (!isDWShOutsideShift.toBoolean() && isDSOS) {
    WebUI.uncheck(isDisplayWithinShiftOutsideShifDataWeb)
}



if(isDSOS ||isDWShOutsideShift.toBoolean() && shiftType !=null && shiftType !="")
{
   CustomKeywords.'com.prohanceapplication.customreports.CustomReports.selectShiftType'(shiftType)
}	

CustomKeywords.'com.prohanceapplication.customreports.CustomReports.selectDayType'(dayType,dayTypesE,weekTypesE)
System.out.println(isEnableFilter.toBoolean());
if (isEnableFilter.toBoolean() && !isDnableFH) {
	WebUI.check(isDnableFHWeb)
} else if (!isEnableFilter.toBoolean() && isDnableFH) {
	WebUI.uncheck(isDnableFHWeb)
}
if(isEnableFilter.toBoolean())
{
	CustomKeywords.'com.prohanceapplication.customreports.CustomReports.selectFilterType'(filterTypeMin, lhType, obj1, obj2)
	CustomKeywords.'com.prohanceapplication.customreports.CustomReports.setFilterValue'(filterTypeMin, loggedhoursMin, considerBetweenTo, obj3, obj4)
}

String updatedReportDurationElementValue =CustomKeywords.'com.prohanceapplication.common.ElementActions.getSelectedDropdownValue'(findTestObject("Object Repository/Organization Settings/Work Time/Custom Reports/Common/Select_View by_Main"))

if (isEnablePDFilter.toBoolean() && !isDnablePD && !updatedReportDurationElementValue.equalsIgnoreCase("Day")) {
	WebUI.check(isDnablePDWeb)
} else if (!isEnablePDFilter.toBoolean() && isDnablePD) {
	WebUI.uncheck(isDnablePDWeb)
}

if(isEnablePDFilter.toBoolean()  && !updatedReportDurationElementValue.equalsIgnoreCase("Day"))
{
	CustomKeywords.'com.prohanceapplication.customreports.CustomReports.selectFilterType'(filterTypeDays, pdType, obj5, obj6)
	CustomKeywords.'com.prohanceapplication.customreports.CustomReports.setFilterValue'(filterTypeDays,  loggedhoursDays, daysConsiderBetweenTo, obj7, obj8)
}	

		List<String> immediateChildFolders = CustomKeywords.'com.prohanceapplication.common.ObjectUtils.getImmediateChildFolders'("Organization Settings/Work Time/Custom Reports/Check_Box")

for (String filePath : immediateChildFolders) {
	
				customizeReportView(filePath ,reportDuration)
	
  //String filePath ="Organization Settings/Work Time/Custom Reports/Check_Box/Login LogOut"
System.out.println(filePath);
customizeReportView(filePath ,reportDuration)
    for (int i = 0; i < select.getOptions().size(); i++) {
        select.selectByIndex(i)
        WebUI.delay(1)
		String value = select.getFirstSelectedOption().getAttribute("value");
		System.out.println(value +"               -------------------------->");
		if(value.equalsIgnoreCase("TeamMemberId"))
		{
			if((filePath.endsWith("Login LogOut") || filePath.endsWith("Work Template") ) && "D".equalsIgnoreCase(reportDuration))
			{
				userCustomizeReportView(filePath);
				//customizeReportView(filePath ,reportDuration)
			}	
			if (isDisplayData.toBoolean() && !isDDChecked) {
				WebUI.check(isDDCheckedCheckbox)
				WebUI.delay(2)
				if(showByGroupVal !=null && showByGroupVal !="")
				{
					CustomKeywords.'com.prohanceapplication.customreports.CustomReports.selectGroupType'(showByGroupVal)
				}
			} else if (!isDisplayData.toBoolean() && isDDChecked) {
				WebUI.uncheck(isDDCheckedCheckbox)
			}
			
			
			if(isDisplayTimeZone.toBoolean() && !isDTZW && "D".equalsIgnoreCase(reportDuration))
			{
				WebUI.check(isDisplayTimeZoneDataWeb)
				
			}	
			else if (!isDisplayTimeZone.toBoolean() && isDTZW) {
				WebUI.uncheck(isDisplayTimeZoneDataWeb)
			}
			System.out.println(reportDuration);
			if (((isDisplayTimeZone.toBoolean() || isDTZW) && timeZoneType !=null) && 'D'.equalsIgnoreCase(reportDuration)) {
    CustomKeywords.'com.prohanceapplication.customreports.CustomReports.selectTimeZoneType'(timeZoneType)
}
		}
		
		
		WebUI.waitForElementClickable(findTestObject('Object Repository/Organization Settings/Work Time/Custom Reports/Common/UPDATE FIELDS'), 5)
		WebUI.click(findTestObject('Object Repository/Organization Settings/Work Time/Custom Reports/Common/UPDATE FIELDS'))
	
		WebUI.waitForElementClickable(findTestObject('Object Repository/Organization Settings/Work Time/Custom Reports/Common/FETCH'), 5)
		WebUI.click(findTestObject('Object Repository/Organization Settings/Work Time/Custom Reports/Common/FETCH'))
	
		WebUI.waitForPageLoad(10)
		handleExportAndValidation();
		//sortingValidation();
	    
		WebUI.waitForElementClickable(findTestObject('Object Repository/Organization Settings/Work Time/Custom Reports/Common/CUSTOMIZE REPORT FIELDS'), 5)
		safeScrollAndClick(findTestObject('Object Repository/Organization Settings/Work Time/Custom Reports/Common/CUSTOMIZE REPORT FIELDS'))
		
		
    }
	//handleExportAndValidation();
	customizeReportViewReset(filePath)
    }
	cleanupWindow();
    
} else {
    println("WORK TIME menu not found")
}









void customizeReportViewReset(String filePath) 
{
Map<String, TestObject> getObjectsAsMap = CustomKeywords.'com.prohanceapplication.common.ObjectUtils.getObjectsAsMap'(filePath)
System.out.println(getObjectsAsMap);

// Group objects by common prefix (before _Main/_Avg/_Tot)
def grouped = getObjectsAsMap.groupBy { entry ->
    entry.key.replaceAll(/_(Main|Avg|Tot)$/, '')
}



grouped.each { groupKey, entries ->

    def mainObj = entries.find { it.key.endsWith("_Main") }?.value
   

    if (mainObj) {
        // Check if already checked
        boolean isChecked = WebUI.verifyElementChecked(mainObj, 1, FailureHandling.OPTIONAL)

        if (isChecked) {  // Only click if NOT checked
            WebUI.scrollToElement(mainObj, 5)
            WebUI.waitForElementClickable(mainObj, 10)
            WebUI.click(mainObj)
        }
    }

   
}

	
}
void customizeReportView(String filePath ,String reportDuration)
{
	boolean isDay = "D".equalsIgnoreCase(reportDuration);
	
	Map<String, TestObject> getObjectsAsMap = CustomKeywords.'com.prohanceapplication.common.ObjectUtils.getObjectsAsMap'(filePath)
	def grouped = getObjectsAsMap.groupBy { entry ->
		entry.key.replaceAll(/_(Main|Avg|Tot)$/, '')
	}
	
grouped.each { groupKey, entries ->

    def mainObj = entries.find { it.key.endsWith("_Main") }?.value
    def avgObj  = entries.find { it.key.endsWith("_Avg") }?.value
    def totObj  = entries.find { it.key.endsWith("_Tot") }?.value
	//def userObj  = entries.find { it.key.endsWith("_User") }?.value

        safeClickCheckbox(mainObj)
        safeClickCheckbox(avgObj)
        // Enable if Tot should be clicked
         safeClickCheckbox(totObj)
}
}


void userCustomizeReportView(String filePath) {
    Map<String, TestObject> getObjectsAsMap = CustomKeywords.'com.prohanceapplication.common.ObjectUtils.getObjectsAsMap'(
        filePath)

    def grouped = getObjectsAsMap.groupBy({ def entry ->
            entry.key.replaceAll('_(User)$', '')
        })

    grouped.each({ def groupKey, def entries ->
            def userObj = entries.find({ 
                    it.key.endsWith('_User')
                })?.value

            if (userObj) {
                safeClickCheckbox(userObj)
            }
        })
}
/*
void safeClickCheckbox(TestObject obj) {
	if (obj == null) return

	try {
		WebUI.scrollToElement(obj, 5)

		
		boolean isChecked = WebUI.verifyElementChecked(obj, 1, FailureHandling.OPTIONAL)

		if (!isChecked) {
			WebUI.waitForElementClickable(obj, 10)
			WebUI.click(obj)
		}
	} catch (Exception e) {
		println("⚠ Skipped element (already checked or not clickable): ${obj.getObjectId()}")
	}
}*/
def safeClickCheckbox(TestObject obj) {
	if (obj == null) {
		KeywordUtil.logInfo("⚠ Checkbox object is null, skipping.")
		return
	}

	try {
		// Scroll to checkbox
		WebUI.scrollToElement(obj, 5)

		// Check if it is already checked using 'checked' attribute
		boolean isChecked = WebUI.getAttribute(obj, "checked") == "true"

		if (!isChecked) {
			// Wait until clickable and click
			WebUI.waitForElementClickable(obj, 10)
			WebUI.click(obj)
			KeywordUtil.logInfo("✔ Checkbox clicked: ${obj.getObjectId()}")
		} else {
			KeywordUtil.logInfo("✔ Checkbox already checked: ${obj.getObjectId()}")
		}
	} catch (Exception e) {
		KeywordUtil.markError("⚠ Could not click checkbox: ${obj.getObjectId()}. Reason: ${e.message}")
	}
}
def cleanupWindow() {
	WebUI.switchToDefaultContent()
	WebUI.closeWindowIndex(1)
	WebUI.switchToWindowIndex(0)
}


def handleExportAndValidation() {
	
		CustomKeywords.'com.prohancewebapplication.file.FileSizeFinder.exisitingFileDelete'()
//	
//		String exportResolved = exportType ?: false
//		String browserName = DriverFactory.getExecutedBrowser()
//		KeywordUtil.logInfo("Browser Name : ${browserName}")
	
		String exportXpath = CustomKeywords.'com.prohanceapplication.common.Export.export'("Excel")
	
		if (exportXpath) {
			TestObject exportBtn = new TestObject()
			exportBtn.addProperty('xpath', ConditionType.EQUALS, exportXpath)
			safeScrollAndClick(exportBtn)
		}
	
		if (true) {
	
			def rows = CustomKeywords.'com.prohancewebapplication.webtable.Table.webTableDataReader'(
				findTestObject(
					'Object Repository/Organization Settings/Work Time/Custom Reports/Table/Table_Id'),
				findTestObject(
					'Object Repository/Organization Settings/Work Time/Custom Reports/Table/Table_Row')
			)
	
			if (rows) {
				CustomKeywords.'com.prohanceapplication.webtableexcelvalidation.DataValidator.validateWebTableWithDownloadedExcel'(rows)
			}
	
			CustomKeywords.'com.prohancewebapplication.file.FileSizeFinder.fileSize'()
		}
	}
	def sortingValidation() {
    String tableHeaderObj ="Object Repository/Organization Settings/Work Time/Custom Reports/Table/Table_Header"
    String tableRow="Object Repository/Organization Settings/Work Time/Custom Reports/Table/Table_Row"

    List<WebElement> headers = WebUI.findWebElements(findTestObject(tableHeaderObj), GlobalVariable.Delay)
    int headerCount = headers.size()
    KeywordUtil.logInfo("Total headers: " + headerCount)

    for (int i = 0; i < headerCount; i++) {

        
        headers = WebUI.findWebElements(findTestObject(tableHeaderObj), GlobalVariable.Delay)
        WebElement header = headers.get(i)
        String headerText = header.getText()
        KeywordUtil.logInfo("Processing header: " + headerText)

      
        WebElement link = header.findElement(By.tagName("a"))

      
        WebUI.executeJavaScript("arguments[0].scrollIntoView({block:'center'});", Arrays.asList(link))
        WebUI.delay(0.5)

       
        WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(link))
        WebUI.delay(1)
        CustomKeywords.'com.prohancewebapplication.webtable.Table.verifySorting'(i, "ASC", findTestObject(tableRow))
        KeywordUtil.logInfo("Verified ASC for header: " + headerText)

       
        headers = WebUI.findWebElements(findTestObject(tableHeaderObj), GlobalVariable.Delay)
        header = headers.get(i)
        link = header.findElement(By.tagName("a"))

      
        WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(link))
        WebUI.delay(1)
        CustomKeywords.'com.prohancewebapplication.webtable.Table.verifySorting'(i, "DESC", findTestObject(tableRow))
        KeywordUtil.logInfo("Verified DESC for header: " + headerText)
    }
}

	def safeScrollAndClick(TestObject to) {
		WebElement el = WebUI.findWebElement(to, GlobalVariable.Delay)
		WebUI.executeJavaScript('arguments[0].scrollIntoView(true);', [el])
		WebUI.delay(0.3)
		WebUI.executeJavaScript('arguments[0].click();', [el])
	}