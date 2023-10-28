package com.bloodbanksystem.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonorDiseaseDTO {
    private Long donorId;
    private String disease;
}
