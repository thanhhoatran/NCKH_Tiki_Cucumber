package Actions;

import Pages.TikiFollowPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TikiFollowAction {
    public static  void clickOnTheoDoi(WebDriver driver)
    {
        driver.findElement(By.xpath(TikiFollowPage.btnTheodoi)).click();
    }
}
