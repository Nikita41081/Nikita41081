// User should navigate to Computers, click on desktop
// select Build your own computer, select radio button Hdd 400gb
//click on add to cart button
// display message your product has been added to shopping cart.

package NopCommFramework2203;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class NopComputersTest {
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
    public void openComputers() {
        WebElement clickOnComputers = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]"));
        clickOnComputers.click();
    }

    public void openDesktops() {
        WebElement clickOnComputers = driver.findElement(By.xpath("//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]"));
        clickOnComputers.click();

        WebElement clickonDesktops = driver.findElement(By.xpath("//div[@class='item-grid']//div[1]//div[1]//div[1]//a[1]//img[1]"));
        clickonDesktops.click();

        WebElement buildOwnComputer = driver.findElement(By.xpath("//h2[@class='product-title']//a[contains(text(),'Build your own computer')]"));
        buildOwnComputer.click();

        WebElement hddGB = driver.findElement(By.xpath("//input[@id='product_attribute_3_7']"));
        hddGB.click();
        Assert.assertTrue(hddGB.isSelected());

        WebElement addToCart = driver.findElement(By.id("add-to-cart-button-1"));
        addToCart.click();

        WebElement confirm = driver.findElement(By.xpath("//p[@class='content']"));
        String expectedResult = "The product has been added to your shopping cart";
        String actualResult = confirm.getText();
        Assert.assertEquals(expectedResult, actualResult);
        System.out.println(confirm.getText());
    }

    @After
    public void closeBrowser() {

        driver.quit();

    }
}