package listener;

import common.BaseCase;
import common.ScreenShotUtils;
import io.qameta.allure.Attachment;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

/**
 * @author 向阳
 * @date 2020/9/2-16:50
 */
//动态替换每一个@test注解标记的测试方法--全局监听器
public class TestResultListener implements IHookable {
    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        //正常执行测试方法
        iHookCallBack.runTestMethod(iTestResult);
        //iTestResult是测试执行的结果，iTestResult.getThrowable()--》获取结果中的异常
        //测试结果存在异常（包括代码异常、断言失败）
        if(iTestResult.getThrowable() != null){
            //iTestResult.getInstance()方法，可以获取当前测试类的实例对象
            //如果当前执行loginCase,iTestResult.getInstance()返回对象为loginCase对象
            //用父类类型接收子类对象，可以兼容其他测试类
            BaseCase baseCase=(BaseCase) iTestResult.getInstance();
//            ScreenShotUtils.screenshotFile(baseCase.driver);
            byte[] bytes = ScreenShotUtils.screenShotByte(baseCase.driver);
            saveScreenshot(bytes);
        }
    }
    //allure的注解，用于将图片作为附件保存到allure报告中
    //value:附件名    type:附件类型
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }
}
