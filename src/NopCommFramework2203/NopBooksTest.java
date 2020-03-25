// go to books category, verify user should navigate to books successfully,
//click on sort by position drop down menu, arrange into A to Z.
// select first book and add to favourite.
package NopCommFramework2203;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class NopBooksTest {
    private String baseUrl = "https://demo.nopcommerce.com/";
    private WebDriver driver;


    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        driver = new ChromeDriver(options);
        driver.manage().window().setPosition(new Point(-2000, 0));//display 2
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    @Test

    public void verifyUserShouldNavigateToBooksPage() throws InterruptedException {
        WebElement bookslink = driver.findElement(By.linkText("Books"));
        bookslink.click();
        WebElement welcomeText = driver.findElement(By.xpath("//div[@class=\"page-title\"]//h1"));
        Thread.sleep(3000);
        String expectedText = "Books";
        String actualText = welcomeText.getText();
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void booksPage() throws InterruptedException {

        WebElement books = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Books')]"));
        books.click();

        WebElement sortBy = driver.findElement(By.xpath("//option[contains(text(),'Name: A to Z')]"));
        sortBy.click();

        WebElement dropDown = driver.findElement(By.xpath("//select[@name='products-orderby']"));
        dropDown.click();
        Thread.sleep(3000);

        WebElement wishList = driver.findElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[2]//div[3]//div[2]//input[3]"));
        wishList.click();

        WebElement wishListText = driver.findElement(By.xpath("//p[@class='content']"));
        String expectedText = "The product has been added to your wishlist";
        String actualText = wishListText.getText();
        Assert.assertEquals(expectedText, actualText);
        System.out.println(wishListText.getText());

    }

    @After
    public void closeBrowser() {

        driver.quit();

    }
}

