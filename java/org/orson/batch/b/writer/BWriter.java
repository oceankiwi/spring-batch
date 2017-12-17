package org.orson.batch.b.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class BWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println(items);
	}
}
