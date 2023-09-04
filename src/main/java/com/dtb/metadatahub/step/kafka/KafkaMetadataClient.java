/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.zookeeper.WatchedEvent
 *  org.apache.zookeeper.Watcher
 *  org.apache.zookeeper.ZooKeeper
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.dtb.metadatahub.step.kafka;

import com.dtb.metadatahub.step.kafka.KafkaStepMeta;
import java.io.IOException;
import java.util.List;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaMetadataClient
implements Watcher {
    private static final Logger log = LoggerFactory.getLogger(KafkaMetadataClient.class);
    private ZooKeeper client;

    private KafkaMetadataClient() {
    }

    public KafkaMetadataClient(KafkaStepMeta stepMeta) throws IOException {
        this.client = new ZooKeeper(stepMeta.getServers(), 10000, (Watcher)new KafkaMetadataClient());
    }

    public byte[] getClusterInfo() throws Exception {
        return this.client.getData("/cluster/id", false, null);
    }

    public List<String> getBrokerIdList() throws Exception {
        return this.client.getChildren("/brokers/ids", false);
    }

    public byte[] getBrokerDetail(String brokerId) throws Exception {
        return this.client.getData("/brokers/ids/" + brokerId, false, null);
    }

    public List<String> getTopicList() throws Exception {
        return this.client.getChildren("/brokers/topics", false);
    }

    public byte[] getTopicDetail(String topicName) throws Exception {
        return this.client.getData("/brokers/topics/" + topicName, false, null);
    }

    public List<String> getTopicPartitionIds(String topicName) throws Exception {
        return this.client.getChildren("/brokers/topics/" + topicName + "/partitions", false);
    }

    public byte[] getPartitionDetail(String topicName, String partitionId) throws Exception {
        return this.client.getData("/brokers/topics/" + topicName + "/partitions/" + partitionId + "/state", false, null);
    }

    public void closeClient() {
        try {
            this.client.close();
        }
        catch (InterruptedException e) {
            log.error("Close zookeeper client exception. The reason is : {}", (Throwable)e);
        }
    }

    public void process(WatchedEvent event) {
    }
}
