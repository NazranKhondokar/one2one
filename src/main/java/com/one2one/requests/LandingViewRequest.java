package com.one2one.requests;

import com.one2one.entities.LandingView;
import com.one2one.entities.Subject;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LandingViewRequest {

    private Long landingview_id;
    private Long class_id;
    private Long promotion_id;
    private Long review_id;

    public LandingView to(LandingViewRequest request) {
        LandingView landingView = new LandingView();
        BeanUtils.copyProperties(request, landingView);
        return landingView;
    }

    public void update(LandingViewRequest request, LandingView landingView) {
        BeanUtils.copyProperties(request, landingView);
    }
}
