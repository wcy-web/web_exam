package pages;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * @author 向阳
 * @date 2020/8/26-21:46
 */
public class PrivateLettersPage extends BasePage {
    public PrivateLettersPage(WebDriver driver) {
        super(driver);
    }
    //搜索收件人
    private By searchPeopleBy=By.xpath("//input[@placeholder='搜索学号、姓名']");
    public void clickSendSearchPeople(){
        click(searchPeopleBy);
    }
    public void sendSearchPeople(String peopleName){
        sendkeys(searchPeopleBy,peopleName);
    }
    //选择收件人
    public void checkPeople(String peopleName){
        click(By.xpath("//p[text()='"+peopleName+"']"));
    }
    //发送的内容
    private By messageTextareaBy=By.xpath("//textarea[@class='ps-container']");
    public void sendMessage(String message){
        sendkeys(messageTextareaBy,message);
    }
    //发送按钮
    private By sendButtonBy=By.xpath("//a[text()='发送']");
    public void clickSendButton(){
        click(sendButtonBy);
    }
    //错误信息
    private By errorMsgBy=By.xpath("//span[text()='私信内容不能为空']");
    public String getErrorMsg(){
        return getText(errorMsgBy);
    }
    //自己发送的私信内容
    private By lettersBySelf=By.xpath("//div[@class='self']/div");
    public String getLetters(){
        List<WebElement> elements = driver.findElements(lettersBySelf);
        WebElement webElement = elements.get(elements.size() - 1);
        String text = webElement.getText();
        return text;
    }
}
