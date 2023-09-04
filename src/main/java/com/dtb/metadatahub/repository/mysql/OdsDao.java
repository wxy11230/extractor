/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.entity.HiveColumnDO
 *  com.dtb.metadatahub.entity.HiveDatabaseDO
 *  com.dtb.metadatahub.entity.HiveTableDO
 *  com.dtb.metadatahub.entity.HiveViewDO
 *  com.dtb.metadatahub.entity.KafkaBrokerDO
 *  com.dtb.metadatahub.entity.KafkaPartitionDO
 *  com.dtb.metadatahub.entity.KafkaTopicDO
 *  com.dtb.metadatahub.entity.MppColumnDO
 *  com.dtb.metadatahub.entity.MppDatabaseDO
 *  com.dtb.metadatahub.entity.MppTableDO
 *  com.dtb.metadatahub.entity.RdbColumnDO
 *  com.dtb.metadatahub.entity.RdbDatabaseDO
 *  com.dtb.metadatahub.entity.RdbTableDO
 *  org.apache.ibatis.annotations.Mapper
 */
package com.dtb.metadatahub.repository.mysql;

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
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OdsDao {
    public void addOdsHiveDatabaseList(List<HiveDatabaseDO> var1);

    public void addOdsHiveTableList(List<HiveTableDO> var1);

    public void addOdsHiveColumnList(List<HiveColumnDO> var1);

    public void addOdsHiveViewList(List<HiveViewDO> var1);

    public void addOdsRdbDatabaseList(List<RdbDatabaseDO> var1);

    public void addOdsRdbTableList(List<RdbTableDO> var1);

    public void addOdsRdbColumnList(List<RdbColumnDO> var1);

    public void addOdsMppDatabaseList(List<MppDatabaseDO> var1);

    public void addOdsMppTableList(List<MppTableDO> var1);

    public void addOdsMppColumnList(List<MppColumnDO> var1);

    public void addOdsKafkaBrokerList(List<KafkaBrokerDO> var1);

    public void addOdsKafkaTopicList(List<KafkaTopicDO> var1);

    public void addOdsKafkaPartitionList(List<KafkaPartitionDO> var1);
}
