package com.pearson.aimia;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.pearson.NumberToWords;
import com.pearson.Translator;
import com.pearson.exception.SubsectionLengthException;
import com.pearson.impl.NumberToWordsImpl;
import com.pearson.impl.TranslatorImpl;

public class NumberToWordsTest{

	private NumberToWords numberToWords;
	private Translator translator;
	private long number = 0;
	private String numberAsString = "", expectedResult;
	private Properties props;
	private static final String DIGITS_TO_NUMBERS_FILE="num_of_digits_to_words.properties";
	
	@Before
	public void setUp() throws Exception {
		
		props = new Properties();
		props.load(new FileInputStream(new File(DIGITS_TO_NUMBERS_FILE)));
		translator = new TranslatorImpl(props);
		numberToWords = new NumberToWordsImpl(translator);
		number = 0;
		numberAsString = "";
		expectedResult = "";
	}

	@After
	public void tearDown() throws Exception {
		translator = null;
		numberToWords = null;
	}

	@Test
	public void testConvertZeroToWord() throws SubsectionLengthException {
		number = 0;
		expectedResult = "zero";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 00000;
		expectedResult = "zero";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
	}
	@Test
	public void testConvertSingleDigitToWord() throws SubsectionLengthException {
		number = 1;
		expectedResult = "one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 2;
		expectedResult = "two";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 3;
		expectedResult = "three";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
	}
	
	@Test
	public void testConvert2DigitsToWordsInTens() throws SubsectionLengthException {
		number = 10;
		expectedResult = "ten";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 20;
		expectedResult = "twenty";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 21;
		expectedResult = "twenty one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 17;
		expectedResult = "seventeen";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 55;
		expectedResult = "fifty five";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 99;
		expectedResult = "ninety nine";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
	}
	
	@Test
	public void testConvert3DigitsToHundredsInWords() throws SubsectionLengthException {
		number = 100;
		expectedResult = "one hundred";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 105;
		expectedResult = "one hundred and five";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 210;
		expectedResult = "two hundred and ten";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 506;
		expectedResult = "five hundred and six";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 787;
		expectedResult = "seven hundred and eighty seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 999;
		expectedResult = "nine hundred and ninety nine";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
	}
	
	@Test
	public void testConvert4To6DigitsToThousandsInWords() throws SubsectionLengthException {
		number =1000;
		expectedResult = "one thousand";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =1001;
		expectedResult = "one thousand and one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =1011;
		expectedResult = "one thousand and eleven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =1101;
		expectedResult = "one thousand one hundred and one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =1111;
		expectedResult = "one thousand one hundred and eleven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =10001;
		expectedResult = "ten thousand and one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =12000;
		expectedResult = "twelve thousand";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =15145;
		expectedResult = "fifteen thousand one hundred and forty five";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =33707;
		expectedResult = "thirty three thousand seven hundred and seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =115387;
		expectedResult = "one hundred and fifteen thousand three hundred and eighty seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString); 
		
		number =515387;
		expectedResult = "five hundred and fifteen thousand three hundred and eighty seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		
		number =965997;
		expectedResult = "nine hundred and sixty five thousand nine hundred and ninety seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
	}
	
	@Test
	public void testConvert7To9DigitsToMillionsInWords() throws SubsectionLengthException {
		number = 1000000;
		expectedResult = "one million";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 1000001;
		expectedResult = "one million and one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 2000001;
		expectedResult = "two million and one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 2000010;
		expectedResult = "two million and ten";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 2000017;
		expectedResult = "two million and seventeen";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 10000627;
		expectedResult = "ten million six hundred and twenty seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 19001833;
		expectedResult = "nineteen million one thousand eight hundred and thirty three";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 48056833;
		expectedResult = "forty eight million fifty six thounsand eight hundred and thirty three";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		
		number = 56945781;
		expectedResult = "fifty six million nine hundred and forty five thousand seven hundred and eighty one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		assertEquals (expectedResult, numberAsString);
		
		number = 148056833;
		expectedResult = "one hundred and forty eight million fifty six thousand eight hundred and thirty three";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = 348756833;
		expectedResult = "three hundred and forty eight million seven hundred and fifty six thousand eight hundred and thirty three";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
	}
	

	
	
	
	@Test
	public void testNegativeConvertZeroToWord() throws SubsectionLengthException {
		number = -0;
		expectedResult = "zero";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -00000;
		expectedResult = "zero";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
	}
	@Test
	public void testConvertNegativeSingleDigitToWord() throws SubsectionLengthException {
		number = -1;
		expectedResult = "negative one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -2;
		expectedResult = "negative two";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -3;
		expectedResult = "negative three";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
	}
	
