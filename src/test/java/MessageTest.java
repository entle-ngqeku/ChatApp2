/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.chatapp2.Message;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */

public class MessageTest {
    
    @Test
    public void testMessageLengthValid() {
        Message msg = new Message("+27718693002", "Hi there", 1);
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }
    
    @Test
    public void testMessageLengthInvalid() {
        String longMsg = "A".repeat(260); // 260 characters
        Message msg = new Message("+27718693002", longMsg, 1);
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", 
                     msg.checkMessageLength());
    }
    
    @Test
    public void testRecipientValid() {
        Message msg = new Message("+27718693002", "Test", 1);
        assertEquals("Cell phone number successfully captured.", 
                     msg.checkRecipientCell());
    }
    
    @Test
    public void testRecipientInvalid() {
        Message msg = new Message("08575975889", "Test", 1);
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                     msg.checkRecipientCell());
    }
    
    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message("+27718693002", "Hi tonight?",0);
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }
    
    @Test
    public void testMessageIDExists() {
        Message msg = new Message("+27718693002", "Test", 1);
        assertTrue(msg.checkMessageID());
        assertEquals(10, msg.getMessageID().length());
    }
}
