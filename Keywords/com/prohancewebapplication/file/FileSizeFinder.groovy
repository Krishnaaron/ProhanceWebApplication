package com.prohancewebapplication.file

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil

import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption
public class FileSizeFinder {



	@Keyword
	public void fileSize() {

		try {
			String downloadPath = System.getProperty("user.home") + File.separator + "Downloads"
			File dir = new File(downloadPath)

			if (!dir.exists() || !dir.isDirectory()) {
				KeywordUtil.logInfo("Downloads folder not found: " + downloadPath)
				return
			}

			File[] files = dir.listFiles()

			if (files == null) {
				KeywordUtil.logInfo("No files found in Downloads Top Applications Details Report")
				return
			}
			//		String designationFolder = "MyNewFolder";
			//
			//		File designationFile = new File(
			//				System.getProperty("user.home") + File.separator + designationFolder
			//				);

			//		if (!designationFile.exists()) {
			//			boolean created = designationFile.mkdir();
			//
			//		} else {
			//			System.out.println("Folder already exists");
			//		}

			for (File file : files) {

				if (file.getName().startsWith("Top Applications Details Report")
						&& file.length() > 0) {
					KeywordUtil.logInfo(file.getName() + " " + file.length())
					//				Files.move(
					//					file.toPath(),
					//					new File(designationFolder, file.getName()).toPath(),
					//					StandardCopyOption.REPLACE_EXISTING
					//			)
				}
			}
		}catch(Exception e) {
			KeywordUtil.markError(e.getMessage())
		}
	}

	@Keyword
	public void exisitingFileDelete() {

		try {
			String downloadPath = System.getProperty("user.home") + File.separator + "Downloads"
			System.out.println(downloadPath);
			File dir = new File(downloadPath)

			if (!dir.exists() || !dir.isDirectory()) {
				KeywordUtil.logInfo("Downloads folder not found: " + downloadPath)
				return
			}

			File[] files = dir.listFiles()

			if (files == null) {
				KeywordUtil.logInfo("No files found in Downloads Top Applications Details Report")
				return
			}

			for (File file : files) {

				if (file.getName().startsWith("Top Applications Details Report")) {

					file.delete();
				}
			}
		}catch(Exception e) {
			KeywordUtil.markError(e.getMessage())
		}
	}
}