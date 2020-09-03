package cases;


import common.BaseCase;
import common.Contains;
import listener.retryListener;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AddClassPage;
import pages.HomeworkIndexPage;
import pages.LoginPage;

/**
 * @author 向阳
 * @date 2020/8/26-15:32
 */
public class AddClassroom extends BaseCase {
    @BeforeTest
    public void init(){
        login();
        try {
            Thread.sleep(2000);
            AddClassPage addClassPage=new AddClassPage(driver);
            addClassPage.clickAddClass();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test()
    //加入课堂
    public void addClassTrue() throws InterruptedException {
        AddClassPage addClassPage=new AddClassPage(driver);
        addClassPage.sendInvitationCode(Contains.INVITATION_CODE);
        addClassPage.clickAddButton();
        HomeworkIndexPage homeworkIndexPage=new HomeworkIndexPage(driver);
        //加入课程成功提示信息
        Thread.sleep(500);
        Assert.assertEquals(homeworkIndexPage.getResultMsg(),"加入课堂成功");

        String currentUrl = driver.getCurrentUrl();
        //当前页面的url是否为首页的url
        Assert.assertEquals(currentUrl,"https://www.ketangpai.com/Main/index.html");

        //判断新加入的课堂名称是否可见
        Thread.sleep(2000);
        Assert.assertTrue(homeworkIndexPage.classnameIsVisible(Contains.CLASS_NAME));
    }
    @Test(retryAnalyzer = retryListener.class,dataProvider = "getData_1")
    //加入课堂
    public void addClassFalse_001(String invitationCode) throws InterruptedException {
        Thread.sleep(2000);
        AddClassPage addClassPage=new AddClassPage(driver);
        addClassPage.sendInvitationCode(invitationCode);
        addClassPage.clickAddButton();
        Thread.sleep(1000);
        Assert.assertEquals(addClassPage.getErrorMsg(),"加课验证码必须是6位字符");

    }
    @Test(retryAnalyzer = retryListener.class,dataProvider = "getData_2")
    //加入课堂
    public void addClassFalse_002(String invitationCode) throws InterruptedException {
        Thread.sleep(2000);
        AddClassPage addClassPage=new AddClassPage(driver);
        addClassPage.sendInvitationCode(invitationCode);
        addClassPage.clickAddButton();
        Thread.sleep(1000);
        Assert.assertEquals(addClassPage.getErrorMsg(),"加课码不能为空");

    }
    @Test(retryAnalyzer = retryListener.class,dataProvider = "getData_3")
    //加入课堂
    public void addClassFalse_003(String invitationCode) throws InterruptedException {
        Thread.sleep(3000);
        AddClassPage addClassPage=new AddClassPage(driver);
        addClassPage.sendInvitationCode(invitationCode);
        addClassPage.clickAddButton();
        Thread.sleep(1000);
        Assert.assertEquals(addClassPage.getErrorMsg(),"该加课码不存在或者已经失效");

    }
//    @Test
//    public void addClassTwice() throws InterruptedException {
//        AddClassPage addClassPage=new AddClassPage(driver);
//        addClassPage.clickAddClass();
//        Thread.sleep(2000);
//        addClassPage.sendInvitationCode(Contains.INVITATION_CODE);
//        addClassPage.clickAddButton();
//        Thread.sleep(1000);
//        Assert.assertEquals(addClassPage.getErrorMsg(),"你已经选过此课程");
//        addClassPage.clickcancelButton();
//    }
    @DataProvider
    public Object[] getData_1(){
        Object[] data={"123"};
        return data;
    }
    @DataProvider
    public Object[] getData_2(){
        Object[] data={"      "};
        return data;
    }
    @DataProvider
    public Object[] getData_3(){
        Object[] data={"1234567","111111"};
        return data;
    }

    //登录
    public void login(){
        openBrowser("chrome");
        browserMaxSize();
        driver.get(Contains.URL);
        LoginPage loginPage=new LoginPage(driver);
        loginPage.sendAccount(Contains.ACCOUNT);
        loginPage.sendPass(Contains.PASS);
        loginPage.clickLoginButton();
    }
}
