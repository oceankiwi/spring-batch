package org.orson.batch.a.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class AWriter implements ItemWriter<String>{

	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println(items);
	}

}
