package com.picksa.picksaserver.question.dto.request;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

public record QuestionUpdateFinalRequest(Long id, Boolean isDetermined) {
}
