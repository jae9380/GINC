package com.example.ginc.domain.batch.batchConfiguration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RefuelingConfiguration {
    @Bean(name = "refuelingJob")
    public Job batchJob1(JobRepository jobRepository, Step step) {
        return new JobBuilder("refuelingJob", jobRepository)
                .start(step)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager ptm) {
        return new StepBuilder("refuelingStep1", jobRepository)
                .tasklet((contribution, chunkContext) -> {
//                    Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
//
//                    log.info("carId : "+jobParameters.get("carId"));
//                    log.info("segmentTotalDistance : "+jobParameters.get("segmentTotalDistance"));
//                    log.info("totalRefuelingCost : "+jobParameters.get("totalRefuelingCost"));
//                    log.info("refuelingVolume : "+jobParameters.get("refuelingVolume"));



                    return RepeatStatus.FINISHED;
                }, ptm)
                .build();
    }
}
