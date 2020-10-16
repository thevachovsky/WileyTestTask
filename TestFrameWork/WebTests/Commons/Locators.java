package TestFrameWork.WebTests.Commons;

public class Locators {
    public static String doNotChangeLocationButton = "//button[@class='changeLocationCancelBtn button large button-dark-gray']";
    public static String searchButton = "//div[@class='main-navigation-search']//span[@class='input-group-btn']//button";
    public static String subjectsButton = "//*[@class='dropdown-submenu']/a[contains(text(), 'SUBJECTS')]";

    public static String searchBox = "//input[@id='js-site-search-input'][@type='search']";
    public static String searchTips = "//*[@class='ui-autocomplete ui-front main-navigation-search-autocomplete " +
            "ui-menu ui-widget ui-widget-content ps-container ps-theme-default']";
    public static String subjects = "//a[contains(text(), 'SUBJECTS')]/../div[@class='dropdown-menu collapse']";
    public static String productOptionButton = ".//*[@class='productButtonGroupName']";
    public static String oBookOptionButton = ".//*[contains(text(), 'O-Book')]";
    public static String addToCartButton = ".//button[@class='small-button add-to-cart-button js-add-to-cart']";
    public static String offerLocator = "//section[@class='product-item']";
    public static String viewOnWileyOnlineLibraryButton = ".//*[contains(text(),'View on Wiley Online Library')]";
    public static String whoWeServeBlockTitle = "//a[@class='who-we-serve-block-title']";
    public static String productTitle = ".//*[@class='product-title']/a";
    public static String groupNameWithPrice = ".//*[@class='groupNameWithPrice']";
}
