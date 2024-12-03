package com.example.ginc.domain.batch.item;

import com.example.ginc.domain.garage.domain.GarageDomainEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class BatchItemProcessor implements ItemProcessor<GarageDomainEntity, GarageDomainEntity> {

    @Override
    public GarageDomainEntity process(GarageDomainEntity item) throws Exception {
        return null;
    }
}
