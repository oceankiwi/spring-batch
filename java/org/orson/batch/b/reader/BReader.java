package org.orson.batch.b.reader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class BReader implements ItemReader<String> {
	private Integer count = 0;
	
	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		count++;
		
		if(count > 100) {
			return null;
		}
		
		Thread.sleep(1000);
		return "BReader" + count;
	}


}
