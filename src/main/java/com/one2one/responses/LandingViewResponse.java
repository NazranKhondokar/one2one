package com.one2one.responses;

import com.one2one.entities.LandingView;
import com.one2one.entities.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@NoArgsConstructor

public class LandingViewResponse {
    private Long landingview_id;
    private Long class_id;
    private Long promotion_id;
    private Long review_id;


    public static LandingViewResponse from(LandingView landingView) {
        LandingViewResponse response = new LandingViewResponse();
        BeanUtils.copyProperties(landingView, response);
        return response;
    }
}
