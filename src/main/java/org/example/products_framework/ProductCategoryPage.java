package org.example.products_framework;

// This implementation allows for further expansion into searching multiple different product categories.
public enum ProductCategoryPage {

    SMARTWATCHES("https://www.x-kom.pl/g-4/c/2435-smartwatche.html",
            "Smartwatche - Sklep komputerowy - x-kom.pl"),
    LAPTOPS_2_IN_1("https://www.x-kom.pl/g-2/c/2158-laptopy-2-w-1.html",
            "Laptopy 2 w 1 - Sklep komputerowy - x-kom.pl"),
    PC_GAMES("https://www.x-kom.pl/g-7/c/1686-gry-na-pc.html", "Gry na PC - Sklep komputerowy - x-kom.pl");

    private final String url;
    private final String title;

    ProductCategoryPage(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}
