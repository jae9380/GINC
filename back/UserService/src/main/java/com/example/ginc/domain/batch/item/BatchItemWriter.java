package com.example.ginc.domain.batch.item;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class BatchItemWriter implements ItemWriter<GarageDomainEntity> {
    @Override
    public void write(Chunk chunk) throws Exception {

    }
}
