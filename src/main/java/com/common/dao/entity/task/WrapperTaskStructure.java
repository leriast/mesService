package com.common.dao.entity.task;

/**
 * Created by root on 12/28/16.
 */
public class WrapperTaskStructure {
    public WrapperTaskStructure(){}
    private Task task;
    private Structure structure;


    public WrapperTaskStructure(Task task, Structure structure) {
        this.task = task;
        this.structure = structure;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Structure getStructure() {
        return structure;
    }

    public void setStructure(Structure structure) {
        this.structure = structure;
    }
}
