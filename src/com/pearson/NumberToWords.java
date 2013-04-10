package com.pearson;

import com.pearson.exception.SubsectionLengthException;

public interface NumberToWords {

	public String convertNumToWords(long number, int sectionStart) throws com.pearson.exception.SubsectionLengthException;
}
