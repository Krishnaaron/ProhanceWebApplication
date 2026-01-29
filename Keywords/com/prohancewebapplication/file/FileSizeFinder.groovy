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



	public void moveDownloadedFile() {

		try {

			String downloadPath = System.getProperty("user.home") + File.separator + "Downloads"
			File downloadDir = new File(downloadPath)

			if (!downloadDir.exists() || !downloadDir.isDirectory()) {
				KeywordUtil.markError("Downloads folder not found: " + downloadPath)
				return
			}


			String destinationPath = System.getProperty("user.home") + File.separator + "MyNewFolder"
			File destinationDir = new File(destinationPath)

			if (!destinationDir.exists()) {
				destinationDir.mkdirs()
				KeywordUtil.logInfo("Created folder: " + destinationPath)
			}

			File[] files = downloadDir.listFiles()

			if (files == null || files.length == 0) {
				KeywordUtil.logInfo("No files found in Downloads")
				return
			}

			for (File file : files) {

				if (file.getName().startsWith("Top Applications Details Report") && file.length() > 0) {

					File destinationFile = new File(destinationDir, file.getName())

					boolean moved = false
					int retry = 0
					int maxRetry = 10

					while (!moved && retry < maxRetry) {
						try {
							Files.move(
									file.toPath(),
									destinationFile.toPath(),
									StandardCopyOption.REPLACE_EXISTING
									)
							moved = true
							KeywordUtil.logInfo("File moved successfully: " + file.getName())
						} catch (Exception e) {
							retry++
							KeywordUtil.logInfo("File is in use. Retrying (" + retry + "/" + maxRetry + ")")
							Thread.sleep(2000)
						}
					}

					if (!moved) {
						KeywordUtil.markError("Unable to move file after retries: " + file.getName())
					}
				}
			}
		} catch (Exception e) {
			KeywordUtil.markError("Unexpected error: " + e.getMessage())
		}
	}
}