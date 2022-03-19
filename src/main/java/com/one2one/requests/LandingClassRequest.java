package com.one2one.requests;

import com.one2one.entities.LandingClass;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor
public class LandingClassRequest {
    private Long landingClassId;
    private String landingClassName;
    private String imageUrl;
    private Long classId;
    private String description;

    public LandingClass to(LandingClassRequest request) {
        LandingClass landingClass = new LandingClass();
        BeanUtils.copyProperties(request, landingClass);
        return landingClass;
    }

    public void update(LandingClassRequest request, LandingClass landingClass) {
        BeanUtils.copyProperties(request, landingClass);
    }
}
