package com.tomo.mcauthentication.infrastructure.processing;

public interface PipelineBuilder<C, R> {
    PipelineBuilder with(C aRequest);
    R build();
}
