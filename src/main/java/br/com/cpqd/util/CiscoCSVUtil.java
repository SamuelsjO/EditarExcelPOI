package br.com.cpqd.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CiscoCSVUtil {

	static DateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	static DateFormat yyyyMMdd = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

	public static void main(String[] args) throws IOException {
		
		if(args.length != 1){
			System.out.println("Quantidade de parametros inválida, favor informar nome do arquivo como parâmetro!!!");
			System.exit(0);
		}
		
		String fileName = new File(".").getCanonicalPath() + File.separator + args[0];
		String fileNameNew = new File(".").getCanonicalPath() + File.separator + args[0].substring(0, args[0].lastIndexOf(".")) + "_alterado.csv";
		System.out.println(fileNameNew);

		try {
			@SuppressWarnings("resource")
			CSVReader iterator = new CSVReader(new FileReader(fileName));
			@SuppressWarnings("deprecation")
			CSVWriter writer = new CSVWriter(new FileWriter(fileNameNew), '\t');

			String result;

			for (String[] nextLine : iterator) {

				result = nextLine[0] + " , " + nextLine[1] + " , " + converteUTFparaData(nextLine[2], "yyyyMMdd")
						+ " , " + nextLine[3];

				String[] entries = result.split(",");
				writer.writeNext(entries);

			}
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println();
		System.out.println("Arquivo CSV editado com sucesso!");

	}

	public static String converteUTFparaData(String dataUTF, String formatoDesejado) {
		String numStr;
		long numLong = 0;

		try {
			numLong = Long.parseLong(dataUTF);
			// OBs.:Argumento deve ser passado em milissegundos!!!
			numLong = 1000 * numLong;
		} catch (Exception e) {
			return dataUTF;
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
		// System.out.println("Datas: " + numStr);
		return numStr;

	}
}