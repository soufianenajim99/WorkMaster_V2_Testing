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
        JobOffer jobOffer = new JobOffer();
        when(jobOfferRepositoryMock.save(jobOffer)).thenReturn(jobOffer);

        JobOffer result = jobOfferService.save(jobOffer);

        assertEquals(jobOffer, result);
        verify(jobOfferRepositoryMock, times(1)).save(jobOffer);
    }

    @Test
    void findById() {

        UUID id = UUID.randomUUID();
        JobOffer jobOffer = new JobOffer();
        when(jobOfferRepositoryMock.findById(id)).thenReturn(Optional.of(jobOffer));

        Optional<JobOffer> result = jobOfferService.findById(id);

        assertTrue(result.isPresent());
        assertEquals(jobOffer, result.get());
        verify(jobOfferRepositoryMock, times(1)).findById(id);
    }

    @Test
    void findAll() {

        JobOffer jobOffer1 = new JobOffer();
        JobOffer jobOffer2 = new JobOffer();
        List<JobOffer> jobOfferList = Arrays.asList(jobOffer1, jobOffer2);
        when(jobOfferRepositoryMock.findAll()).thenReturn(jobOfferList);
        List<JobOffer> result = jobOfferService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(jobOffer1));
        assertTrue(result.contains(jobOffer2));
        verify(jobOfferRepositoryMock, times(1)).findAll();
    }

    @Test
    void update() {

        JobOffer jobOffer = new JobOffer();
        when(jobOfferRepositoryMock.update(jobOffer)).thenReturn(jobOffer);
        JobOffer result = jobOfferService.update(jobOffer);

        assertEquals(jobOffer, result);
        verify(jobOfferRepositoryMock, times(1)).update(jobOffer);
    }

    @Test
    void deleteById() {

        UUID id = UUID.randomUUID();
        jobOfferService.deleteById(id);

        verify(jobOfferRepositoryMock, times(1)).deleteById(id);
    }
}