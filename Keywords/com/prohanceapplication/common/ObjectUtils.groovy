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


import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.testobject.ObjectRepository as OR

 
class ObjectUtils {
 
    /**
     * Get TestObject list from Object Repository folder
     * @param folderPath (relative to Object Repository)
     */
    @Keyword
    List<TestObject> getObjects(String folderPath) {
 
        String dirPath = "${RunConfiguration.getProjectDir()}/Object Repository/${folderPath}"
        File dir = new File(dirPath)
 
        if (!dir?.directory) return []
 
        return dir.listFiles()
                  .findAll { it.name.endsWith('.rs') }
                  .collect {
                      OR.findTestObject("${folderPath}/${it.name[0..-4]}")
                  }
    }
	@Keyword
	Map<String, TestObject> getObjectsAsMap(String folderPath) {
		
			String dirPath = "${RunConfiguration.getProjectDir()}/Object Repository/${folderPath}"
			File dir = new File(dirPath)
		
			if (!dir?.directory) return [:]
		
			return dir.listFiles()
				.findAll { it.name.endsWith('.rs') }
				.collectEntries { file ->
					String objName = file.name[0..-4]
					[
						(objName): OR.findTestObject("${folderPath}/${objName}")
					]
				}
		}
		
		
		@Keyword
		public List<String> getImmediateChildFolders(String logicalPath) {
			List<String> result = []
	
			String projectDir = RunConfiguration.getProjectDir()
		
			String folderPath = projectDir + "/Object Repository/" + logicalPath.replace("/", File.separator)
			File rootFolder = new File(folderPath)
	
			if (!rootFolder.exists()) {
				println("Folder does not exist: " + rootFolder.absolutePath)
				return result
			}
	
			
			rootFolder.listFiles().findAll { it.isDirectory() }.each { File f ->
				String childLogicalPath = logicalPath + "/" + f.name
				result.add(childLogicalPath)
			}
	
			return result
		}
}