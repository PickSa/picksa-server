package com.picksa.picksaserver.application.service.converter;


import com.picksa.picksaserver.application.domain.ApplicationEntity;
import com.picksa.picksaserver.application.domain.OriginalApplicationEntity;
import com.picksa.picksaserver.global.domain.Part;

public interface ApplicationCoverter {

    Part part();

    ApplicationEntity convertOriginalToApplication(OriginalApplicationEntity original, String interviewAvailables, Long originalId, int generation);

}
