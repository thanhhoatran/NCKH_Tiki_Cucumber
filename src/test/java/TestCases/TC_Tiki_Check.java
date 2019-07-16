package TestCases;

import Actions.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class TC_Tiki_Check {
    WebDriver driver;
    Properties user = new Properties();
    InputStream input = null;
    String dynamicName = "ThanhHoa Tran";

    @Given("^A user is on website with \"(.*?)\", url: \"(.*?)\" and size\\((\\d+),(\\d+)\\)$")
    public void a_user_is_on_website_with_url_and_size(String browser, String url, int w, int h) throws Throwable {
        switch (browser)
        {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\drivers\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications"); //vô hiệu hóa thông báo chrome
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", ".\\src\\main\\resources\\drivers\\geckodriver.exe");
                FirefoxOptions option = new FirefoxOptions();
                driver = new FirefoxDriver(option);
                break;
            case "IE":
                System.setProperty("webdriver.edge.driver", ".\\src\\main\\resources\\drivers\\MicrosoftWebDriver.exe");
                driver = new EdgeDriver();
                break;
        }

        driver.get(url);

        Dimension d = new Dimension(w,h); //Resize current window to the set dimension
        driver.manage().window().setSize(d);
        // driver.manage().window().maximize();
        try
        {
            input = new FileInputStream("src/main/resources/testData/user.txt");
            user.load(input);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    @When("^Hover on \"(.*?)\" field and click on \"(.*?)\" link\\.$")
    public void hover_on_field_and_click_on_link(String arg1, String arg2) throws Throwable {
       // Thread.sleep(2000);
        TikiLoginAction.clickOnDangNhap1(driver);
        TikiLoginAction.clickOnDangNhap2(driver);
    }

    @When("^Enter all valid data in all field of Login form\\.$")
    public void enter_all_valid_data_in_all_field_of_Login_form() throws Throwable {
        Thread.sleep(2000);
        TikiLoginAction.enterEmailAndPassword(driver,user.getProperty("userid"),user.getProperty("password"));
    }

    @When("^Click on \"(.*?)\" button\\.$")
    public void click_on_button(String arg1) throws Throwable {
        Thread.sleep(2000);
        TikiLoginAction.clickOnLoginButton(driver);
    }

    @When("^Enter a valid data into Search field\\.$")
    public void enter_a_valid_data_into_Search_field() throws Throwable {
        Thread.sleep(2000);
        TikiSearchAction.enterSearch(driver,"laptop");
    }
    @When("^Click on \"(.*?)\" button of search form\\.$")
    public void click_on_button_of_search_form(String arg1) throws Throwable {
        Thread.sleep(2000);
        TikiSearchAction.clickSearch(driver);
    }
    @When("^Click on a product\\.$")
    public void click_on_a_product() throws Throwable {
        //Thread.sleep(2000);
        TikiAddToCartAction.clickProduct(driver);
//        ArrayList<String> tags = new ArrayList<String>(driver.getWindowHandles());
//        driver.switchTo().window(tags.get(1));
    }
    @When("^Click on \"(.*?)\" button of product\\.$")
    public void click_on_button_of_product(String arg1) throws Throwable {
        Thread.sleep(2000);
        TikiAddToCartAction.clickAdd(driver);
    }
    @When("^Click on \"(.*?)\" button of account\\.$")
    public void click_on_button_of_account(String arg1) throws Throwable {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@id='header-user']//b[text()='Chào ' and text()='"+ dynamicName +"']")).click();
        TikiLogoutAction.clickLogout(driver);
    }
    @When("^User login again\\.$")
    public void user_login_again() throws Throwable {
        Thread.sleep(2000);
        TikiLoginAction.clickOnDangNhap1(driver);
        TikiLoginAction.clickOnDangNhap2(driver);
        Thread.sleep(2000);
        TikiLoginAction.enterEmailAndPassword(driver,user.getProperty("userid"),user.getProperty("password"));
        TikiLoginAction.clickOnLoginButton(driver);
    }
    @When("^Click on \"(.*?)\" menu\\.$")
    public void click_on_menu(String arg1) throws Throwable {
        Thread.sleep(2000);
        TikiLogoutAction.clickCart(driver);
    }
/*    @Then("^\"(.*?)\" popup is displayed\\.$")
    public void popup_is_displayed(String arg1) throws Throwable {
        Thread.sleep(2000);
        String AddToCartXpath = "//p[contains(.,\"Thêm vào giỏ hàng thành công!\")]";
        boolean isAddToCart = driver.findElement(By.xpath(AddToCartXpath)).isDisplayed();
        Assert.assertEquals(true, isAddToCart);
    }
    */
    @Then("^The products of cart still displayed\\.$")
    public void the_products_of_cart_still_displayed() throws Throwable {
        Thread.sleep(2000);
        String LogoutCheckXpath = "//a[@href=\"https://tiki.vn/laptop-dell-latitude-5490-42lt540012-p12341341.html?src=cart-page&spid=14921472\"]";
        boolean isLogoutCheck = driver.findElement(By.xpath(LogoutCheckXpath)).isDisplayed();
        Assert.assertEquals(true, isLogoutCheck);
    }
    @When("^Delete\\.$")
    public void delete() throws Throwable {
        Thread.sleep(2000);
        TikiAddToCartAction.clickDeleteProduct(driver);
    }

    //
    @When("^Click on \"(.*?)\" button of menu\\.$")
    public void click_on_button_of_menu(String arg1) throws Throwable {
        Thread.sleep(2000);
        TikiFollowAction.clickOnTheoDoi(driver);
    }
}
