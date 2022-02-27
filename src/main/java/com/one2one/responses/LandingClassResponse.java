package com.one2one.responses;

import com.one2one.entities.LandingClass;
import com.one2one.requests.LandingClassRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor

public class LandingClassResponse {
    private Long landingClassId;
    private String landingClassName;
    private String imageUrl;
    private Long ClassId;
    private String description;

    public static LandingClassResponse from(LandingClass landingClass) {
        LandingClassResponse response = new LandingClassResponse();
        BeanUtils.copyProperties(landingClass, response);
        return response;
    }
}
