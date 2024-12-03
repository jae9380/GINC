package com.example.ginc.domain.batch.batchConfiguration;

import com.example.ginc.domain.batch.item.BatchItemProcessor;
import com.example.ginc.domain.batch.item.BatchItemReader;
import com.example.ginc.domain.batch.item.BatchItemWriter;
import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.context.annotation.Bean;

@Configuration
@RequiredArgsConstructor
public class RefuelingConfiguration {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager ptm;
    private final BatchItemReader reader;
    private final BatchItemProcessor processor;
    private final BatchItemWriter writer;



    @Bean(name = "refuelingJob")
    public Job batchJob1() {
        return new JobBuilder("refuelingJob", jobRepository)
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return new StepBuilder("refuelingStep1", jobRepository)
                .<GarageDomainEntity,GarageDomainEntity>chunk(1,ptm)
                .reader(reader.getGarageDataByCarId())
                .processor(processor)
                .writer(writer)
                .build();
    }
}
