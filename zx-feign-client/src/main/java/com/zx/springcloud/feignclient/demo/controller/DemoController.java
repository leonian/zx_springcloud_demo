package com.zx.springcloud.feignclient.demo.controller;

import com.zx.springcloud.feignclient.demo.feign.DemoFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * demo示例
 * @author Nian.Li
 * @version 1.0
 * @date 2018-05-29
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private DemoFeignClient demoFeignClient;

    @GetMapping("getData")
    public String getData(String id) {
        logger.info("/demo/getData id:{}",id);
        String result = demoFeignClient.getData(id);
        logger.info("调用feign服务方的结果:{}",result);

        return "调用feign服务方的数据是："+result;
    }


    /**
     * 用于测试zipkin的
     * @param id
     * @return
     */
    @GetMapping("getZipkinData")
    public String getZipkinData(String id) {
        logger.info("/demo/getZipkinData id:{}",id);

        String result = demoFeignClient.getZipkinData(id);
        logger.info("/demo/getZipkinData 调用feign服务方的结果:{}",result);

        return "调用服务:"+applicationName+",端口:"+port+",参数id:"+id+"; >>>>>> feign调用返回的结果:"+result;
    }
}
