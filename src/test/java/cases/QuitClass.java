package cases;

import common.BaseCase;
import common.Contains;
import listener.retryListener;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AddClassPage;
import pages.HomeworkIndexPage;
import pages.LoginPage;

/**
 * @author 向阳
 * @date 2020/8/29-12:56
 */
public class QuitClass extends BaseCase {
    @BeforeTest
    public void quitClassInit() throws InterruptedException {
        driver.get("https://www.ketangpai.com/Main/index.html");
        Thread.sleep(2000);
        HomeworkIndexPage indexPage=new HomeworkIndexPage(driver);
        indexPage.clickMore(Contains.CLASS_NAME);
        Thread.sleep(2000);
        indexPage.clickQuitClass(Contains.CLASS_NAME);
        Thread.sleep(2000);
    }

    @Test()
//    退课
    public void quitClassTrue() throws InterruptedException {
        HomeworkIndexPage indexPage=new HomeworkIndexPage(driver);
        indexPage.sendPasswordToQuit(Contains.PASS);
        indexPage.clickQuitButton();
        Thread.sleep(2000);
        //退课成功页面提示
        Assert.assertEquals(indexPage.getResultMsg(),"课程退课成功");
    }
    @Test(retryAnalyzer = retryListener.class,dataProvider = "getData")
    public void quitClassFalse(String password) throws InterruptedException {
        HomeworkIndexPage indexPage=new HomeworkIndexPage(driver);
        indexPage.sendPasswordToQuit(password);
        indexPage.clickQuitButton();
        //报错断言：密码错误
        Assert.assertEquals(indexPage.getQuitErrorMsg(),"密码错误");
        Thread.sleep(2000);
    }
    @DataProvider
    public Object[] getData(){
        Object[] data={"中文111","@111","1","1234567890111111","111 111","abc12345678"};
        return data;
    }
}
