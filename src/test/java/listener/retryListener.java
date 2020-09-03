package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * @author 向阳
 * @date 2020/9/2-20:20
 */
public class retryListener implements IRetryAnalyzer {
    int retryMaxCount=2;
    int currentCount=0;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if(currentCount<retryMaxCount){
            currentCount++;
            return true;
        }else {
            return false;
        }
    }
}
