package com.ezbuy.runtime;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * author : yutianran
 * time   : 2019/01/15
 * desc   :
 * version: 1.0
 */
public class MainPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        System.out.println("========================");
        System.out.println("Hello ezgradle MainPlugin!");
        System.out.println("========================");

        project.getGradle().addListener(new TimeListener());

        AppExtension android = project.getExtensions().getByType(AppExtension.class);
        android.registerTransform(new TransformImpl());
    }
}
