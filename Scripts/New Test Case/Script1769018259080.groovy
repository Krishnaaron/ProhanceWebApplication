import com.kms.katalon.core.util.KeywordUtil
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
 
 
 
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import com.kms.katalon.core.util.KeywordUtil
import java.io.FileInputStream
 
def readExcelFromDownloads() {
	
	
	
	String excelPath = null;
	String downloadPath = System.getProperty("user.home") + File.separator + "Downloads"
	File downloadDir = new File(downloadPath)

	if (!downloadDir.exists() || !downloadDir.isDirectory()) {
		KeywordUtil.markError("Downloads folder not found: " + downloadPath)
		return
	}
	File[] files = downloadDir.listFiles()
	
		   if (files == null || files.length == 0) {
			   KeywordUtil.logInfo("No files found in Downloads")
			   return
		   }
	for (File file : files) {
    if (file.getName().startsWith('Top Applications Details Report') && (file.length() > 0)) {
        excelPath = file.getPath()
    }
}
	FileInputStream fis
	Workbook workbook
	List<List<String>> excelData = []
	try {
		fis = new FileInputStream(excelPath)
 
		if (excelPath.endsWith('.xlsx')) {
			workbook = new XSSFWorkbook(fis)
		} else if (excelPath.endsWith('.xls')) {
			workbook = new HSSFWorkbook(fis)
		} else {
			throw new IllegalArgumentException('Excel must be .xlsx or .xls')
		}
 
		Sheet sheet = workbook.getSheetAt(0)
 

		
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {   
			
				Row row = sheet.getRow(i)
				if (row == null) continue
			
				List<String> rowData = []
			
				for (int c = 0; c < row.getLastCellNum(); c++) {
			
					Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK)
					rowData.add(cell.toString().trim())
				}
			
				excelData.add(rowData)
				KeywordUtil.logInfo("Excel Row ${i + 1}: ${rowData}")
			}
		
			System.out.println(excelData);
	} catch (Exception e) {
		KeywordUtil.markFailed('Error reading Excel: ' + e.getMessage())
	} finally {
		workbook?.close()
		fis?.close()
	}
}
 
// Call the method
readExcelFromDownloads()
 