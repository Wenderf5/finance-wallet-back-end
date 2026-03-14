import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.financewallet.dataBase.repositories.UserRepository;
import com.financewallet.redis.RedisTemplate;
import com.financewallet.services.EmailService;
import com.financewallet.services.UserService;
import com.financewallet.exceptions.InvalidEmailCodeException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private RedisTemplate redisTemplate;

    @Mock
    private EmailService emailService;
    
    @Mock 
    private JsonObject jsonObject;

    @Mock
    private Gson gson;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnTrue() {
        String jsonValue = "{"
                + "\"email\": \"test@email.com\","
                + "\"password\": \"testPassword\","
                + "\"userName\": \"testUserName\","
                + "\"emailCode\": \"testCode\""
                + "}";
        
        JsonObject mockJsonObject = new JsonObject();
        mockJsonObject.addProperty("emailCode", "testCode");

        when(this.redisTemplate.get("testKey")).thenReturn(jsonValue);
        when(this.gson.fromJson(jsonValue, JsonObject.class)).thenReturn(mockJsonObject);

        Boolean result = this.userService.verifyEmailCode("testKey", "testCode");

        assertEquals(true, result);
    }
    @Test
    public void shouldThrowInvalidEmailCodeException() {
        String jsonValue = "{"
                + "\"email\": \"test@email.com\","
                + "\"password\": \"testPassword\","
                + "\"userName\": \"testUserName\","
                + "\"emailCode\": \"wrongCode\""
                + "}";
        
        JsonObject mockJsonObject = new JsonObject();
        mockJsonObject.addProperty("emailCode", "wrongCode");

        when(this.redisTemplate.get("testKey")).thenReturn(jsonValue);
        when(this.gson.fromJson(jsonValue, JsonObject.class)).thenReturn(mockJsonObject);

        InvalidEmailCodeException exception = assertThrows(InvalidEmailCodeException.class, () -> {
            this.userService.verifyEmailCode("testKey", "testCode");
        });

        assertEquals("Invalid code", exception.getMessage());
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenGenericExceptionOccurs() {
        when(this.redisTemplate.get("testKey")).thenThrow(new RuntimeException("Redis error"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            this.userService.verifyEmailCode("testKey", "testCode");
        });

        assertEquals("Error verifying code", exception.getMessage());
    }
}
