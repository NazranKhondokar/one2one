package com.one2one.requests;

import com.one2one.entities.PackageClass;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class PackageClassListRequest {

    private Long packageId;
    private List<PackageClassRequest> packageClasses;
}

