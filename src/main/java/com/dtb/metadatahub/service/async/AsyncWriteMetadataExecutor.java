package com.dtb.metadatahub.service.async;

import com.dtb.metadatahub.entity.ChangeMakerDO;
import com.dtb.metadatahub.entity.HiveColumnDO;
import com.dtb.metadatahub.entity.HiveDatabaseDO;
import com.dtb.metadatahub.entity.HiveTableDO;
import com.dtb.metadatahub.entity.HiveViewDO;
import com.dtb.metadatahub.entity.KafkaBrokerDO;
import com.dtb.metadatahub.entity.KafkaPartitionDO;
import com.dtb.metadatahub.entity.KafkaTopicDO;
import com.dtb.metadatahub.entity.MppColumnDO;
import com.dtb.metadatahub.entity.MppDatabaseDO;
import com.dtb.metadatahub.entity.MppTableDO;
import com.dtb.metadatahub.entity.RdbColumnDO;
import com.dtb.metadatahub.entity.RdbDatabaseDO;
import com.dtb.metadatahub.entity.RdbTableDO;
import com.dtb.metadatahub.repository.mysql.ChangeMakerDao;
import com.dtb.metadatahub.repository.mysql.OdsDao;
import com.dtb.metadatahub.step.atlas.AtlasMetadataStep;
import com.dtb.metadatahub.step.kafka.KafkaMetadataStep;
import com.dtb.metadatahub.step.mpp.MppMetadataStep;
import com.dtb.metadatahub.step.rdbms.RdbMetadataStep;
import com.dtb.metadatahub.util.SnowFlakeUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AsyncWriteMetadataExecutor {
    private static final Logger log = LoggerFactory.getLogger(AsyncWriteMetadataExecutor.class);
    @Value(value="${batch.insert.nums}")
    private int insertNums;
    @Autowired
    private OdsDao odsDao;
    @Autowired
    private ChangeMakerDao makerDao;

    @Async(value="executorAsyncExecutor")
    @Transactional(value="masterTransactionManager", propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
    public void insertRdbMetaData(RdbMetadataStep metadataStep) throws Exception {
        if (!metadataStep.getSuccessFlag()) {
            log.error("An error occurred during the collection process and the data was not written to the database");
        } else {
            log.info("====>Start insert the rdb metadata into ods database...");
            int count = 0;
            int index = 0;
            List<RdbDatabaseDO> tmpDbList = new ArrayList();
            List<RdbTableDO> tmpTbList = new ArrayList();
            List<RdbColumnDO> tmpColList = new ArrayList();
            List<RdbDatabaseDO> rdbDatabaseList = metadataStep.getDatabaseDOList();
            List<RdbTableDO> rdbTableList = metadataStep.getTableDOList();
            List<RdbColumnDO> rdbColumnList = metadataStep.getColumnDOList();
            if (rdbDatabaseList != null && rdbDatabaseList.size() > 0) {
                if (rdbDatabaseList.size() > this.insertNums) {
                    for(; index < rdbDatabaseList.size(); ++index) {
                        ((List)tmpDbList).add(rdbDatabaseList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsRdbDatabaseList((List)tmpDbList);
                            log.info("Insert {} database entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpDbList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpDbList = rdbDatabaseList;
                }

                if (((List)tmpDbList).size() != 0) {
                    this.odsDao.addOdsRdbDatabaseList((List)tmpDbList);
                    log.info("Insert {} database entity records successfully!", ((List)tmpDbList).size());
                }

                log.info("Successfully inserted {} database metadata record of RDBMS", rdbDatabaseList.size());
                rdbDatabaseList.clear();
            }

            count = 0;
            index = 0;
            if (rdbTableList != null && rdbTableList.size() > 0) {
                Iterator var10 = rdbTableList.iterator();

                while(var10.hasNext()) {
                    RdbTableDO testdo = (RdbTableDO)var10.next();
                    if (testdo.getCreateTime() == 0L) {
                        testdo.setCreateTime((Long)null);
                    }
                }

                if (rdbTableList.size() > this.insertNums) {
                    for(; index < rdbTableList.size(); ++index) {
                        ((List)tmpTbList).add(rdbTableList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsRdbTableList((List)tmpTbList);
                            log.info("Insert {} table entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpTbList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpTbList = rdbTableList;
                }

                if (((List)tmpTbList).size() != 0) {
                    this.odsDao.addOdsRdbTableList((List)tmpTbList);
                    log.info("Insert {} table entity records successfully!", ((List)tmpDbList).size());
                }

                log.info("Successfully inserted {} table metadata record of RDBMS", rdbTableList.size());
                rdbTableList.clear();
            }

            count = 0;
            index = 0;
            if (rdbColumnList != null && rdbColumnList.size() > 0) {
                if (rdbColumnList.size() > this.insertNums) {
                    for(; index < rdbColumnList.size(); ++index) {
                        ((List)tmpColList).add(rdbColumnList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsRdbColumnList((List)tmpColList);
                            log.info("Insert {} column entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpColList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpColList = rdbColumnList;
                }

                if (((List)tmpColList).size() != 0) {
                    this.odsDao.addOdsRdbColumnList((List)tmpColList);
                    log.info("Insert {} column entity records successfully!", ((List)tmpColList).size());
                }

                log.info("Successfully inserted {} column metadata record of RDBMS", rdbColumnList.size());
                rdbColumnList.clear();
            }

            log.info("<====End insert the rdb metadata into ods database");
        }
    }

    public void insertAtlasMetaData(AtlasMetadataStep metadataStep) throws Exception {
        if (!metadataStep.getSuccessFlag()) {
            log.error("An error occurred during the collection process and the data was not written to the database");
        } else {
            log.info("====>Start insert the atlas metadata into ods database...");
            int count = 0;
            int index = 0;
            List<HiveDatabaseDO> tmpDbList = new ArrayList();
            List<HiveTableDO> tmpTbList = new ArrayList();
            List<HiveViewDO> tmpViewList = new ArrayList();
            List<HiveColumnDO> tmpColList = new ArrayList();
            List<HiveDatabaseDO> databaseList = metadataStep.getDatabaseDOList();
            List<HiveTableDO> tableList = metadataStep.getTableDOList();
            List<HiveViewDO> viewList = metadataStep.getViewDOList();
            List<HiveColumnDO> columnList = metadataStep.getColumnDOList();
            if (databaseList != null && databaseList.size() > 0) {
                if (databaseList.size() > this.insertNums) {
                    for(; index < databaseList.size(); ++index) {
                        ((List)tmpDbList).add(databaseList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsHiveDatabaseList((List)tmpDbList);
                            log.info("Insert {} database entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpDbList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpDbList = databaseList;
                }

                if (((List)tmpDbList).size() != 0) {
                    this.odsDao.addOdsHiveDatabaseList((List)tmpDbList);
                    log.info("Insert {} database entity records successfully!", ((List)tmpDbList).size());
                }

                log.info("Successfully inserted {} database metadata record of ATLAS", databaseList.size());
                databaseList.clear();
            }

            count = 0;
            index = 0;
            if (tableList != null && tableList.size() > 0) {
                if (tableList.size() > this.insertNums) {
                    for(; index < tableList.size(); ++index) {
                        ((List)tmpTbList).add(tableList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsHiveTableList((List)tmpTbList);
                            log.info("Insert {} table entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpTbList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpTbList = tableList;
                }

                if (((List)tmpTbList).size() != 0) {
                    this.odsDao.addOdsHiveTableList((List)tmpTbList);
                    log.info("Insert {} table entity records successfully!", ((List)tmpDbList).size());
                }

                log.info("Successfully inserted {} table metadata record of ATLAS", tableList.size());
                tableList.clear();
            }

            count = 0;
            index = 0;
            if (columnList != null && columnList.size() > 0) {
                if (columnList.size() > this.insertNums) {
                    for(; index < columnList.size(); ++index) {
                        ((List)tmpColList).add(columnList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsHiveColumnList((List)tmpColList);
                            log.info("Insert {} column entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpColList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpColList = columnList;
                }

                if (((List)tmpColList).size() != 0) {
                    this.odsDao.addOdsHiveColumnList((List)tmpColList);
                    log.info("Insert {} column entity records successfully!", ((List)tmpColList).size());
                }

                log.info("Successfully inserted {} column metadata record of ATLAS", columnList.size());
                columnList.clear();
            }

            count = 0;
            index = 0;
            if (viewList != null && viewList.size() > 0) {
                if (viewList.size() > this.insertNums) {
                    for(; index < viewList.size(); ++index) {
                        ((List)tmpViewList).add(viewList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsHiveViewList((List)tmpViewList);
                            log.info("Insert {} column entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpColList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpViewList = viewList;
                }

                if (((List)tmpViewList).size() != 0) {
                    this.odsDao.addOdsHiveViewList((List)tmpViewList);
                    log.info("Insert {} column entity records successfully!", ((List)tmpViewList).size());
                }

                log.info("Successfully inserted {} view metadata record of ATLAS", viewList.size());
                viewList.clear();
            }

            List<ChangeMakerDO> makerList = metadataStep.getChangeMakerDOList();
            if (makerList != null && makerList.size() > 0) {
                Iterator var13 = makerList.iterator();

                while(var13.hasNext()) {
                    ChangeMakerDO maker = (ChangeMakerDO)var13.next();
                    if (this.makerDao.getByQualifiedName(maker.getQualifiedName()) == null) {
                        maker.setId(SnowFlakeUtil.getInstance().nextId());
                        maker.setType("atlas");
                        maker.setCreateTime(new Date());
                        maker.setUpdateTime(new Date());
                        this.makerDao.addChangeMaker(maker);
                    } else {
                        maker.setUpdateTime(new Date());
                        this.makerDao.updateChangeMaker(maker);
                    }
                }
            }

            log.info("<====End insert the rdb metadata into ods database");
        }
    }

    @Async(value="executorAsyncExecutor")
    @Transactional(value="masterTransactionManager", propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
    public void insertMppMetaData(MppMetadataStep metadataStep) throws Exception {
        if (!metadataStep.getSuccessFlag()) {
            log.error("An error occurred during the collection process and the data was not written to the database");
        } else {
            log.info("====>Start insert the mpp metadata into ods database...");
            int count = 0;
            int index = 0;
            List<MppDatabaseDO> tmpDbList = new ArrayList();
            List<MppTableDO> tmpTbList = new ArrayList();
            List<MppColumnDO> tmpColList = new ArrayList();
            List<MppDatabaseDO> mppDatabaseList = metadataStep.getDatabaseDOList();
            List<MppTableDO> mppTableList = metadataStep.getTableDOList();
            List<MppColumnDO> mppColumnList = metadataStep.getColumnDOList();
            if (mppDatabaseList != null && mppDatabaseList.size() > 0) {
                if (mppDatabaseList.size() > this.insertNums) {
                    for(; index < mppDatabaseList.size(); ++index) {
                        ((List)tmpDbList).add(mppDatabaseList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsMppDatabaseList((List)tmpDbList);
                            log.info("Insert {} database entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpDbList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpDbList = mppDatabaseList;
                }

                if (((List)tmpDbList).size() != 0) {
                    this.odsDao.addOdsMppDatabaseList((List)tmpDbList);
                    log.info("Insert {} database entity records successfully!", ((List)tmpDbList).size());
                }

                log.info("Successfully inserted {} database metadata record of RDBMS", mppDatabaseList.size());
                mppDatabaseList.clear();
            }

            count = 0;
            index = 0;
            if (mppTableList != null && mppTableList.size() > 0) {
                if (mppTableList.size() > this.insertNums) {
                    for(; index < mppTableList.size(); ++index) {
                        ((List)tmpTbList).add(mppTableList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsMppTableList((List)tmpTbList);
                            log.info("Insert {} table entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpTbList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpTbList = mppTableList;
                }

                if (((List)tmpTbList).size() != 0) {
                    this.odsDao.addOdsMppTableList((List)tmpTbList);
                    log.info("Insert {} table entity records successfully!", ((List)tmpDbList).size());
                }

                log.info("Successfully inserted {} table metadata record of RDBMS", mppTableList.size());
                mppTableList.clear();
            }

            count = 0;
            index = 0;
            if (mppColumnList != null && mppColumnList.size() > 0) {
                if (mppColumnList.size() > this.insertNums) {
                    for(; index < mppColumnList.size(); ++index) {
                        ((List)tmpColList).add(mppColumnList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsMppColumnList((List)tmpColList);
                            log.info("Insert {} column entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpColList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpColList = mppColumnList;
                }

                if (((List)tmpColList).size() != 0) {
                    this.odsDao.addOdsMppColumnList((List)tmpColList);
                    log.info("Insert {} column entity records successfully!", ((List)tmpColList).size());
                }

                log.info("Successfully inserted {} column metadata record of RDBMS", mppColumnList.size());
                mppColumnList.clear();
            }

            log.info("<====End insert the mpp metadata into ods database");
        }
    }

    @Async(value="executorAsyncExecutor")
    @Transactional(value="masterTransactionManager", propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
    public void insertKafkaMetaData(KafkaMetadataStep metadataStep) throws Exception {
        if (!metadataStep.getSuccessFlag()) {
            log.error("An error occurred during the collection process and the data was not written to the database");
        } else {
            log.info("====>Start insert the kafka metadata into ods database...");
            int count = 0;
            int index = 0;
            List<KafkaBrokerDO> tmpBrokerList = new ArrayList();
            List<KafkaTopicDO> tmpTopicList = new ArrayList();
            List<KafkaPartitionDO> tmpPartitionList = new ArrayList();
            List<KafkaBrokerDO> kafkaBrokerList = metadataStep.getBrokerDOList();
            List<KafkaTopicDO> kafkaTopicList = metadataStep.getTopicDOList();
            List<KafkaPartitionDO> kafkaPartitionList = metadataStep.getPartitionDOList();
            if (kafkaBrokerList != null && kafkaBrokerList.size() > 0) {
                if (kafkaBrokerList.size() > this.insertNums) {
                    for(; index < kafkaBrokerList.size(); ++index) {
                        ((List)tmpBrokerList).add(kafkaBrokerList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsKafkaBrokerList((List)tmpBrokerList);
                            log.info("Insert {} kafka broker entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpBrokerList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpBrokerList = kafkaBrokerList;
                }

                if (((List)tmpBrokerList).size() != 0) {
                    this.odsDao.addOdsKafkaBrokerList((List)tmpBrokerList);
                    log.info("Insert {} kafka broker entity records successfully!", ((List)tmpBrokerList).size());
                }

                log.info("Successfully inserted {} broker metadata record of Kafka", kafkaBrokerList.size());
                kafkaBrokerList.clear();
            }

            count = 0;
            index = 0;
            if (kafkaTopicList != null && kafkaTopicList.size() > 0) {
                if (kafkaTopicList.size() > this.insertNums) {
                    for(; index < kafkaTopicList.size(); ++index) {
                        ((List)tmpTopicList).add(kafkaTopicList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsKafkaTopicList((List)tmpTopicList);
                            log.info("Insert {} kafka topic entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpTopicList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpTopicList = kafkaTopicList;
                }

                if (((List)tmpTopicList).size() != 0) {
                    this.odsDao.addOdsKafkaTopicList((List)tmpTopicList);
                    log.info("Insert {} kafka topic entity records successfully!", ((List)tmpTopicList).size());
                }

                log.info("Successfully inserted {} topic metadata record of KAFKA", kafkaTopicList.size());
                kafkaTopicList.clear();
            }

            count = 0;
            index = 0;
            if (kafkaPartitionList != null && kafkaPartitionList.size() > 0) {
                if (kafkaPartitionList.size() > this.insertNums) {
                    for(; index < kafkaPartitionList.size(); ++index) {
                        ((List)tmpPartitionList).add(kafkaPartitionList.get(index));
                        if (count == this.insertNums - 1) {
                            this.odsDao.addOdsKafkaPartitionList((List)tmpPartitionList);
                            log.info("Insert {} kafka partition entity records successfully!", this.insertNums);
                            count = 0;
                            ((List)tmpPartitionList).clear();
                        } else {
                            ++count;
                        }
                    }
                } else {
                    tmpPartitionList = kafkaPartitionList;
                }

                if (((List)tmpPartitionList).size() != 0) {
                    this.odsDao.addOdsKafkaPartitionList((List)tmpPartitionList);
                    log.info("Insert {} kafka partition entity records successfully!", ((List)tmpPartitionList).size());
                }

                log.info("Successfully inserted {} partition metadata record of KAFKA", kafkaPartitionList.size());
                kafkaPartitionList.clear();
            }

            log.info("<====End insert the KAFKA metadata into ods database");
        }
    }
}
