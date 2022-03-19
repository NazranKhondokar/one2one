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

    private Long landingViewId;
    private Long classId;
    private Long promotionId;
    private Long reviewId;

    public LandingView to(LandingViewRequest request) {
        LandingView landingView = new LandingView();
        BeanUtils.copyProperties(request, landingView);
        return landingView;
    }

    public void update(LandingViewRequest request, LandingView landingView) {
        BeanUtils.copyProperties(request, landingView);
    }
}
