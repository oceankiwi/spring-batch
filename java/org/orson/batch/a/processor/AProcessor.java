package org.orson.batch.a.processor;

import org.springframework.batch.item.ItemProcessor;

public class AProcessor implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		return "org.orson.batch.a.processor.AProcessor.process(String) :" + item + " - " + Thread.currentThread().getName();
	}
}
