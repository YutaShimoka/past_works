package demo.batch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class BatchItemReader implements ItemReader<String> {

    private BufferedReader br;

    private void open() throws IOException {
        Path path = Paths.get("C:\\local\\hos\\test_data.csv.gz");
        InputStream is = Files.newInputStream(path);
        GZIPInputStream gzip = new GZIPInputStream(is);
        InputStreamReader isr = new InputStreamReader(gzip, StandardCharsets.UTF_8);
        br = new BufferedReader(isr); // see https://qiita.com/n_slender/items/ef777ba3fe636c1ea2d6#%E8%AA%AD%E3%81%BF%E8%BE%BC%E3%81%BF
    }

    private int count = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count++ < 1) {
            this.open();
        }
        String line;
        while ((line = br.readLine()) != null) {
            return line;
        }
        if (Objects.nonNull(br)) {
            br.close();
        }
        return null;
    }

}
