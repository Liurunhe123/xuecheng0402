package com.xuecheng.content.feignclient;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author: Ricky
 * @date: 2023/5/8
 * @projectname: xuecheng0402
 * @description TODO
 **/
public class MediaServiceClientFallback implements MediaServiceClient{
    @Override
    public String upload(MultipartFile filedata, String objectName) throws IOException {
        return null;
    }
}
