package com.alamega.alamegaspringapp.controllers;

import com.alamega.alamegaspringapp.SystemData;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    @GetMapping({"/", "index", "index.html"})
    public String index() {
        return "index";
    }

    @GetMapping({"mac/{macAddress}"})
    public String info(@PathVariable String macAddress, Model model) {
        if (SystemData.All.containsKey(macAddress)) {
            JSONObject json =  SystemData.All.get(macAddress);
            model.addAttribute("mac",  json.get("mac"));
            model.addAttribute("cpuusage",  json.get("cpuusage"));
            model.addAttribute("cores",  json.get("cores"));
            model.addAttribute("os",  json.get("os"));
            model.addAttribute("username",  json.get("username"));
            model.addAttribute("ramusage",  ((JSONObject)json.get("ram")).get("usage"));
            model.addAttribute("ramtotal",  ((JSONObject)json.get("ram")).get("total"));
            //Массив логических дисков
            model.addAttribute("discs",  json.get("discs"));
            //Массив физических дисков
            model.addAttribute("discsphysical",  json.get("discsphysical"));
            //Массив процессоров
            model.addAttribute("cpuinfo",  json.get("cpuinfo"));
            //Массив графических процессоров
            model.addAttribute("gpuinfo",  json.get("gpuinfo"));
            return "info";
        } else {
            return "index";
        }
    }
}
