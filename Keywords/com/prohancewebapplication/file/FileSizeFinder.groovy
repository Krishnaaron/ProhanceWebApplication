package com.prohancewebapplication.file

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

public class FileSizeFinder {
	
	
	public long fileSize()
	{
		String downloadPath = "C:/Downloads"
		File dir = new File(downloadPath)
		 
		// wait download complete
		while (dir.listFiles().any { it.name.endsWith(".crdownload") }) {
			Thread.sleep(1000)
		}
		 
		// get latest file
		File file = dir.listFiles()
				.findAll { it.isFile() && !it.name.endsWith(".crdownload") }
				.sort { -it.lastModified() }
				.first()
		 
		assert file.length() > 0 : "File size is 0 KB"
		 
		println "Downloaded file: ${file.name}"
		println "Size: ${file.length()} bytes"
	}

}
