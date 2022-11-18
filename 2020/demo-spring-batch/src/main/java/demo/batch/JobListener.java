package demo.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobListener implements JobExecutionListener {

    @Autowired
    private BatchItemCounter counter;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            StringBuilder sb = new StringBuilder();
            sb.append("!!! JOB FINISHED! Time to verify the results\n");
            sb.append(String.format("\tCount of processed company: [Inserted: %d, Updated: %d, Skipped: %d]",
                    counter.getInsertCount(), counter.getUpdateCount(), counter.getSkipCount()));
            log.info(sb.toString());
        }
    }

}
