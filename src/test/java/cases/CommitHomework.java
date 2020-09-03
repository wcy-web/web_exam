package cases;

import common.BaseCase;
import common.Contains;
import listener.retryListener;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.CommitHomeworkPage;
import pages.HomeworkIndexPage;


import java.util.List;

/**
 * @author 向阳
 * @date 2020/8/26-17:27
 */
public class CommitHomework extends BaseCase {
    Logger logger= Logger.getLogger(CommitHomework.class);
    @BeforeTest
    public void commitHomeworkInit() throws InterruptedException {
        //切换页面句柄，到课堂首页
        switchHandle("课堂-简单好用的互动课堂管理工具");
        System.out.println(driver.getWindowHandle());
        HomeworkIndexPage homeworkIndexPage=new HomeworkIndexPage(driver);
        Thread.sleep(2000);
        homeworkIndexPage.clickClassTitle(Contains.CLASS_NAME);
        Thread.sleep(2000);
        homeworkIndexPage.clickHomeworkTitle();
    }

    @DataProvider
    public Object[] getFile(){
        Object[] files={"第二题.txt","1.png","cat.jpg","1.rar","1.xls"};
        return files;
    }
    @Test(retryAnalyzer = retryListener.class,dataProvider = "getFile")
    //上传一个文件
    public void CommitHomework_001(String fileName) throws Exception {
        Thread.sleep(1000);
        HomeworkIndexPage homeworkIndexPage=new HomeworkIndexPage(driver);
        homeworkIndexPage.scrollToUploadIntoView(Contains.HOMEWORK_TITLE);
        homeworkIndexPage.clickUploadHomework(Contains.HOMEWORK_TITLE);
        CommitHomeworkPage commitHomeworkPage=new CommitHomeworkPage(driver);
        Thread.sleep(2000);
        commitHomeworkPage.clickCommitButton();
        commitHomeworkPage.clickConfirmButton();
        commitHomeworkPage.clickAddIcon();
        Thread.sleep(1000);
        Runtime runtime = Runtime.getRuntime();

        String sendCMD=String.format("src\\test\\resources\\loadHomework1.exe %s",fileName);
        //通过autoIt上传文件
        runtime.exec(sendCMD);
        Thread.sleep(8000);
        commitHomeworkPage.scrollToTextareaIntoView();
        commitHomeworkPage.clickAddTextarea();
        long l = System.currentTimeMillis();
        Thread.sleep(1000);
        commitHomeworkPage.sendMessage(Contains.HOMEWORK_MESSAGE+l);
        commitHomeworkPage.clickSaveButton();
        commitHomeworkPage.scrollToCommitButtonIntoView();
        commitHomeworkPage.clickAgainButton();
        Thread.sleep(2000);
        commitHomeworkPage.clickKnowenButton();
        Thread.sleep(3000);
//        List<String> homeworkFileName = commitHomeworkPage.getHomeworkFileName(1);
//        boolean b=compareList(homeworkFileName,files);
//        Assert.assertTrue(b);
        boolean b=driver.getPageSource().contains(fileName);
        Assert.assertTrue(driver.getPageSource().contains(fileName));

        logger.info("提交作业页面的状态是："+commitHomeworkPage.getCommitStatus());
        Assert.assertEquals(commitHomeworkPage.getCommitStatus(),"已完成");

        browserBack();
        Thread.sleep(2000);

        homeworkIndexPage.scrollToUploadIntoView(Contains.HOMEWORK_TITLE);
        logger.info("作业标题后的提交状态是："+homeworkIndexPage.getStatus(Contains.HOMEWORK_TITLE));
        Assert.assertEquals(homeworkIndexPage.getStatus(Contains.HOMEWORK_TITLE),"已提交");
    }

//    @Test
//    //上传2个文件
//    public void CommitHomework_002() throws Exception {
//        CommitHomeworkPage commitHomeworkPage=new CommitHomeworkPage(driver);
//        commitHomeworkPage.clickCommitButton();
//        commitHomeworkPage.clickConfirmButton();
//        commitHomeworkPage.clickAddIcon();
//        Thread.sleep(1000);
//        Runtime runtime = Runtime.getRuntime();
//        String[] files={"作业.txt","第二题.txt"};
//        //通过autoIt上传文件
//        runtime.exec("src\\test\\resources\\loadHomework1.exe",files);
//        Thread.sleep(2000);
//        commitHomeworkPage.scrollToTextareaIntoView();
//        commitHomeworkPage.clickAddTextarea();
//        commitHomeworkPage.sendMessage(Contains.HOMEWORK_MESSAGE);
//        commitHomeworkPage.clickSaveButton();
//        commitHomeworkPage.scrollToCommitButtonIntoView();
//        commitHomeworkPage.clickAgainButton();
//        Thread.sleep(2000);
//        commitHomeworkPage.clickKnowenButton();
//        List homeworkFileName = commitHomeworkPage.getHomeworkFileName(2);
//        System.out.println(homeworkFileName.toArray());
//        Assert.assertTrue(compareList(homeworkFileName,files));
//    }
//    @Test
//    //上传3个文件
//    public void CommitHomework_003() throws Exception {
//        CommitHomeworkPage commitHomeworkPage=new CommitHomeworkPage(driver);
//        commitHomeworkPage.clickCommitButton();
//        commitHomeworkPage.clickConfirmButton();
//        commitHomeworkPage.clickAddIcon();
//        Thread.sleep(1000);
//        Runtime runtime = Runtime.getRuntime();
//        String[] files={"作业.txt","第二题.txt","1.png"};
//        //通过autoIt上传文件
//        runtime.exec("src\\test\\resources\\loadHomework1.exe",files);
//        Thread.sleep(2000);
//        commitHomeworkPage.scrollToTextareaIntoView();
//        commitHomeworkPage.clickAddTextarea();
//        commitHomeworkPage.sendMessage(Contains.HOMEWORK_MESSAGE);
//        commitHomeworkPage.clickSaveButton();
//        commitHomeworkPage.scrollToCommitButtonIntoView();
//        commitHomeworkPage.clickAgainButton();
//        Thread.sleep(2000);
//        commitHomeworkPage.clickKnowenButton();
//        List homeworkFileName = commitHomeworkPage.getHomeworkFileName(3);
//        System.out.println(homeworkFileName.toArray());
//        Assert.assertTrue(compareList(homeworkFileName,files));
//    }

    public boolean compareList(List list,String[] data){
        boolean b=false;
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).equals(data[i])) {
                b=true;
            }else{
                b=false;
            }
        }
        System.out.println(b);
        return b;
    }
}
