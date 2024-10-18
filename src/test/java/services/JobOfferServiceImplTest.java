package services;

import entities.JobOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repositories.repositoryinterfaces.JobOfferRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobOfferServiceImplTest {

    @InjectMocks
    JobOfferServiceImpl jobOfferService;

    @Mock
    JobOfferRepository jobOfferRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        jobOfferService = new JobOfferServiceImpl(jobOfferRepositoryMock);
    }

    @Test
    void testSavingJobOffer_usingMock() {
        // Arrange
        JobOffer jobOffer = new JobOffer();
        when(jobOfferRepositoryMock.save(jobOffer)).thenReturn(jobOffer);

        // Act
        JobOffer result = jobOfferService.save(jobOffer);

        // Assert
        assertEquals(jobOffer, result);
        verify(jobOfferRepositoryMock, times(1)).save(jobOffer);
    }

    @Test
    void findById() {
        // Arrange
        UUID id = UUID.randomUUID();
        JobOffer jobOffer = new JobOffer();
        when(jobOfferRepositoryMock.findById(id)).thenReturn(Optional.of(jobOffer));

        // Act
        Optional<JobOffer> result = jobOfferService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(jobOffer, result.get());
        verify(jobOfferRepositoryMock, times(1)).findById(id);
    }

    @Test
    void findAll() {
        // Arrange
        JobOffer jobOffer1 = new JobOffer();
        JobOffer jobOffer2 = new JobOffer();
        List<JobOffer> jobOfferList = Arrays.asList(jobOffer1, jobOffer2);
        when(jobOfferRepositoryMock.findAll()).thenReturn(jobOfferList);

        // Act
        List<JobOffer> result = jobOfferService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(jobOffer1));
        assertTrue(result.contains(jobOffer2));
        verify(jobOfferRepositoryMock, times(1)).findAll();
    }

    @Test
    void update() {
        // Arrange
        JobOffer jobOffer = new JobOffer();
        when(jobOfferRepositoryMock.update(jobOffer)).thenReturn(jobOffer);

        // Act
        JobOffer result = jobOfferService.update(jobOffer);

        // Assert
        assertEquals(jobOffer, result);
        verify(jobOfferRepositoryMock, times(1)).update(jobOffer);
    }

    @Test
    void deleteById() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        jobOfferService.deleteById(id);

        // Assert
        verify(jobOfferRepositoryMock, times(1)).deleteById(id);
    }
}