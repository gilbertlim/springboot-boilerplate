package com.boilerplate.member.application.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.boilerplate.member.application.data.dto.response.MemberResponse;
import com.boilerplate.member.domain.data.entity.Member;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MemberFieldMapper {

    MemberFieldMapper INSTANCE = Mappers.getMapper(MemberFieldMapper.class);

    MemberResponse toMemberResponse(Member member);
}