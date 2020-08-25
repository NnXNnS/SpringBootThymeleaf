package com.bcaf.ivan.SpringBootThymeleaf.Controller;

import com.bcaf.ivan.SpringBootThymeleaf.Entity.Mahasiswa;
import com.bcaf.ivan.SpringBootThymeleaf.Util.ItemDao;
import com.bcaf.ivan.SpringBootThymeleaf.Util.MahasiswaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {
    private final ItemDao itemDao;

    @Autowired
    private MahasiswaDao mahasiswaDao;
    @Autowired
    public IndexController() {
        this.itemDao=new ItemDao();
    }
    @GetMapping
    @RequestMapping({"/","/mahasiswas"})
    public String main(@PageableDefault(size=5) Pageable pageable, Model model) {
        Page<Mahasiswa> listMahasiswa=mahasiswaDao.findAll(pageable);
        model.addAttribute("page",listMahasiswa);
        return "index";
    }
    @GetMapping
    @RequestMapping({"/search"})
    public String search(@PageableDefault(size=5) Pageable pageable, Model model) {
        Page<Mahasiswa> listMahasiswa=mahasiswaDao.findAll(pageable);
        model.addAttribute("page",listMahasiswa);
        return "index";
    }
//    @GetMapping
//    @RequestMapping({"/index" })
//    public String main(Model model) {
//        List<Item> listItem=itemDao.doSelectAllItems();
//        listItem.sort((Item i,Item i2)-> i.getItemName().compareTo(i2.getItemName()));
//        model.addAttribute("listItem",listItem);
//        return "index";
//    }
}
