import java.util.ArrayList;

public class SmartwatchesPage {

    private static final String SMARTWATCHES_URL = "https://www.x-kom.pl/g-4/c/2435-smartwatche.html";
    private static final String SMARTWATCHES_TITLE = "Smartwatche - Sklep komputerowy - x-kom.pl";

    public void goTo() {
        Browser.goTo(SMARTWATCHES_URL);
    }

    public boolean isAt() {
        return Browser.getTitle().equals(SMARTWATCHES_TITLE);
    }

    public ArrayList<ProductItem> getSmartwatches() {
        return Browser.getProductItems();
    }

}
