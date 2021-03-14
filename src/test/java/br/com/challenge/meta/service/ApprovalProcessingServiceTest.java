package br.com.challenge.meta.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ServerErrorException;

import br.com.challenge.meta.model.ApprovalProcessing.ApprovalProcessing;
import br.com.challenge.meta.repository.ApprovalProcessingRepository;
import br.com.challenge.meta.service.ApprovalProcessing.ApprovalProcessingService;
import br.com.challenge.meta.service.ApprovalProcessing.impl.ApprovalProcessingServiceImpl;
import br.com.challenge.meta.service.mapper.ApprovalProcessingMapper;

@ExtendWith(SpringExtension.class)
public class ApprovalProcessingServiceTest {

    ApprovalProcessingService approvalProcessingService;

    @MockBean
    ApprovalProcessingRepository repository;

    @MockBean
    ApprovalProcessingMapper mapper;

    @BeforeEach
    public void setUp() {
        this.approvalProcessingService = new ApprovalProcessingServiceImpl(repository, mapper);
    }

    @Test
    @DisplayName("Deve excluir registro ApprovalProcessing com sucesso.")
    public void deveExcluirRegistroComSucesso() {

        Optional<ApprovalProcessing> approvalProcessing = Optional.of(ApprovalProcessing.newBuilder().build());
        when(repository.findById(any())).thenReturn(approvalProcessing);

        assertTrue(this.approvalProcessingService.delete(any()));
    }

    @Test
    @DisplayName("Deve lançar excessão por id inválido.")
    public void deveLancarErroPorIdInvalido() {

        when(repository.findById(any())).thenReturn(Optional.empty());
        RuntimeException assertThrows = assertThrows(RuntimeException.class, () -> {
            this.approvalProcessingService.delete(any());
        });

        assertEquals("Id invalid.", assertThrows.getMessage());
    }

    @Test
    @DisplayName("Deve lançar excessão por erro no banco de dados.")
    public void deveLancarErroPorServerErrorException() {

        doThrow(Exception.class).when(repository).delete(any());
        ServerErrorException assertThrows = assertThrows(ServerErrorException.class, () -> {
            this.approvalProcessingService.delete(any());
        });

        assertEquals("Failed delete approvalProcessing. Error: Erro servidor.", assertThrows.getMessage());
    }

}
