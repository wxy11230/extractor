/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.atlas.client.AtlasApiClient
 *  org.apache.atlas.client.model.AtlasExportResult
 *  org.apache.atlas.model.discovery.AtlasSearchResult
 *  org.apache.atlas.model.impexp.AtlasExportRequest
 *  org.apache.atlas.model.instance.AtlasEntity$AtlasEntityWithExtInfo
 *  org.apache.atlas.model.instance.AtlasEntityHeader
 *  org.apache.atlas.model.instance.AtlasObjectId
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.dtb.metadatahub.step.atlas;

import com.dtb.metadatahub.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.atlas.client.AtlasApiClient;
import org.apache.atlas.client.model.AtlasExportResult;
import org.apache.atlas.model.discovery.AtlasSearchResult;
import org.apache.atlas.model.impexp.AtlasExportRequest;
import org.apache.atlas.model.instance.AtlasEntity;
import org.apache.atlas.model.instance.AtlasEntityHeader;
import org.apache.atlas.model.instance.AtlasObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AtlasMetadataClient {
    private static final Logger log = LoggerFactory.getLogger(AtlasMetadataClient.class);
    private AtlasApiClient atlasApiClient = new AtlasApiClient();

    public AtlasEntity.AtlasEntityWithExtInfo getHiveTableMetaData(String qualifiedName) throws Exception {
        HashMap<String, String> uniqAttributes = new HashMap<String, String>();
        uniqAttributes.put("qualifiedName", qualifiedName);
        AtlasEntity.AtlasEntityWithExtInfo entityWithExtInfo = this.atlasApiClient.getEntityByAttribute("hive_table", uniqAttributes);
        return entityWithExtInfo;
    }

    public AtlasExportResult getHiveMetaData(String qualifiedName, String typeName) throws Exception {
        return this.getHiveMetaData(qualifiedName, typeName, 0L);
    }

    public AtlasExportResult getHiveMetaData(String qualifiedName, String typeName, long changeMarker) throws Exception {
        return this.getHiveMetaData(qualifiedName, typeName, changeMarker, true);
    }

    public AtlasExportResult getHiveMetaData(String qualifiedName, String typeName, long changeMarker, boolean skipLineage) throws Exception {
        log.info("Export the {} entity {} metadata with the changerMarker {}", new Object[]{typeName, qualifiedName, changeMarker});
        AtlasObjectId atlasObjectId = new AtlasObjectId(typeName, "qualifiedName", (Object)qualifiedName);
        ArrayList<AtlasObjectId> itemsToExport = new ArrayList<AtlasObjectId>();
        itemsToExport.add(atlasObjectId);
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("fetchType", "incremental");
        options.put("changeMarker", changeMarker);
        options.put("matchType", "matches");
        options.put("skipLineage", skipLineage);
        options.put("ignoreProcessRelationships", true);
        AtlasExportRequest request = new AtlasExportRequest();
        request.setItemsToExport(itemsToExport);
        request.setOptions(options);
        AtlasExportResult result = this.atlasApiClient.exportMetadata(request);
        return result;
    }

    public List<String> getDBQualifiedNames(String clusterName) throws Exception {
        AtlasSearchResult result = this.atlasApiClient.basicSearch("hive_db", (String)null, "", true, 10000, 0);
        List<String> qualifiedNameList = new ArrayList();
        if (result != null) {
            List<AtlasEntityHeader> entityHeaders = result.getEntities();
            Iterator var5 = entityHeaders.iterator();

            while(var5.hasNext()) {
                AtlasEntityHeader entityHeader = (AtlasEntityHeader)var5.next();
                if (entityHeader.getTypeName().equalsIgnoreCase("hive_db")) {
                    String qualifiedName = (String)entityHeader.getAttribute("qualifiedName");
                    if (!StringUtil.isEmpty(clusterName)) {
                        String tmpClusterName = "";
                        if (!StringUtil.isEmpty(qualifiedName)) {
                            tmpClusterName = qualifiedName.substring(qualifiedName.indexOf("@") + 1, qualifiedName.length());
                        }

                        if (tmpClusterName.equalsIgnoreCase(clusterName)) {
                            qualifiedNameList.add(qualifiedName);
                        }
                    } else {
                        qualifiedNameList.add(qualifiedName);
                    }
                }
            }
        }
        return qualifiedNameList;
    }
}
