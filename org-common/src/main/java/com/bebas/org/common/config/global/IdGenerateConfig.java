package com.bebas.org.common.config.global;

import com.bebas.org.common.constants.StringPool;
import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author WuHao
 * @date 2022/5/11 19:20
 */
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "project.idgenerate")
public class IdGenerateConfig {

    private static final Logger log = LoggerFactory.getLogger(IdGenerateConfig.class);

    /**
     * 机器码,必须由外部设定，最大值 2^WorkerIdBitLength-1
     */
    private static short workerId;
    /**
     * 雪花计算方法 （1-漂移算法|2-传统算法），默认1
     */
    private static short method;
    /**
     * 基础时间（ms单位）,不能超过当前系统时间
     */
    private static long baseTime;
    /**
     * 机器码位长, 默认值6，取值范围 [1, 15]（要求：序列数位长+机器码位长不超过22）
     */
    private static byte workerIdBitLength;
    /**
     * 序列数位长,默认值6，取值范围 [3, 21]（要求：序列数位长+机器码位长不超过22）
     */
    private static byte seqBitLength;
    /**
     * 最大序列数（含）,设置范围 [MinSeqNumber, 2^SeqBitLength-1]，默认值0，表示最大序列数取最大值（2^SeqBitLength-1]）
     */
    private static short maxSeqNumber;
    /**
     * 最小序列数（含）, 默认值5，取值范围 [5, MaxSeqNumber]，每毫秒的前5个序列数对应编号是0-4是保留位，其中1-4是时间回拨相应预留位，0是手工新值预留位
     */
    private static short minSeqNumber;
    /**
     * 最大漂移次数（含）,默认2000，推荐范围500-10000（与计算能力有关）
     */
    private static short topOverCostCount;
    public Runnable initIdGenerateBean = () -> {
        IdGeneratorOptions idOptions = new IdGeneratorOptions(workerId);
        idOptions.WorkerIdBitLength = workerIdBitLength;
        idOptions.BaseTime = baseTime;
        idOptions.TopOverCostCount = topOverCostCount;
        idOptions.Method = method;
        idOptions.WorkerId = workerId;
        idOptions.MaxSeqNumber = maxSeqNumber;
        idOptions.SeqBitLength = seqBitLength;
        idOptions.MinSeqNumber = minSeqNumber;
        YitIdHelper.setIdGenerator(idOptions);
        log.info("{} {}", StringPool.DASH, "初始化数据库唯一id配置信息");
    };

    public static short getWorkerId() {
        return workerId;
    }

    public void setWorkerId(short workerId) {
        IdGenerateConfig.workerId = workerId;
    }

    public static short getMethod() {
        return method;
    }

    public void setMethod(short method) {
        IdGenerateConfig.method = method;
    }

    public static long getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(long baseTime) {
        IdGenerateConfig.baseTime = baseTime;
    }

    public static byte getWorkerIdBitLength() {
        return workerIdBitLength;
    }

    public void setWorkerIdBitLength(byte workerIdBitLength) {
        IdGenerateConfig.workerIdBitLength = workerIdBitLength;
    }

    public static byte getSeqBitLength() {
        return seqBitLength;
    }

    public void setSeqBitLength(byte seqBitLength) {
        IdGenerateConfig.seqBitLength = seqBitLength;
    }

    public static short getMaxSeqNumber() {
        return maxSeqNumber;
    }

    public void setMaxSeqNumber(short maxSeqNumber) {
        IdGenerateConfig.maxSeqNumber = maxSeqNumber;
    }

    public static short getMinSeqNumber() {
        return minSeqNumber;
    }

    public void setMinSeqNumber(short minSeqNumber) {
        IdGenerateConfig.minSeqNumber = minSeqNumber;
    }

    public static short getTopOverCostCount() {
        return topOverCostCount;
    }

    public void setTopOverCostCount(short topOverCostCount) {
        IdGenerateConfig.topOverCostCount = topOverCostCount;
    }

    @Bean("idGenerateBean")
    public IdGenerateConfig idGenerateBean() {
        initIdGenerateBean.run();
        return null;
    }

}
