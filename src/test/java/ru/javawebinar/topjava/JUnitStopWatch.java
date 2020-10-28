package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;

public class JUnitStopWatch extends Stopwatch {

    @Override
    protected void finished(long nanos, Description description) {
        String testMethodName = description.getMethodName();
        System.out.printf("Test %s finished, spent %d microseconds%n",
                testMethodName, TimeUnit.MICROSECONDS.toMillis(nanos));
    }
}
