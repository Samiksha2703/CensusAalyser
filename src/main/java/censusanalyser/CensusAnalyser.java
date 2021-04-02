package censusanalyser;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyser {
    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        String[] csvFile = csvFilePath.split("[.]");
        if (!csvFile[1].equals("csv")) {
            throw new CensusAnalyserException("Invalid file type.", CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
        }
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
            int namOfEateries = 0;
            while (censusCSVIterator.hasNext()) {
                namOfEateries++;
                IndiaCensusCSV censusData = censusCSVIterator.next();
            }
            return namOfEateries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public void checkDelimeter(String csvFilePath) throws CensusAnalyserException {
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(csvFilePath));
            while (true) {
                String line = br.readLine();
                String[] Linecolumns = line.split(",");
                if (Linecolumns.length < 4) {
                    throw new CensusAnalyserException("Wrong Delimeter Type", CensusAnalyserException.ExceptionType.WRONG_DELI_METER_DATA_FILE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}