package com.bcaf.ivan.SpringBootThymeleaf.Controller;

import com.bcaf.ivan.SpringBootThymeleaf.Entity.Item;
import com.bcaf.ivan.SpringBootThymeleaf.Util.ItemDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    private final ItemDao itemDao;

    @Autowired
    public ItemController() {
        this.itemDao=new ItemDao();
    }
    @PostMapping
    @RequestMapping("getItem")
    public String getItem(int id,Model model) throws JsonProcessingException {
        Item iu=itemDao.doSelectItembyId(id);
        ObjectMapper Obj = new ObjectMapper();
        return Obj.writeValueAsString(iu);
    }

    @PostMapping
    @RequestMapping("updateItem")
    public String updateItem(int id,String name,int type,String desc,double price,int stock) {
        Item iu=itemDao.doSelectItembyId(id);
        iu.setItemName(name);
        iu.setItemType(type);
        iu.setDescription(desc);
        iu.setPrice(price);
        iu.setStock(stock);
        itemDao.doUpdateItems(iu);
        return "index";
    }

    @PostMapping
    @RequestMapping("saveItem")
    public String saveItem(String name,int type,String desc,double price,int stock) {
        Item iu=new Item();
        iu.setItemName(name);
        iu.setItemType(type);
        iu.setDescription(desc);
        iu.setPrice(price);
        iu.setStock(stock);
        itemDao.doInsertItems(iu);
        return "index";
    }

    @PostMapping
    @RequestMapping("deleteItem")
    public String deleteItem(int id) {
        Item item=new Item();
        item.setId(id);
        itemDao.doDeleteItem(item);
        return "index";
    }
}
