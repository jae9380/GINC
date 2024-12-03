package com.example.ginc.domain.batch.item;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import com.example.ginc.domain.garage.domain.RefuelDomainEntity;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class BatchItemReader {
    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public ItemReader<GarageDomainEntity> getGarageDataByCarId() {
        JpaPagingItemReader<GarageDomainEntity> reader = new JpaPagingItemReader<GarageDomainEntity>();
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setQueryString("SELECT g FROM GARAGE g WHERE g.id = 1");
        reader.setPageSize(1);
        System.out.println("operation");
        return reader;
    }
}
