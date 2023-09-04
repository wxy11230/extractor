/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dtb.metadatahub.entity.ChangeMakerDO
 *  org.apache.ibatis.annotations.Mapper
 */
package com.dtb.metadatahub.repository.mysql;

import com.dtb.metadatahub.entity.ChangeMakerDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChangeMakerDao {
    public ChangeMakerDO getByQualifiedName(String var1);

    public void addChangeMaker(ChangeMakerDO var1);

    public void updateChangeMaker(ChangeMakerDO var1);
}
