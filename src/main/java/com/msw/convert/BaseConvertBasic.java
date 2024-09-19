package com.msw.convert;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BaseConvertBasic {
    BaseConvertBasic INSTANCE = Mappers.getMapper(BaseConvertBasic.class);

}
