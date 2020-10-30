package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public abstract class AbstractServiceTest {
    private static final Logger log = LoggerFactory.getLogger(AbstractServiceTest.class);
    private static StringBuilder results = new StringBuilder();
    private static final String RESET = "\033[0m";  // Text Reset
    private static final String GREEN_BOLD = "\033[1;32m";  // GREEN

    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String testMethodName = description.getMethodName();
            String result = String.format("\n" + GREEN_BOLD + "%-25s %-5d ms" + RESET,
                    testMethodName, TimeUnit.NANOSECONDS.toMillis(nanos));
            log.info(result);
            results.append(result);
        }
    };

    @AfterClass
    public static void printResults() {
        log.info(String.valueOf(results));
        results.setLength(0);
    }
}
