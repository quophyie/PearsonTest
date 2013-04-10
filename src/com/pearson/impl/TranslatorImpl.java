package com.pearson.impl;

import java.util.Properties;

import com.pearson.Translator;
import com.pearson.exception.SubsectionLengthException;

public class TranslatorImpl implements Translator {

	private Properties props;
	
	public TranslatorImpl(){
		
	}

	public TranslatorImpl(Properties props){
		this.props  = props;
	}

	@Override
	public String getNegativeAsWord(){
		return props.getProperty("negative")+" ";
	}
	@Override
	public String translate_21_To_99_Into_Words(String numAsString) throws SubsectionLengthException{
		if (numAsString==null || numAsString.length() > 2 || "".equals(numAsString.trim()))
			 throw new SubsectionLengthException("Subsection CANNOT BE null or an empty string and the length CANNOT BE less than 3");
		
		String formattedNum="";
		if (numAsString.length() < 3){
			formattedNum = props.getProperty(numAsString.substring(0, 1)+"0"); //Get the tens part of the number as words e.g, fifty 
			formattedNum += " "+props.getProperty(numAsString.substring(1)); //Get the ones part of the number as words.g. six
		}else
		{
			formattedNum = props.getProperty(numAsString.substring(1, 2)+"0"); //Get the tens part of the number as words e.g, fifty 
			formattedNum += " "+props.getProperty(numAsString.substring(2)); //Get the ones part of the number as words.g. six
		}
		
		return formattedNum;
	}

	@Override
	public  String translate_0_To_20_Into_Words(String sectionOfNumAsString) throws SubsectionLengthException{
		
		if (sectionOfNumAsString==null || sectionOfNumAsString.length() > 2 || "".equals(sectionOfNumAsString.trim()))
			 throw new SubsectionLengthException("Subsection CANNOT BE null or an empty string and the length CANNOT BE less than 2");
		String sectionOfNumConvertedToWords = "";
		
		//If the number starts with zero but does not end in zero, then we only take into consideration the last digit in the number
		if (sectionOfNumAsString.length() >1  && sectionOfNumAsString.startsWith("0") && !sectionOfNumAsString.endsWith("0"))
			sectionOfNumAsString = sectionOfNumAsString.substring(1);
		else if (sectionOfNumAsString.length() > 1 && sectionOfNumAsString.endsWith("00"))
			return  sectionOfNumConvertedToWords;
		
		sectionOfNumConvertedToWords = props.getProperty(sectionOfNumAsString);
		
		if(sectionOfNumConvertedToWords==null)
			sectionOfNumConvertedToWords="";
			
		return sectionOfNumConvertedToWords;
	}

	@Override
	public String translate_100_To_999_Into_Words(String subSectionOfNumAsString) throws SubsectionLengthException{
		if (subSectionOfNumAsString==null || subSectionOfNumAsString.length() != 3 || "".equals(subSectionOfNumAsString.trim()))
			 throw new SubsectionLengthException("Subsection CANNOT BE null or an empty string and the length CANNOT BE less than 3");
			String numConvertedToWords = "";
			
				//Make sure  that we do not get the words if the hundreds section ends in 000
				if (!subSectionOfNumAsString.equals("000") &&!subSectionOfNumAsString.startsWith("0"))
					numConvertedToWords = translate_0_To_20_Into_Words(subSectionOfNumAsString.substring(0,1)) + " "+ props.getProperty("hundred");
				
					String temp = new String (numConvertedToWords);
					if (!subSectionOfNumAsString.endsWith("00"))
						numConvertedToWords = temp+" "+props.getProperty("and")+" ";
					int subSectionNumber = Integer.valueOf(subSectionOfNumAsString.substring(1));
					 if ((subSectionNumber <= 10) || (subSectionNumber > 10 && subSectionNumber < 20) || (subSectionNumber < 100 && subSectionOfNumAsString.endsWith("0")))
						numConvertedToWords = numConvertedToWords+ translate_0_To_20_Into_Words(subSectionOfNumAsString.substring(1));
					 else
						 numConvertedToWords = numConvertedToWords+ translate_21_To_99_Into_Words(subSectionOfNumAsString.substring(1));
			
			return numConvertedToWords;
		}

	@Override
	public String addSubSectionDescription(String sectionString, String numberAsString) throws SubsectionLengthException{
		String formattedString ="";
		if (sectionString.length() > 3)
			throw new SubsectionLengthException("Subsection length is greater than 3. Subsection length CANNOT BE greater than 3");
		formattedString = convertSubSectionDigitsToWords(sectionString);
		
		if (!sectionString.equals("000")) {
			if (numberAsString.length() > 3 && formattedString.startsWith(" and"))
				formattedString = formattedString.replace(" and", "");
				if (numberAsString.length() >= 4 && numberAsString.length() <= 6) {
					formattedString += " " + props.getProperty("thousand");
				} else if (numberAsString.length() >= 7 && numberAsString.length() <= 9) {
					formattedString += " " + props.getProperty("million");
				}
		}
		
		return formattedString;
	}
	
	
	public String convertSubSectionDigitsToWords(String subSectionOfNumAsString) throws SubsectionLengthException{
		String numConvertedToWords = "";
		
		if (subSectionOfNumAsString==null || subSectionOfNumAsString.length() > 3 || "".equals(subSectionOfNumAsString.trim()))
			 throw new SubsectionLengthException("Subsection CANNOT BE null or an empty string and the length CANNOT BE less than 3");;
		
		int sectionNumber = Integer.valueOf(subSectionOfNumAsString);
					
		int remainder = (int) (sectionNumber % 10);
		
		//Convert numbers that are 2 digits or less in length
		//Convert numbers from 0 to 20 or numbers from 20 to 90 to numbers ending in 0 to words
		if ( "0".equals(subSectionOfNumAsString)||(!subSectionOfNumAsString.startsWith("0") 
				&& ((sectionNumber <= 10) || (sectionNumber > 10 
						&& sectionNumber < 20) || (sectionNumber < 100 
								&& subSectionOfNumAsString.endsWith("0"))))){
			numConvertedToWords += this.translate_0_To_20_Into_Words(subSectionOfNumAsString);
		}
		//Convert numbers from 21 to 99 that dont end in '0' to words  
		else if ( !subSectionOfNumAsString.startsWith("0") && sectionNumber >= 20 && sectionNumber <= 99 && remainder > 0){
				numConvertedToWords += this.translate_21_To_99_Into_Words(subSectionOfNumAsString);
		}
		
		//If the numbers from 100 to 999
		if (subSectionOfNumAsString.length() == 3)
		numConvertedToWords += this.translate_100_To_999_Into_Words(subSectionOfNumAsString);
		
		return numConvertedToWords;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

}
