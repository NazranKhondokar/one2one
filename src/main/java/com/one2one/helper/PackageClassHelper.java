package com.one2one.helper;

import com.one2one.entities.BaseEntity;
import com.one2one.entities.Package;
import com.one2one.entities.PackageClass;
import com.one2one.entities.composite.PackageClassCompositeKey;
import com.one2one.enums.RecordStatus;
import com.one2one.requests.PackageClassListRequest;
import com.one2one.requests.PackageClassRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PackageClassHelper {

    private void setBaseEntityProperties(BaseEntity baseEntity, RecordStatus recordStatus) {
        baseEntity.setCreatedBy(1L);
        baseEntity.setUpdatedBy(1L);
        baseEntity.setRecordStatus(recordStatus);
    }

    public void setPackageClass(PackageClassListRequest dto, Package packageEntity,
                                 RecordStatus recordStatus) {
        Map<String, Integer> packageClassIdToVersion = new HashMap<>();

        for (PackageClass packageClass : packageEntity.getPackageClasses()) {
            packageClassIdToVersion.put("%s-%s-%s-%s-%s".formatted(packageEntity.getId(),
                    packageClass.getCompositeKey().getAcademicClassId(),
                    packageClass.getCompositeKey().getSubjectId(),
                    packageClass.getCompositeKey().getStudentUserId(),
                    packageClass.getCompositeKey().getTeacherUserId()),
                    packageClass.getRecordVersion());
        }
        packageEntity.getPackageClasses().clear();

        if (Objects.isNull(dto.getPackageClasses())) return;

        for (PackageClassRequest packageClassRequest : dto.getPackageClasses()) {
            PackageClass packageClass = new PackageClass();
            packageClass.setPackageClassIsPaid(packageClassRequest.getPackageClassIsPaid());
            packageClass.setPackageClassIsEnrolled(packageClassRequest.getPackageClassIsEnrolled());
            packageClass.setRecordVersion(packageClassIdToVersion
                    .getOrDefault("%s-%s-%s-%s-%s".formatted(
                            packageEntity.getId(),
                            packageClassRequest.getAcademicClassId(),
                            packageClassRequest.getSubjectId(),
                            packageClassRequest.getStudentUserId(),
                            packageClassRequest.getTeacherUserId()), 0));
            packageClass.setCompositeKey(new PackageClassCompositeKey(
                    packageEntity, packageClassRequest.getAcademicClassId(),
                    packageClassRequest.getSubjectId(),
                    packageClassRequest.getStudentUserId(),
                    packageClassRequest.getTeacherUserId()));
            setBaseEntityProperties(packageClass, recordStatus);
            packageEntity.getPackageClasses().add(packageClass);
        }
    }
}
