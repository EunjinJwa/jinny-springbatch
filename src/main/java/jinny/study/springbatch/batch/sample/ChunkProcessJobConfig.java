package jinny.study.springbatch.batch.sample;

import java.util.Arrays;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChunkProcessJobConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ChunkProcessJobConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public ItemReader<Integer> itemReader() {
        System.out.println("Run reader");
        // 간단한 숫자 리스트를 생성하는 ItemReader
        List<Integer> items = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);

        return new ListItemReader<>(items);
    }

    @Bean
    public ItemProcessor<Integer, String> itemProcessor() {
        // 각 숫자에 대해 간단한 가공을 수행함
        return item -> {
            System.out.println(Thread.currentThread().getName() + "|  Run processor : " + item);
            return item + " processed";
        };
    }

    @Bean
    public ItemWriter<String> itemWriter() {
        // 가곡된 결과를 출력하는 ItemWriter
        return items -> {
            System.out.println(Thread.currentThread().getName() + " Run writer");
            for (String item : items) {
                System.out.println(Thread.currentThread().getName() + " | writing >> " + item);
            }
        };
    }

    @Bean
    public Job chunkProcessJob(Step chunkProcessStep) {
        return jobBuilderFactory.get("chunkProcessJob")
            .start(chunkProcessStep)
            .build();
    }

    @Bean
    public Step chunkProcessStep(ItemReader<Integer> itemReader,
        ItemProcessor<Integer, String> itemProcessor,
        ItemWriter<String> itemWriter) {
        return stepBuilderFactory.get("chunkProcessStep")
            .<Integer, String>chunk(3) // cunk size 3
            .reader(itemReader)
            .processor(itemProcessor)
            .writer(itemWriter)
            .build();
    }



}
