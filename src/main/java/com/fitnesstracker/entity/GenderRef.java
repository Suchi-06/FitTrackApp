package com.fitnesstracker.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "gender_ref")
public class GenderRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_id")
    private Long refId;

    @Column(name = "gender_description", nullable = false)
    private String genderDescription;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "genderRefId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Users> users;

    // Constructors, Getters, Setters
    public GenderRef() {}

    public GenderRef(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getGenderDescription() {
        return genderDescription;
    }

    public void setGenderDescription(String genderDescription) {
        this.genderDescription = genderDescription;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}