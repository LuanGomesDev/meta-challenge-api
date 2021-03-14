package br.com.challenge.meta.service.ApprovalProcessing.impl;

import br.com.challenge.meta.dto.model.ApprovalProcessingDTO;
import br.com.challenge.meta.filter.ApprovalProcessingFilter;
import br.com.challenge.meta.model.ApprovalProcessing.ApprovalProcessing;
import br.com.challenge.meta.repository.ApprovalProcessingRepository;
import br.com.challenge.meta.service.ApprovalProcessing.ApprovalProcessingService;
import br.com.challenge.meta.service.mapper.ApprovalProcessingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApprovalProcessingServiceImpl implements ApprovalProcessingService {

    private final ApprovalProcessingRepository repository;
    private final ApprovalProcessingMapper mapper;

    @Override
    public List<ApprovalProcessingDTO> findAll(ApprovalProcessingFilter filter, Pageable pageable) {
        return null;
    }

    @Override
    public ApprovalProcessingDTO save(ApprovalProcessingDTO dto) {
        return null;
    }

    @Override
    public ApprovalProcessingDTO update(ApprovalProcessingDTO dto, UUID id) {
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        ApprovalProcessing approvalProcessingToDelete = this.repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id invalid."));

        try {
            this.repository.delete(approvalProcessingToDelete);
        } catch (Exception e) {
            throw new ServerErrorException("Failed delete approvalProcessing. Error: " + e.getMessage());
        }

        return true;
    }
}
