
package common;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * @author 向阳
 * @date 2020/9/2-16:38
 */
public class ScreenShotUtils {
    //将截图保存到固定路径、固定名称
    public static void screenshotFile(WebDriver driver){
        TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
        //OutputType.FILE -->返回File类型
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File targerFile = new File("E:\\java19.jpg");
        try {
            FileUtils.copyFile(srcFile,targerFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    public static byte[] screenShotByte(WebDriver driver){
        TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
        //返回字节数组
        byte[] bytes = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        return bytes;
    }
}
