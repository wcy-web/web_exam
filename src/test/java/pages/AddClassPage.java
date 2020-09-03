package pages;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author 向阳
 * @date 2020/8/26-15:44
 */
public class AddClassPage extends BasePage {
    public AddClassPage(WebDriver driver) {
        super(driver);
    }
    //加入课程
    private By addClassBy=By.xpath("//div[text()='+ 加入课程']");
    public void clickAddClass(){
        click(addClassBy);
    }
    //加课验证码
    private By invitationCodeBy=By.xpath("//input[@placeholder='请输入课程加课验证码']");
    public void sendInvitationCode(String invitationCode){
        sendkeys(invitationCodeBy,invitationCode);
    }
    //加入按钮
    private By addButtonBy=By.xpath("//div[text()='加入课程']/following-sibling::div[2]/ul/li[2]/a");
    public void clickAddButton(){
        click(addButtonBy);
    }
    //报错提示信息
    private By errorMsg=By.xpath("//div[@id='error-tip']/span");
    public String getErrorMsg(){
        return getText(errorMsg);
    }
    //取消按钮
    private By cancelButtonBy=By.xpath("//div[text()='加入课程']/following-sibling::div[2]/ul/li[1]/a");
    public void clickcancelButton(){
        click(cancelButtonBy);
    }
}

