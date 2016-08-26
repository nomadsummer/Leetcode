import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class IPAdressGeneratorTest {
	IPAddressGenerator ipAddressGenerator = new IPAddressGenerator();
	
	
	@Test
	public void testInvalidInputNull() {
		
		assertEquals(0, ipAddressGenerator.generateIPAddress(null).size());
	}
	
	@Test
	public void testInvalidInputEmptyString() {
		List<String> rst = new ArrayList<String>();
		assertEquals(rst, ipAddressGenerator.generateIPAddress(""));
		assertEquals(0, ipAddressGenerator.generateIPAddress("").size());
		assertTrue(ipAddressGenerator.generateIPAddress("").isEmpty());
	}
	
	@Test 
	public void testGenerateIpAddress() {
		String input = "12345";
		List<String> expected = Arrays.asList("1.2.3.45", "1.2.34.5", "1.23.4.5", "12.3.4.5");
		List<String> actual = ipAddressGenerator.generateIPAddress(input);
		
		assertEquals(expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
	@Test 
	public void testFailToGenerateIpAddress() {
		String input = "123";
		
		List<String> actual = ipAddressGenerator.generateIPAddress(input);
		
		assertEquals(0, actual.size());
		assertTrue(actual.isEmpty());
	}
	
	@Test
	public void testGenerateIpAddressWithSpecialCharacters() {
		String input = "0000";
		List<String> actual = ipAddressGenerator.generateIPAddress(input);
		List<String> expected = Arrays.asList("0.0.0.0");
		
		assertEquals(expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
	@Test
	void testGenerateIpAddressSuccess() {
		String input = "25525511135";
		
		List<String> actual = ipAddressGenerator.generateIPAddress(input);
		List<String> expected = Arrays.asList("255.255.11.135", "255.255.111.35");
		
		assertEquals(expected.size(), actual.size());
		assertEquals(expected, actual);
	}
	
}
