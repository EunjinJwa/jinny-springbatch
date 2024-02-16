package jinny.study.springbatch.batch.myjob;

import jinny.study.springbatch.batch.myjob.step.MyJobStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
public class MyJobBatch {

    private final JobBuilderFactory jobBuilderFactory;
    private final MyJobStep myJobStep;

    public MyJobBatch(JobBuilderFactory jobBuilderFactory, MyJobStep myJobStep) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.myJobStep = myJobStep;
    }

//    @Bean
    public Job myJob() {
        System.out.println("* * * * * * * Run myJob");
        return jobBuilderFactory.get("myJob")
            .start(myJobStep.myStep1())
            .build();
    }

//    @Bean
//    public Job myJob(Step myStep) {
//        System.out.println("* * * * * * * Run myJob");
//        return jobBuilderFactory.get("myJob")
//            .incrementer(new RunIdIncrementer())
//            .flow(myStep)
//            .end()
//            .build();
//    }


}
