package br.com.cpqd.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class CiscoExcelUtil {

	private static final String fileName = "C:\\Users\\soliveira\\Desktop\\Planilha.xls";
//	private static final String fileName = "C:\\Users\\soliveira\\Desktop\\novo.xls";

	static DateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	static DateFormat yyyyMMdd = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

	public static void main(String[] args) throws IOException, FileNotFoundException {
		args.equals(fileName);

		try {
			FileInputStream file = new FileInputStream(new File(CiscoExcelUtil.fileName));

			@SuppressWarnings("resource")
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheetDate = workbook.getSheetAt(0);

			for (int i = 0; i < sheetDate.getPhysicalNumberOfRows(); i++) {

				Row row = sheetDate.getRow(i);
				Cell cellDateUnformatted = row.getCell(47);
				Cell cellDateUnformatted2 = row.getCell(49);
				if (cellDateUnformatted != null && cellDateUnformatted2 != null 
						&& cellDateUnformatted.getStringCellValue() != null) {
					
					Cell cellDateFormatter = row.getCell(47);
					sheetDate.autoSizeColumn(48);
					Cell cellDateFormatter2 = row.getCell(49);
					sheetDate.autoSizeColumn(50);
					
					if (cellDateFormatter == null || cellDateFormatter2 == null) {
						cellDateFormatter = row.createCell(47);
						cellDateFormatter2 = row.createCell(49);
						
					}

					cellDateFormatter
							.setCellValue((converteUTFparaData(cellDateUnformatted.getStringCellValue(), "yyyyMMdd")));
					cellDateFormatter2
					.setCellValue((converteUTFparaData(cellDateUnformatted2.getStringCellValue(), "yyyyMMdd")));

				} 
				
				
			}

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(CiscoExcelUtil.fileName));
			workbook.write(outFile);
			outFile.close();
			System.out.println("Arquivo Excel editado com sucesso!");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Arquivo Excel não encontrado!");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro na ediçao do arquivo!");
		}

	}

	public static String converteUTFparaData(String dataUTF, String formatoDesejado) {
		String numStr;
		long numLong = 0;

		try {
			numLong = Long.parseLong(dataUTF);
			// OBs.:Argumento deve ser passado em milissegundos!!!
			numLong = 1000 * numLong;
		} catch (Exception e) {
			numLong = 0L;
			System.out.println("Erro ao converter o parametro!");
		}

		// OBs.:Argumento deve ser passado em milissegundos!!!
		if (formatoDesejado.equalsIgnoreCase("YYYYMMDD")) {
			numStr = yyyyMMdd.format(new Date(numLong));
		} else if (formatoDesejado.equalsIgnoreCase("YYYYMMDD")) {
			numStr = ddMMyyyy.format(new Date(numLong));
		} else {
			System.out.println("Formato de data não implementado.");
			numStr = null;
		}
		System.out.println("Datas: " + numStr);
		return numStr;

	}

}
