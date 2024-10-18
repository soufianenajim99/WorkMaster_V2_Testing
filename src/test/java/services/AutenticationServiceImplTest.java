package services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AutenticationServiceImplTest {

    @InjectMocks
    AutenticationServiceImpl authenticationService;

    @Mock
    EntityManagerFactory entityManagerFactoryMock;

    @Mock
    EntityManager entityManagerMock;

    @Mock
    Query queryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(entityManagerFactoryMock.createEntityManager()).thenReturn(entityManagerMock);
    }

    @Test
    void authenticateAdmin_withCorrectCredentials_shouldReturnTrue() {
        // Arrange
        String username = "adminUser";
        String password = "adminPass";
        String entityName = "Admin";
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter("username", username)).thenReturn(queryMock);
        when(queryMock.setParameter("password", password)).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(new Object());

        // Act
        boolean result = authenticationService.authenticateAdmin(username, password);

        // Assert
        assertTrue(result);
        verify(entityManagerMock, times(1)).createQuery(anyString());
        verify(entityManagerMock, times(1)).close();
    }

    @Test
    void authenticateAdmin_withIncorrectCredentials_shouldReturnFalse() {
        // Arrange
        String username = "admin";
        String password = "admin";
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter("username", username)).thenReturn(queryMock);
        when(queryMock.setParameter("password", password)).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenThrow(NoResultException.class);

        // Act
        boolean result = authenticationService.authenticateAdmin(username, password);

        // Assert
        assertFalse(result);
        verify(entityManagerMock, times(1)).createQuery(anyString());
        verify(entityManagerMock, times(1)).close();
    }

    @Test
    void getUser_withCorrectCredentials_shouldReturnUserObject() {
        // Arrange
        String entityName = "Employee";
        String username = "employee";
        String password = "employee";
        Object expectedUser = new Object();
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter("username", username)).thenReturn(queryMock);
        when(queryMock.setParameter("password", password)).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenReturn(expectedUser);

        // Act
        Object result = authenticationService.getUser(entityName, username, password);

        // Assert
        assertNotNull(result);
        assertEquals(expectedUser, result);
        verify(entityManagerMock, times(1)).createQuery(anyString());
        verify(entityManagerMock, times(1)).close();
    }

    @Test
    void getUser_withIncorrectCredentials_shouldReturnNull() {
        // Arrange
        String entityName = "Recruiter";
        String username = "recruiter";
        String password = "wrongPass";
        when(entityManagerMock.createQuery(anyString())).thenReturn(queryMock);
        when(queryMock.setParameter("username", username)).thenReturn(queryMock);
        when(queryMock.setParameter("password", password)).thenReturn(queryMock);
        when(queryMock.getSingleResult()).thenThrow(NoResultException.class);

        // Act
        Object result = authenticationService.getUser(entityName, username, password);

        // Assert
        assertNull(result);
        verify(entityManagerMock, times(1)).createQuery(anyString());
        verify(entityManagerMock, times(1)).close();
    }
}
