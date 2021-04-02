package censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStates {

    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;

    @CsvBindByName(column = "State Name", required = true)
    public String stateName;

    @CsvBindByName(column = "TIN", required = true)
    public int tIN;

    @CsvBindByName(column = "StateCode", required = true)
    public int stateCode;
}
