package com.amazingwomenstory.covidvaccine.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEntity {

    public static final String TABLE_PREFIX = "covidvaccine_";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "modified_on")
    private Date modifiedOn;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @PrePersist
    public void onPrePersist() {
        this.createdOn = new Date();
        this.createdBy = this.modifiedBy == null ? EntityModifier.COVIDVACCIE_BACKEND.name() : this.createdBy;
        this.modifiedOn = new Date();
        this.modifiedBy = this.modifiedBy == null ? EntityModifier.COVIDVACCIE_BACKEND.name() : this.modifiedBy;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.modifiedOn = new Date();
        this.modifiedBy = this.modifiedBy == null ? EntityModifier.COVIDVACCIE_BACKEND.name() : this.modifiedBy;
    }
}
