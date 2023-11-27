package com.proyecto.controller;

import java.io.OutputStream;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.Venta;
import com.proyecto.repository.IContenidoRepository;
import com.proyecto.repository.IVentaRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@AllArgsConstructor
@RequestMapping("ventas")
public class VentaController {

    IVentaRepository iVentaRepository;
    IContenidoRepository iContenidoRepository;

    @GetMapping
    public String cargarVentas(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("lstContenidos", iContenidoRepository.findAll());
        model.addAttribute("lstVentas", iVentaRepository.findAll());

        return "crudventa";
    }

    @GetMapping("/editar/{id_venta}")
    public String editarVenta(@PathVariable String id_venta, Model model) {
        Venta v = iVentaRepository.findById(id_venta).get();
        model.addAttribute("venta", v);
        model.addAttribute("lstVentas", iVentaRepository.findAll());
        model.addAttribute("lstContenidos", iContenidoRepository.findAll());
        return "crudventa";
    }

    @PostMapping("/guardar")
    public String guardarVenta(@Valid @ModelAttribute Venta venta, Model model) {
        try {
            iVentaRepository.save(venta);
            model.addAttribute("mensaje", "Informacion Guardada Correctamente");
            model.addAttribute("cssmensaje", "alert alert-success");
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            model.addAttribute("mensaje", "Error en el Guardado de Informacion");
            model.addAttribute("cssmensaje", "alert alert-danger");
        }
        return "redirect:/ventas";
    }

    @GetMapping("/eliminar/{id_venta}")
    public String eliminarContenido(@PathVariable String id_venta, Model model) {
        try {
            Venta v = iVentaRepository.findById(id_venta).get();
            iVentaRepository.delete(v);
            return "redirect:/ventas";
        } catch (Exception e) {
            return "redirect:/ventas";
        }
    }

    // Reporte final

    @GetMapping("/todo")
    public String eliminarTodo(Model model) {
        try {
            iVentaRepository.deleteAll();
            return "redirect:/ventas";
        } catch (Exception e) {
            return "redirect:/ventas";
        }
    }

    private DataSource dataSource;

    private ResourceLoader resourceLoader;

    @GetMapping("/reporte")
    public void reportesConFiltro(HttpServletResponse response) {

        response.setHeader("Content-Disposition", "inline;");
        //
        response.setContentType("application/pdf");
        try {
            // Definir los parametros

            // Carga el jasper
            String ru = resourceLoader.getResource("classpath:reportes/resumenOrden.jasper").getURI().getPath();
            // Combinar el .jasper con la conexion
            JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
            OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
