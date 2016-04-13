package com.demo.controller;

import com.demo.model.Dic;
import com.demo.model.User;
import com.pla.query.On;
import com.pla.query.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by chey on 2015/9/14.
 */
@Controller
public class DicController extends BaseController {
//    @Autowired
//    private DicService dicService;

    @RequestMapping(value = "/dic/list")
    public String list(Dic dic, @RequestParam(defaultValue = "1") int page) {
        Pager<Dic> pager = dic.finder().eq("dicKey").eq("dicValue").like("dicContent")
                .asc("id").asc("sort").pager(page, 10);

        modelMap.put("pager", pager);

        List<User> users = User.finder(User.class).join("role").on(On.idEq(1l)).list("id", "userName", "role.name", "role.alias");
        for (User user : users) {
            System.out.println(user.getId() + " " + user.getUserName() + " " +
                    (user.getRole() != null ? (user.getRole().getName() + " " + user.getRole().getAlias()) : ""));
        }

        return "/dic/list";
    }


    @RequestMapping(value = "/dic/create", method = RequestMethod.GET)
    public String create(Dic dic) {
        return "/dic/create";
    }

    @RequestMapping(value = "/dic/save", method = RequestMethod.POST)
    public String save(Dic dic) {
        dic.save();
        return "redirect:/dic/list";
    }
}
