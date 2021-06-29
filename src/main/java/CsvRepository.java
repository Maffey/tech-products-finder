import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvRepository {
    private static final Logger logger = LogManager.getLogger(CsvRepository.class);
    // TODO
    private final String csvFile;

    public CsvRepository(String csvFile) {
        this.csvFile = csvFile;
        logger.debug("CSV File path: " + this.csvFile);
    }

    public void print() {
        ArrayList<String[]> dataRows = read();
        if (!dataRows.isEmpty()) {
            for (String[] row : dataRows) {
                System.out.println(Arrays.toString(row));
            }
        } else {
            logger.info("dataRows are empty. No printing to be executed.");
        }
    }

    public ArrayList<String[]> read() {
        ArrayList<String[]> csvContents = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(csvFile);
                CSVReader csvReader = new CSVReader(fileReader);
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                csvContents.add(nextRecord);
            }

        } catch (IOException e) {
            logger.warn("No file found. Returning empty contents.");
            e.printStackTrace();
        } catch (CsvValidationException e) {
            logger.warn("Wrong record in CSV file. Returning empty contents.");
        }

        return csvContents;
    }

    public void save(ArrayList<String[]> listOfRows, String[] header) {
        File csvFile = new File(this.csvFile);
        try {
            FileWriter fileWriter = new FileWriter(csvFile);
            CSVWriter csvWriter = new CSVWriter(fileWriter);
            csvWriter.writeNext(header);

            for (String[] row : listOfRows) {
                csvWriter.writeNext(row);
            }
            csvWriter.close();

        } catch (IOException e) {
            logger.warn("No file found. Save operation unsuccessful.");
            e.printStackTrace();
        }
    }

    private boolean csvFileExists(String checkedCsvFile) {
        File csvFile = new File(checkedCsvFile);
        return csvFile.exists();
    }

}
