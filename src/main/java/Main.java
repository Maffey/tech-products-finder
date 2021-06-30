import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        ProductsPage smartwatchesPage = new ProductsPage(ProductCategoryPage.SMARTWATCHES);
        smartwatchesPage.goTo();

        // Find all smartwatches and gather information about them
        ArrayList<ProductItem> listOfSmartwatches = smartwatchesPage.getSmartwatches();
        logger.info("Reading smartwatch data successful.");

        CsvRepository csvRepo = new CsvRepository("csv_output/smartwatches_list.csv");
        csvRepo.saveProductsList(listOfSmartwatches);
        // csvRepo.saveStringsList(csvRows, header);
        // csvRepo.print();

        Browser.close();
    }

}
