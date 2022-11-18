package demo.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import demo.dto.CompanyDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BatchItemProcessor implements ItemProcessor<String, CompanyDTO> {

    @Autowired
    private Validator validator;

    @Override
    public CompanyDTO process(String item) throws Exception {

        String[] lineSplit = addQuotation(item).split("\",\"");

        if (lineSplit.length != 30) {
            log.error(String.format("Error: demo-spring-batch, %s", item));
            return null; // Error Skip
        }

        List<String> strList = new ArrayList<>();

        // remove quotation
        for (String str : lineSplit) { // "4010801014350"
            str = str.replaceAll("(^\")|(\"$)", ""); // 4010801014350
            // convert empty ("") to null for minimum size validation check
            strList.add(!str.equals("") ? str : null);
        }

        CompanyDTO dto = CompanyDTO.valueOf(strList.stream().toArray(String[]::new));

        Set<ConstraintViolation<CompanyDTO>> result = validator.validate(dto);
        if (!result.isEmpty()) {
            log.warn(String.format("Validation: demo.dto.CompanyDTO[corporateNumber=%s], (detail) %s", dto.getCorporateNumber(), result));
            return null; // Validation Skip
        }
        return dto;
    }

    private String addQuotation(String line) {

        line = line.replaceAll("\uFEFF", ""); // remove BOM

        String regex = "\".*?\"";

        if (line.contains("\"")) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(line);
            while (m.find()) {
                line = line.replaceFirst(regex, m.group().replaceAll(",", "\uFEFF").replaceAll("(^\")|(\"$)", ""));
            }
        }

        return line.replaceAll("(^)|($)", "\"").replaceAll(",", "\",\"").replaceAll("\uFEFF", ",");
    }

}
