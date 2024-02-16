package jinny.study.springbatch.batch.sample;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicJobSample {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public BasicJobSample(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job basicJob() {
        System.out.println("* * * * basic job start * * * *");
        return jobBuilderFactory.get("basicJob")
            .start(basicStep1())
            .build();
    }

    @Bean
    public Step basicStep1() {
        return stepBuilderFactory.get("basicStep1")
            .tasklet(basicTasklet1())
            .build();
    }

    @Bean
    public Tasklet basicTasklet1() {
        return (contribution, chunkContext) -> {
            System.out.println("Tasklet1 >>> Hello, Spring Batch!");
            return RepeatStatus.FINISHED;
        };
    }


}
