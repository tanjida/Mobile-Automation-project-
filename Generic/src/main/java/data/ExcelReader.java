package data;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelReader {
   Workbook wb = null;
   HSSFSheet hssf_sheet = null;
   XSSFSheet xssf_sheet = null;
   Cell cell = null;
   FileOutputStream fio = null;
   int numberOfRows, numberOfCol, rowNum;

   public String[][] fileReader1(String path, String sheetIndex) throws IOException {
      String[][] data = null;
      File file = new File(path);
      FileInputStream fis = new FileInputStream(file);

      // Handles both .xls and .xlsx files.
      if (file.getName().endsWith(".xls")) {
         wb = new HSSFWorkbook(fis);
         hssf_sheet = ((HSSFWorkbook) wb).getSheet(sheetIndex);
         numberOfRows = hssf_sheet.getLastRowNum() + 1;
         numberOfCol = hssf_sheet.getRow(0).getLastCellNum();
         data = new String[numberOfRows -1][numberOfCol];

         for (int i = 1; i < numberOfRows; i++) {
            HSSFRow rows = hssf_sheet.getRow(i);
            for (int j = 0; j < numberOfCol; j++) {
               HSSFCell cell = rows.getCell(j);
               String cellData = cell.getStringCellValue();
               data[i-1][j] = cellData;
            }
         }
      } else if(file.getName().endsWith(".xlsx")) {
         wb = new XSSFWorkbook(fis);
         xssf_sheet = ((XSSFWorkbook) wb).getSheet(sheetIndex);
         numberOfRows = xssf_sheet.getLastRowNum() + 1;
         numberOfCol = xssf_sheet.getRow(0).getLastCellNum();
         data = new String[numberOfRows -1][numberOfCol];

         for (int i = 1; i < numberOfRows; i++) {
            XSSFRow rows = xssf_sheet.getRow(i);
            for (int j = 0; j < numberOfCol; j++) {
               XSSFCell cell = rows.getCell(j);
               String cellData = cell.getStringCellValue();
               data[i-1][j] = cellData;
            }
         }
      }

      return data;
   }

   public String[] fileReader2(String path, int sheetIndex) throws IOException {
      String[] data = {};
      File file = new File(path);
      FileInputStream fis = new FileInputStream(file);

      // Handles both .xls and .xlsx files.
      if (file.getName().endsWith(".xls")) {
         wb = new HSSFWorkbook(fis);
         hssf_sheet = ((HSSFWorkbook) wb).getSheetAt(sheetIndex);
         numberOfRows = hssf_sheet.getLastRowNum() + 1;
         numberOfCol = hssf_sheet.getRow(0).getLastCellNum();
         data = new String[numberOfRows - 1];

         for (int i = 1; i < numberOfRows; i++) {
            HSSFRow rows = hssf_sheet.getRow(i);
            for (int j = 0; j < numberOfCol; j++) {
               HSSFCell cell = rows.getCell(j);
               String cellData = getCellValue(cell);
               data[i - 1] = cell.getStringCellValue();
            }
         }
      } else if(file.getName().endsWith(".xlsx")) {
         wb = new XSSFWorkbook(fis);
         xssf_sheet = ((XSSFWorkbook) wb).getSheetAt(sheetIndex);
         numberOfRows = xssf_sheet.getLastRowNum() + 1;
         numberOfCol = xssf_sheet.getRow(0).getLastCellNum();
         data = new String[numberOfRows - 1];

         for (int i = 1; i < numberOfRows; i++) {
            XSSFRow rows = xssf_sheet.getRow(i);
            for (int j = 0; j < numberOfCol; j++) {
               DataFormatter df = new DataFormatter();
               XSSFCell cell = rows.getCell(j);
               String cellData = df.formatCellValue(cell);;
               data[i - 1] = cell.getStringCellValue();
            }
         }
      }
      return data;
   }

   public String getCellValue(HSSFCell cell) {
      Object value = null;

      int dataType = cell.getCellType();
      switch (dataType) {
         case HSSFCell.CELL_TYPE_NUMERIC:
            value = cell.getNumericCellValue();
            break;
         case HSSFCell.CELL_TYPE_STRING:
            value = cell.getStringCellValue();
            break;
         case HSSFCell.CELL_TYPE_BOOLEAN:
            value = cell.getBooleanCellValue();
            break;
      }
      return value.toString();

   }

   public void writeBack(String value) throws IOException {
      wb = new HSSFWorkbook();
      hssf_sheet = ((HSSFWorkbook) wb).createSheet();
      Row row = hssf_sheet.createRow(rowNum);
      row.setHeightInPoints(10);

      fio = new FileOutputStream(new File("ExcelFile.xls"));
      wb.write(fio);
      for (int i = 0; i < row.getLastCellNum(); i++) {
         row.createCell(i);
         cell.setCellValue(value);
      }
      fio.close();
      wb.close();
   }
}