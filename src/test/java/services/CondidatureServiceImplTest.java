package services;

import entities.Condidature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repositories.repositoryinterfaces.CondidatureRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CondidatureServiceImplTest {

    @InjectMocks
    CondidatureServiceImpl condidatureService;

    @Mock
    CondidatureRepository condidatureRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        condidatureService = new CondidatureServiceImpl(condidatureRepositoryMock);
    }

    @Test
    void save_shouldPersistCondidature() {
        // Arrange
        Condidature condidature = new Condidature();
        when(condidatureRepositoryMock.save(condidature)).thenReturn(condidature);

        // Act
        Condidature result = condidatureService.save(condidature);

        // Assert
        assertEquals(condidature, result);
        verify(condidatureRepositoryMock, times(1)).save(condidature);
    }

    @Test
    void findById_shouldReturnCondidature_whenExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        Condidature condidature = new Condidature();
        when(condidatureRepositoryMock.findById(id)).thenReturn(Optional.of(condidature));

        // Act
        Optional<Condidature> result = condidatureService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(condidature, result.get());
        verify(condidatureRepositoryMock, times(1)).findById(id);
    }

    @Test
    void findById_shouldReturnEmpty_whenNotExists() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(condidatureRepositoryMock.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Condidature> result = condidatureService.findById(id);

        // Assert
        assertFalse(result.isPresent());
        verify(condidatureRepositoryMock, times(1)).findById(id);
    }

    @Test
    void findAll_shouldReturnListOfCondidatures() {
        // Arrange
        Condidature condidature1 = new Condidature();
        Condidature condidature2 = new Condidature();
        List<Condidature> condidatureList = Arrays.asList(condidature1, condidature2);
        when(condidatureRepositoryMock.findAll()).thenReturn(condidatureList);

        // Act
        List<Condidature> result = condidatureService.findAll();

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(condidature1));
        assertTrue(result.contains(condidature2));
        verify(condidatureRepositoryMock, times(1)).findAll();
    }

    @Test
    void update_shouldModifyExistingCondidature() {
        // Arrange
        Condidature condidature = new Condidature();
        when(condidatureRepositoryMock.update(condidature)).thenReturn(condidature);

        // Act
        Condidature result = condidatureService.update(condidature);

        // Assert
        assertEquals(condidature, result);
        verify(condidatureRepositoryMock, times(1)).update(condidature);
    }

    @Test
    void deleteById_shouldRemoveCondidature() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        condidatureService.deleteById(id);

        // Assert
        verify(condidatureRepositoryMock, times(1)).deleteById(id);
    }
}