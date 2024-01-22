package jinny.study.springbatch.batch.myjob.task;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class MyTasklet1 implements Tasklet {

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        // ChunkContext에서 StepExecution을 가져옴
        StepExecution stepExecution = chunkContext.getStepContext().getStepExecution();

        // StepExecution에 속성 추가
        stepExecution.getExecutionContext().put("currentTime", System.currentTimeMillis());

        // 추가한 속성 가져오기
        String myValue = (String) stepExecution.getExecutionContext().get("currentTime");
        System.out.println("Value from ExecutionContext: " + myValue);


        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        // JobParameters 활용
        System.out.println("Job Parameters: " + jobParameters);
        // 실제 작업 로직 처리
        return RepeatStatus.FINISHED;
    }
}
