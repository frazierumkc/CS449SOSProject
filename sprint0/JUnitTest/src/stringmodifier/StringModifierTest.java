package stringmodifier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringModifierTest {

  StringModifier stringModTest = new StringModifier();

  @Test
  void testUnmodifiedString() {
    assertEquals("Johnathan hello", stringModTest.unmodifiedString("Johnathan hello"));
  }
  
  @Test
  void testRepeatedString() {
    assertEquals("JohnJohnJohn", stringModTest.repeatedString("John", 3));
  }  
  
  @Test
  void testRepeatedStringEmpty() {
    assertEquals("", stringModTest.repeatedString("John", 0));
  } 

}