package com.picksa.picksaserver.application.service;

import com.picksa.picksaserver.applicant.domain.AnswerEntity;
import com.picksa.picksaserver.applicant.domain.ApplicantEntity;
import com.picksa.picksaserver.applicant.repository.AnswerRepository;
import com.picksa.picksaserver.applicant.repository.ApplicantRepository;
import com.picksa.picksaserver.application.dto.response.ApplicationDetailResponse;
import com.picksa.picksaserver.application.dto.response.ApplicationSyncResponse;
import com.picksa.picksaserver.global.domain.Generation;
import com.picksa.picksaserver.global.domain.Part;
import com.picksa.picksaserver.question.QuestionEntity;
import com.picksa.picksaserver.question.repository.QuestionRepository;
import com.picksa.picksaserver.sync.domain.SyncRequestLog;
import com.picksa.picksaserver.sync.repository.SyncLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationConvertService {

    private final ApplicationService applicationService;
    private final SyncLogRepository syncLogRepository;
    private final QuestionRepository questionRepository;
    private final ApplicantRepository applicantRepository;
    private final AnswerRepository answerRepository;

    // TODO: 2024-02-12 코드 가독성 개선
    @Transactional
    public void convertApplicationToApplicant() {
        SyncRequestLog lastRequestLog = syncLogRepository.getFirstByOrderByCreatedAtDesc();
        int generation = Generation.getGenerationOfThisYear();
        List<QuestionEntity> questionEntities = questionRepository.findDeterminedQuestionsByGeneration(generation);
        System.out.println("questionEntities.size() = " + questionEntities.size());
        if (questionEntities.isEmpty()) {
            throw new IllegalArgumentException("이번 기수의 질문이 존재하지 않습니다.");
        }

        // 올해의 질문 Map 구조로 변환
        Map<Integer, Map<Part, QuestionEntity>> questions = new HashMap<>();
        for (QuestionEntity question : questionEntities) {

            if (!questions.containsKey(question.getSequence())) {
                questions.put(question.getSequence(), new HashMap<Part, QuestionEntity>());
            }

            questions.get(question.getSequence()).put(question.getTag().getPart(), question);
        }

        for (Map.Entry<Integer, Map<Part, QuestionEntity>> sequence : questions.entrySet()) {
            System.out.println(sequence.getKey() + " : " + sequence.getValue());
        }


        ApplicationSyncResponse response;
        if (lastRequestLog == null) {
            response = applicationService.getAllApplications();
        } else {
            response = applicationService.getApplicationsAfter(lastRequestLog.getProcessedAt());
        }
        System.out.println("response = " + response.applications().size());
        SyncRequestLog newLog = SyncRequestLog.builder().processedAt(response.until()).build();
        syncLogRepository.save(newLog);

        // Application -> Applicant 변환
        List<ApplicantEntity> applicants = new ArrayList<>();
        List<AnswerEntity> answers = new ArrayList<>();
        for (ApplicationDetailResponse application : response.applications()) {
            ApplicantEntity applicant = application.toEntity();
            applicants.add(applicant);

            // 질문 답변 리스트로 변환
            List<String> applicationAnswers = collectAnswersInList(application);
            System.out.println("applicationAnswers.size() = " + applicationAnswers.size());
            int index = 0;
            Part applicationPart = application.part();
            for (String applicationAnswer : applicationAnswers) {
                if (applicationAnswer == null) {
                    index++;
                    continue;
                }

                if (questions.get(index+1).containsKey(Part.ALL)) {
                    QuestionEntity question = questions.get(index + 1).get(Part.ALL);
                    String answerContent = applicationAnswers.get(index);
                    answers.add(createAnswerEntity(question, answerContent, applicant));
                } else if (questions.get(index + 1).containsKey(application.part())) {
                    QuestionEntity question = questions.get(index + 1).get(applicationPart);
                    String answerContent = applicationAnswers.get(index);
                    answers.add(createAnswerEntity(question, answerContent, applicant));
                } else {
                    index++;
                    continue;
                }

                index++;
            }

        }

        // 저장
        applicantRepository.saveAll(applicants);
        answerRepository.saveAll(answers);

    }

    // TODO: 2024-01-21 응집도 개선 (메서드 분리)
    private List<String> collectAnswersInList(ApplicationDetailResponse application) {
        List<String> applicationAnswers = new ArrayList<>();
        applicationAnswers.add(application.commonAnswer1());
        applicationAnswers.add(application.commonAnswer2());
        applicationAnswers.add(application.commonAnswer3());
        applicationAnswers.add(application.commonAnswer4());
        applicationAnswers.add(application.commonAnswer5());
        applicationAnswers.add(application.partAnswer1());
        applicationAnswers.add(application.partAnswer2());
        applicationAnswers.add(application.partAnswer3());

        return applicationAnswers;
    }

    private AnswerEntity createAnswerEntity(QuestionEntity question, String answerContent, ApplicantEntity applicant) {
        return AnswerEntity.builder()
                .question(question)
                .content(answerContent)
                .applicant(applicant)
                .build();
    }

}
