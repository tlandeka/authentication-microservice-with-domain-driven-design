package com.tomo.mcauthentication.infrastructure.processing;

public interface PipelineBuilder<C, R> {
    <H extends PipelineBuilder> H with(C aRequest);
    R build();
}
