package br.com.parses.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class CiscoCSVUtil {
	
	private static final String fileName = "C:\\Users\\soliveira\\Desktop\\arq_unico.csv";
	private static final String NewfileName = "C:\\Users\\soliveira\\Desktop\\newarq_unico.csv";
	
	static DateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	static DateFormat yyyyMMdd = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");

	public static void main(String[] args) throws IOException {

		CSVReader reader = new CSVReader(new FileReader(fileName));
		CSVWriter writer = new CSVWriter(new FileWriter(NewfileName));
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			// nextLine[] is an array of values from the line
//			if(nextLine[2] != null){
//				nextLine[2] = converteUTFparaData(nextLine[2], "yyyyMMdd");
//			}
			System.out.println(nextLine[0]+ " , " + nextLine[1] + " , "+ nextLine[2] + " , "+ nextLine[3]);

		}
		while ((nextLine = reader.readNext()) != null) {
		    List<String> lineAsList = new ArrayList<String>(Arrays.asList(nextLine));
		    // Add stuff using linesAsList.add(index, newValue) as many times as you need.
		    writer.writeNext((String[]) lineAsList.toArray());

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