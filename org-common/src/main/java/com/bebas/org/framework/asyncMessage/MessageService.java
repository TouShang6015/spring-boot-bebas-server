package com.bebas.org.framework.asyncMessage;

/**
 * 异步消息发送接口
 * @author wuhao
 * @date 2022/8/25 17:53
 */
public interface MessageService {

    /**
     * send发送消息
     * @param channelId 通道id
     */
    void send(String channelId, String data);

}
