package com.omr.solutions.utils.task;

/**
 * Created by omar on 30/07/15.
 */
public interface OnTaskCompleted {

    void onTaskCompleted(String tagOrigen,Object object);
    void onTaskCanceled(String tagOrigen);

}
