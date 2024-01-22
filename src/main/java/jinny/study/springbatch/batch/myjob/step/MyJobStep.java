package jinny.study.springbatch.batch.myjob.step;

import jinny.study.springbatch.batch.myjob.task.MyTasklet1;
import jinny.study.springbatch.domain.entity.MyData;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MyJobStep {

    private final StepBuilderFactory stepBuilderFactory;
    private final MyTasklet1 myTasklet1;

    public MyJobStep(StepBuilderFactory stepBuilderFactory, MyTasklet1 myTasklet1) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.myTasklet1 = myTasklet1;
    }

    @Bean
    public Step myStep1(ItemReader<MyData> reader, ItemProcessor<MyData, String> processor, ItemWriter<String> writer) {
        return stepBuilderFactory.get("myStep1")
            .<MyData, String>chunk(10)
            .reader(reader)
            .processor(processor)
            .writer(writer)
            .build();
    }

    @Bean
    public Step myStep2() {
        System.out.println("* * * * * * Run myStep2");
        return stepBuilderFactory.get("myStep2")
            .tasklet(myTasklet1)
            .build();
    }
}
