import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.opencsv.exceptions.CsvValidationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvRepository {
    private static final Logger logger = LogManager.getLogger(CsvRepository.class);
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
                CSVReader csvReader = new CSVReader(fileReader)
        ) {
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                csvContents.add(nextRecord);
            }

        } catch (IOException e) {
            logger.warn("No file found. Returning empty contents.");
        } catch (CsvValidationException e) {
            logger.warn("Wrong record in CSV file. Returning empty contents.");
        }

        return csvContents;
    }

    public void saveStringsList(ArrayList<String[]> listOfRows, String[] header) {
        File csvFile = new File(this.csvFile);
        try (
                FileWriter fileWriter = new FileWriter(csvFile);
                CSVWriter csvWriter = new CSVWriter(fileWriter)
        ) {
            csvWriter.writeNext(header);

            for (String[] row : listOfRows) {
                csvWriter.writeNext(row);
            }

        } catch (IOException e) {
            logger.warn("No file found. Save operation unsuccessful.");
            e.printStackTrace();
        }
    }

    public void saveProductsList(ArrayList<ProductItem> listOfProducts) {
        File csvFile = new File(this.csvFile);
        try (
                FileWriter fileWriter = new FileWriter(csvFile)
        ) {
            StatefulBeanToCsv<ProductItem> beanToCsv = new StatefulBeanToCsvBuilder<ProductItem>(fileWriter)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();

            beanToCsv.write(listOfProducts);

        } catch (IOException e) {
            logger.warn("No file found. Save operation unsuccessful.");
            e.printStackTrace();

        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
            logger.error("CSV data error.");
            e.printStackTrace();
        }
    }

}
