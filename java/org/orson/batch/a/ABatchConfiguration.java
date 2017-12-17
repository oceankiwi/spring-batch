package org.orson.batch.a;

import javax.sql.DataSource;

import org.orson.batch.a.processor.AProcessor;
import org.orson.batch.a.reader.AReader;
import org.orson.batch.a.writer.AWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class ABatchConfiguration {

	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    public JobLauncher jobLauncher;

    @Autowired
    public DataSource dataSource;
	
	@Bean
	public AReader aReader() {
		AReader aReader = new AReader();
		return aReader;
	}
	
	@Bean
	public AProcessor aProcess() {
		AProcessor aProcessor = new AProcessor();
		return aProcessor;
	}
	
	@Bean
	public AWriter aWriter() {
		return new AWriter();
	}
	
	@Bean("AJob")
	public Job aRegisterJob() {
		return jobBuilderFactory.get("ABatchJob")
				.incrementer(new RunIdIncrementer())
				.flow(bSteps())
				.end()
				.build();
	}
	
	@Bean("AStep")
	public Step bSteps() {
		return stepBuilderFactory.get("ABatchStep")
				.<String, String> chunk(1)
				.reader(aReader())
				.processor(aProcess())
				.writer(aWriter())
				.build();
	}
	
}
