package org.geno.com.common;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Test retry analyzer.
 */

public class Analyzer implements IRetryAnalyzer {
    private int count = 0;
    private int maxCount = 0;

    @Override
    public boolean retry(ITestResult result) {

        System.out.println("running retry logic for '" + result.getName() +
                "' on class " + this.getClass().getName());
        if (count < maxCount) {
            System.out.println("RETRY COUNT> " + count);
            System.out.println("RETRY MAX COUNT> " + maxCount);
            result.getTestContext().getSkippedTests().removeResult(result.getMethod());
            count++;
            return true;
        }
        return false;
    }
}
