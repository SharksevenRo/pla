package com.pla.demo.controller;

import com.pla.demo.model.Dic;
import com.pla.demo.model.QRole;
import com.pla.demo.model.QUser;
import com.pla.demo.model.User;
import com.pla.query.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chey on 2015/9/14.
 */
@Controller
public class DicController extends BaseController {

    @RequestMapping(value = "/dic/list")
    public String list(Dic dic, @RequestParam(defaultValue = "1") int page) {
        Pager<Dic> pager = dic.finder().eq("dicKey").eq("dicValue").like("dicContent")
                .asc("id").asc("sort").pager(page, 10);

        modelMap.put("pager", pager);

        QUser user = QUser.user;
        QRole role = QRole.role;
        Pager<User> pager2 = User.query(User.class).select(user).from(user).leftJoin(user.role, role)
                .where(role.roleName.eq("aaa")).orderBy(user.id.desc()).pageSize(10).pageNo(page).fetchPager();

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

    @RequestMapping(value = "/dic/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(value = "id") Long id) {
        Dic dic = Dic.finder(Dic.class).load(id);
        model.addAttribute("dic", dic);
        return "/dic/edit";
    }

    @RequestMapping(value = "/dic/update", method = RequestMethod.POST)
    public String update(Dic dic) {
        dic.update("dicKey", "dicValue", "dicContent", "keyDesc", "sort");
        return "redirect:/dic/list";
    }

    @RequestMapping(value = "/dic/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Dic.finder(Dic.class).load(id).delete();
        return "redirect:/dic/list";
    }
}
