package com.xuecheng.content.feignclient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: Ricky
 * @date: 2023/5/9
 * @projectname: xuecheng0402
 * @description TODO
 **/
@Slf4j
@Component
public class SearchServiceClientFallbackFactory implements FallbackFactory<SearchServiceClient> {
    @Override
    public SearchServiceClient create(Throwable cause) {

        return new SearchServiceClient() {
            @Override
            public Boolean add(CourseIndex courseIndex) {
                //降级方法
                log.debug("调用搜索服务上传索引时发生熔断，异常信息:{}", cause.toString(), cause);
                return false;
            }
        };
    }
}
