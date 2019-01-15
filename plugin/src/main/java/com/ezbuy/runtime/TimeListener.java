package com.ezbuy.runtime;

import org.gradle.BuildListener;
import org.gradle.BuildResult;
import org.gradle.api.Task;
import org.gradle.api.execution.TaskExecutionListener;
import org.gradle.api.initialization.Settings;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.tasks.TaskState;

import java.util.ArrayList;
import java.util.List;

/**
 * author : yutianran
 * time   : 2019/01/15
 * desc   :
 * version: 1.0
 */
public class TimeListener implements TaskExecutionListener, BuildListener {

    private long lastTime = 0;
    private long spendTime = 0;
    private List<TimeCache> times = new ArrayList<>();

    @Override
    public void beforeExecute(Task task) {
        if (lastTime > 0) {
            spendTime = System.currentTimeMillis() - lastTime;
        }
        lastTime = System.currentTimeMillis();
    }

    @Override
    public void afterExecute(Task task, TaskState taskState) {
        times.add(new TimeCache(spendTime, task.getPath()));
//        task.project.logger.warn "${task.path} spend ${ms}ms"
    }

    @Override
    public void buildFinished(BuildResult result) {
        System.out.println("Task spend time:");
        for (TimeCache time : times) {
            if (time.spendTime >= 50) {
                System.out.println(String.format("%sms %s",time.spendTime ,time.task));
            }
        }
    }

    @Override
    public void buildStarted(Gradle gradle) {
    }

    @Override
    public void projectsEvaluated(Gradle gradle) {
    }

    @Override
    public void projectsLoaded(Gradle gradle) {
    }

    @Override
    public void settingsEvaluated(Settings settings) {
    }

    public static class TimeCache {
        long spendTime;
        String task;

        public TimeCache(long spendTime, String task) {
            this.spendTime = spendTime;
            this.task = task;
        }
    }
}
