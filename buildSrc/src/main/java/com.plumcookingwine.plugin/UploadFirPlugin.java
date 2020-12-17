package com.plumcookingwine.plugin;

import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.Task;
import org.gradle.api.file.FileCollection;

import java.io.File;
import java.util.Set;
import java.util.function.BiConsumer;

/**
 * Created by kangf on 2020/12/10.
 */
public class UploadFirPlugin implements Plugin<Project> {

    @Override
    public void apply(Project target) {

        Project parent = target.getParent();
//
        if (parent == null) {
            throw new GradleException("该插件只能在子module中使用");
        }
//
//        System.out.println(parent.getName());
//
//        Set<Task> taskSet = parent.getTasksByName("assembleDebug", true);

        parent.getAllTasks(true).forEach((project, tasks) -> {

            for (Task task : tasks) {
                System.out.println("task ============= " + project.getName() + "=======" + task.getName());

            }


        });
//
//
//
//        if (taskSet.isEmpty()) {
//
//            throw new GradleException("assembleDebug任务不存在");
//        }

//        for (Task task : taskSet) {
//
//            System.out.println("taskName ==== " + task.getProject().getName() + "========" + task.getName());
//
//            task.doLast(t -> {
//
//                FileCollection files = t.getOutputs().getFiles();
//
//                System.out.println("files ==== " + files);
//
//                for (File file : files) {
//
//                    System.out.println("file ======== " + file.getName());
//
//                }
//            });
//        }
    }
}
