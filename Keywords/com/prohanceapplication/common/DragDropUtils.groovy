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
import org.openqa.selenium.interactions.Actions
 
class DragDropUtils {
 
    /* ================= NORMAL DRAG & DROP ================= */
    @Keyword
    def dragAndDrop(TestObject sourceTO, TestObject targetTO) {
 
        WebElement source = WebUI.findWebElement(sourceTO, GlobalVariable.Delay)
        WebElement target = WebUI.findWebElement(targetTO, GlobalVariable.Delay)
 
        Actions action = new Actions(WebUI.getWebDriver())
        action.dragAndDrop(source, target).build().perform()
 
        KeywordUtil.logInfo("Drag and Drop executed")
    }
 
    /* ================= JS DRAG & DROP (BEST FOR HTML5) ================= */
    @Keyword
    def dragAndDropUsingJS(TestObject sourceTO, TestObject targetTO) {
 
        WebElement source = WebUI.findWebElement(sourceTO, GlobalVariable.Delay)
        WebElement target = WebUI.findWebElement(targetTO, GlobalVariable.Delay)
 
        String js = '''
            function createEvent(type) {
              var event = document.createEvent("CustomEvent");
              event.initCustomEvent(type, true, true, null);
              event.dataTransfer = {
                data: {},
                setData: function(key, value) {
                  this.data[key] = value;
                },
                getData: function(key) {
                  return this.data[key];
                }
              };
              return event;
            }
 
            var dragStartEvent = createEvent('dragstart');
            arguments[0].dispatchEvent(dragStartEvent);
 
            var dropEvent = createEvent('drop');
            arguments[1].dispatchEvent(dropEvent);
 
            var dragEndEvent = createEvent('dragend');
            arguments[0].dispatchEvent(dragEndEvent);
        '''
 
        WebUI.executeJavaScript(js, [source, target])
        KeywordUtil.logInfo("Drag and Drop using JS executed")
    }
 
    /* ================= DRAG BY OFFSET ================= */
    @Keyword
    def dragByOffset(TestObject sourceTO, int xOffset, int yOffset) {
 
        WebElement source = WebUI.findWebElement(sourceTO, GlobalVariable.Delay)
 
        Actions action = new Actions(WebUI.getWebDriver())
        action.clickAndHold(source)
              .moveByOffset(xOffset, yOffset)
              .release()
              .build()
              .perform()
 
        KeywordUtil.logInfo("Drag by offset executed")
    }
}