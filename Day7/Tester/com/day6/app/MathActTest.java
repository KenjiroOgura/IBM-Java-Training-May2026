package com.day6.app;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Executable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MathActTest {
	private MathAct mat;
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
	
	
//
	@BeforeEach
	void setUp() throws Exception {
		mat = new MathAct();
	}

	

	@Test
	void testAdd() {
		assertEquals(4.5,MathAct.add(2, (float) 2.5));
	}
	
	@Test 
	void testSub(){
		assertEquals(2.5,MathAct.subtract((float)5.0, (float)2.5));
	}
	
	@Test
	void testMult() {
		assertEquals(35,MathAct.multiply((float)7.0,(float) 5.0));
	}
	
	@Test
	void testDivideByZero() {
		 assertAll(
	                () -> assertThrows(ArithmeticException.class, () -> MathAct.divide((float)0, (float) 0.0)),
	                () -> assertEquals(6, MathAct.divide(24, 4))
	            ); 
		
	}

	
	 @Test
	    public void testExceptionThrown() {
	        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
	            throw new IllegalArgumentException("Invalid argument");
	        });
	        Assertions.assertEquals("Invalid argument", exception.getMessage());
	    }
	 //analysis complete = test
	//ConsoleCapture cc
	 
//	@Test
//	void testDiv() {
//		 assertEquals(6,mat.divide(24, 4));
//	}
	
	
//	 @Test(expected = ArithmeticException.class)
//	   public void testDiv() {	
//		 System.out.println("Inside testPrintMessage()");
//		 mat.divide((float)25, (float) 0.0);    
//	   }
	 
//	@Test
//	void testDiv() {
//		 assertThrows(ArithmeticException.class, () -> {
//	            mat.divide((float)25, (float) 0.0);
//	        });
//	}
	
	
}
