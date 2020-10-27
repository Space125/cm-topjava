package ru.javawebinar.topjava;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.service.MealServiceTest;

import java.util.Map;
import java.util.TreeMap;

public class AbstractTest {
    private static final Logger log = LoggerFactory.getLogger(MealServiceTest.class);
    private static final Map<String, Long> testResults = new TreeMap<>();

    @Rule
    public JUnitStopWatch stopWatch = new JUnitStopWatch() {
        @Override
        protected void finished(long nanos, Description description) {
            testResults.put(description.getMethodName(), nanos / 1000);
            super.finished(nanos, description);
        }
    };

    @AfterClass
    public static void printResults() {
        testResults.forEach((k, v) -> log.info("Method {} finished in {} microseconds", k, v));
    }
}
