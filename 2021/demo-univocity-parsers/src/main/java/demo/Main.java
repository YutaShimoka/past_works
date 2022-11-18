package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import com.univocity.parsers.tsv.TsvParserSettings;
import com.univocity.parsers.tsv.TsvRoutines;

import demo.dao.DaoFactory;
import demo.dao.OnSiteInfoDao;
import demo.dto.OnSiteInfoDTO;
import demo.entity.OnSiteInfo;
import demo.util.Checker;

public class Main {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        Path path = Paths.get("src\\main\\resources\\read_test.tsv");
        try (InputStream is = Files.newInputStream(path);
                InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);) {
            TsvParserSettings parserSettings = new TsvParserSettings();
            TsvRoutines routines = new TsvRoutines(parserSettings);
            Iterator<OnSiteInfoDTO> iterator = routines.iterate(OnSiteInfoDTO.class, br).iterator();

            var dtoList = new ArrayList<OnSiteInfoDTO>();

            while (iterator.hasNext()) {

                OnSiteInfoDTO dto = iterator.next();
                var fieldList = new ArrayList<String>();
                fieldList.add(Checker.chkSize(dto.getEmployeeId(), 6, 6));
                fieldList.add(Checker.chkSize(dto.getLastName(), null, 30));
                fieldList.add(Checker.chkSize(dto.getFirstName(), null, 30));
                fieldList.add(Checker.chkYmd(dto.getStartDate()));
                fieldList.add(Checker.chkSize(dto.getTeamName(), null, 255));
                fieldList.add(Checker.chkIsValidInt(dto.getRookieFlag()));

                // Validation
                if (fieldList.stream().filter(x -> Optional.ofNullable(x).orElse("").equals("orz")).count() == 0) {
                    dtoList.add(dto);
                } else {
                    System.out.format("Validation: %s\n", dto.toString());
                }
            }

            DaoFactory factory = new DaoFactory();
            OnSiteInfoDao dao = factory.getOnSiteInfoDao();

            dao.init();

            dtoList.stream().peek(dto -> System.out.format("debug: %s\n", dto)).forEach(dto -> {

                OnSiteInfo entity = new OnSiteInfo();
                entity.setEmployeeId(dto.getEmployeeId());
                entity.setLastName(dto.getLastName());
                entity.setFirstName(dto.getFirstName());
                entity.setStartDate(dto.getStartDate());
                entity.setTeamName(dto.getTeamName());
                entity.setRookieFlag(dto.getRookieFlagAsInteger());
                dao.create(entity);
            });

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.print("\n");
        System.out.println(String.format("Result: %.2f 秒", (endTime - startTime) / 1000.0));
    }
}
