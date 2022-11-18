package demo.batch;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        // TODO 自動生成されたメソッド・スタブ

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        log.info("Summary: " + stepExecution.getSummary());
        if (stepExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! STEP FINISHED! Time to verify the results");
            return ExitStatus.COMPLETED;
        }
        return ExitStatus.FAILED;
    }

}
