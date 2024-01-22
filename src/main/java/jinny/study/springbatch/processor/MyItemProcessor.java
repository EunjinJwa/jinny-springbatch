package jinny.study.springbatch.processor;

import jinny.study.springbatch.domain.entity.MyData;
import org.springframework.batch.item.ItemProcessor;

public class MyItemProcessor implements ItemProcessor<MyData, String> {

    @Override
    public String process(MyData myData) throws Exception {
        // 특정 로직을 수행한 후 결과를 반환
        String processData = "Processed: " + myData.toString();
        System.out.println(processData);
        return processData;
    }
}
