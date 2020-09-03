package common;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;



import java.util.Set;


/**
 * @author 向阳
 * @date 2020/8/26-14:49
 */
public class BaseCase {
    //log4j日志对象
    public Logger logger=Logger.getLogger(BaseCase.class);
    public static WebDriver driver;

    /**
     * 打开浏览器
     * @param browserName
     */
    public  void openBrowser(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            //chrome浏览器
        System.setProperty("webdriver.chrome.driver","src\\test\\resources\\chromedriver.exe");
        ChromeDriver chromeDriver=new ChromeDriver();
            driver=chromeDriver;
            logger.info("==========打开chrome浏览器==============");
        }else if(browserName.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
            FirefoxDriver firefoxDriver=new FirefoxDriver();
            driver=firefoxDriver;
            logger.info("==========打开firefox浏览器==============");
        }else if(browserName.equalsIgnoreCase("ie")){
            //The path to the driver executable must be set by the webdriver.ie.driver system property
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            System.setProperty("webdriver.ie.driver","src\\test\\resources\\IEDriverServer.exe");
            InternetExplorerDriver internetExplorerDriver=new InternetExplorerDriver(capabilities);
            driver= internetExplorerDriver;
            logger.info("==========打开ie浏览器==============");
        }else{
            logger.info("=============不是常规浏览器===========");
        }
    }
    //浏览器最大化
    public void browserMaxSize(){
        WebDriver.Window window=driver.manage().window();
        window.maximize();
        logger.info("浏览器最大化");
    }
    //浏览器后退
    public void browserBack(){
        WebDriver.Navigation navigate = driver.navigate();
        navigate.back();
        logger.info("浏览器地址后退");
    }
    public void browserForward(){
        WebDriver.Navigation navigate = driver.navigate();
        navigate.forward();
    }
    //切换页面句柄
    public void switchHandle(String titleName){
        Set<String> handles = driver.getWindowHandles();
        String currentHandle = driver.getWindowHandle();
        for (String handle : handles) {
            if(driver.getTitle().equals(titleName)){
                break;
            }else{
                driver.switchTo().window(handle);
            }
        }
        logger.info("切换页面的句柄");
    }
}
