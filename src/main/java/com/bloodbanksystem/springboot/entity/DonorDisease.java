package com.bloodbanksystem.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "donordisease")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonorDisease implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private DonorDiseaseId donorDiseaseId;
}
