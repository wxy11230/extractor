
package com.dtb.metadatahub.step.atlas;

import com.dtb.metadatahub.step.atlas.entities.AtlasHiveColumnEntity;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveDatabaseEntity;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveTableEntity;
import com.dtb.metadatahub.step.atlas.entities.AtlasHiveViewEntity;
import com.dtb.metadatahub.util.SnowFlakeUtil;

import java.util.*;

import org.apache.atlas.client.model.AtlasExportResult;
import org.apache.atlas.model.impexp.AtlasExportResult.OperationStatus;
import org.apache.atlas.model.instance.AtlasEntity;
import org.apache.atlas.model.instance.AtlasEntity.AtlasEntityWithExtInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AtlasMetadataProxy {
    private static final Logger log = LoggerFactory.getLogger(AtlasMetadataProxy.class);
    private AtlasMetadataClient atlasMetadataClient = new AtlasMetadataClient();
    private List<AtlasHiveDatabaseEntity> databaseEntityList = new ArrayList<AtlasHiveDatabaseEntity>();
    private List<AtlasHiveViewEntity> viewEntityList = new ArrayList<AtlasHiveViewEntity>();
    private List<AtlasHiveTableEntity> tableEntityList = new ArrayList<AtlasHiveTableEntity>();
    private List<AtlasHiveColumnEntity> columnEntityList = new ArrayList<AtlasHiveColumnEntity>();
    private Long changeMarker = 0L;
    private boolean isClear;

    public AtlasMetadataProxy(AtlasStepMeta stepMeta) {
        this.isClear = stepMeta.isExportClear();
    }

    public List<String> getDataBaseListByClusterName(String clusterName) throws Exception {
        return this.atlasMetadataClient.getDBQualifiedNames(clusterName);
    }

    public void getDataBaseMetadata(String qualifiedName, String typeName, Long changeMarker) throws Exception {
        AtlasExportResult result = this.atlasMetadataClient.getHiveMetaData(qualifiedName, typeName, changeMarker);
        if (result != null) {
            this.changeMarker = result.getChangeMarker();
            if (result.getStatus() != OperationStatus.SUCCESS) {
                log.warn("Exported  entity by Database {} status is {}", qualifiedName, result.getStatus());
            } else {
                while(true) {
                    while(result.hasNext()) {
                        AtlasEntityWithExtInfo extInfoEntity = result.next();
                        AtlasEntity atlasEntity = extInfoEntity.getEntity();
                        String entityTypeName = atlasEntity.getTypeName();
                        String createBy = atlasEntity.getCreatedBy();
                        String updateBy = atlasEntity.getUpdatedBy();
                        String guid = atlasEntity.getGuid();
                        String status = atlasEntity.getStatus().toString();
                        long version = atlasEntity.getVersion();
                        String rs_qualifiedName = (String)atlasEntity.getAttribute("qualifiedName");
                        String name = (String)atlasEntity.getAttribute("name");
                        Date createTime = atlasEntity.getCreateTime();
                        Date updateTime = atlasEntity.getUpdateTime();
                        String desc;
                        String comment;
                        String tableType;
                        String dbGuid;
                        if (entityTypeName.equals("hive_db")) {
                            desc = (String)atlasEntity.getAttribute("clusterName");
                            comment = (String)atlasEntity.getAttribute("location");
                            tableType = (String)atlasEntity.getAttribute("owner");
                            String ownerType = (String)atlasEntity.getAttribute("ownerType");
                            dbGuid = (String)atlasEntity.getAttribute("description");
                            AtlasHiveDatabaseEntity dbEntity = new AtlasHiveDatabaseEntity();
                            dbEntity.setId(SnowFlakeUtil.getInstance().nextId());
                            dbEntity.setGuid(guid);
                            dbEntity.setCreatedBy(createBy);
                            dbEntity.setCreateTime(createTime.getTime());
                            dbEntity.setUpdatedBy(updateBy);
                            dbEntity.setUpdateTime(updateTime.getTime());
                            dbEntity.setVersion(version);
                            dbEntity.setDbStatus(status);
                            dbEntity.setQualifiedName(qualifiedName);
                            dbEntity.setTypeName(entityTypeName);
                            dbEntity.setName(name);
                            dbEntity.setClusterName(desc);
                            dbEntity.setDescription(dbGuid);
                            dbEntity.setLocation(comment);
                            dbEntity.setOwner(tableType);
                            dbEntity.setOwnerType(ownerType);
                            dbEntity.setRecordCreateTime(new Date());
                            dbEntity.setRecordStatus("1");
                            this.databaseEntityList.add(dbEntity);
                        } else if (entityTypeName.equals("hive_table")) {
                            desc = (String)atlasEntity.getAttribute("description");
                            comment = (String)atlasEntity.getAttribute("comment");
                            tableType = (String)atlasEntity.getAttribute("tableType");
                            Map<String, Object> dbinfoMap = (Map)atlasEntity.getRelationshipAttribute("db");
                            dbGuid = (String)dbinfoMap.get("guid");
                            String databaseName = (String)dbinfoMap.get("displayText");
                            Boolean isTemporary = (Boolean)atlasEntity.getAttribute("temporary");
                            String totalSize;
                            String numRows;
                            String rawDataSize;
                            List partitionKeyList;
                            List relatColumnList;
                            String partitionKeys;
                            if (!"VIRTUAL_VIEW".equals(tableType)) {
                                Map<String, Object> paramMap = (Map)atlasEntity.getAttribute("parameters");
                                totalSize = (String)paramMap.get("totalSize");
                                numRows = (String)paramMap.get("numRows");
                                rawDataSize = (String)paramMap.get("rawDataSize");
                                String numFiles = (String)paramMap.get("numFiles");
                                partitionKeyList = (List)atlasEntity.getRelationshipAttribute("partitionKeys");
                                partitionKeys = "";

                                Map partitionKey;
                                for(Iterator var32 = partitionKeyList.iterator(); var32.hasNext(); partitionKeys = partitionKeys + (String)partitionKey.get("displayText") + ",") {
                                    partitionKey = (Map)var32.next();
                                }

                                partitionKeys = "".equals(partitionKeys) ? "" : partitionKeys.substring(0, partitionKeys.length() - 1);
                                Map<String, Object> sdMap = (Map)atlasEntity.getAttribute("sd");
                                String location = "";
                                String ddlStr;
                                if (sdMap != null && sdMap.containsKey("guid")) {
                                    ddlStr = (String)sdMap.get("guid");
                                    AtlasEntity sdReferredEntity = extInfoEntity.getReferredEntity(ddlStr);
                                    location = (String)sdReferredEntity.getAttribute("location");
                                }

                                ddlStr = "";
                                List<Map<String, Object>> ddlInfoList = (List)atlasEntity.getRelationshipAttribute("ddlQueries");
                                AtlasEntity ddlReferredEntity;
                                if (ddlInfoList != null && ddlInfoList.size() > 0) {
                                    for(Iterator var36 = ddlInfoList.iterator(); var36.hasNext(); ddlStr = ddlStr + ddlReferredEntity.getAttribute("queryText") + ";") {
                                        Map<String, Object> ddlInfo = (Map)var36.next();
                                        String ddlGuid = (String)ddlInfo.get("guid");
                                        ddlReferredEntity = extInfoEntity.getReferredEntity(ddlGuid);
                                    }
                                }

                                AtlasHiveTableEntity tbEntity = new AtlasHiveTableEntity();
                                tbEntity.setId(SnowFlakeUtil.getInstance().nextId());
                                tbEntity.setGuid(guid);
                                tbEntity.setName(name);
                                tbEntity.setDbGuid(dbGuid);
                                tbEntity.setDbName(databaseName);
                                tbEntity.setDescription(desc);
                                tbEntity.setComment(comment);
                                tbEntity.setTypeName(entityTypeName);
                                tbEntity.setTableType(tableType);
                                tbEntity.setTbStatus(status);
                                tbEntity.setTemporary(isTemporary);
                                tbEntity.setTotalSize(Double.valueOf(totalSize == null ? "0" : totalSize));
                                tbEntity.setNumRows(Long.valueOf(numRows == null ? "0" : numRows));
                                tbEntity.setRowDataSize(Double.valueOf(rawDataSize == null ? "0" : rawDataSize));
                                tbEntity.setNumFiles(Long.valueOf(numFiles == null ? "0" : numFiles));
                                tbEntity.setPartitionKeys(partitionKeys);
                                tbEntity.setQualifiedName(rs_qualifiedName);
                                tbEntity.setOwner((String)atlasEntity.getAttribute("owner"));
                                tbEntity.setLocation(location);
                                tbEntity.setCreatedBy(createBy);
                                tbEntity.setCreateTime(createTime.getTime());
                                tbEntity.setUpdatedBy(updateBy);
                                tbEntity.setUpdateTime(updateTime.getTime());
                                tbEntity.setVersion(version);
                                tbEntity.setLastAccessTime((Long)atlasEntity.getAttribute("lastAccessTime"));
                                tbEntity.setQueryText(ddlStr);
                                tbEntity.setRecordCreateTime(new Date());
                                tbEntity.setRecordStatus("1");
                                relatColumnList = (List)atlasEntity.getRelationshipAttribute("columns");
                                tbEntity.setColumnNum(relatColumnList.size());
                                this.tableEntityList.add(tbEntity);
                                log.info("--------The result of parsing the export is:the table {} has column num {}--------", tbEntity.getQualifiedName(), tbEntity.getColumnNum());
                            } else {
                                String originalText = (String)atlasEntity.getAttribute("viewOriginalText");
                                totalSize = (String)atlasEntity.getAttribute("viewExpandedText");
                                numRows = "";
                                List<Map<String, Object>> ddlInfoList = (List)atlasEntity.getRelationshipAttribute("ddlQueries");
                                AtlasEntity ddlReferredEntity;
                                if (ddlInfoList != null && ddlInfoList.size() > 0) {
                                    for(Iterator var47 = ddlInfoList.iterator(); var47.hasNext(); numRows = numRows + ddlReferredEntity.getAttribute("queryText") + ";") {
                                        Map<String, Object> ddlInfo = (Map)var47.next();
                                        partitionKeys = (String)ddlInfo.get("guid");
                                        ddlReferredEntity = extInfoEntity.getReferredEntity(partitionKeys);
                                    }
                                }

                                AtlasHiveViewEntity viewEntity = new AtlasHiveViewEntity();
                                viewEntity.setId(SnowFlakeUtil.getInstance().nextId());
                                viewEntity.setGuid(guid);
                                viewEntity.setName(name);
                                viewEntity.setDbGuid(dbGuid);
                                viewEntity.setDbName(databaseName);
                                viewEntity.setDescription(desc);
                                viewEntity.setComment(comment);
                                viewEntity.setTypeName(entityTypeName);
                                viewEntity.setTableType(tableType);
                                viewEntity.setViewStatus(status);
                                viewEntity.setTemporary(isTemporary);
                                viewEntity.setQualifiedName(rs_qualifiedName);
                                viewEntity.setOwner((String)atlasEntity.getAttribute("owner"));
                                viewEntity.setCreatedBy(createBy);
                                viewEntity.setCreateTime(createTime.getTime());
                                viewEntity.setUpdatedBy(updateBy);
                                viewEntity.setUpdateTime(updateTime.getTime());
                                viewEntity.setVersion(version);
                                viewEntity.setLastAccessTime((Long)atlasEntity.getAttribute("lastAccessTime"));
                                viewEntity.setViewOriginalText(originalText);
                                viewEntity.setViewExpandedText(totalSize);
                                viewEntity.setQueryText(numRows);
                                viewEntity.setRecordCreateTime(new Date());
                                viewEntity.setRecordStatus("1");
                                relatColumnList = (List)atlasEntity.getRelationshipAttribute("columns");
                                viewEntity.setColumnNum(relatColumnList.size());
                                this.viewEntityList.add(viewEntity);
                                log.info("--------The result of parsing the export is:the view {} has column num {}--------", viewEntity.getQualifiedName(), viewEntity.getColumnNum());
                            }

                            relatColumnList = (List)atlasEntity.getRelationshipAttribute("columns");

                            AtlasHiveColumnEntity columnEntity;
                            for(Iterator var44 = relatColumnList.iterator(); var44.hasNext(); this.columnEntityList.add(columnEntity)) {
                                Map<String, Object> relatColumn = (Map)var44.next();
                                rawDataSize = (String)relatColumn.get("guid");
                                AtlasEntity colReferredEntity = extInfoEntity.getReferredEntity(rawDataSize);
                                columnEntity = new AtlasHiveColumnEntity();
                                columnEntity.setId(SnowFlakeUtil.getInstance().nextId());
                                columnEntity.setGuid(colReferredEntity.getGuid());
                                columnEntity.setName((String)colReferredEntity.getAttribute("name"));
                                columnEntity.setDescription((String)colReferredEntity.getAttribute("description"));
                                columnEntity.setComment((String)colReferredEntity.getAttribute("comment"));
                                columnEntity.setDataType((String)colReferredEntity.getAttribute("type"));
                                columnEntity.setColumnStatus(colReferredEntity.getStatus().toString());
                                columnEntity.setTableGuid(guid);
                                columnEntity.setTableName(name);
                                columnEntity.setTypeName(colReferredEntity.getTypeName());
                                columnEntity.setOwner((String)colReferredEntity.getAttribute("owner"));
                                columnEntity.setQualifiedName((String)colReferredEntity.getAttribute("qualifiedName"));
                                columnEntity.setVersion(colReferredEntity.getVersion());
                                columnEntity.setCreatedBy(colReferredEntity.getCreatedBy());
                                columnEntity.setCreateTime(colReferredEntity.getCreateTime().getTime());
                                columnEntity.setUpdatedBy(colReferredEntity.getUpdatedBy());
                                columnEntity.setUpdateTime(colReferredEntity.getUpdateTime().getTime());
                                columnEntity.setPosition(colReferredEntity.getAttribute("position") == null ? 0 : (Integer)colReferredEntity.getAttribute("position"));
                                columnEntity.setRecordCreateTime(new Date());
                                columnEntity.setRecordStatus("1");
                                if (!"VIRTUAL_VIEW".equals(tableType)) {
                                    columnEntity.setColumnType("hive_table_cloumn");
                                } else {
                                    columnEntity.setColumnType("hive_view_cloumn");
                                }
                            }
                        }
                    }

                    if (this.isClear) {
                        log.info("Clean up the exported temporary ZIP files");
                        result.clear();
                    }
                    break;
                }
            }
        }

    }

    public void getTableMetadata(String qualifiedName) throws Exception {
        AtlasEntityWithExtInfo entityWithExtInfo = this.atlasMetadataClient.getHiveTableMetaData(qualifiedName);
        AtlasEntity entity = entityWithExtInfo.getEntity();
        String guid = entity.getGuid();
        String name = (String)entity.getAttribute("name");
        String description = (String)entity.getAttribute("description");
        String comment = (String)entity.getAttribute("comment");
        String tableType = (String)entity.getAttribute("tableType");
        String status = entity.getStatus().toString();
        Date createTime = entity.getCreateTime();
        Date updateTime = entity.getUpdateTime();
        Map<String, Object> dbinfoMap = (Map)entity.getRelationshipAttribute("db");
        String dbGuid = (String)dbinfoMap.get("guid");
        String databaseName = (String)dbinfoMap.get("displayText");
        boolean isTemporary = (Boolean)entity.getAttribute("temporary");
        String typeName = entity.getTypeName();
        String ddl = "";
        List<Map<String, Object>> ddlInfoList = (List)entity.getRelationshipAttribute("ddlQueries");
        String numRows;
        AtlasEntity ddlReferredEntity;
        if (ddlInfoList != null && ddlInfoList.size() > 0) {
            for(Iterator var19 = ddlInfoList.iterator(); var19.hasNext(); ddl = ddl + ddlReferredEntity.getAttribute("queryText") + ";") {
                Map<String, Object> ddlInfo = (Map)var19.next();
                numRows = (String)ddlInfo.get("guid");
                ddlReferredEntity = entityWithExtInfo.getReferredEntity(numRows);
            }
        }

        Map<String, Object> paramMap = (Map)entity.getAttribute("parameters");
        String totalSize = (String)paramMap.get("totalSize");
        numRows = (String)paramMap.get("numRows");
        String rawDataSize = (String)paramMap.get("rawDataSize");
        String numFiles = (String)paramMap.get("numFiles");
        List<Map<String, Object>> partitionKeyList = (List)entity.getRelationshipAttribute("partitionKeys");
        String partitionKeys = "";
        Iterator var26 = partitionKeyList.iterator();

        while(var26.hasNext()) {
            Map<String, Object> partitionKey = (Map)var26.next();
            if (partitionKey.containsKey("displayText")) {
                partitionKeys = partitionKeys + (String)partitionKey.get("displayText") + ",";
            }
        }

        partitionKeys = "".equals(partitionKeys) ? "" : partitionKeys.substring(0, partitionKeys.length() - 1);
        Map<String, Object> sdMap = (Map)entity.getAttribute("sd");
        String location = "";
        String owner;
        if (sdMap != null && sdMap.containsKey("guid")) {
            owner = (String)sdMap.get("guid");
            AtlasEntity sdReferredEntity = entityWithExtInfo.getReferredEntity(owner);
            location = (String)sdReferredEntity.getAttribute("location");
        }

        owner = (String)entity.getAttribute("owner");
        long lastAccessTime = (Long)entity.getAttribute("lastAccessTime");
        String entityQualifiedName = (String)entity.getAttribute("qualifiedName");
        String createdBy = entity.getCreatedBy();
        String updatedBy = entity.getUpdatedBy();
        long version = entity.getVersion();
        AtlasHiveTableEntity tbEntity = new AtlasHiveTableEntity();
        tbEntity.setId(SnowFlakeUtil.getInstance().nextId());
        tbEntity.setGuid(guid);
        tbEntity.setName(name);
        tbEntity.setDbGuid(dbGuid);
        tbEntity.setDbName(databaseName);
        tbEntity.setDescription(description);
        tbEntity.setComment(comment);
        tbEntity.setTypeName(typeName);
        tbEntity.setTableType(tableType);
        tbEntity.setTbStatus(status);
        tbEntity.setTemporary(isTemporary);
        tbEntity.setTotalSize(Double.valueOf(totalSize == null ? "0" : totalSize));
        tbEntity.setNumRows(Long.valueOf(numRows == null ? "0" : numRows));
        tbEntity.setRowDataSize(Double.valueOf(rawDataSize == null ? "0" : rawDataSize));
        tbEntity.setNumFiles(Long.valueOf(numFiles == null ? "0" : numFiles));
        tbEntity.setPartitionKeys(partitionKeys);
        tbEntity.setQualifiedName(entityQualifiedName);
        tbEntity.setOwner(owner);
        tbEntity.setLocation(location);
        tbEntity.setCreatedBy(createdBy);
        tbEntity.setCreateTime(createTime.getTime());
        tbEntity.setUpdatedBy(updatedBy);
        tbEntity.setUpdateTime(updateTime.getTime());
        tbEntity.setVersion(version);
        tbEntity.setLastAccessTime(lastAccessTime);
        tbEntity.setQueryText(ddl);
        tbEntity.setRecordCreateTime(new Date());
        tbEntity.setRecordStatus("1");
        List<Map<String, Object>> columns = (List)entity.getRelationshipAttribute("columns");
        tbEntity.setColumnNum(columns.size());
        this.tableEntityList.add(tbEntity);
        List<Map<String, Object>> relatColumnList = (List)entity.getRelationshipAttribute("columns");

        AtlasHiveColumnEntity columnEntity;
        for(Iterator var39 = relatColumnList.iterator(); var39.hasNext(); this.columnEntityList.add(columnEntity)) {
            Map<String, Object> relatColumn = (Map)var39.next();
            String columnGuid = (String)relatColumn.get("guid");
            AtlasEntity colReferredEntity = entityWithExtInfo.getReferredEntity(columnGuid);
            columnEntity = new AtlasHiveColumnEntity();
            columnEntity.setId(SnowFlakeUtil.getInstance().nextId());
            columnEntity.setGuid(colReferredEntity.getGuid());
            columnEntity.setName((String)colReferredEntity.getAttribute("name"));
            columnEntity.setDescription((String)colReferredEntity.getAttribute("description"));
            columnEntity.setComment((String)colReferredEntity.getAttribute("comment"));
            columnEntity.setDataType((String)colReferredEntity.getAttribute("type"));
            columnEntity.setColumnStatus(colReferredEntity.getStatus().toString());
            columnEntity.setTableGuid(guid);
            columnEntity.setTableName(name);
            columnEntity.setTypeName(colReferredEntity.getTypeName());
            columnEntity.setOwner((String)colReferredEntity.getAttribute("owner"));
            columnEntity.setQualifiedName((String)colReferredEntity.getAttribute("qualifiedName"));
            columnEntity.setVersion(colReferredEntity.getVersion());
            columnEntity.setCreatedBy(colReferredEntity.getCreatedBy());
            columnEntity.setCreateTime(colReferredEntity.getCreateTime().getTime());
            columnEntity.setUpdatedBy(colReferredEntity.getUpdatedBy());
            columnEntity.setUpdateTime(colReferredEntity.getUpdateTime().getTime());
            columnEntity.setPosition(colReferredEntity.getAttribute("position") == null ? 0 : (Integer)colReferredEntity.getAttribute("position"));
            columnEntity.setRecordCreateTime(new Date());
            columnEntity.setRecordStatus("1");
            if (!"VIRTUAL_VIEW".equals(tableType)) {
                columnEntity.setColumnType("hive_table_cloumn");
            } else {
                columnEntity.setColumnType("hive_view_cloumn");
            }
        }

    }

    private void metricVerify(Map<String, Integer> metricMap) {
        try {
            if (metricMap != null && metricMap.size() >= 3) {
                int dbNum = metricMap.containsKey("entity:hive_db") ? metricMap.get("entity:hive_db") : 0;
                int tableNum = metricMap.containsKey("entity:hive_table") ? metricMap.get("entity:hive_table") : 0;
                int columnNum = metricMap.containsKey("entity:hive_column") ? metricMap.get("entity:hive_column") : 0;
                log.info("-------Statistics:export database entity {}, table entity {},column entity {}-------", new Object[]{dbNum, tableNum, columnNum});
                log.info("-------Statistics:parsed database entity {}, table entity {},column entity {}-------", new Object[]{this.databaseEntityList.size(), this.tableEntityList.size() + this.viewEntityList.size(), this.columnEntityList.size()});
                if (dbNum != this.databaseEntityList.size()) {
                    log.warn("=======Export database num {} is not equal to parsed database size {}=======", (Object)dbNum, this.databaseEntityList);
                }
                if (tableNum != this.tableEntityList.size() + this.viewEntityList.size()) {
                    log.warn("=======Export table num {} is not equal to parsed table size {}=======", (Object)tableNum, (Object)(this.tableEntityList.size() + this.viewEntityList.size()));
                }
                if (columnNum != this.columnEntityList.size()) {
                    log.warn("=======Export column num {} is not equal to parsed column size {}=======", (Object)columnNum, (Object)this.columnEntityList.size());
                }
            }
        }
        catch (Exception e) {
            log.error("-------Verify metric error-------", (Throwable)e);
        }
    }

    public AtlasMetadataClient getAtlasMetadataClient() {
        return this.atlasMetadataClient;
    }

    public List<AtlasHiveDatabaseEntity> getDatabaseEntityList() {
        return this.databaseEntityList;
    }

    public List<AtlasHiveViewEntity> getViewEntityList() {
        return this.viewEntityList;
    }

    public List<AtlasHiveTableEntity> getTableEntityList() {
        return this.tableEntityList;
    }

    public List<AtlasHiveColumnEntity> getColumnEntityList() {
        return this.columnEntityList;
    }

    public Long getChangeMarker() {
        return this.changeMarker;
    }

    public boolean isClear() {
        return this.isClear;
    }

    public void setAtlasMetadataClient(AtlasMetadataClient atlasMetadataClient) {
        this.atlasMetadataClient = atlasMetadataClient;
    }

    public void setDatabaseEntityList(List<AtlasHiveDatabaseEntity> databaseEntityList) {
        this.databaseEntityList = databaseEntityList;
    }

    public void setViewEntityList(List<AtlasHiveViewEntity> viewEntityList) {
        this.viewEntityList = viewEntityList;
    }

    public void setTableEntityList(List<AtlasHiveTableEntity> tableEntityList) {
        this.tableEntityList = tableEntityList;
    }

    public void setColumnEntityList(List<AtlasHiveColumnEntity> columnEntityList) {
        this.columnEntityList = columnEntityList;
    }

    public void setChangeMarker(Long changeMarker) {
        this.changeMarker = changeMarker;
    }

    public void setClear(boolean isClear) {
        this.isClear = isClear;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AtlasMetadataProxy)) {
            return false;
        }
        AtlasMetadataProxy other = (AtlasMetadataProxy)o;
        if (!other.canEqual(this)) {
            return false;
        }
        AtlasMetadataClient this$atlasMetadataClient = this.getAtlasMetadataClient();
        AtlasMetadataClient other$atlasMetadataClient = other.getAtlasMetadataClient();
        if (this$atlasMetadataClient == null ? other$atlasMetadataClient != null : !this$atlasMetadataClient.equals(other$atlasMetadataClient)) {
            return false;
        }
        List<AtlasHiveDatabaseEntity> this$databaseEntityList = this.getDatabaseEntityList();
        List<AtlasHiveDatabaseEntity> other$databaseEntityList = other.getDatabaseEntityList();
        if (this$databaseEntityList == null ? other$databaseEntityList != null : !((Object)this$databaseEntityList).equals(other$databaseEntityList)) {
            return false;
        }
        List<AtlasHiveViewEntity> this$viewEntityList = this.getViewEntityList();
        List<AtlasHiveViewEntity> other$viewEntityList = other.getViewEntityList();
        if (this$viewEntityList == null ? other$viewEntityList != null : !((Object)this$viewEntityList).equals(other$viewEntityList)) {
            return false;
        }
        List<AtlasHiveTableEntity> this$tableEntityList = this.getTableEntityList();
        List<AtlasHiveTableEntity> other$tableEntityList = other.getTableEntityList();
        if (this$tableEntityList == null ? other$tableEntityList != null : !((Object)this$tableEntityList).equals(other$tableEntityList)) {
            return false;
        }
        List<AtlasHiveColumnEntity> this$columnEntityList = this.getColumnEntityList();
        List<AtlasHiveColumnEntity> other$columnEntityList = other.getColumnEntityList();
        if (this$columnEntityList == null ? other$columnEntityList != null : !((Object)this$columnEntityList).equals(other$columnEntityList)) {
            return false;
        }
        Long this$changeMarker = this.getChangeMarker();
        Long other$changeMarker = other.getChangeMarker();
        if (this$changeMarker == null ? other$changeMarker != null : !((Object)this$changeMarker).equals(other$changeMarker)) {
            return false;
        }
        return this.isClear() == other.isClear();
    }

    protected boolean canEqual(Object other) {
        return other instanceof AtlasMetadataProxy;
    }

    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        AtlasMetadataClient $atlasMetadataClient = this.getAtlasMetadataClient();
        result = result * 59 + ($atlasMetadataClient == null ? 43 : $atlasMetadataClient.hashCode());
        List<AtlasHiveDatabaseEntity> $databaseEntityList = this.getDatabaseEntityList();
        result = result * 59 + ($databaseEntityList == null ? 43 : ((Object)$databaseEntityList).hashCode());
        List<AtlasHiveViewEntity> $viewEntityList = this.getViewEntityList();
        result = result * 59 + ($viewEntityList == null ? 43 : ((Object)$viewEntityList).hashCode());
        List<AtlasHiveTableEntity> $tableEntityList = this.getTableEntityList();
        result = result * 59 + ($tableEntityList == null ? 43 : ((Object)$tableEntityList).hashCode());
        List<AtlasHiveColumnEntity> $columnEntityList = this.getColumnEntityList();
        result = result * 59 + ($columnEntityList == null ? 43 : ((Object)$columnEntityList).hashCode());
        Long $changeMarker = this.getChangeMarker();
        result = result * 59 + ($changeMarker == null ? 43 : ((Object)$changeMarker).hashCode());
        result = result * 59 + (this.isClear() ? 79 : 97);
        return result;
    }

    public String toString() {
        return "AtlasMetadataProxy(atlasMetadataClient=" + this.getAtlasMetadataClient() + ", databaseEntityList=" + this.getDatabaseEntityList() + ", viewEntityList=" + this.getViewEntityList() + ", tableEntityList=" + this.getTableEntityList() + ", columnEntityList=" + this.getColumnEntityList() + ", changeMarker=" + this.getChangeMarker() + ", isClear=" + this.isClear() + ")";
    }
}
