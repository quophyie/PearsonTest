package com.pearson;

import com.pearson.exception.SubsectionLengthException;

/**
 * 
 * @author dman
 *	A translator to translate digits to words
 */
public interface Translator {

	/**
	 * Translates the digits 21 to 99 into words
	 * @param numAsString
	 * @return
	 * @throws SubsectionLengthException 
	 */
	public String translate_21_To_99_Into_Words(String numAsString) throws SubsectionLengthException;
	/**
	 * Translates the digits zero to twenty to words
	 * @param sectionOfNumAsString
	 * @return
	 * @throws SubsectionLengthException 
	 */
	public String translate_0_To_20_Into_Words(String sectionOfNumAsString) throws SubsectionLengthException;
	
	/**
	 * Translates the digits 100 to 999 to words
	 * @param sectionOfNumAsString
	 * @return
	 */
	public String translate_100_To_999_Into_Words(String subSectionOfNumAsString) throws SubsectionLengthException;
	
	/**
	 * Adds identifier to subsection. For example if the sectionAsNumber is in the thousands
	 * section of numberAsString, the words 'thousands' to the returned String 
	 * @return
	 * @throws SubsectionLengthException 
	 */
	public String addSubSectionDescription(String sectionString, String numberAsString) throws SubsectionLengthException;
	
	/**
	 * Converts a the digits of subsection of a given number as to words. For example given 100200
	 * @param subSectionOfNumAsString
	 * @return
	 * @throws SubsectionLengthException
	 */
	public String convertSubSectionDigitsToWords(String subSectionOfNumAsString) throws SubsectionLengthException;
	
	/**
	 * returns the words 'negative'
	 * @return
	 */
	public String getNegativeAsWord();
}
