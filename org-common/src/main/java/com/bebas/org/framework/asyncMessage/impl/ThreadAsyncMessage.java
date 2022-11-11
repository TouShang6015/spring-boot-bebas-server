package com.bebas.org.framework.asyncMessage.impl;

import com.bebas.org.framework.asyncMessage.MessageService;
import com.bebas.org.framework.asyncMessage.annotation.MessageListener;
import com.org.bebasWh.core.spring.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 消息发送实现类（jvm线程）
 *
 * @author Wuhao
 * @date 2022/8/25 22:07
 */
@Component
@Slf4j
public class ThreadAsyncMessage implements MessageService {

    /**
     * 操作延迟 毫秒
     */
    private static final int OPERATE_DELAY_TIME = 50;

    @Resource
    private ScheduledExecutorService scheduledExecutorService;

    /**
     * send发送消息
     *
     * @param channelId 通道id
     */
    @Override
    public void send(String channelId, String data) {
        try {
            Map<Object, Class<?>> contextAnnotationClassByType = SpringUtils.getContextAnnotationClassByType(Service.class);
            for (Object bean : contextAnnotationClassByType.keySet()) {
                Class<?> aClass = contextAnnotationClassByType.get(bean);
                Method[] methods = aClass.getMethods();
                for (Method method : methods) {
                    //获取指定方法上的注解的属性
                    MessageListener annotation = method.getAnnotation(MessageListener.class);
                    if (annotation != null) {
                        if (annotation.value().equals(channelId)) {
                            scheduledExecutorService.schedule(() -> {
                                try {
                                    log.info("异步任务执行 - {}", Thread.currentThread().getName());
                                    method.invoke(bean, data.getBytes(StandardCharsets.UTF_8));
                                } catch (IllegalAccessException | InvocationTargetException e) {
                                    e.printStackTrace();
                                }
                            }, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异步任务执行失败");
        }
    }

}
