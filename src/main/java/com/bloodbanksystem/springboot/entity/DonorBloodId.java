package com.bloodbanksystem.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonorBloodId implements Serializable {
    private static final long serialVersionUID = 1L;


    @NotNull
    @Column(name = "DonorId")
    private Long DonorId;


    @NotNull
    @Column(name = "BloodId")
    private Long BloodId;

}
