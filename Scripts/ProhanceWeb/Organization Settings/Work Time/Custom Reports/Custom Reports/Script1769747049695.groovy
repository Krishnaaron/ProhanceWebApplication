import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.support.ui.Select
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

if (WebUI.waitForElementPresent(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'), 
    GlobalVariable.Delay)) {
    WebUI.waitForElementClickable(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'), 
        GlobalVariable.Delay)
     WebUI.click(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'))
	 
	 WebUI.waitForElementClickable(findTestObject('Object Repository/Organization Settings/New Folder/Page_ProHance/a_WORK TIME'),
		 GlobalVariable.Delay)
	WebDriver driver = DriverFactory.getWebDriver()
List<WebElement> elements = driver.findElements(By.xpath("//a[@data-menulink='EfficiencyDetailsFilter']"))

if (!elements.isEmpty()) {
    elements.get(0).click()  // click the first one
} else {
    println "Element not found!"
}
WebUI.switchToWindowIndex(1);
WebElement iddd = driver.findElement(By.xpath("//span[@ id ='show-content-menu-main']"));
iddd.click();

TestObject selectObject = new TestObject()
selectObject.addProperty("xpath", ConditionType.EQUALS, "//select[@id='customReportViewByFilter']")
TestObject viewByDropdown = new TestObject("ViewByDropdown")
viewByDropdown.addProperty(
	"xpath",
	ConditionType.EQUALS,
	"//select[@id='customReportViewByFilter']"
)

// Wait for dropdown
WebUI.waitForElementPresent(viewByDropdown, 20)

// Initialize Select
WebElement dropdownElement = WebUI.findWebElement(viewByDropdown)
Select select = new Select(dropdownElement)

// Get selected value
String selectedValue = select.getFirstSelectedOption().getText().trim()
println("Selected Value: " + selectedValue)

// Get all dropdown values
List<String> dropdownValues = []
select.getOptions().each {
	dropdownValues.add(it.getText().trim())
}

for (int i = 0; i < select.getOptions().size(); i++) {

    // open dropdown (visual)
    dropdownElement.click()
    WebUI.delay(1)

    // select option
    select.selectByIndex(i)
    WebUI.delay(1)
}
println("All Dropdown Values: " + dropdownValues)

//System.out.println(lis);
}