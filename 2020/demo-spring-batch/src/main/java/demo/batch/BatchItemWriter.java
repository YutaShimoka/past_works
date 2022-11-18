package demo.batch;

import java.util.List;
import java.util.Optional;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import demo.dto.CompanyDTO;
import demo.entity.Company;
import demo.service.CompanyService;

public class BatchItemWriter implements ItemWriter<CompanyDTO> {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private BatchItemCounter counter;

    @Override
    public void write(List<? extends CompanyDTO> items) throws Exception {
        // write items
        items.stream().forEach(this::writeItem);
    }

    private void writeItem(CompanyDTO dto) {

        Optional<Company> companyOpt = companyService.readByCorporateNumber(dto.getCorporateNumberAsLong());
        companyOpt.ifPresentOrElse(entity -> {
            if (companyService.isSame(entity, dto)) {
                counter.incrSkipped();
            } else {
                companyService.update(entity, dto);
                counter.incrUpdated();
            }
        },
                () -> {
                    companyService.create(dto);
                    counter.incrInserted();
                });
    }

}
