package stringmodifier;

public class StringModifier {
  
  public String unmodifiedString(String string) {
    return string;
  }
  
  /**
   * Returns a new string consisting of the original string concatenated onto itself a given number of times.
   * @param string String to be repeated
   * @param repeatNumber Number of times the given string will be repeated
   * @return Self-concatenated string
   */
  public String repeatedString(String string, int repeatNumber) {
    String newString = "";
    for (int i = 0; i < repeatNumber; i++) {
      newString += string;
    }
    return newString;
  }
  
}
