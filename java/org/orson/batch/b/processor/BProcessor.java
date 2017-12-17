package org.orson.batch.b.processor;

import org.springframework.batch.item.ItemProcessor;

public class BProcessor implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		return "org.orson.batch.b.processor.BProcessor.process(String) :" + item;
	}
}
