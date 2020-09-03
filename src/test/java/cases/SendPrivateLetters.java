package cases;

import common.BaseCase;
import common.Contains;
import listener.retryListener;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomeworkIndexPage;
import pages.PrivateLettersPage;

/**
 * @author 向阳
 * @date 2020/8/26-21:45
 */
public class SendPrivateLetters extends BaseCase {
    @BeforeTest
    public void openSendLettersPage(){
        HomeworkIndexPage homeworkIndexPage=new HomeworkIndexPage(driver);
        homeworkIndexPage.clickPrivateLetters();
        switchHandle("私信-简单好用的互动课堂管理工具");
    }
    //私信内容不为空用例
    @Test(retryAnalyzer = retryListener.class)
    public void sendPrivateLettersTrue() throws Exception {
        Thread.sleep(3000);
        PrivateLettersPage privateLettersPage=new PrivateLettersPage(driver);
       // switchHandle("私信-简单好用的互动课堂管理工具");
        privateLettersPage.clickSendSearchPeople();
        privateLettersPage.sendSearchPeople(Contains.SEND_MESSAGE_PEOPLE);
        privateLettersPage.checkPeople(Contains.SEND_MESSAGE_PEOPLE);
        privateLettersPage.sendMessage(Contains.SENT_MESSAGE);
        privateLettersPage.clickSendButton();
        Thread.sleep(2000);
        //发送私信成功断言：自己发送信息的最后一条，与发送内容一致
        Assert.assertEquals(privateLettersPage.getLetters(),Contains.SENT_MESSAGE);
    }
    //私信内容为空用例
    @Test(retryAnalyzer = retryListener.class)
    public void sendPrivateLettersFalse() throws Exception {
        Thread.sleep(3000);
        PrivateLettersPage privateLettersPage=new PrivateLettersPage(driver);
       // switchHandle("私信-简单好用的互动课堂管理工具");
        privateLettersPage.clickSendSearchPeople();
        privateLettersPage.sendSearchPeople(Contains.SEND_MESSAGE_PEOPLE);
        privateLettersPage.checkPeople(Contains.SEND_MESSAGE_PEOPLE);
        privateLettersPage.sendMessage("");
        privateLettersPage.clickSendButton();
        Thread.sleep(1000);
        Assert.assertEquals(privateLettersPage.getErrorMsg(),"私信内容不能为空");
    }
//    @AfterTest
//    public void closeSendLettersPage(){
//        driver.close();
//    }
}
