package com.common.listener;

import com.common.dao.entity.task.Task;
import org.json.simple.JSONArray;

/**
 * Created by root on 12/27/16.
 */
public class Wrapper implements Comparable<Wrapper>{
    private Task task;
    private JSONArray jsonarr;
    public Wrapper(){}
    public Wrapper(Task task,JSONArray jsonarr){
        this.task=task;
        this.jsonarr=jsonarr;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public JSONArray getJsonArr() {
        return jsonarr;
    }

    public void setJsonArr(JSONArray jsonarr) {
        this.jsonarr = jsonarr;
    }

    @Override
    public int compareTo(Wrapper o) {
        if(o.task.getId()>this.task.getId()) return -1;
        if(o.task.getId()<this.task.getId()) return 1;
        return 0;
    }
}
