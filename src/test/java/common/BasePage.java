package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * @author 向阳
 * @date 2020/8/26-15:15
 */
public class BasePage {
    public Logger logger=Logger.getLogger(BasePage.class);
    public static WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver=driver;
    }
    //等待元素可见
    public WebElement waitElementVisibility(By by){
        WebDriverWait wait=new WebDriverWait(driver,10);
        WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return webElement;
    }
    //等待元素可被点击
    public  WebElement waitElementToBeClickable(By by){
        WebDriverWait wait =new WebDriverWait(driver,10);
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(by));
        return webElement;
    }
    //封装输入操作
    public  void sendkeys(By by,String data){
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(data);
        logger.info("往元素["+by+"]中输入了数据["+data+"]");
    }
    //封装点击操作
    public  void click(By by){
        driver.findElement(by).click();
        logger.info("点击了元素["+by+"]");
    }
    //封装获取文本值方法
    public  String getText(By by){
        String data=driver.findElement(by).getText();
        logger.info("获取元素【"+by+"】的文本值【"+data+"】");
        return data;
    }
    //滑动页面
    public  void scrollScreen(By by){
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        WebElement element = driver.findElement(by);
        jse.executeScript("arguments[0].scrollIntoView(0)",element);
    }
    //获取元素的属性值
    public String getElementValue(By by,String attributeName){
        String attribute = waitElementVisibility(by).getAttribute(attributeName);
        logger.info("获取元素【"+by+"】的属性值为【"+attribute+"】");
        return attribute;
    }
    //输入按键操作
    public void typeKey(By by, Keys keys){
        waitElementVisibility(by).sendKeys(keys);
        logger.info("输入按键操作【"+keys.name()+"】");
    }
}
