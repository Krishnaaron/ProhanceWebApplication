package com.prohanceapplication.common

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType

public class Export {

    String exportTypeExcel = 'Excel'
    String exportTypePdf = 'Pdf'

    @Keyword
    public String export(String exportType) {

        TestObject exportSpan = new TestObject()

        if (exportType.equalsIgnoreCase(exportTypeExcel)) {

            
               return "//span[.//img[@title='Export to Excel']]" ;
            
        }
		
		else if (exportType.equalsIgnoreCase(exportTypePdf))
		{
			
			return "//span[.//img[@title='Export to PDF']]" ;
			
		}	
		else {
			
			exportSpan.addProperty(null);
			
		}
		
	return exportSpan;
    }
}
