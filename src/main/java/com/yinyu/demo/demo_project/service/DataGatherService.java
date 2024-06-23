package com.yinyu.demo.demo_project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.LinkedList;

@Slf4j
@Service
public class DataGatherService {
    public static LinkedList<String> queue = new LinkedList<>();
    private boolean acceptNew = true;

    public Object accept(String data) {
        if (acceptNew) {
            queue.add(data);
            return new Date();
        }
        else {
            return "No more data accepted.";
        }
    }

    public Object consume() {
        if (!queue.isEmpty()) {
            return queue.removeFirst();
        }
        else {
            return "No more data.";
        }
    }

    @PreDestroy
    public void close() {
        try {
            if (log.isDebugEnabled()) {
                log.debug("close called");
            }

            acceptNew = false;

            while (!queue.isEmpty()) {
                log.warn("waiting for close, queue.size = {}", queue.size());
                Thread.currentThread().sleep(3000);
            }

            if (log.isDebugEnabled()) {
                log.debug("close finished");
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
