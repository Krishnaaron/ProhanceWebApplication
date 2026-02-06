package com.prohanceapplication.customreports

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.Select

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.prohancewebapplication.webtable.Table




public class CustomReports {

	Table objTabe =new Table();
	@Keyword
	def selectReportDuration(String reportDuration, String reportDurationElement) {

    TestObject durationObj = findTestObject(
        "Object Repository/Organization Settings/Work Time/Custom Reports/Common/Select_View by_Main"
    )

    WebElement element = WebUI.findWebElement(durationObj)

    Select selectViewBy = new Select(element)
	
	KeywordUtil.markWarning("Selected Value : " +selectViewBy.getFirstSelectedOption())
	
	if(reportDurationElement.equalsIgnoreCase("D") && reportDuration.equalsIgnoreCase("D"))
	{
		selectViewBy.selectByValue(reportDuration)
	}	
	else if(reportDurationElement.equalsIgnoreCase("W"))
	{
		if(reportDuration.equalsIgnoreCase("D") || reportDuration.equalsIgnoreCase("W"))
		{
			selectViewBy.selectByValue(reportDuration)
		}	
	}	
	else if(reportDurationElement.equalsIgnoreCase("M"))
	{
			if(reportDuration.equalsIgnoreCase("D") || reportDuration.equalsIgnoreCase("M"))
			{
				selectViewBy.selectByValue(reportDuration)
			}
	}
	else if(reportDurationElement.equalsIgnoreCase("R"))
	{
			if(reportDuration.equalsIgnoreCase("D") || reportDuration.equalsIgnoreCase("M") || reportDuration.equalsIgnoreCase("W") || reportDuration.equalsIgnoreCase("R"))
			{
					selectViewBy.selectByValue(reportDuration)
			}
	}
	else
	{
		selectViewBy.selectByValue("D")
	}
			
}

@Keyword
def selectUserType(String userType)
{
	TestObject durationObj = findTestObject(
		"Object Repository/Organization Settings/Work Time/Custom Reports/Common/Select_UserType_Main"
	)

	WebElement element = WebUI.findWebElement(durationObj)

	Select selectUserType = new Select(element)
	if(userType.equalsIgnoreCase("All Users"))
	{
		selectUserType.selectByIndex(0)
	}	
	else if(userType.equalsIgnoreCase("AGENT"))
	{
		selectUserType.selectByIndex(1);
	}
	else if(userType.equalsIgnoreCase("AGENTLESS"))
	{
		selectUserType.selectByIndex(2);
	}
	else
	{
		selectUserType.selectByIndex(0);
	}	
}

@Keyword
def selectGroupType(String showByGroupVal)
{
	TestObject durationObj = findTestObject(
		"Object Repository/Organization Settings/Work Time/Custom Reports/Common/Select_Group_Main"
	)

	WebElement element = WebUI.findWebElement(durationObj)

	Select selectUserType = new Select(element)
	if(showByGroupVal.equalsIgnoreCase("acrossAllGroups"))
	{
		selectUserType.selectByIndex(0)
	}
	else if(showByGroupVal.equalsIgnoreCase("byEachGroup"))
	{
		selectUserType.selectByIndex(1);
	}
    else
	{
		selectUserType.selectByIndex(0);
	}
}


@Keyword
def selectShiftType(String showByShiftValue)
{
	TestObject durationObj = findTestObject(
		"Object Repository/Organization Settings/Work Time/Custom Reports/Common/Select_Shift_Main"
	)

	WebElement element = WebUI.findWebElement(durationObj)

	Select selectUserType = new Select(element)
	if(showByShiftValue.equalsIgnoreCase("acrossAllShift"))
	{
		selectUserType.selectByIndex(0)
	}
	else if(showByShiftValue.equalsIgnoreCase("byEachShift"))
	{
		selectUserType.selectByIndex(1);
	}
	else
	{
		selectUserType.selectByIndex(0);
	}
}


@Keyword
def selectTimeZoneType(String showByTimezoneValue)
{
	TestObject durationObj = findTestObject(
		"Object Repository/Organization Settings/Work Time/Custom Reports/Common/Select_Time Zone"
	)

	WebElement element = WebUI.findWebElement(durationObj)

	Select selectUserType = new Select(element)
	if(showByTimezoneValue.equalsIgnoreCase("byLoggedInUser"))
	{
		selectUserType.selectByIndex(0)
	}
	else if(showByTimezoneValue.equalsIgnoreCase("bySpecificUser"))
	{
		selectUserType.selectByIndex(1);
	}
	else
	{
		selectUserType.selectByIndex(0);
	}
}

@Keyword
def selectDayType(String dayType,String dayTypesE,weekTypesE)
{
	TestObject durationObj = findTestObject(
		"Object Repository/Organization Settings/Work Time/Custom Reports/Common/Select_Day Type_Main"
	)

	WebElement element = WebUI.findWebElement(durationObj)

	Select selectUserType = new Select(element)
	if(dayType.equalsIgnoreCase("dayType"))
	{
		selectUserType.selectByIndex(0);
	}
	else if(dayType.equalsIgnoreCase("dayOfWeek"))
	{
		selectUserType.selectByIndex(1);
	}
	else
	{
		selectUserType.selectByIndex(0);
	}
	
	WebUI.click(findTestObject("Object Repository/Organization Settings/Work Time/Custom Reports/Common/day_Type_Click"))
	selectDayTypes(dayType,dayTypesE,weekTypesE);
	WebUI.click(findTestObject("Object Repository/Organization Settings/Work Time/Custom Reports/Common/day_Type_Click"))
	//List<WebElement> rows =objTabe.webTableDataReader(findTestObject("Object Repository/Organization Settings/Work Time/Custom Reports/Common/day_Type_Table"), findTestObject("Object Repository/Organization Settings/Work Time/Custom Reports/Common/day_Type_TableRow"))
	//objTabe.getWebTableData(rows);
	
}


def selectDayTypes(String Type, String dayTypesE , String weekTypesE)
{

 String dayTypes =null;
 String xPath=null;
if(Type.equalsIgnoreCase("dayType"))
{
	
	xPath ="//table [@ id='dayTypeTable']//td//input[@type='checkbox']";
	dayTypes =dayTypesE;
}
else
{
	xPath ="//table [@ id='dayOfWeekTable']//td//input[@ type ='checkbox']";
	dayTypes = selectDayWeeks(weekTypesE)
	
}	
		
String[] datTypesA = dayTypes.split(",");
def driver = DriverFactory.getWebDriver();
List<WebElement> checkboxes = driver.findElements(
    By.xpath(xPath)
)
 
for (WebElement cb : checkboxes) {
 
    String actualValue = cb.getAttribute("value")?.trim()
 
    boolean match = datTypesA.any {
        it.equalsIgnoreCase(actualValue)
    }
 
    if (match) {
        if (!cb.isSelected()) {
            cb.click()
        }
    } else {
        if (cb.isSelected()) {
            cb.click()
        }
    }

}
}


def String selectDayWeeks(String dayTypes) {

String[] datTypesA = dayTypes.split(",")
String dayTypesArr = ""

for (int i = 0; i < datTypesA.length; i++) {

    if (datTypesA[i].equalsIgnoreCase("Mon")) {
        dayTypesArr += "2,"
    } else if (datTypesA[i].equalsIgnoreCase("Tue")) {
        dayTypesArr += "3,"
    } else if (datTypesA[i].equalsIgnoreCase("Wed")) {
        dayTypesArr += "4,"
    } else if (datTypesA[i].equalsIgnoreCase("Thu")) {
        dayTypesArr += "5," 
    } else if (datTypesA[i].equalsIgnoreCase("Fri")) {
        dayTypesArr += "6,"
    } else if (datTypesA[i].equalsIgnoreCase("Sat")) {
        dayTypesArr += "7,"
    } else if (datTypesA[i].equalsIgnoreCase("Sun")) {
        dayTypesArr += "1,"
    }
}
dayTypesArr = dayTypesArr.substring(0, dayTypesArr.length() - 1)
return dayTypesArr
}


@Keyword
def selectFilterType(String filterType,String filterTypeCondition ,String obj1 ,String obj2)
{
	TestObject filterTypeObj = findTestObject(obj1)

	WebElement element = WebUI.findWebElement(filterTypeObj)

	Select selectUserType = new Select(element)
	if(filterType.equalsIgnoreCase("Ignore"))
	{
		selectUserType.selectByIndex(0)
	}
	else if(filterType.equalsIgnoreCase("Consider"))
	{
		selectUserType.selectByIndex(1);
	}
	else
	{
		selectUserType.selectByIndex(0);
	}
	
	
	selectFilterTypeCondition(filterTypeCondition, filterType, obj2);
	}

	
	@Keyword
	def selectFilterTypeCondition(String filterTypeCondition, String filterType, String obj)
	{
		TestObject filterTypeConditionObj = findTestObject(obj)
	
		WebElement element = WebUI.findWebElement(filterTypeConditionObj)
	
		Select selectUserType = new Select(element)
		if(filterTypeCondition.equalsIgnoreCase("greaterThan") && filterType.equalsIgnoreCase("Ignore"))
		{
			selectUserType.selectByIndex(0)
		}
		else if(filterTypeCondition.equalsIgnoreCase("lessThan") && filterType.equalsIgnoreCase("Ignore"))
		{
			selectUserType.selectByIndex(1);
		}
		else if(filterType.equalsIgnoreCase("Consider"))
			{
				selectUserType.selectByIndex(2);
			}
		else
		{
			selectUserType.selectByIndex(0);
		}
		
		}
		
		
		@Keyword
		def setFilterValue(String filterType,String v1 , String v2, String objText1,  String objText2)
		{
			WebUI.setText(findTestObject(objText1), v1)
		    if(filterType.equalsIgnoreCase("Consider"))
		    {
				WebUI.setText(findTestObject(objText2), v2)
			}	
		}
		@Keyword
		def selectBusinessImpactCategories()
		{
		   String imgBicObj ="Object Repository/Organization Settings/Work Time/Custom Reports/New Folder -2/Img_Efficiency_Bic";
		   String tableBicObj ="Object Repository/Organization Settings/Work Time/Custom Reports/New Folder -2/Table_Efficiency_Bic";
			if(WebUI.verifyElementVisible(findTestObject(imgBicObj)))
			{
				WebUI.click(findTestObject(imgBicObj))
			}
				
		 String dayTypes ="3,4";
		 String xPath=null;
		
			
			xPath ="//div [@ id ='EfficiencyDialogDiv']//table//td//input [@ type ='checkbox']";
		
			if(dayTypes.contains("4"))
			{
				dayTypes =dayTypes.replace("4", "true");
			}	
		String[] datTypesA = dayTypes.split(",");
		def driver = DriverFactory.getWebDriver();
		List<WebElement> checkboxes = driver.findElements(
			By.xpath(xPath)
		)
		 
		for (WebElement cb : checkboxes) {
		 
			String actualValue = cb.getAttribute("value")?.trim()
		    System.out.println(actualValue);
			
			boolean match = datTypesA.any {
				it.equalsIgnoreCase(actualValue)
			}
		 
			if (match) {
				if (!cb.isSelected()) {
					cb.click()
				}
			} else {
				if (cb.isSelected()) {
					cb.click()
				}
			}
		
		}
		WebUI.click(findTestObject(imgBicObj))
	}
}
