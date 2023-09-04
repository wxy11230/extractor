/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.entity.KafkaBrokerDO
 *  com.dtb.metadatahub.entity.KafkaPartitionDO
 *  com.dtb.metadatahub.entity.KafkaTopicDO
 *  com.dtb.metadatahub.step.IStep
 *  com.dtb.metadatahub.step.kafka.KafkaMetadataProxy
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.dtb.metadatahub.step.kafka;

import com.dtb.metadatahub.entity.KafkaBrokerDO;
import com.dtb.metadatahub.entity.KafkaPartitionDO;
import com.dtb.metadatahub.entity.KafkaTopicDO;
import com.dtb.metadatahub.step.IStep;
import com.dtb.metadatahub.step.kafka.KafkaMetadataProxy;
import com.dtb.metadatahub.step.kafka.KafkaStepMeta;
import com.dtb.metadatahub.step.kafka.entities.BrokerEntity;
import com.dtb.metadatahub.step.kafka.entities.PartitionEntity;
import com.dtb.metadatahub.step.kafka.entities.TopicEntity;
import com.dtb.metadatahub.util.ConvertUtils;
import com.dtb.metadatahub.util.MD5Util;
import com.dtb.metadatahub.util.SnowFlakeUtil;
import com.dtb.metadatahub.util.StringUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaMetadataStep
implements IStep {
    private static final Logger log = LoggerFactory.getLogger(KafkaMetadataStep.class);
    private boolean successFlag;
    private KafkaMetadataProxy proxy;
    private KafkaStepMeta stepMeta;
    private List<String> extractorTopicList;
    private List<String> ignoreTopicList;
    private List<KafkaBrokerDO> brokerDOList;
    private List<KafkaTopicDO> topicDOList;
    private List<KafkaPartitionDO> partitionDOList;

    public KafkaMetadataStep(KafkaStepMeta stepMeta) {
        this.stepMeta = stepMeta;
        this.proxy = new KafkaMetadataProxy(stepMeta);
        this.successFlag = false;
        this.brokerDOList = new ArrayList<KafkaBrokerDO>();
        this.topicDOList = new ArrayList<KafkaTopicDO>();
        this.partitionDOList = new ArrayList<KafkaPartitionDO>();
        this.extractorTopicList = new ArrayList<String>();
        this.ignoreTopicList = new ArrayList<String>();
        this.transVariable(this.extractorTopicList, stepMeta.getExtratorTopics());
        this.transVariable(this.ignoreTopicList, stepMeta.getIgnoreTopics());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void getMetadata() {
        try {
            List<BrokerEntity> brokerResultList = new ArrayList();
            List<TopicEntity> topicResultList = new ArrayList();
            List<PartitionEntity> partitionResultList = new ArrayList();
            String clusterId = this.proxy.getClusterId();
            List<String> brokerIds = this.proxy.getBrokerList();
            Iterator var6 = brokerIds.iterator();

            String extractorTopic;
            while(var6.hasNext()) {
                extractorTopic = (String)var6.next();
                BrokerEntity brokerEntity = this.proxy.getBrokerDetail(clusterId, extractorTopic);
                brokerResultList.add(brokerEntity);
            }

            if (this.extractorTopicList != null && this.extractorTopicList.size() > 0) {
                var6 = this.extractorTopicList.iterator();

                while(var6.hasNext()) {
                    extractorTopic = (String)var6.next();
                    TopicEntity topicEntity = this.proxy.getTopicDetail(clusterId, extractorTopic);
                    topicResultList.add(topicEntity);
                    List<String> partitionIds = this.proxy.getPartitionList(extractorTopic);
                    Iterator var24 = partitionIds.iterator();

                    while(var24.hasNext()) {
                        String partitionId = (String)var24.next();
                        PartitionEntity partitionEntity = this.proxy.getPartitionDetail(extractorTopic, partitionId);
                        partitionResultList.add(partitionEntity);
                    }
                }
            } else {
                List<String> topicNames = this.proxy.getTopicNameList();
                if (this.ignoreTopicList != null && this.ignoreTopicList.size() > 0) {
                    this.ignoreTopicName(topicNames, this.ignoreTopicList);
                }

                Iterator var20 = topicNames.iterator();

                while(var20.hasNext()) {
                    String topicName = (String)var20.next();
                    TopicEntity topicEntity = this.proxy.getTopicDetail(clusterId, topicName);
                    topicResultList.add(topicEntity);
                    List<String> partitionIds = this.proxy.getPartitionList(topicName);
                    Iterator var11 = partitionIds.iterator();

                    while(var11.hasNext()) {
                        String partitionId = (String)var11.next();
                        PartitionEntity partitionEntity = this.proxy.getPartitionDetail(topicName, partitionId);
                        partitionResultList.add(partitionEntity);
                    }
                }
            }

            this.transToDOList(brokerResultList, topicResultList, partitionResultList, clusterId);
            this.successFlag = true;
        } catch (Exception var17) {
            this.successFlag = false;
            var17.printStackTrace();
            log.error("采集kafka元数据出现异常，异常原因：{}", var17);
        } finally {
            this.proxy.close();
        }

    }

    public boolean getSuccessFlag() {
        return this.successFlag;
    }

    private void transVariable(List<String> list, String strs) {
        if (!StringUtil.isEmpty(strs)) {
            String[] str1 = strs.trim().split("\\,");
            if (str1.length == 0) {
                list.add(strs.trim());
            } else {
                for (String str : str1) {
                    if (StringUtil.isEmpty(str)) continue;
                    list.add(str.trim());
                }
            }
        }
    }

    private void ignoreTopicName(List<String> inputTopicNameList, List<String> ignoreTopicNameList) {
        for (String ignoreTopicName : ignoreTopicNameList) {
            if (!inputTopicNameList.contains(ignoreTopicName)) continue;
            inputTopicNameList.remove(ignoreTopicName);
        }
    }

    private void transToDOList(List<BrokerEntity> brokerList, List<TopicEntity> topicList, List<PartitionEntity> partitionList, String clusterId) {
        String qualifiedName;
        this.brokerDOList = ConvertUtils.copyList(brokerList, KafkaBrokerDO.class);
        for (KafkaBrokerDO kafkaBrokerDO : this.brokerDOList) {
            kafkaBrokerDO.setId(Long.valueOf(SnowFlakeUtil.getInstance().nextId()));
            kafkaBrokerDO.setRecordCreateTime(new Date());
            kafkaBrokerDO.setRecordStatus("1");
            kafkaBrokerDO.setQualifiedName(kafkaBrokerDO.getHost() + ":" + kafkaBrokerDO.getPort() + "@" + kafkaBrokerDO.getClusterId());
        }
        this.topicDOList = ConvertUtils.copyList(topicList, KafkaTopicDO.class);
        for (KafkaTopicDO kafkaTopicDO : this.topicDOList) {
            kafkaTopicDO.setId(Long.valueOf(SnowFlakeUtil.getInstance().nextId()));
            qualifiedName = kafkaTopicDO.getName() + "@" + kafkaTopicDO.getClusterId();
            kafkaTopicDO.setQualifiedName(qualifiedName);
            kafkaTopicDO.setGuid(MD5Util.getMD5(qualifiedName));
            kafkaTopicDO.setRecordCreateTime(new Date());
            kafkaTopicDO.setRecordStatus("1");
        }
        this.partitionDOList = ConvertUtils.copyList(partitionList, KafkaPartitionDO.class);
        for (KafkaPartitionDO kafkaPartitionDO : this.partitionDOList) {
            kafkaPartitionDO.setId(Long.valueOf(SnowFlakeUtil.getInstance().nextId()));
            qualifiedName = kafkaPartitionDO.getTopicName() + "." + kafkaPartitionDO.getGuid() + "@" + clusterId;
            kafkaPartitionDO.setQualifiedName(qualifiedName);
            kafkaPartitionDO.setGuid(MD5Util.getMD5(qualifiedName));
            kafkaPartitionDO.setRecordCreateTime(new Date());
            kafkaPartitionDO.setRecordStatus("1");
        }
    }

    public KafkaMetadataProxy getProxy() {
        return this.proxy;
    }

    public KafkaStepMeta getStepMeta() {
        return this.stepMeta;
    }

    public List<String> getExtractorTopicList() {
        return this.extractorTopicList;
    }

    public List<String> getIgnoreTopicList() {
        return this.ignoreTopicList;
    }

    public List<KafkaBrokerDO> getBrokerDOList() {
        return this.brokerDOList;
    }

    public List<KafkaTopicDO> getTopicDOList() {
        return this.topicDOList;
    }

    public List<KafkaPartitionDO> getPartitionDOList() {
        return this.partitionDOList;
    }

    public void setSuccessFlag(boolean successFlag) {
        this.successFlag = successFlag;
    }

    public void setProxy(KafkaMetadataProxy proxy) {
        this.proxy = proxy;
    }

    public void setStepMeta(KafkaStepMeta stepMeta) {
        this.stepMeta = stepMeta;
    }

    public void setExtractorTopicList(List<String> extractorTopicList) {
        this.extractorTopicList = extractorTopicList;
    }

    public void setIgnoreTopicList(List<String> ignoreTopicList) {
        this.ignoreTopicList = ignoreTopicList;
    }

    public void setBrokerDOList(List<KafkaBrokerDO> brokerDOList) {
        this.brokerDOList = brokerDOList;
    }

    public void setTopicDOList(List<KafkaTopicDO> topicDOList) {
        this.topicDOList = topicDOList;
    }

    public void setPartitionDOList(List<KafkaPartitionDO> partitionDOList) {
        this.partitionDOList = partitionDOList;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof KafkaMetadataStep)) {
            return false;
        }
        KafkaMetadataStep other = (KafkaMetadataStep)o;
        if (!other.canEqual(this)) {
            return false;
        }
        if (this.getSuccessFlag() != other.getSuccessFlag()) {
            return false;
        }
        KafkaMetadataProxy this$proxy = this.getProxy();
        KafkaMetadataProxy other$proxy = other.getProxy();
        if (this$proxy == null ? other$proxy != null : !this$proxy.equals(other$proxy)) {
            return false;
        }
        KafkaStepMeta this$stepMeta = this.getStepMeta();
        KafkaStepMeta other$stepMeta = other.getStepMeta();
        if (this$stepMeta == null ? other$stepMeta != null : !((Object)this$stepMeta).equals(other$stepMeta)) {
            return false;
        }
        List<String> this$extractorTopicList = this.getExtractorTopicList();
        List<String> other$extractorTopicList = other.getExtractorTopicList();
        if (this$extractorTopicList == null ? other$extractorTopicList != null : !((Object)this$extractorTopicList).equals(other$extractorTopicList)) {
            return false;
        }
        List<String> this$ignoreTopicList = this.getIgnoreTopicList();
        List<String> other$ignoreTopicList = other.getIgnoreTopicList();
        if (this$ignoreTopicList == null ? other$ignoreTopicList != null : !((Object)this$ignoreTopicList).equals(other$ignoreTopicList)) {
            return false;
        }
        List<KafkaBrokerDO> this$brokerDOList = this.getBrokerDOList();
        List<KafkaBrokerDO> other$brokerDOList = other.getBrokerDOList();
        if (this$brokerDOList == null ? other$brokerDOList != null : !((Object)this$brokerDOList).equals(other$brokerDOList)) {
            return false;
        }
        List<KafkaTopicDO> this$topicDOList = this.getTopicDOList();
        List<KafkaTopicDO> other$topicDOList = other.getTopicDOList();
        if (this$topicDOList == null ? other$topicDOList != null : !((Object)this$topicDOList).equals(other$topicDOList)) {
            return false;
        }
        List<KafkaPartitionDO> this$partitionDOList = this.getPartitionDOList();
        List<KafkaPartitionDO> other$partitionDOList = other.getPartitionDOList();
        return !(this$partitionDOList == null ? other$partitionDOList != null : !((Object)this$partitionDOList).equals(other$partitionDOList));
    }

    protected boolean canEqual(Object other) {
        return other instanceof KafkaMetadataStep;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * 59 + (this.getSuccessFlag() ? 79 : 97);
        KafkaMetadataProxy $proxy = this.getProxy();
        result = result * 59 + ($proxy == null ? 43 : $proxy.hashCode());
        KafkaStepMeta $stepMeta = this.getStepMeta();
        result = result * 59 + ($stepMeta == null ? 43 : ((Object)$stepMeta).hashCode());
        List<String> $extractorTopicList = this.getExtractorTopicList();
        result = result * 59 + ($extractorTopicList == null ? 43 : ((Object)$extractorTopicList).hashCode());
        List<String> $ignoreTopicList = this.getIgnoreTopicList();
        result = result * 59 + ($ignoreTopicList == null ? 43 : ((Object)$ignoreTopicList).hashCode());
        List<KafkaBrokerDO> $brokerDOList = this.getBrokerDOList();
        result = result * 59 + ($brokerDOList == null ? 43 : ((Object)$brokerDOList).hashCode());
        List<KafkaTopicDO> $topicDOList = this.getTopicDOList();
        result = result * 59 + ($topicDOList == null ? 43 : ((Object)$topicDOList).hashCode());
        List<KafkaPartitionDO> $partitionDOList = this.getPartitionDOList();
        result = result * 59 + ($partitionDOList == null ? 43 : ((Object)$partitionDOList).hashCode());
        return result;
    }

    public String toString() {
        return "KafkaMetadataStep(successFlag=" + this.getSuccessFlag() + ", proxy=" + this.getProxy() + ", stepMeta=" + this.getStepMeta() + ", extractorTopicList=" + this.getExtractorTopicList() + ", ignoreTopicList=" + this.getIgnoreTopicList() + ", brokerDOList=" + this.getBrokerDOList() + ", topicDOList=" + this.getTopicDOList() + ", partitionDOList=" + this.getPartitionDOList() + ")";
    }
}
