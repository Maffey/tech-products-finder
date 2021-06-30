import java.util.ArrayList;

public class ProductsPage {

    private final ProductCategoryPage productCategoryPage;

    public ProductsPage(ProductCategoryPage productCategoryPage) {
        this.productCategoryPage = productCategoryPage;
    }

    public void goTo() {
        Browser.goTo(productCategoryPage.getUrl());
    }

    public boolean isAt() {
        return Browser.getTitle().equals(productCategoryPage.getTitle());
    }

    public ArrayList<ProductItem> getSmartwatches() {
        return Browser.getProductItems();
    }

}
