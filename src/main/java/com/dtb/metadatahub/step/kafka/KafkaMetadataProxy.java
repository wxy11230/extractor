/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONObject
 *  com.dtb.metadatahub.step.kafka.KafkaMetadataClient
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.dtb.metadatahub.step.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dtb.metadatahub.step.kafka.KafkaMetadataClient;
import com.dtb.metadatahub.step.kafka.KafkaStepMeta;
import com.dtb.metadatahub.step.kafka.entities.BrokerEntity;
import com.dtb.metadatahub.step.kafka.entities.PartitionEntity;
import com.dtb.metadatahub.step.kafka.entities.TopicEntity;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaMetadataProxy {
    private static final Logger log = LoggerFactory.getLogger(KafkaMetadataProxy.class);
    private KafkaMetadataClient client;

    public KafkaMetadataProxy(KafkaStepMeta stepMeta) {
        try {
            this.client = new KafkaMetadataClient(stepMeta);
        }
        catch (IOException e) {
            e.printStackTrace();
            log.error("init KafkaMetadataProxy error ! The reason is : {}", (Throwable)e);
        }
    }

    public String getClusterId() throws Exception {
        byte[] result = this.client.getClusterInfo();
        if (Objects.isNull(result)) {
            return null;
        }
        JSONObject resultJson = JSON.parseObject((String)new String(result));
        String clusterId = resultJson.getString("id");
        return clusterId;
    }

    public List<String> getBrokerList() throws Exception {
        return this.client.getBrokerIdList();
    }

    public BrokerEntity getBrokerDetail(String clusterId, String brokerId) throws Exception {
        byte[] result = this.client.getBrokerDetail(brokerId);
        if (Objects.isNull(result)) {
            return null;
        }
        JSONObject resultJson = JSON.parseObject((String)new String(result));
        String host = resultJson.getString("host");
        int port = resultJson.getIntValue("port");
        int version = resultJson.getIntValue("version");
        Date createTime = new Date(Long.parseLong(resultJson.getString("timestamp")));
        BrokerEntity brokerEntity = new BrokerEntity();
        brokerEntity.setClusterId(clusterId);
        brokerEntity.setGuid(Long.parseLong(brokerId));
        brokerEntity.setHost(host);
        brokerEntity.setPort(port);
        brokerEntity.setVersion(version);
        brokerEntity.setCreateTime(createTime);
        return brokerEntity;
    }

    public List<String> getTopicNameList() throws Exception {
        return this.client.getTopicList();
    }

    public TopicEntity getTopicDetail(String clusterId, String topicName) throws Exception {
        byte[] result = this.client.getTopicDetail(topicName);
        if (Objects.isNull(result)) {
            return null;
        }
        List<String> partitionIds = this.getPartitionList(topicName);
        JSONObject resultJson = JSON.parseObject((String)new String(result));
        int version = resultJson.getIntValue("version");
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setClusterId(clusterId);
        topicEntity.setVersion(version);
        topicEntity.setName(topicName);
        topicEntity.setPartitionNum(partitionIds.size());
        return topicEntity;
    }

    public List<String> getPartitionList(String topicName) throws Exception {
        return this.client.getTopicPartitionIds(topicName);
    }

    public PartitionEntity getPartitionDetail(String topicName, String partitionId) throws Exception {
        byte[] result = this.client.getPartitionDetail(topicName, partitionId);
        if (Objects.isNull(result)) {
            return null;
        }
        JSONObject resultJson = JSON.parseObject((String)new String(result));
        int leaderBrokenId = resultJson.getIntValue("leader");
        int version = resultJson.getIntValue("version");
        JSONArray isrArr = resultJson.getJSONArray("isr");
        PartitionEntity partitionEntity = new PartitionEntity();
        partitionEntity.setGuid(partitionId);
        partitionEntity.setTopicName(topicName);
        partitionEntity.setIsrNum(isrArr.size());
        partitionEntity.setLeaderBrokenId(leaderBrokenId);
        partitionEntity.setVersion(version);
        return partitionEntity;
    }

    public void close() {
        this.client.closeClient();
    }
}
