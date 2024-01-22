package jinny.study.springbatch.core.batch;

import javax.sql.DataSource;
import jinny.study.springbatch.domain.entity.MyData;
import jinny.study.springbatch.mapper.MyDataMapper;
import jinny.study.springbatch.processor.MyItemProcessor;
import jinny.study.springbatch.service.MyItemWriter;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    private final DataSource dataSource;

    public BatchConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public ItemReader<MyData> myDataReader() {
        JdbcCursorItemReader<MyData> reader = new JdbcCursorItemReader<>();
        reader.setDataSource(dataSource);
        reader.setSql("select id, name, value from my_data");
        reader.setRowMapper(new MyDataMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<MyData, String> myDataProcessor() {
        return new MyItemProcessor();
    }

    @Bean
    public ItemWriter myDataWriter() {
        return new MyItemWriter();
    }

}
