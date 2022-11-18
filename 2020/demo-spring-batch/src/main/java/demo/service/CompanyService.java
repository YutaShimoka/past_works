package demo.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dto.CompanyDTO;
import demo.entity.Company;
import demo.repository.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public void create(CompanyDTO dto) {
        Company entity = new Company();
        entity.setCorporateNumber(dto.getCorporateNumberAsLong());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPrefectureCode(dto.getPrefectureCodeAsInteger());
        entity.setCityCode(dto.getCityCodeAsInteger());
        entity.setPostCode(dto.getPostCodeAsInteger());
        companyRepository.save(entity);
    }

    public void update(Company entity, CompanyDTO dto) {
        entity.setCorporateNumber(dto.getCorporateNumberAsLong());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPrefectureCode(dto.getPrefectureCodeAsInteger());
        entity.setCityCode(dto.getCityCodeAsInteger());
        entity.setPostCode(dto.getPostCodeAsInteger());
        companyRepository.save(entity);
    }

    public boolean isSame(Company entity, CompanyDTO dto) {
        return Objects.equals(entity.getName(), dto.getName())
                && Objects.equals(entity.getAddress(), dto.getAddress())
                && Objects.equals(entity.getPrefectureCode(), dto.getPrefectureCodeAsInteger())
                && Objects.equals(entity.getCityCode(), dto.getCityCodeAsInteger())
                && Objects.equals(entity.getPostCode(), dto.getPostCodeAsInteger());
    }

    public Optional<Company> readByCorporateNumber(Long corporateNumber) {
        return companyRepository.findById(corporateNumber);
    }

}
