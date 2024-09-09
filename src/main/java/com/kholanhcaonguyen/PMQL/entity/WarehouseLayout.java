package com.kholanhcaonguyen.PMQL.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "warehouse_layout")
public class WarehouseLayout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row_name")
    private String rowName;

    @Column(name = "storage_position")
    private String storagePosition;

    @Column(name = "row_number")
    private Integer rowNumber;

    @Column(name = "sequence_number")
    private Integer sequenceNumber;

    @Column(name = "level")
    private Integer level;

    @Column(name = "in_use")
    private Boolean inUse;

    @Column(name = "notes")
    private String notes;

    @Column(name = "code")
    private String code;

    @Column(name = "top_value")
    private String topValue;

    @Column(name = "low_value")
    private String lowValue;

    @Column(name = "extra_info")
    private String extraInfo;

    @Column(name = "store")
    private String store;

    @Column(name = "lock_status")
    private Boolean lockStatus;

    @Column(name = "created_name")
    private String createdName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public String getStoragePosition() {
        return storagePosition;
    }

    public void setStoragePosition(String storagePosition) {
        this.storagePosition = storagePosition;
    }

    public Integer getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(Integer rowNumber) {
        this.rowNumber = rowNumber;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTopValue() {
        return topValue;
    }

    public void setTopValue(String topValue) {
        this.topValue = topValue;
    }

    public String getLowValue() {
        return lowValue;
    }

    public void setLowValue(String lowValue) {
        this.lowValue = lowValue;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public Boolean getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Boolean lockStatus) {
        this.lockStatus = lockStatus;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
