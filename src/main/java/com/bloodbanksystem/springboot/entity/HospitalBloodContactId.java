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
public class HospitalBloodContactId implements Serializable {


    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(name = "hospitalId")
    private Long hospitalId;

    @NotNull
    @Column(name = "bloodBankId")
    private Long bloodBankId;

}

