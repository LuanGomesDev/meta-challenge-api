package br.com.challenge.meta.service.mapper;

import br.com.challenge.meta.dto.model.ApprovalProcessingDTO;
import br.com.challenge.meta.model.ApprovalProcessing.ApprovalProcessing;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApprovalProcessingMapper {

    ApprovalProcessing toEntity(ApprovalProcessingDTO dto);

    ApprovalProcessingDTO toDto(ApprovalProcessing entity);

    List<ApprovalProcessingDTO> toDto(List<ApprovalProcessing> entity);
}
