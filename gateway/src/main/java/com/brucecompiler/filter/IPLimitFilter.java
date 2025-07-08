package com.brucecompiler.filter;

import com.brucecompiler.enums.ResponseStatusEnum;
import com.brucecompiler.result.Result;
import com.brucecompiler.utils.RedisOperator;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class IPLimitFilter implements GlobalFilter, Ordered {

    private final RedisOperator redisOperator;

    @Autowired
    public IPLimitFilter(RedisOperator redisOperator) {
        this.redisOperator = redisOperator;
    }

    /**
     * 需求:
     * 判断某个请求的ip在20秒内的请求次数是否超过3次
     * 如果超过3次，则限制访问30秒
     * 等待30秒静默后, 才能够继续恢复访问
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("IPLimitFilter, order: 1");
        if(true) {
            // 终止请求, 返回错误信息
            return renderMsg(exchange, ResponseStatusEnum.FAILED);
        }
        // 默认放行请求到后续的路由(服务)
        return chain.filter(exchange);
    }

    private Mono<Void> renderMsg(ServerWebExchange exchange, ResponseStatusEnum status) {
        // 1. 获得相应的response
        ServerHttpResponse response = exchange.getResponse();

        // 2. 构建一个jsonResult
        Result result = Result.exception(status);

        // 3. 设置Content-Type, 只在不存在的时候设置
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        // 4. 修改response的状态码
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);

        // 5. 转换json并且像response中写入数据
        String resultJson = new Gson().toJson(result);

        DataBuffer buffer = response.bufferFactory().wrap(resultJson.getBytes(StandardCharsets.UTF_8));

        return response.writeWith(Mono.just(buffer));
    }

    // 过滤器的顺序, 数字越小, 优先级越大
    @Override
    public int getOrder() {
        return 1;
    }
}
