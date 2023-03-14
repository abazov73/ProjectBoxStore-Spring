package com.example.ipLab;

import com.example.ipLab.TypesCalc.Service.CalcService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IpLabApplicationTests {

	@Autowired
	CalcService calcService;

	@Test
	void testIntegerCalcSum() {
		final Object res = calcService.Sum(2, 2, "int");
		Assertions.assertEquals("4", res.toString());
	}
	@Test
	void testIntegerCalcDif() {
		final Object res = calcService.Dif(2, 2, "int");
		Assertions.assertEquals("0", res.toString());
	}

	@Test
	void testIntegerCalcMultiply() {
		final Object res = calcService.Multiply(2, 3, "int");
		Assertions.assertEquals("6", res.toString());
	}
	@Test
	void testIntegerCalcDiv() {
		final Object res = calcService.Div(4, 2, "int");
		Assertions.assertEquals("2", res.toString());
	}

	@Test
	void testIntegerCalcDivBy0() {
		final Object res = calcService.Div(4, 0, "int");
		Assertions.assertEquals("0", res.toString());
	}

	@Test
	void testStringCalcSum(){
		final Object res = calcService.Sum("2", "2", "string");
		Assertions.assertEquals("22", res.toString());
	}

	@Test
	void testStringCalcDif(){
		final Object res = calcService.Dif("524", "24", "string");
		Assertions.assertEquals("5", res.toString());
	}

	@Test
	void testStringCalcMultiply(){
		final Object res = calcService.Multiply("523", "215", "string");
		Assertions.assertEquals("52", res.toString());
	}

	@Test
	void testStringCalcDiv(){
		final Object res = calcService.Div("135", "24", "string");
		Assertions.assertEquals("12345", res.toString());
	}

	@Test
	void testSpeakerErrorWiredInt() {
		Assertions.assertThrows(NoSuchBeanDefinitionException.class, () -> calcService.Sum("1", "1", "integer"));
	}
}
