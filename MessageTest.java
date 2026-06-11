/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatapppart1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class MessageTest {
    
// Message objects used for testing
private Message message1;
private Message message2;

// Runs before every test
@Before
public void setUp() {

// Valid POE test data
message1 = new Message(0, "+27718693002", "Hi Mike can you join us for dinner tonight");

// Invalid recipient POE test data
message2 = new Message(1, "08575975889", "Hi Keegan did you receive the payment");
}

// Test valid message length
@Test
public void testCheckMessageLength_validMessage_returnsSuccess() {
    String result = message1.checkMessageLength();
    assertEquals("Message ready to send.", result);
}

// Test message longer than 250 characters
@Test
public void testCheckMessageLength_over250chars_returnsFailureWithCount() {

    String longMessage = "a".repeat(260);

    Message msg = new Message(1, "+27718693002", longMessage);

    String result = msg.checkMessageLength();

    assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
}

// Test valid recipient number
@Test
public void testCheckRecipientCell_validNumber_returnsSuccess() {
    String result = message1.checkRecipientCell();

    assertEquals("Cell phone number successfully captured.", result);
}

// Test invalid recipient number
@Test
public void testCheckRecipientCell_invalidNumber_returnsFailure() {

    String result = message2.checkRecipientCell();

    assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
}

// Test message hash format
@Test
public void testCreateMessageHash_correctFormat_endsWithExpectedWords() {

    String hash = message1.createMessageHash();

    assertTrue(hash.endsWith(":0:HITONIGHT"));
}

// Test hash is uppercase
@Test
public void testCreateMessageHash_isUppercase() {

    String hash = message1.createMessageHash();

    assertEquals(hash.toUpperCase(), hash);
}

// Test message ID exists
@Test
public void testCheckMessageID_generatedID_isNotNull() {

    assertNotNull(message1);
}

// Test message ID length
@Test
public void testCheckMessageID_generatedID_isExactly10Chars() {

    assertTrue(message1.checkMessageID());
}

class TestableMessage extends Message {

private int option;

public TestableMessage(int option) {

super(1, "+27718693002", "Test message");

this.option = option;
}

@Override
public String sentMessage() {

switch (option) {

    case 1:
        return "Message successfully sent.";

    case 2:
        return "Press 0 to delete the message.";

    case 3:
        return "Message successfully stored.";
        
default:
return "Invalid option.";

}
}
}

// Test Send option
@Test
public void testSentMessage_userSelectsSend_returnsCorrectString() {

    TestableMessage msg = new TestableMessage(1);

    assertEquals("Message successfully sent.", msg.sentMessage() );
}

// Test Disregard option
@Test
public void testSentMessage_userSelectsDisregard_returnsCorrectString() {

    TestableMessage msg = new TestableMessage(2);

    assertEquals("Press 0 to delete the message.", msg.sentMessage());
}

// Test Store option
@Test
public void testSentMessage_userSelectsStore_returnsCorrectString() {

    TestableMessage msg = new TestableMessage(3);

    assertEquals("Message successfully stored.", msg.sentMessage());
}

    // Test longest stored message
    @Test
    public void testDisplayLongestMessage_returnsCorrectLongestMessage() {
        
        Message.clearArrays();
        Message.addStoredMessage("Where are you? You are late! I have asked you to be on time.");
        Message.addStoredMessage("Ok, I am leaving without you.");
        
        String result = new Message(1, "+27838884567", "Test").displayLongestMessage();
        
        assertEquals("Where are you? You are late! I have asked you to be on time.", result);
    }
    
    // Test sent messages
    @Test
    public void testSentMessagesArray_correctlyPopulated() {
        Message.clearArrays();
        
        Message.addSentMessage("Did you get the cake?");
        Message.addSentMessage("It is dinner time !");
        
        assertEquals("Did you get the cake?", Message.getSentMessages().get(0));
        assertEquals("It is dinner time !", Message.getSentMessages().get(1));
    }
   
    // Test searching for a message using its message ID
    @Test
    public void testSearchByMessageID_returnsCorrectMessage() {
        Message.clearArrays();

        Message.addStoredMessage("It is dinner time !");
        Message.addRecipient("0838884567");
        Message.addMessageID("MSG001");

        Message msg = new Message(1, "0838884567", "Test");
        
        String result = msg.searchByMessageID("MSG001");

        assertTrue(result.contains("0838884567"));
        assertTrue(result.contains("It is dinner time !"));
    }
    
    // Test searching for messages using a recipient number
    @Test
    public void testSearchByRecipient_returnsCorrectMessages() {
        
        Message.clearArrays();
        Message.addStoredMessage("Where are you? You are late! I have asked you to be on time.");
        Message.addStoredMessage("Ok, I am leaving without you.");
        Message.addRecipient("+27838884567");
        Message.addRecipient("+27838884567");

        Message msg = new Message(1, "+27838884567", "Test");

        String result = msg.searchByRecipient("+27838884567");

        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }
    
    // Test deleting a message using its hash
    @Test
    public void testDeleteByHash_removesMessageSuccessfully() {
        
        Message.clearArrays();
        Message.addStoredMessage("Where are you? You are late! I have asked you to be on time.");
        Message.addMessageHash("HASH002");
        
        Message msg = new Message(1, "+27838884567", "Test");
        
        String result = msg.deleteByHash("HASH002");

        assertEquals("Message successfully deleted.",result);
        assertEquals(0, Message.getStoredMessages().size());
    }
    
    // Test report when no stored messages exist
    @Test
    public void testPrintMessageReport_containsMessageDetails() {
        
        Message.clearArrays();
        
        Message.addStoredMessage("Ok, I am leaving without you.");
        Message.addRecipient("+27838884567");
        Message.addMessageHash("HASH001");
         Message.addMessageID("MSG001");
        
        Message msg = new Message(1, "+27838884567", "Test");
        String result = msg.printMessages();
        
         assertTrue(result.contains("MSG001"));
        assertTrue(result.contains("HASH001"));
        assertTrue(result.contains("+27838884567"));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }
    
}
