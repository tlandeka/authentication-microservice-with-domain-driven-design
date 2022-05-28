package com.tomo.mcauthentication.application;

import org.modelmapper.ModelMapper;

public class BaseMapper {
    protected ModelMapper modelMapper;

    public BaseMapper() {}

    public BaseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    protected <T, E> T toDto(E source, Class<T> destinationType) {
        return modelMapper.map(source, destinationType);
    }
}
