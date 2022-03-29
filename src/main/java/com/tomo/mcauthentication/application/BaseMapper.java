package com.tomo.mcauthentication.application;

import org.modelmapper.ModelMapper;

public class BaseMapper {
    protected ModelMapper modelMapper;

    public BaseMapper() {}

    public BaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