	@Test
	public void testConvertNegative2DigitsToWordsInTens() throws SubsectionLengthException {
		number = -10;
		expectedResult = "negative ten";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -20;
		expectedResult = "negative twenty";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -21;
		expectedResult = "negative twenty one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -17;
		expectedResult = "negative seventeen";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -55;
		expectedResult = "negative fifty five";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -99;
		expectedResult = "negative ninety nine";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
	}
	
	@Test
	public void testConvertNegative3DigitsToHundredsInWords() throws SubsectionLengthException {
		number = -100;
		expectedResult = "negative one hundred";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -105;
		expectedResult = "negative one hundred and five";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -210;
		expectedResult = "negative two hundred and ten";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -506;
		expectedResult = "negative five hundred and six";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -787;
		expectedResult = "negative seven hundred and eighty seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -999;
		expectedResult = "negative nine hundred and ninety nine";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
	}
	
	@Test
	public void testNegativeConvert4To6DigitsToThousandsInWords() throws SubsectionLengthException {
		number =-1000;
		expectedResult = "negative one thousand";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =-1001;
		expectedResult = "negative one thousand and one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =-1011;
		expectedResult = "negative one thousand and eleven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =-1101;
		expectedResult = "negative one thousand one hundred and one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =-1111;
		expectedResult = "negative one thousand one hundred and eleven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		
		number =-10001;
		expectedResult = "negative ten thousand and one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =-12000;
		expectedResult = "negative twelve thousand";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =-15145;
		expectedResult = "negative fifteen thousand one hundred and forty five";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =-33707;
		expectedResult = "negative thirty three thousand seven hundred and seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number =-115387;
		expectedResult = "negative one hundred and fifteen thousand three hundred and eighty seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString); 
		
		number =-515387;
		expectedResult = "negative five hundred and fifteen thousand three hundred and eighty seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		
		number =-965997;
		expectedResult = "negative nine hundred and sixty five thousand nine hundred and ninety seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
	}
	
	@Test
	public void testConvertNegative7To9DigitsToMillionsInWords() throws SubsectionLengthException {
		number = -1000000;
		expectedResult = "negative one million";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -1000001;
		expectedResult = "negative one million and one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -2000001;
		expectedResult = "negative two million and one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -2000010;
		expectedResult = "negative two million and ten";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -2000017;
		expectedResult = "negative two million and seventeen";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -10000627;
		expectedResult = "negative ten million six hundred and twenty seven";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -19001833;
		expectedResult = "negative nineteen million one thousand eight hundred and thirty three";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -48056833;
		expectedResult = "negative forty eight million fifty six thounsand eight hundred and thirty three";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		
		number = -56945781;
		expectedResult = "negative fifty six million nine hundred and forty five thousand seven hundred and eighty one";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		assertEquals (expectedResult, numberAsString);
		
		number = -148056833;
		expectedResult = "negative one hundred and forty eight million fifty six thousand eight hundred and thirty three";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
		
		number = -348756833;
		expectedResult = "negative three hundred and forty eight million seven hundred and fifty six thousand eight hundred and thirty three";
		numberAsString = numberToWords.convertNumToWords (number, 0);
		assertEquals (expectedResult, numberAsString);
	}
	

}
