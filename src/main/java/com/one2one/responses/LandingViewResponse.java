package com.one2one.responses;

import com.one2one.entities.LandingView;
import com.one2one.entities.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor

public class LandingViewResponse {
    private Long landingViewId;
    private Long classId;
    private Long promotionId;
    private Long reviewId;


    public static LandingViewResponse from(LandingView landingView) {
        LandingViewResponse response = new LandingViewResponse();
        BeanUtils.copyProperties(landingView, response);
        return response;
    }
}
