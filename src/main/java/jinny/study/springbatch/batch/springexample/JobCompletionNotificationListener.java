package jinny.study.springbatch.batch.springexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    private final JdbcTemplate jdbcTemplate;

    private JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {
        LOG.info("beforeJob ============================");
    }

    // job을 통해 등록된 데이터를 검증함
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOG.info("afterJob ============ JOB Finished ================");

            jdbcTemplate
                .query("SELECT first_name, last_name FROM people",
                (rs, row) -> new Person(
                    rs.getString(1),
                    rs.getString(2))
            ).forEach(person -> LOG.debug("Found <" + person + "> in the database."));
        }
    }
}
