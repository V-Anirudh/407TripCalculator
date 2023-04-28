public class testCases {
    
@Test
public void testValidTrip() {
    String start = "QEW";
    String end = "Highway 400";
    double expectedDistance = 25.0; // according to interchanges.json
    double expectedCost = 6.25; // toll rate of $0.25/km
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    TollCalculator.costOfTrip(start, end);
    String expectedOutput = String.format("Distance: %.3f km\nCost: $%.2f\n", expectedDistance, expectedCost);
    assertEquals(expectedOutput, outContent.toString());
}
@Test
public void testStartInterchangeNotFound() {
    String start = "Non-existent interchange";
    String end = "Highway 400";
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    TollCalculator.costOfTrip(start, end);
    String expectedOutput = "interchanges.json file not found!\n";
    assertEquals(expectedOutput, outContent.toString());
}

@Test
public void testInvalidInput() {
    String start = "";
    String end = "Highway 400";
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    TollCalculator.costOfTrip(start, end);
    String expectedOutput = "interchanges.json file not found!\n";
    assertEquals(expectedOutput, outContent.toString());
}
@Test
public void testSameStartAndEndInterchange() {
    String start = "QEW";
    String end = "QEW";
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    TollCalculator.costOfTrip(start, end);
    String expectedOutput = "Distance: 0.000 km\nCost: $0.00\n";
    assertEquals(expectedOutput, outContent.toString());
}


}
