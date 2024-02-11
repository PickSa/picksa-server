package com.picksa.picksaserver.application.service.converter;

import com.picksa.picksaserver.applicant.domain.InterviewScheduleEntity;
import com.picksa.picksaserver.application.domain.InterviewAvailable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InterviewAvailableConverter {

    private static final String BLANK = "";
    private static final long TIME_UNIT = 60;
    private static final char AVAILABLE = '1';

    public String formatTotalToBinary(InterviewAvailable interviewAvailable, List<InterviewScheduleEntity> interviewSchedules) {

        List<String> interviewAvailables = new ArrayList<>();
        interviewAvailables.add(interviewAvailable.getDay1());
        interviewAvailables.add(interviewAvailable.getDay2());
        interviewAvailables.add(interviewAvailable.getDay3());

        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i<interviewSchedules.size(); i++) {
            stringBuilder.append(formatDayToBinary(interviewAvailables.get(i), interviewSchedules.get(i)));
        }

        return stringBuilder.toString();
    }


    private String formatDayToBinary(String original, InterviewScheduleEntity schedule) {

        String bianaryFormatOfDay = getBaseFormat(schedule);

        if (!StringUtils.hasText(original)) {
            return bianaryFormatOfDay;
        }

        String[] choices = splitChoiceOriginal(original);

        for(String choice:choices) {
            LocalTime startOfTime = getStartOfTime(choice);
            int indexOfTime = getIndexOfTime(schedule.getStartAt(), startOfTime);
            bianaryFormatOfDay = setTimeAvailable(bianaryFormatOfDay, indexOfTime);
        }

        return bianaryFormatOfDay;
    }

    private String getBaseFormat(InterviewScheduleEntity schedule) {
        Duration duration = Duration.between(schedule.getStartAt(), schedule.getFinishAt());
        System.out.println("duration.toMinutes() = " + duration.toMinutes());
        long length = duration.toMinutes() / TIME_UNIT;
        System.out.println(length);
        String baseFormat = "0".repeat((int) length);

        return baseFormat;
    }

    private String[] splitChoiceOriginal(String choice) {
        String blankRemoved = choice.replaceAll(" ", BLANK);
        String[] splitted = blankRemoved.split(",");

        return splitted;
    }

    private LocalTime getStartOfTime(String choice) {
        String[] times = choice.split("-");
        String[] timeElements = times[0].split(":");
        int hour = Integer.parseInt(timeElements[0]);
        int minute = Integer.parseInt(timeElements[1]);

        return LocalTime.of(hour, minute);
    }

    private int getIndexOfTime(LocalTime startOfDay, LocalTime time) {
        Duration duration = Duration.between(startOfDay, time);
        System.out.println("duration = " + duration.toMinutes());
        long index = duration.toMinutes() / TIME_UNIT;
        System.out.println("index = " + index);
        return (int) index;
    }

    private String setTimeAvailable(String formattedString, int index) {
        StringBuilder stringBuilder = new StringBuilder(formattedString);
        stringBuilder.setCharAt(index, AVAILABLE);

        return stringBuilder.toString();
    }

}

