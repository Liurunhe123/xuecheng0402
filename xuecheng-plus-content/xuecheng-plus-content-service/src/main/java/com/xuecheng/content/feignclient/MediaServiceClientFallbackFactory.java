package com.xuecheng.content.feignclient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: Ricky
 * @date: 2023/5/8
 * @projectname: xuecheng0402
 * @description TODO
 **/
@Slf4j
@Component
public class MediaServiceClientFallbackFactory implements FallbackFactory<MediaServiceClient> {
    @Override
    public MediaServiceClient create(Throwable cause) {
        return new MediaServiceClient() {
            @Override
            public String upload(MultipartFile filedata, String objectName) throws IOException {
                //降级方法
                log.debug("调用媒资管理服务上传文件时发生熔断，异常信息:{}", cause.toString(), cause);
                return null;

            }
        };
    }
}
