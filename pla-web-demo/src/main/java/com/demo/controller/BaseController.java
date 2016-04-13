package com.demo.controller;

import com.demo.controller.customeditor.CustomDateEditor;
import com.demo.controller.customeditor.CustomDoubleEditor;
import com.demo.controller.customeditor.CustomIntegerEditor;
import com.demo.controller.customeditor.CustomLongEditor;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


public class BaseController {
    protected Logger logger = Logger.getLogger(this.getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
        binder.registerCustomEditor(Integer.class, new CustomIntegerEditor(true));
        binder.registerCustomEditor(Long.class, new CustomLongEditor(true));
        binder.registerCustomEditor(Double.class, new CustomDoubleEditor(true));
    }

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected ModelMap modelMap;
    protected Model model;

    @ModelAttribute
    public void setProperties(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Model model) {
        this.request = request;
        this.response = response;
        this.modelMap = modelMap;
    }
}
