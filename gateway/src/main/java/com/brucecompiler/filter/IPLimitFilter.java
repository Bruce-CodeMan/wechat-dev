package com.brucecompiler.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IPLimitFilter {

    /**
     * 需求:
     * 判断某个请求的ip在20秒内的请求次数是否超过3次
     * 如果超过3次，则限制访问30秒
     * 等待30秒静默后, 才能够继续恢复访问
     */

}
