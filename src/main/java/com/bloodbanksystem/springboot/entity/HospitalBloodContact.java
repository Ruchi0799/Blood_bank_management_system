package com.bloodbanksystem.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "hospitalbloodcontact")
public class HospitalBloodContact implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private HospitalBloodContactId id;
}

