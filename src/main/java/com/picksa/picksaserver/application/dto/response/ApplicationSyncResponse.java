package com.picksa.picksaserver.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ApplicationSyncResponse(
        LocalDateTime until,
        List<ApplicationDetailResponse> applications
) {

    public static ApplicationSyncResponse of(LocalDateTime until, List<ApplicationDetailResponse> responses) {
        return new ApplicationSyncResponse(until, responses);
    }

}
