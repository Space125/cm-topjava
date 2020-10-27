package ru.javawebinar.topjava;

import org.junit.rules.Stopwatch;
import org.junit.runner.Description;

import java.util.concurrent.TimeUnit;

public class JUnitStopWatch extends Stopwatch {
    private static void logInfo(Description description, String status, long nanos) {
        String testMethodName = description.getMethodName();
        System.out.printf("Test %s %s, spent %d microseconds%n",
                testMethodName, status, TimeUnit.NANOSECONDS.toMicros(nanos));
    }

    @Override
    protected void finished(long nanos, Description description) {
        logInfo(description, "finished", nanos);
    }
}
