package com.picksa.picksaserver.evaluation.dto.response;

public record DecideResponse(
    Long applicantId,
    String result
) {
    
    public static DecideResponse from(Long applicantId, String result) {
        return new DecideResponse(
            applicantId,
            result
        );
    }

}
