/** 
 * Project Name:mi-cims-admin-平台API企业级
 * File Name:CSVUtils.java 
 * Package Name:com.mi.cims.util 
 * Copyright (c) 2017,
 * Company: Magic Image,Ltd.
 * 
 * @author Magic Image-刘恒玉
 * @date 2017年10月18日 上午11:03:45
 * @version V1.0.0
 */

package com.mi.cims.util;

import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.alibaba.fastjson.JSON;

/**
 * ClassName: ExcelUtils Function: (excel文件导出).
 *
 * @author Magic Image-马云涛
 * @date 2017年10月18日 上午11:03:45
 * @version V1.0.0
 */
public class ExcelUtils {
	/**
	 * 
	 * excelExport:(EXCEL文件输出).
	 * 
	 * @author 马云涛
	 * @date 2019年01月02日 上午9:09:25
	 * @param os
	 *            输出数据流
	 * @param hwk
	 *            EXCEL文件对象
	 * @throws Exception
	 */
	public static void excelExport(OutputStream os, HSSFWorkbook hwk) throws Exception {
		// 写入流
		hwk.write(os);
		os.flush();
		os.close();
	}

	/**
	 * createTableHeader:(创建标题)
	 * 
	 * @author 马云涛
	 * @date 2019年01月02日 上午9:09:25
	 * @param sheet
	 *            EXCEL的sheet对象
	 * @param flag
	 *            月报年报标识
	 */
//	public static void createTableHeader(HSSFSheet sheet, List<TemplateDetailInfo> templateDetailInfoMappe) {
//		Row row = sheet.createRow((short) 0);
//		String[] tableHeader = new String[templateDetailInfoMappe.size() + 1];
//		tableHeader[0] = "公司名称";
//		for (int i = 0; i < templateDetailInfoMappe.size(); i++) {
//			tableHeader[i + 1] = templateDetailInfoMappe.get(i).getItemTitle();
//		}
//		for (int i = 0; i < tableHeader.length; i++) {
//			Cell cell = row.createCell(i);
//			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//			cell.setCellValue(tableHeader[i]);
//		}
//	}

	/**
	 * createTableRow:(创建月报具体数据内容)
	 * 
	 * @author 马云涛
	 * @date 2019年01月02日 上午9:09:25
	 * @param po
	 *            报税统计信息PO
	 * @param rowIndex
	 *            开始写入数据行
	 * @param hwk
	 *            EXCEL文件对象
	 * @param sheet
	 *            EXCEL的sheet对象
	 * @param cellStyle
	 *            cell样式
	 */
	public static void createTableRow(List<?> list, short rowIndex, HSSFWorkbook hwk, HSSFSheet sheet,
			HSSFCellStyle cellStyle) {
		// 创建第rowIndex行
		HSSFRow row = sheet.createRow((short) rowIndex);
		for (int i = 0; i < list.size(); i++) {
			// 创建第i个单元格
			HSSFCell cell = row.createCell(i);
			// if (cell.getCellType() != 1) {
			// cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// }
			cell.setCellStyle(cellStyle);
			String value = "";
			if (list.get(i) instanceof java.util.List) {// 如果list.get(i)是List的实例(多选)
				List<?> multiSelectList = (List<?>) (list.get(i));
				for (int j = 0; j < multiSelectList.size(); j++) {
					// 获得多选中各个选项
					Object obj = multiSelectList.get(j);
					if (j == (multiSelectList.size() - 1)) {// 最后一个选项后面不加换行
						String temp = JSON.toJSONString(obj).trim();
						value += temp.substring(1, temp.length() - 1);
					} else {
						String temp = JSON.toJSONString(obj).trim();
						// 多选各个选项间加入换行\r\n
						value += temp.substring(1, temp.length() - 1) + "\r\n";
					}
				}
			} else {// 如果不是多选
				value = JSON.toJSONString(list.get(i));
				value.trim();
				value = value.substring(1, value.length() - 1);
			}
			cell.setCellValue(new HSSFRichTextString(value));
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
		}
	}

