package com.yinyu.demo.demo_project.ctrl;

import com.yinyu.demo.demo_project.service.DataGatherService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dg")
public class DataGatherCtrl {

    @Resource
    private DataGatherService dataGatherService;

    @PostMapping("/accept")
    public Object accept(@RequestParam("data") String data) {
        return dataGatherService.accept(data);
    }

    @PostMapping("/consume")
    public Object consume() {
        return dataGatherService.consume();
    }
}
