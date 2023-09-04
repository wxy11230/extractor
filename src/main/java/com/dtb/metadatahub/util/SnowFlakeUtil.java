/*
 * Decompiled with CFR 0.152.
 */
package com.dtb.metadatahub.util;

public class SnowFlakeUtil {
    public static volatile SnowFlakeUtil snowFlake;
    private static final long START_STAMP = 1480166465631L;
    private static final long SEQUENCE_BIT = 12L;
    private static final long MACHINE_BIT = 5L;
    private static final long DATA_CENTER_BIT = 5L;
    private static final long MAX_DATA_CENTER_NUM = 31L;
    private static final long MAX_MACHINE_NUM = 31L;
    private static final long MAX_SEQUENCE = 4095L;
    private static final long MACHINE_LEFT = 12L;
    private static final long DATA_CENTER_LEFT = 17L;
    private static final long TIMESTAMP_LEFT = 22L;
    private long dataCenterId;
    private long machineId;
    private long sequence = 0L;
    private long lastStamp = -1L;

    public SnowFlakeUtil(long dataCenterId, long machineId) {
        if (dataCenterId > 31L || dataCenterId < 0L) {
            throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0");
        }
        if (machineId > 31L || machineId < 0L) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static SnowFlakeUtil getInstance() {
        if (snowFlake != null) return snowFlake;
        Class<SnowFlakeUtil> clazz = SnowFlakeUtil.class;
        synchronized (SnowFlakeUtil.class) {
            if (snowFlake != null) return snowFlake;
            snowFlake = new SnowFlakeUtil(5L, 5L);
            // ** MonitorExit[var0] (shouldn't be in output)
            return snowFlake;
        }
    }

    public synchronized long nextId() {
        long currStamp = this.getNewStamp();
        if (currStamp < this.lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        if (currStamp == this.lastStamp) {
            this.sequence = this.sequence + 1L & 0xFFFL;
            if (this.sequence == 0L) {
                currStamp = this.getNextMill();
            }
        } else {
            this.sequence = 0L;
        }
        this.lastStamp = currStamp;
        return currStamp - 1480166465631L << 22 | this.dataCenterId << 17 | this.machineId << 12 | this.sequence;
    }

    private long getNextMill() {
        long mill = this.getNewStamp();
        while (mill <= this.lastStamp) {
            mill = this.getNewStamp();
        }
        return mill;
    }

    private long getNewStamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        SnowFlakeUtil flakeUtils = new SnowFlakeUtil(5L, 5L);
        long id = flakeUtils.nextId();
        System.out.println(id);
    }
}
