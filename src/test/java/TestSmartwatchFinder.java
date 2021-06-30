import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

// TODO: Parametrize
public class TestSmartwatchFinder {

    private static ProductItem smartwatch;
    private static ArrayList<ProductItem> smartwatchItemElements;
    private static final String TEST_SAVE_FILE = ".\\src\\test\\test_files\\test_saving_objects.csv";
    private static final String[] HEADER = {"NAME", "PRICE", "RATING"};

    @BeforeAll
    static void setUpDriver() {
        ProductsPage smartwatchesPage = new ProductsPage(ProductCategoryPage.SMARTWATCHES);
        smartwatchesPage.goTo();

        smartwatchItemElements = smartwatchesPage.getSmartwatches();
    }

    @AfterAll
    static void tearResources() {
        Browser.close();
        File file = new File(TEST_SAVE_FILE);
        if (file.delete()) {
            System.out.println("File deleted successfully");
        } else {
            System.out.println("Failed to delete the file");
        }
    }

    @Test
    void canFindSmartwatchTitleOfFirstElement() {
        smartwatch = smartwatchItemElements.get(0);
        String smartwatchName = smartwatch.getName();
        assertThat(smartwatchName).isEqualTo("Smartband Xiaomi Mi Band 5 czarny");
    }

    @Test
    void canFindSmartwatchPriceOfFirstElement() {
        smartwatch = smartwatchItemElements.get(0);
        float smartwatchPrice = smartwatch.getPrice();
        assertThat(smartwatchPrice).isEqualTo(119.00f);
    }

    @Test
    void canFindRatingOfFirstElement() {
        smartwatch = smartwatchItemElements.get(0);
        float smartwatchRating = smartwatch.getRating();
        assertThat(smartwatchRating).isEqualTo(5.5f);
    }

    @Test
    void canFindSmartwatchTitleOfThirdElement() {
        smartwatch = smartwatchItemElements.get(2);
        String smartwatchName = smartwatch.getName();
        assertThat(smartwatchName).isEqualTo("Smartband Xiaomi Mi Band 6");
    }

    @Test
    void canFindSmartwatchPriceOfThirdElement() {
        smartwatch = smartwatchItemElements.get(2);
        float smartwatchPrice = smartwatch.getPrice();
        assertThat(smartwatchPrice).isEqualTo(179.00f);
    }

    @Test
    void canFindRatingOfThirdElement() {
        smartwatch = smartwatchItemElements.get(2);
        float smartwatchRating = smartwatch.getRating();
        assertThat(smartwatchRating).isEqualTo(5.8f);
    }

    @Test
    void productObjectHasCorrectTitle() {
        smartwatch = smartwatchItemElements.get(0);
        assertThat(smartwatch.getName()).isEqualTo("Smartband Xiaomi Mi Band 5 czarny");
    }

    @Test
    void productObjectHasCorrectPricing() {
        smartwatch = smartwatchItemElements.get(0);
        assertThat(smartwatch.getPrice()).isEqualTo(119.0f);
    }

    @Test
    void productObjectHasCorrectRating() {
        smartwatch = smartwatchItemElements.get(0);
        assertThat(smartwatch.getRating()).isEqualTo(5.5f);
    }

    @Test
    void productItemPrintsCorrectInfo() {
        smartwatch = smartwatchItemElements.get(0);
        String printData = smartwatch.print();
        assertThat(printData).isEqualTo("Title: Smartband Xiaomi Mi Band 5 czarny | Price: 119.0 | Rating: 5.5");
    }

    @Test
    void saveObjectRowsToCsvFile() {
        CsvRepository csvRepo = new CsvRepository(TEST_SAVE_FILE);
        ArrayList<String[]> listOfRows = new ArrayList<>();
        listOfRows.add(HEADER);
        for (ProductItem smartwatch: smartwatchItemElements) {
            listOfRows.add(new String[]{smartwatch.getName(),
                    String.valueOf(smartwatch.getPrice()),
                    String.valueOf(smartwatch.getRating())});
        }
        csvRepo.saveProductsList(smartwatchItemElements);
        ArrayList<String[]> csvContents = csvRepo.read();
        assertThat(listOfRows).hasSameElementsAs(csvContents);
    }

}
