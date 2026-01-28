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
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.firefox.FirefoxOptions
import org.openqa.selenium.firefox.FirefoxProfile
import org.openqa.selenium.edge.EdgeOptions

import java.nio.file.Files
import java.nio.file.Paths

import internal.GlobalVariable

public class DowloadLocation {
      @Keyword
	Map createBrowserWithDownload(String browserName, String subFolder) {

		String downloadFolder = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + subFolder


		if (!Files.exists(Paths.get(downloadFolder))) {
			Files.createDirectories(Paths.get(downloadFolder))
		}

		WebDriver driver
		browserName = browserName.toLowerCase()

		switch (browserName) {
			case "chrome":
				ChromeOptions chromeOptions = new ChromeOptions()
				Map<String, Object> prefs = new HashMap<>()
				prefs.put("download.default_directory", downloadFolder)
				prefs.put("download.prompt_for_download", false)
				prefs.put("download.directory_upgrade", true)
				chromeOptions.setExperimentalOption("prefs", prefs)
				driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions)
				break

			case "firefox":
				FirefoxProfile profile = new FirefoxProfile()
				profile.setPreference("browser.download.folderList", 2)
				profile.setPreference("browser.download.dir", downloadFolder)
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"application/pdf,application/zip,text/csv,application/octet-stream")
				profile.setPreference("pdfjs.disabled", true)
				FirefoxOptions firefoxOptions = new FirefoxOptions()
				firefoxOptions.setProfile(profile)
				driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions)
				break

			case "edge":
				EdgeOptions edgeOptions = new EdgeOptions()
				Map<String, Object> edgePrefs = new HashMap<>()
				edgePrefs.put("download.default_directory", downloadFolder)
				edgePrefs.put("download.prompt_for_download", false)
				edgePrefs.put("download.directory_upgrade", true)
				edgeOptions.setExperimentalOption("prefs", edgePrefs)
				driver = new org.openqa.selenium.edge.EdgeDriver(edgeOptions)
				break

			default:
				throw new IllegalArgumentException("Browser " + browserName + " not supported.")
		}

		DriverFactory.changeWebDriver(driver)

        System.out.println(driver +" " + downloadFolder);
		return [driver: driver, downloadFolder: downloadFolder]
	}
}