	/**
	 * getData:(获取具体数据内容)
	 * 
	 * @author 马云涛
	 * @date 2019年01月02日 上午9:09:25
	 * @param po
	 *            报税统计信息PO
	 * @param i
	 *            税种标识
	 * @return 返回字符串数值
	 */
	public static String getData(List<?> list, int i) {
		String cellData = "";
		// switch (i) {
		// case 0:
		// cellData = po.getSocialCreditCode();
		// break;
		// case 1:
		// cellData = po.getCompanyName();
		// break;
		// case 2:
		// cellData = po.getZengzhiTax();
		// break;
		// case 3:
		// cellData = po.getQiyesuodeTax();
		// break;
		// case 4:
		// cellData = po.getGerensuodeshuiTax();
		// break;
		// case 5:
		// cellData = po.getTudizengzhiTax();
		// break;
		// case 6:
		// cellData = po.getFangchanTax();
		// break;
		// case 7:
		// cellData = po.getTudishiyongTax();
		// break;
		// case 8:
		// cellData = po.getYinhuaTax();
		// break;
		// case 9:
		// cellData = po.getQiTax();
		// break;
		// case 10:
		// cellData = po.getHuanbaoTax();
		// break;
		// }
		return cellData;
	}

	/**
	 * createTableHeader:(创建标题)
	 * 
	 * @author 马云涛
	 * @date 2019年01月02日 上午9:09:25
	 * @param sheet
	 *            EXCEL的sheet对象
	 * @param flag
	 *            月报年报标识
	 */
	public static void createTableHeader(HSSFSheet sheet, Integer flag) {
		Row row = sheet.createRow((short) 0);
		String[] tableHeader;
		// 月报标题
		if (flag == 0) {
			tableHeader = new String[12];
			tableHeader[0] = "纳税人识别号";
			tableHeader[1] = "纳税人名称";
			tableHeader[2] = "增值税";
			tableHeader[3] = "企业所得税";
			tableHeader[4] = "个人所得税";
			tableHeader[5] = "土地增值税";
			tableHeader[6] = "房产税";
			tableHeader[7] = "土地使用税";
			tableHeader[8] = "印花税";
			tableHeader[9] = "契税";
			tableHeader[10] = "环保税";
			tableHeader[11] = "城市维护建设税";
		} else {
			// 年报标题
			tableHeader = new String[12];
			tableHeader[0] = "纳税人识别号";
			tableHeader[1] = "纳税人名称";
			tableHeader[2] = "增值税";
			tableHeader[3] = "企业所得税";
			tableHeader[4] = "个人所得税";
			tableHeader[5] = "土地增值税";
			tableHeader[6] = "房产税";
			tableHeader[7] = "土地使用税";
			tableHeader[8] = "印花税";
			tableHeader[9] = "契税";
			tableHeader[10] = "环保税";
			tableHeader[11] = "城建费附加税";
			
		}
		for (int i = 0; i < tableHeader.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(tableHeader[i]);
		}
	}

//	/**
//	 * createTableRow:(创建月报具体数据内容)
//	 * 
//	 * @author 马云涛
//	 * @date 2019年01月02日 上午9:09:25
//	 * @param po
//	 *            报税统计信息PO
//	 * @param rowIndex
//	 *            开始写入数据行
//	 * @param hwk
//	 *            EXCEL文件对象
//	 * @param sheet
//	 *            EXCEL的sheet对象
//	 * @param cellStyle
//	 *            cell样式
//	 */
//	public static void createTableRow(DeclareTaxInfoExtension po, short rowIndex, HSSFWorkbook hwk, HSSFSheet sheet,
//			HSSFCellStyle cellStyle) {
//		// 创建第rowIndex行
//		HSSFRow row = sheet.createRow((short) rowIndex);
//		for (int i = 0; i < 12; i++) {
//			// 创建第i个单元格
//			HSSFCell cell = row.createCell(i);
//			if (cell.getCellType() != 1) {
//				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//			}
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(getData(po, i));
//			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//		}
//	}
//
//	/**
//	 * createYearTableRow:(创建年报具体数据内容)
//	 * 
//	 * @author 马云涛
//	 * @date 2019年01月02日 上午9:09:25
//	 * @param po
//	 *            报税统计信息PO
//	 * @param rowIndex
//	 *            开始写入数据行
//	 * @param hwk
//	 *            EXCEL文件对象
//	 * @param sheet
//	 *            EXCEL的sheet对象
//	 * @param cellStyle
//	 *            cell样式
//	 */
//	public static void createYearTableRow(DeclareTaxYearInfoExtension po, short rowIndex, HSSFWorkbook hwk,
//			HSSFSheet sheet, HSSFCellStyle cellStyle) {
//		// 创建第rowIndex行
//		HSSFRow row = sheet.createRow((short) rowIndex);
//		for (int i = 0; i < 12; i++) {
//			// 创建第i个单元格
//			HSSFCell cell = row.createCell(i);
//			if (cell.getCellType() != 1) {
//				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//			}
//			cell.setCellStyle(cellStyle);
//			cell.setCellValue(getTaxYearData(po, i));
//			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
//		}
//	}

//	/**
//	 * getData:(获取具体数据内容)
//	 * 
//	 * @author 马云涛
//	 * @date 2019年01月02日 上午9:09:25
//	 * @param po
//	 *            报税统计信息PO
//	 * @param i
//	 *            税种标识
//	 * @return 返回字符串数值
//	 */
//	public static String getData(DeclareTaxInfoExtension po, int i) {
//		String cellData = "";
//		switch (i) {
//		case 0:
//			cellData = po.getSocialCreditCode();
//			break;
//		case 1:
//			cellData = po.getCompanyName();
//			break;
//		case 2:
//			cellData = po.getZengzhiTax();
//			break;
//		case 3:
//			cellData = po.getQiyesuodeTax();
//			break;
//		case 4:
//			cellData = po.getGerensuodeshuiTax();
//			break;
//		case 5:
//			cellData = po.getTudizengzhiTax();
//			break;
//		case 6:
//			cellData = po.getFangchanTax();
//			break;
//		case 7:
//			cellData = po.getTudishiyongTax();
//			break;
//		case 8:
//			cellData = po.getYinhuaTax();
//			break;
//		case 9:
//			cellData = po.getQiTax();
//			break;
//		case 10:
//			cellData = po.getHuanbaoTax();
//			break;
//		case 11:
//			cellData = po.getChengjianTax();
//			break;
//		}
//		return cellData;
//	}
//
//	/**
//	 * getTaxYearData:(获取年报具体数据内容)
//	 * 
//	 * @author 马云涛
//	 * @date 2019年01月02日 上午9:09:25
//	 * @param po
//	 *            报税统计信息PO
//	 * @param i
//	 *            税种标识
//	 * @return 返回字符串数值
//	 */
//	public static String getTaxYearData(DeclareTaxYearInfoExtension po, int i) {
//		String cellData = "";
//		switch (i) {
//		case 0:
//			cellData = po.getSocialCreditCode();
//			break;
//		case 1:
//			cellData = po.getCompanyName();
//			break;
//		case 2:
//			cellData = po.getZengzhiTax();
//			break;
//		case 3:
//			cellData = po.getQiyesuodeTax();
//			break;
//		case 4:
//			cellData = po.getGerensuodeshuiTax();
//			break;
//		case 5:
//			cellData = po.getTudizengzhiTax();
//			break;
//		case 6:
//			cellData = po.getFangchanTax();
//			break;
//		case 7:
//			cellData = po.getTudishiyongTax();
//			break;
//		case 8:
//			cellData = po.getYinhuaTax();
//			break;
//		case 9:
//			cellData = po.getQiTax();
//			break;
//		case 10:
//			cellData = po.getHuanbaoTax();
//			break;
//		case 11:
//			cellData = po.getChengjianTax();
//			break;
//
//		}
//		return cellData;
//	}
}