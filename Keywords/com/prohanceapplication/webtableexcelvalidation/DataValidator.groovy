package com.prohanceapplication.webtableexcelvalidation

import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI



public class DataValidator {

	@Keyword
	public void validateWebTableWithDownloadedExcel(List<WebElement> rows) {
		try {

			List<String> headers = []
			List<WebElement> headerCells = rows.get(0).findElements(By.tagName("th"))

			headerCells.each {
				headers.add(it.getText().trim())
			}


			List<List<String>> webTableData = []

			for (int r = 1; r < rows.size(); r++) {
				List<WebElement> tds = rows.get(r).findElements(By.tagName("td"))
				List<String> rowValues = []

				tds.each {
					rowValues.add(it.getText().trim().replaceAll('[+\\--]', ""))
				}
				webTableData.add(rowValues)
			}

			if (webTableData.isEmpty()) {
				KeywordUtil.markFailed("Web table has no data rows")
				return
			}


			//		File excelFileDow = waitForExcelDownload(
			//			"Top Applications Details Report", 10
			//		)
			//
			//		if (excelFileDow == null) {
			//			KeywordUtil.markFailed("Excel file download not completed within timeout")
			//			return
			//		}

			// ================= FIND DOWNLOADED EXCEL =================
			String downloadPath = System.getProperty("user.home") + File.separator + "Downloads"
			File downloadDir = new File(downloadPath)

			if (!downloadDir.exists()) {
				KeywordUtil.markFailed("Downloads folder not found")
				return
			}

			File excelFile = downloadDir.listFiles()
					.findAll { it.name.startsWith("Top Applications Details Report") }
					.sort { -it.lastModified() }
					.find { it.length() > 0 }

			if (excelFile == null) {
				KeywordUtil.markFailed("Excel file not found in Downloads")
				return
			}


			Workbook workbook
			FileInputStream fis = new FileInputStream(excelFile)
			DataFormatter formatter = new DataFormatter()

			if (excelFile.name.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(fis)
			} else if (excelFile.name.endsWith(".xls")) {
				workbook = new HSSFWorkbook(fis)
			} else {
				KeywordUtil.markFailed("Unsupported Excel format")
				return
			}

			Sheet sheet = workbook.getSheetAt(0)


			int headerRowIndex = -1

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i)
				if (row == null) continue

					boolean match = true
				for (int c = 0; c < headers.size(); c++) {
					String excelHeader = formatter.formatCellValue(row.getCell(c)).trim()
					if (!excelHeader.equalsIgnoreCase(headers.get(c))) {
						match = false
						break
					}
				}

				if (match) {
					headerRowIndex = i
					break
				}
			}

			if (headerRowIndex == -1) {
				KeywordUtil.markFailed("Excel header row does not match web table headers")
				return
			}


			for (int r = 0; r < webTableData.size(); r++) {

				Row excelRow = sheet.getRow(headerRowIndex + 1 + r)
				if (excelRow == null) {
					KeywordUtil.markFailed("Missing Excel row for web row ${r + 1}")
					continue
				}

				for (int c = 0; c < webTableData[r].size(); c++) {

					String excelValue = formatter.formatCellValue(
							excelRow.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
							).trim()

					String webValue = webTableData[r][c]

					if (!excelValue.equals(webValue)) {
						KeywordUtil.markFailed(
								"Mismatch | Row: ${r + 1}, Column: ${c + 1} | Web: '${webValue}' | Excel: '${excelValue}'"
								)
					}
					else{
						KeywordUtil.markPassed(
								"Mismatch | Row: ${r + 1}, Column: ${c + 1} | Web: '${webValue}' | Excel: '${excelValue}'"
								)
					}
				}
			}

			workbook.close()
			fis.close()

			KeywordUtil.markPassed("Web table data perfectly matches Excel file")
		}catch(Exception e) {
			KeywordUtil.markError("data validation problem  : " + e.getMessage())
		}
	}



	private File waitForExcelDownload(String filePrefix, int timeoutInSeconds = 10) {

		String downloadPath = System.getProperty("user.home") + File.separator + "Downloads"
		File dir = new File(downloadPath)

		int waited = 0

		while (waited < timeoutInSeconds) {


			boolean downloading = dir.listFiles().any { it.name.endsWith(".crdownload") }

			if (!downloading) {
				File excelFile = dir.listFiles()
						.findAll { it.name.startsWith(filePrefix) && it.name.endsWith(".xlsx") }
						.sort { -it.lastModified() }
						.find { it.length() > 0 }

				if (excelFile != null) {
					return excelFile
				}
			}

			WebUI.delay(1)
			waited++
		}

		return null
	}
}
