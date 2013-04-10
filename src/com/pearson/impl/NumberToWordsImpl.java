package com.pearson.impl;


import com.pearson.NumberToWords;
import com.pearson.Translator;
import com.pearson.exception.SubsectionLengthException;
/**
 * A class that converts numbers to words
 * @author dman
 *
 */
public class NumberToWordsImpl implements NumberToWords
{
	
	private String numberConvertedAsString = "";
	private Translator translator;
	public NumberToWordsImpl(){
		
	}
	
	public NumberToWordsImpl(Translator translator){
		this.translator = translator;
	}

	@Override
	public String convertNumToWords(long number, int sectionStart) throws SubsectionLengthException {
		
		String numAsString = String.valueOf(number);
		//Note that we  take the  the number as starting from the 
		//1st non zero characte of the number supplied to the end of the number.
		//This is because for example, 0001000, 00001000, 1000 and 01000 are all the same number i.e. 1000
		if (numAsString.length() > 1) {
			for (int i = 0; i < numAsString.length(); i++) {
				if (numAsString.charAt(i) == '0') {
					numAsString = numAsString.replace(numAsString.charAt(i),' ').trim();
				} else {
					break;
				}
			}
		}
		numberConvertedAsString = doNumToWordsConversion(numAsString, 0);
		
		return numberConvertedAsString;
			
	}
	
	/**
	 * To convert a number to words, doNumToWordsConversion partitions a number into subsections of
	 * three or less digits 
	 * and then converts each subsection into words by recursively calling itself i.e. doNumToWordsConversion. Each
	 * subsection is converted to words as per the section of the number the digits are found in. 
	 * For example, given a number 110200 (in string form), this method will
	 *  divide the number into two subsection i.e. 110 and 200. The method will then correctly deduce that 110 is in the
	 * thousands section and the  200 is the hundreds section of the number and hence will correctly return the string
	 * "one hundred and ten thousand two hundred"
	 * @param numberAsString: the number to be converted words
	 * @param sectionStart: the start index of the 
	 * @return
	 * @throws SubsectionLengthException 
	 */
	private String doNumToWordsConversion(String numberAsString, int sectionStart) throws SubsectionLengthException{
		String  sectionString="", formattedString = "";
		int  sectionLength = 0;
		int modulo = 3 ;
		
		//Adjust the length of num as string to account for negative numbers
		
		 if (numberAsString.startsWith("-")){
			 formattedString += translator.getNegativeAsWord();
			 numberAsString = numberAsString.replace('-', ' ').trim();
		 }
		
		sectionLength = (numberAsString.length() % modulo);
		sectionLength = sectionLength > 0 ? sectionLength : modulo; 
		
		//Convert numbers that are less than or equal to 999 to words
		if (sectionLength <= 2 && numberAsString.length() <= 3){
			formattedString += translator.convertSubSectionDigitsToWords(numberAsString);
			return formattedString;
		}
		else{
			//Convert numbers that are greater than 999 to words
			sectionString = numberAsString.substring(0, sectionLength);
			formattedString += translator.addSubSectionDescription(sectionString, numberAsString);
			sectionStart = sectionLength;
			
			 if (sectionStart < numberAsString.length()){
				numberAsString = numberAsString.substring(sectionStart);
				sectionLength = (numberAsString.length() % 3);
				sectionLength = sectionLength > 0 ? sectionLength : 3; 
				String tempFormattedString = doNumToWordsConversion(numberAsString, sectionStart);
				if (!"".equals(tempFormattedString)&&!tempFormattedString.startsWith(" "))
					tempFormattedString=" "+tempFormattedString;
				formattedString += tempFormattedString;
			 }
		}
		//remove any unnecessary leading and trailing spaces
		return formattedString.trim();
	}

	public Translator getTranslator() {
		return translator;
	}

	public void setProps(Translator translator) {
		this.translator = translator;
	}

}
