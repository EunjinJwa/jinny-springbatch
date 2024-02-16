package jinny.study.springbatch.batch.myjob.step;

import jinny.study.springbatch.batch.myjob.task.MyTasklet1;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
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
    public Step myStep1() {
        System.out.println("* * * * * * Run myStep1");
        return stepBuilderFactory.get("myStep1")
            .tasklet(myTasklet1)
            .build();
    }
}
