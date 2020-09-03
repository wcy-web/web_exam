package pages;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * @author 向阳
 * @date 2020/8/26-17:30
 */
public class HomeworkIndexPage extends BasePage {
    public HomeworkIndexPage(WebDriver driver) {
        super(driver);
    }
    //点击作业标题
    private By homeworkTitleBy=By.xpath("//a[text()='作业']");
    public void clickHomeworkTitle(){
        click(homeworkTitleBy);
    }
    //点击上传作业
    //private By uploadHomeworkBy =By.xpath("//a[text()='web自动化测试4']/parent::h3/parent::div/following-sibling::div");
    public void scrollToUploadIntoView(String homeworkTitle){
        JavascriptExecutor jse=(JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("//a[text()='" + homeworkTitle + "']/parent::h3/parent::div/following-sibling::div"));
        jse.executeScript("arguments[0].scrollIntoView(0)",element);
    }
    public void clickUploadHomework(String homeworkTitle){
        driver.findElement(By.xpath("//a[text()='"+homeworkTitle+"']/parent::h3/parent::div/following-sibling::div")).click();
    }
    //作业提交后的状态
    public String getStatus(String homeworkTitle){
        WebElement element = driver.findElement(By.xpath("//a[text()='" + homeworkTitle + "']/parent::h3/parent::div/following-sibling::div/a"));
        return element.getText();
    }
    //私信
    private By privateLettersBy=By.xpath("//div[@class='privateLetter']/a");
    public void clickPrivateLetters(){
        click(privateLettersBy);
    }
    //加入的课程名称是否可见
    public boolean classnameIsVisible(String classname){
            return driver.findElement(By.xpath("//a[text()='"+classname+"']")).isDisplayed();
    }
    //点击课程标题
    public void clickClassTitle(String classname){
        driver.findElement(By.xpath("//a[text()='"+classname+"']")).click();
    }
    //点击更多按钮
    public void clickMore(String classname){
        driver.findElement(By.xpath("//a[text()='"+classname+"']/parent::strong/following-sibling::a/span")).click();
    }
    //点击退课按钮
    public void clickQuitClass(String classname){
        driver.findElement(By.xpath("//a[text()='"+classname+"']/parent::strong/following-sibling::ul/li[1]")).click();
    }
    //退课时输入登录密码进行验证
    private By passwordToQuitBy=By.xpath("//input[@placeholder='请输入登录密码验证']");
    public void sendPasswordToQuit(String password){
        sendkeys(passwordToQuitBy,password);
    }
    //退课按钮
    private By quitButtonBy=By.xpath("//div[@class='deletekt']/div[4]/ul/li[2]/a");
    public void clickQuitButton(){
        click(quitButtonBy);
    }
    //退课时报错信息
    private By quitErrorMsg=By.xpath("//div[@id='error-tip']/span");
    public String getQuitErrorMsg(){
        return getText(quitErrorMsg);
    }
    //加入课堂/退课成功后的提示信息
    private By resultMsg = By.xpath("//div[@id='show-tip']/span");
    public String getResultMsg(){
        return getText(resultMsg);
    }
}
