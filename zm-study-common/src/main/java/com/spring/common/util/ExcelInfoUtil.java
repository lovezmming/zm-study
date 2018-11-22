/*
 * Copyright (c) 2017 上海极值信息技术有限公司 All Rights Reserved.
 */
package com.spring.common.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.*;

public class ExcelInfoUtil
{

    public static Map<Integer, Map<Integer, List<Object>>> getXSSFData(File file) throws Exception
    {
        Map<Integer, Map<Integer, List<Object>>> resultMap = new HashMap<Integer, Map<Integer, List<Object>>>();
        FileInputStream input = null;
        XSSFWorkbook workbook = null;
        try
        {
            input = new FileInputStream(file);
            workbook = new XSSFWorkbook(input);
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            int sheetNum = 0;
            while (sheetIterator.hasNext())
            {
                XSSFSheet sheet = (XSSFSheet) sheetIterator.next();
                Map<Integer, List<Object>> sheetMap = new HashMap<Integer, List<Object>>();
                int maxRowNum = sheet.getLastRowNum();
                int maxColumnNum = 0;
                XSSFRow firstRow = sheet.getRow(0);
                if (firstRow != null)
                {
                    maxColumnNum = firstRow.getPhysicalNumberOfCells();
                    for (int i = 0; i <= maxRowNum; i++)
                    {
                        XSSFRow row = sheet.getRow(i);
                        if (row == null)
                        {
                            continue;
                        }
                        if (maxColumnNum < row.getPhysicalNumberOfCells())
                        {
                            maxColumnNum = row.getPhysicalNumberOfCells();
                        }
                    }
                } else
                {
                    resultMap.put(sheetNum, sheetMap);
                    continue;
                }
                for (int rownum = 0; rownum <= maxRowNum; rownum++)
                {
                    List<Object> rowList = new ArrayList<Object>();
                    XSSFRow row = sheet.getRow(rownum);
                    if (row == null)
                    {
                        continue;
                    }
                    for (int columnNum = 0; columnNum < maxColumnNum; columnNum++)
                    {
                        XSSFCell cell = row.getCell(columnNum);
                        rowList.add(cell == null ? null : cell.toString());
                    }
                    sheetMap.put(rownum, rowList);
                }
                resultMap.put(sheetNum, sheetMap);
                sheetNum++;
            }
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            if (input != null)
                input.close();
            if (workbook != null)
                workbook.close();
        }
        return resultMap;
    }

    public static Map<String, Map<Integer, List<Object>>> getXSSFDataBySheetName(File file) throws Exception
    {
        Map<String, Map<Integer, List<Object>>> resultMap = new HashMap<String, Map<Integer, List<Object>>>();
        FileInputStream input = null;
        XSSFWorkbook workbook = null;
        try
        {
            input = new FileInputStream(file);
            workbook = new XSSFWorkbook(input);
            Iterator<Sheet> sheetIterator = workbook.sheetIterator();
            while (sheetIterator.hasNext())
            {
                XSSFSheet sheet = (XSSFSheet) sheetIterator.next();
                String sheetName = sheet.getSheetName();
                Map<Integer, List<Object>> sheetMap = new HashMap<Integer, List<Object>>();
                int maxRowNum = sheet.getLastRowNum();
                XSSFRow firstRow = sheet.getRow(0);
                int maxColumnNum = 0;
                if (firstRow != null)
                {
                    maxColumnNum = firstRow.getPhysicalNumberOfCells();
                    for (int i = 0; i <= maxRowNum; i++)
                    {
                        XSSFRow row = sheet.getRow(i);
                        if (row == null)
                        {
                            continue;
                        }
                        if (maxColumnNum < row.getPhysicalNumberOfCells())
                        {
                            maxColumnNum = row.getPhysicalNumberOfCells();
                        }
                    }
                } else
                {
                    resultMap.put(sheetName, sheetMap);
                    continue;
                }
                for (int rownum = 0; rownum <= maxRowNum; rownum++)
                {
                    List<Object> rowList = new ArrayList<Object>();
                    XSSFRow row = sheet.getRow(rownum);
                    if (row == null)
                    {
                        continue;
                    }
                    for (int columnNum = 0; columnNum < maxColumnNum; columnNum++)
                    {
                        XSSFCell cell = row.getCell(columnNum);
                        rowList.add(cell == null ? null : cell.toString());
                    }
                    sheetMap.put(rownum, rowList);
                }
                resultMap.put(sheetName, sheetMap);
            }
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            if (input != null)
                input.close();
            if (workbook != null)
                workbook.close();
        }
        return resultMap;
    }

