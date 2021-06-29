import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);
    protected static final String XKOM_URL = "https://www.x-kom.pl";

    public static void main(String[] args) {

        SmartwatchesPage smartwatchesPage = new SmartwatchesPage();
        smartwatchesPage.goTo();

        // Find all smartwatches and gather information about them
        ArrayList<ProductItem>  listOfSmartwatches = smartwatchesPage.getSmartwatches();
        ArrayList<String[]> csvRows = new ArrayList<>();
        // TODO: Change writing functionality to use objects instead of String arrays.
        for (ProductItem smartwatch: listOfSmartwatches) {
            String[] csvRow = {smartwatch.getName(), String.valueOf(smartwatch.getPrice()), String.valueOf(smartwatch.getRating())};
            csvRows.add(csvRow);
        }
        logger.info("Reading smartwatch data successful.");

        // Save to CSV file together with the header
        String[] header = {"Smartwatch Name", "Price (PLN)", "Rating (x/6)"};
        CsvRepository csvRepo = new CsvRepository("csv_output/smartwatches_list.csv");
        csvRepo.save(csvRows, header);
        // csvRepo.print();

        Browser.close();
    }

}
