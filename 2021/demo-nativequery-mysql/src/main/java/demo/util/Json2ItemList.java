package demo.util;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.dto.HogeItem;

public class Json2ItemList {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<HogeItem> getHogeItemList(String items) {
        List<HogeItem> hogeItemList = Collections.emptyList();
        try {
            hogeItemList = mapper.readValue(items, new TypeReference<List<HogeItem>>() {});
        } catch (JsonProcessingException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        return hogeItemList;
    }
}
