
package com.dtb.metadatahub.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dtb.metadatahub.config.MappingCommonProperty;
import com.dtb.metadatahub.service.async.AsyncWriteMetadataExecutor;
import com.dtb.metadatahub.step.IStep;
import com.dtb.metadatahub.step.atlas.AtlasMetadataStep;
import com.dtb.metadatahub.step.atlas.AtlasStepMeta;
import com.dtb.metadatahub.step.kafka.KafkaMetadataStep;
import com.dtb.metadatahub.step.kafka.KafkaStepMeta;
import com.dtb.metadatahub.step.mpp.MppMetadataStep;
import com.dtb.metadatahub.step.mpp.MppStepMeta;
import com.dtb.metadatahub.step.rdbms.RdbMetadataStep;
import com.dtb.metadatahub.step.rdbms.RdbStepMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class ExtractorService {
    private static final Logger log = LoggerFactory.getLogger(ExtractorService.class);
    @Autowired
    private MappingCommonProperty mappingProperty;
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private AsyncWriteMetadataExecutor asyncWriteMetadataExecutor;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void getMetadata(String collectType, String collectParamJson) {
        log.info("Collect starting...");
        try {
            if ("Rdb".equals(collectType)) {
                RdbMetadataStep metadataStep = (RdbMetadataStep)this.buildStep(collectType, collectParamJson);
                metadataStep.setDatabaseMappingConfig(this.mappingProperty.getRdbDatabaseConfig());
                metadataStep.setTableMappingConfig(this.mappingProperty.getRdbTableConfig());
                metadataStep.setColumnMappingConfig(this.mappingProperty.getRdbColumnConfig());
                metadataStep.getMetadata();
                log.info("The collection is completed, start writing to the ods database....");
                this.asyncWriteMetadataExecutor.insertRdbMetaData(metadataStep);
            } else if ("Atlas".equals(collectType)) {
                AtlasMetadataStep atlasMetadataStep = (AtlasMetadataStep)this.buildStep(collectType, collectParamJson);
                atlasMetadataStep.setDatabaseMappingConfig(this.mappingProperty.getHiveDatabaseConfig());
                atlasMetadataStep.setViewMappingConfig(this.mappingProperty.getHiveViewConfig());
                atlasMetadataStep.setTableMappingConfig(this.mappingProperty.getHiveTableConfig());
                atlasMetadataStep.setColumnMappingConfig(this.mappingProperty.getHiveColumnConfig());
                atlasMetadataStep.getMetadata();
                log.info("The collection is completed, start writing to the ods database....");
                this.asyncWriteMetadataExecutor.insertAtlasMetaData(atlasMetadataStep);
            } else if ("Mpp".equals(collectType)) {
                MppMetadataStep mppMetadataStep = (MppMetadataStep)this.buildStep(collectType, collectParamJson);
                mppMetadataStep.setDatabaseMappingConfig(this.mappingProperty.getMppDatabaseConfig());
                mppMetadataStep.setTableMappingConfig(this.mappingProperty.getMppTableConfig());
                mppMetadataStep.setColumnMappingConfig(this.mappingProperty.getMppColumnConfig());
                mppMetadataStep.getMetadata();
                log.info("The collection is completed, start writing to the ods database....");
                this.asyncWriteMetadataExecutor.insertMppMetaData(mppMetadataStep);
            } else if ("Kafka".equals(collectType)) {
                KafkaMetadataStep kafkaMetadataStep = (KafkaMetadataStep)this.buildStep(collectType, collectParamJson);
                kafkaMetadataStep.getMetadata();
                log.info("The collection is completed, start writing to the ods database....");
                this.asyncWriteMetadataExecutor.insertKafkaMetaData(kafkaMetadataStep);
            } else {
                log.error("Other collection types are not currently supported !");
            }
        }
        catch (Exception e) {
            log.error("collect metadata error ! reason is : {}", (Throwable)e);
        }
        finally {
            this.threadPoolTaskExecutor.shutdown();
        }
        log.info("Collect finished...");
    }

    private IStep buildStep(String type, String paramJsonStr) {
        JSONObject paramJsonObj = JSON.parseObject((String)paramJsonStr);
        IStep iStep = null;
        boolean isConfig = false;
        String stepName = null;
        switch (type) {
            case "Rdb": {
                isConfig = paramJsonObj.getBooleanValue("isConfig");
                stepName = paramJsonObj.getString("stepName");
                RdbStepMeta rdbStepMeta = null;
                if (isConfig) {
                    String fileName = paramJsonObj.getString("configFileName");
                    rdbStepMeta = new RdbStepMeta(stepName, fileName);
                } else {
                    rdbStepMeta = new RdbStepMeta(stepName);
                    rdbStepMeta.setDatabaseServer(paramJsonObj.getString("databaseServer"));
                    rdbStepMeta.setDatabaseName(paramJsonObj.getString("databaseName"));
                    rdbStepMeta.setDatabaseType(paramJsonObj.getString("stepType"));
                    rdbStepMeta.setPort(paramJsonObj.getIntValue("port"));
                    rdbStepMeta.setUserName(paramJsonObj.getString("userName"));
                    rdbStepMeta.setPassword(paramJsonObj.getString("password"));
                    rdbStepMeta.setIgnoreDDL(paramJsonObj.getBooleanValue("ignoreTableDDL"));
                    rdbStepMeta.setIgnoreRowNum(paramJsonObj.getBooleanValue("ignoreTableRowNum"));
                    rdbStepMeta.setIgnoreTableSize(paramJsonObj.getBooleanValue("ignoreTableSize"));
                    rdbStepMeta.setExtratorDatabaseNames(paramJsonObj.getString("extratorDatabaseNames"));
                    rdbStepMeta.setExtratorTableNames(paramJsonObj.getString("extratorTableNames"));
                    rdbStepMeta.setIgnoreDatabaseNames(paramJsonObj.getString("ignoreDatabaseNames"));
                }
                iStep = new RdbMetadataStep(rdbStepMeta);
                break;
            }
            case "Atlas": {
                isConfig = paramJsonObj.getBooleanValue("isConfig");
                stepName = paramJsonObj.getString("stepName");
                AtlasStepMeta atlasStepMeta = null;
                if (isConfig) {
                    String fileName = paramJsonObj.getString("configFileName");
                    atlasStepMeta = new AtlasStepMeta(stepName, fileName);
                } else {
                    atlasStepMeta = new AtlasStepMeta();
                    atlasStepMeta.setStepName(stepName);
                    atlasStepMeta.setIgnoreExist(paramJsonObj.getBooleanValue("ignoreExist"));
                    atlasStepMeta.setLineageDepth(paramJsonObj.getIntValue("lineageDepth"));
                    atlasStepMeta.setExportClear(paramJsonObj.getBooleanValue("isExportClear"));
                    atlasStepMeta.setDbQualifiedNames(paramJsonObj.getString("dbQualifiedNames"));
                    atlasStepMeta.setTbQualifiedNames(paramJsonObj.getString("tbQualifiedNames"));
                    atlasStepMeta.setClusterQualifiedNames(paramJsonObj.getString("clusterQualifiedNames"));
                }
                iStep = new AtlasMetadataStep(atlasStepMeta);
                break;
            }
            case "Mpp": {
                isConfig = paramJsonObj.getBooleanValue("isConfig");
                stepName = paramJsonObj.getString("stepName");
                MppStepMeta mppStepMeta = null;
                if (isConfig) {
                    String fileName = paramJsonObj.getString("configFileName");
                    mppStepMeta = new MppStepMeta(stepName, fileName);
                } else {
                    mppStepMeta = new MppStepMeta(stepName);
                    mppStepMeta.setDatabaseServer(paramJsonObj.getString("databaseServer"));
                    mppStepMeta.setDatabaseName(paramJsonObj.getString("databaseName"));
                    mppStepMeta.setDatabaseType(paramJsonObj.getString("stepType"));
                    mppStepMeta.setPort(paramJsonObj.getIntValue("port"));
                    mppStepMeta.setUserName(paramJsonObj.getString("userName"));
                    mppStepMeta.setPassword(paramJsonObj.getString("password"));
                    mppStepMeta.setIgnoreDDL(paramJsonObj.getBooleanValue("ignoreTableDDL"));
                    mppStepMeta.setIgnoreRowNum(paramJsonObj.getBooleanValue("ignoreTableRowNum"));
                    mppStepMeta.setIgnoreTableSize(paramJsonObj.getBooleanValue("ignoreTableSize"));
                    mppStepMeta.setExtratorDatabaseNames(paramJsonObj.getString("extratorDatabaseNames"));
                    mppStepMeta.setExtratorTableNames(paramJsonObj.getString("extratorTableNames"));
                    mppStepMeta.setIgnoreDatabaseNames(paramJsonObj.getString("ignoreDatabaseNames"));
                }
                iStep = new MppMetadataStep(mppStepMeta);
                break;
            }
            case "Kafka": {
                isConfig = paramJsonObj.getBooleanValue("isConfig");
                stepName = paramJsonObj.getString("stepName");
                KafkaStepMeta kafkaStepMeta = null;
                if (isConfig) {
                    String fileName = paramJsonObj.getString("configFileName");
                    kafkaStepMeta = new KafkaStepMeta(stepName, fileName);
                    System.out.println("============+>" + kafkaStepMeta.toString());
                } else {
                    kafkaStepMeta = new KafkaStepMeta(stepName);
                    kafkaStepMeta.setServers(paramJsonObj.getString("zkServers"));
                    kafkaStepMeta.setExtratorTopics(paramJsonObj.getString("extratorTopics"));
                    kafkaStepMeta.setIgnoreTopics(paramJsonObj.getString("ignoreTopics"));
                }
                iStep = new KafkaMetadataStep(kafkaStepMeta);
                break;
            }
        }
        return iStep;
    }
}
