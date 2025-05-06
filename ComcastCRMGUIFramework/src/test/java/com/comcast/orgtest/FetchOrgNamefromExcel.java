package com.comcast.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class FetchOrgNamefromExcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		ExcelUtility e=new ExcelUtility();
		String data = e.getDataFromExcel("Org", 10, 2);
		System.out.println(data);
	}
}
