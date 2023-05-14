package com.xuecheng.content;

import com.xuecheng.content.config.MultipartSupportConfig;
import com.xuecheng.content.feignclient.MediaServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author: Ricky
 * @date: 2023/5/8
 * @projectname: xuecheng0402
 * @description 测试远程调用媒资服务
 **/

@SpringBootTest
@Slf4j
public class FeignUploadTest {

    @Autowired
    MediaServiceClient mediaServiceClient;

    //远程调用，上传文件
    @Test
    public void test() throws IOException {

        MultipartFile multipartFile = MultipartSupportConfig.getMultipartFile(new File("D:\\test.html"));
        String upload = mediaServiceClient.upload(multipartFile, "course/3.html");
        if (upload == null) {
            log.debug("走了降级逻辑");

        }
    }


}
