package pages;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author 向阳
 * @date 2020/8/26-15:06
 */
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    private By accountBy=By.name("account");
    private By passBy=By.name("pass");
    private By loginButtonBy=By.xpath("//input[@name='pass']/parent::div/following-sibling::a[1]");

    public void sendAccount(String account){
        sendkeys(accountBy,account);
    }

    public void sendPass(String pass){
        sendkeys(passBy,pass);
    }

    public void clickLoginButton(){
        click(loginButtonBy);
    }
}
