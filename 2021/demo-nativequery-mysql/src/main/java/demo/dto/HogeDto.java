package demo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import demo.entity.Hoge;
import demo.util.Json2ItemList;

public class HogeDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonProperty("items")
    private List<HogeItem> hogeItemList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<HogeItem> getHogeItemList() {
        return hogeItemList;
    }

    public void setHogeItemList(List<HogeItem> hogeItemList) {
        this.hogeItemList = hogeItemList;
    }

    public static HogeDto valueOf(Hoge hoge) {
        HogeDto hogeDto = new HogeDto();
        hogeDto.setId(hoge.getId());
        hogeDto.setHogeItemList(Json2ItemList.getHogeItemList(hoge.getItems()));
        return hogeDto;
    }
}
