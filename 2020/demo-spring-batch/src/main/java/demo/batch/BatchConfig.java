package demo.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import demo.dto.CompanyDTO;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<String> reader() {
        return new BatchItemReader();
    }

    @Bean
    public ItemProcessor<String, CompanyDTO> processor() {
        return new BatchItemProcessor();
    }

    @Bean
    public ItemWriter<CompanyDTO> writer() {
        return new BatchItemWriter();
    }

    @Bean
    public Step demoSpringBatchStep(StepListener stepListener) {
        return this.stepBuilderFactory.get("demo-spring-batch-step").<String, CompanyDTO> chunk(200)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .listener(stepListener)
                .build();
    }

    @Bean
    public Job runJob(JobListener jobListener, Step demoSpringBatchStep) {
        return this.jobBuilderFactory.get("demo-spring-batch-job")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener)
                .start(demoSpringBatchStep)
                .build();
    }

}
