package pages;

import common.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 向阳
 * @date 2020/8/26-16:26
 */
public class CommitHomeworkPage extends BasePage {
    public CommitHomeworkPage(WebDriver driver) {
        super(driver);
    }

    //点击加号图标
    private By addIconBy=By.xpath("//div[@id='viewer-handup']/div[2]/div[2]/div/i/div[2]/label");
    public void clickAddIcon(){
        click(addIconBy);
    }
    //点击提交/更新提交
    private By commitButtonBy=By.xpath("//div[@id='viewer-handup']/div[1]/div[1]/a[1]");
    public void clickCommitButton(){
        click(commitButtonBy);
    }
    public void scrollToCommitButtonIntoView(){
        scrollScreen(commitButtonBy);
    }
    //再次点击提交/更新提交
    private By commitAgainBy=By.xpath("//div[@id='viewer-handup']/div[1]/div[1]/a[2]");
    public void clickAgainButton(){
        click(commitAgainBy);
    }

    //点击确定
    private By confirmButtonBy=By.xpath("//a[text()='确定'][@class='sure active']");
    public void clickConfirmButton(){
        click(confirmButtonBy);
    }
    //知道了
    private By knowenBy=By.xpath("//a[text()='知道了']");
    public void clickKnowenButton(){
        click(knowenBy);
    }
    //点击增加留言
    private By addTextareaBy=By.xpath("//span[text()='作业留言：']/following-sibling::span");
    public void scrollToTextareaIntoView(){
        scrollScreen(addTextareaBy);
    }
    public void clickAddTextarea(){
        click(addTextareaBy);
    }
    //留言文本框
    private By messageTextareaBy=By.id("comment");
    public void sendMessage(String message){
        sendkeys(messageTextareaBy,message);
    }
    //保存按钮
    private By saveBy=By.xpath("//a[text()='保存']");
    public void clickSaveButton(){
        click(saveBy);
    }
    //提交作业状态
    private By statusBy=By.xpath("//div[@class='status fr']/span");
    public String getStatus(){
       return getText(statusBy);
    }
    //获取上传文件的文件名
    private By homeworkFile=By.xpath("//div[@class='file-cont fl']/h3/a");
    //提交作业后的状态
    private By commitStatusBy=By.xpath("//div[@id='viewer-handup']/div[1]/div[2]/span");
    public String getCommitStatus(){
       return getText(commitStatusBy);
    }

    public List<String> getHomeworkFileName(int fileSize){
       // getElementValue(homeworkFile,"title");
        List<WebElement> elements = driver.findElements(homeworkFile);
        List<WebElement> elementslist=new ArrayList();
        for (int i = elements.size()-1; i >=0; i--) {
            elementslist.add(elements.get(i));
            if(elementslist.size()>fileSize-1){
                break;
            }
        }
        List<String> fileNameList=new ArrayList();
        for (int i = 0; i < elementslist.size(); i++) {
            WebElement webElement = elementslist.get(i);
            fileNameList.add(webElement.getAttribute("title"));
        }
       return fileNameList;
    }
}
