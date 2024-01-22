package jinny.study.springbatch.service;

import java.io.Serializable;
import java.util.List;
import org.springframework.batch.item.ItemWriter;

public class MyItemWriter implements ItemWriter<String> {

    @Override
    public void write(List<? extends String> list) throws Exception {
        for (Object processedData : list) {
            // 처리된 데이터를 저장하거나 다른 작업 수행
            System.out.println("write > " + processedData);
        }
    }
}
