package com.example.ginc.domain.batch.service;

import com.example.ginc.domain.batch.eventListener.port.BatchService;
import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BatchServiceImpl implements BatchService {
    private final JobLauncher jobLauncher;
    private final Job refuelingJob;
    @Override
    public void refueling(RefuelDomainEntity entity) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("carId",entity.getCar_id())
                    .addLong("segmentTotalDistance", (long) entity.getSegmentTotalDistance())
                    .addLong("totalRefuelingCost", (long) entity.getTotalRefuelingCost())
                    .addLong("refuelingVolume", (long) entity.getRefuelingVolume())
                    .toJobParameters();

            JobExecution jobExecution = jobLauncher.run(refuelingJob, jobParameters);
            log.info("Batch job started with ID: " + jobExecution.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Failed to start batch job: " + e.getMessage());
        }
    }
}
