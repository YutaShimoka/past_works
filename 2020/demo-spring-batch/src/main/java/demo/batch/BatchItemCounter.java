package demo.batch;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class BatchItemCounter {

    private int insertCount;

    private int updateCount;

    private int skipCount;

    public void incrInserted() {
        insertCount += 1;
    }

    public void incrUpdated() {
        updateCount += 1;
    }

    public void incrSkipped() {
        skipCount += 1;
    }

}