    public static <T> File exportExcelWithDownBox(String title, String[] headers, Collection<T> dataset,
            String pattern, Map<String, String[]> map, String type) throws Exception
    {
        File file = new File(title + ".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        FileOutputStream fos = null;
        try
        {
            XSSFSheet sheet = workbook.createSheet(title);
            sheet = setXSSFValidation(sheet, headers, 0, 0, 0, headers.length);// 第一列的前65536行都设置为选择列表形式.

            Map<String, String> selectSheetMap = new HashMap<String, String>();
            // head
            XSSFRow row = sheet.createRow(0);
            for (short i = 0; i < headers.length; i++)
            {
                XSSFCell cell = row.createCell(i);
                XSSFRichTextString text = new XSSFRichTextString(headers[i]);
                cell.setCellValue(text);

                if (null != map)
                {
                    String excelName = null;
                    if ("a".equals(headers[i]))
                    {
                        excelName = "status";
                    }
                    if ("b".equals(headers[i]))
                    {
                        excelName = "num";
                    }
                    if ("c".equals(headers[i]))
                    {
                        excelName = "haha";
                    }
                    if ("d".equals(headers[i]))
                    {
                        excelName = "num1";
                    }
                    String[] colStrings = map.get(excelName);
                    if (colStrings != null)
                    {
                        if (colStrings.length > 87)
                        {
                            // 下拉菜单数超过87则放不下？需要进行菜单页链接放置
                            sheet = setSelectXSSFValidation(selectSheetMap, sheet, workbook, headers[i].split(" ")[0], colStrings, 1, 10240, i, i);
                        } else
                        {
                            sheet = setXSSFValidation(sheet, colStrings, 1, 10240, i, i);// 第i列的前10240行都设置为选择列表形式.
                        }
                    }
                }
            }
            selectSheetMap = null;
            // data
            if (null != dataset)
            {
                if (!dataset.isEmpty())
                {
                    Iterator<T> it = dataset.iterator();
                    int index = 0;
                    while (it.hasNext())
                    {
                        index++;
                        row = sheet.createRow(index);
                        T t = (T) it.next();
                        Method method = t.getClass().getDeclaredMethod("values");
                        String[] values = (String[]) method.invoke(t);
//                        String[] values = t.values();
                        for (int i = 0; i < values.length; i++)
                            row.createCell(i).setCellValue(new XSSFRichTextString(values[i]));
                    }
                }
            }
            fos = new FileOutputStream(file);
            workbook.write(fos);
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            if (fos != null)
            {
                fos.close();
            }
            workbook = null;
        }
        return file;
    }

    public static XSSFSheet setSelectXSSFValidation(Map<String, String> selectSheetMap, XSSFSheet sheet, XSSFWorkbook workbook, String header, String[] colStrings, int firstRow, int lastRow, int firstCol, int lastCol)
    {
        if (firstRow < 0 || lastRow < 0 || firstCol < 0 || lastCol < 0 || lastRow < firstRow || lastCol < firstCol)
        {
            throw new IllegalArgumentException("Wrong Row or Column index : " + firstRow + ":" + lastRow + ":" + firstCol + ":" + lastCol);
        }

        String selectSheetName = null;
        if (selectSheetMap.get(header) != null)
        {
            selectSheetName = selectSheetMap.get(header);
        } else
        {
            selectSheetName = header + System.currentTimeMillis();
            XSSFSheet selectSheet = workbook.createSheet(selectSheetName);
            for (int k = 0; k < colStrings.length; k++) {
                XSSFRow selectRow = selectSheet.createRow(k);
                XSSFCell selectCell = selectRow.createCell((int) 0);
                selectCell.setCellValue(colStrings[k]);
            }
            workbook.setSheetHidden(workbook.getSheetIndex(selectSheetName), 0);
            XSSFName xssfName = workbook.createName();
            xssfName.setNameName(selectSheetName);
            xssfName.setRefersToFormula(selectSheetName + "!$A$1:$A$" + colStrings.length);
            selectSheetMap.put(header, selectSheetName);
        }
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint(selectSheetName);
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
        validation.setSuppressDropDownArrow(true);
        validation.setShowErrorBox(true);
        validation.setErrorStyle(XSSFDataValidation.ErrorStyle.WARNING);
        validation.createErrorBox("警告", "此信息不在所选范围，请确认是否填写！");
        sheet.addValidationData(validation);
        return sheet;
    }

    public static XSSFSheet setXSSFValidation(XSSFSheet sheet, String[] textlist, int firstRow, int lastRow, int firstCol, int lastCol)
    {

        if (firstRow < 0 || lastRow < 0 || firstCol < 0 || lastCol < 0 || lastRow < firstRow || lastCol < firstCol)
        {
            throw new IllegalArgumentException("Wrong Row or Column index : " + firstRow + ":" + lastRow + ":" + firstCol + ":" + lastCol);
        }

        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet) sheet);

        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(textlist);
        CellRangeAddressList addressList = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        XSSFDataValidation validation = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, addressList);
        validation.setSuppressDropDownArrow(true);
        validation.setShowErrorBox(true);
        validation.setErrorStyle(XSSFDataValidation.ErrorStyle.WARNING);
        validation.createErrorBox("警告", "此信息不在所选范围，请确认是否填写！");
        sheet.addValidationData(validation);
        return sheet;
    }

    public static boolean exportData(List<List<String>> list, File file, String sheetName) throws Exception
    {
        OutputStream os = null;
        Workbook book = null;
        try
        {
            os = new FileOutputStream(file);
            book = new XSSFWorkbook();
            Sheet sheet = book.createSheet(sheetName);
            int rowN = 0;
            for (List<String> subList : list)
            {
                Row row = sheet.createRow(rowN);
                int cellN = 0;
                for (String value : subList)
                {
                    row.createCell(cellN).setCellValue(value);
                    cellN++;
                }
                rowN++;
            }
            book.write(os);
        } catch (Exception e)
        {
            throw e;
        } finally
        {
            if (book != null)
                book.close();
            if (os != null)
                os.close();
        }

        return true;
    }

}
