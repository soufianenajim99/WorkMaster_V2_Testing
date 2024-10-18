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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AutenticationServiceImplTest {

    private AutenticationServiceImpl authenticationService;
    private EntityManagerFactory mockEntityManagerFactory;
    private EntityManager mockEntityManager;
    private Query mockQuery;

    @BeforeEach
    public void setUp() {
        mockEntityManagerFactory = mock(EntityManagerFactory.class);
        mockEntityManager = mock(EntityManager.class);
        mockQuery = mock(Query.class);


        authenticationService = spy(new AutenticationServiceImpl());


        doReturn(mockEntityManager).when(authenticationService).getEntityManager();
    }

    @Test
    public void testAuthenticateAdmin_Success() {

        String username = "admin";
        String password = "admin";
        when(mockEntityManager.createQuery(anyString())).thenReturn(mockQuery);
        when(mockQuery.setParameter("username", username)).thenReturn(mockQuery);
        when(mockQuery.setParameter("password", password)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(new Object()); // Simulate a result found


        boolean result = authenticationService.authenticateAdmin(username, password);


        assertTrue(result, "Admin should be authenticated successfully");
    }

    @Test
    public void testAuthenticateAdmin_Failure() {

        String username = "admin";
        String password = "wrongpassword";
        when(mockEntityManager.createQuery(anyString())).thenReturn(mockQuery);
        when(mockQuery.setParameter("username", username)).thenReturn(mockQuery);
        when(mockQuery.setParameter("password", password)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenThrow(new NoResultException()); // Simulate no result found

        boolean result = authenticationService.authenticateAdmin(username, password);


        assertFalse(result, "Admin should not be authenticated with wrong password");
    }

    @Test
    public void testGetUser_Success() {

        String entityName = "Admin";
        String username = "admin";
        String password = "admin";
        Object mockUser = new Object();
        when(mockEntityManager.createQuery(anyString())).thenReturn(mockQuery);
        when(mockQuery.setParameter("username", username)).thenReturn(mockQuery);
        when(mockQuery.setParameter("password", password)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(mockUser);

        Object result = authenticationService.getUser(entityName, username, password);


        assertNotNull(result, "User should be retrieved successfully");
        assertEquals(mockUser, result, "The retrieved user should match the expected user");
    }

    @Test
    public void testGetUser_NotFound() {

        String entityName = "Admin";
        String username = "admin";
        String password = "wrongpassword";
        when(mockEntityManager.createQuery(anyString())).thenReturn(mockQuery);
        when(mockQuery.setParameter("username", username)).thenReturn(mockQuery);
        when(mockQuery.setParameter("password", password)).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenThrow(new NoResultException());

        Object result = authenticationService.getUser(entityName, username, password);


        assertNull(result, "User should not be retrieved if credentials are incorrect");
    }
}
