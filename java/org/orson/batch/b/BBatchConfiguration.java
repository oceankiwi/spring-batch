package org.orson.batch.b;

import javax.sql.DataSource;

import org.orson.batch.b.processor.BProcessor;
import org.orson.batch.b.reader.BReader;
import org.orson.batch.b.writer.BWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BBatchConfiguration {
	
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
	
	@Bean
	public BReader bReader() {
		BReader bReader = new BReader();
		return bReader;
	}
	
	
	@Bean
	public BProcessor bProcess() {
		BProcessor bProcessor = new BProcessor();
		return bProcessor;
	}
	
	@Bean
	public BWriter bWriter() {
		return new BWriter();
	}
	
	@Bean("BJob")
	public Job bRegisterJob() {
		return jobBuilderFactory.get("BBatchJob").incrementer(new RunIdIncrementer())
		.flow(bSteps()).end().build();
	}
	
	@Bean("BStep")
	public Step bSteps() {
		return stepBuilderFactory.get("BBatchStep")
				.<String, String> chunk(1)
				.reader(bReader())
				.processor(bProcess())
				.writer(bWriter())
				.build();
	}

}
