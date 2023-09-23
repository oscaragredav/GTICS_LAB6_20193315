package com.example.lab6.controller;

import com.example.lab6.entity.Device;
import com.example.lab6.repository.DeviceRepository;
import com.example.lab6.repository.SiteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({"/equipos", "/Equipos"})
public class EquiposController {

    private final DeviceRepository deviceRepository;
    private final SiteRepository siteRepository;

    public EquiposController(DeviceRepository deviceRepository,
                             SiteRepository siteRepository) {
        this.deviceRepository = deviceRepository;
        this.siteRepository = siteRepository;
    }

    @GetMapping("/lista")
    public String lista(Model model){
        List<Device> lista = deviceRepository.findAll();
        model.addAttribute("listaEquipos", lista);
        return "equipos/lista";
    }
    @GetMapping({"/nuevoEquipo","/nuevoEquipos"})
    public String nievoEquipo(Model model){
        model.addAttribute("listaSitios",siteRepository.findAll());
        return "equipos/nuevoEquipo";
    }
    @PostMapping("/guardarEquipo")
    public String guardarNuevoViaje(Device device, RedirectAttributes attr) {
        String estado = device.getDeviceid() == null ? "creado" : "actualizado";
        String mensaje = "Equipo " + estado  + device.getDevicename()+ "creado exitosamente ";
        attr.addFlashAttribute("msg", mensaje);
        deviceRepository.save(device);
        return "redirect:/equipos/lista";
    }

    @GetMapping("/borrarEquipo")
    public String borrarViaje(Model model,
                              @RequestParam("id") int id,
                              RedirectAttributes attr) {

        Optional<Device> optionalDevice = deviceRepository.findById(id);

        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            String devicename = device.getDevicename();
            deviceRepository.deleteById(id);
            attr.addFlashAttribute("msg", "Equipo " + devicename + " borrado exitosamente");

        }
        return "redirect:/Equipos/lista";

    }
    @GetMapping("/editarEquipo")
    public String editarViaje(Model model, @RequestParam("id") int id) {

        Optional<Device> optionalDevice = deviceRepository.findById(id);

        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            model.addAttribute("equipo", device);
            model.addAttribute("listaSitios",siteRepository.findAll());
            return "equipos/editarEquipos";
        } else {
            return "redirect:/equipos";
        }
    }
}