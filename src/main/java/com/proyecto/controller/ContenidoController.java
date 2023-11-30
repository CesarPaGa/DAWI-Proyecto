package com.proyecto.controller;

import java.io.OutputStream;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.model.Contenido;
import com.proyecto.model.Venta;
import com.proyecto.repository.IContenidoRepository;
import com.proyecto.repository.IGeneroRepository;
import com.proyecto.repository.ITipoRepository;
import com.proyecto.repository.IVentaRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
public class ContenidoController {
	@Autowired
	private IContenidoRepository repoContenido;
	@Autowired
	private ITipoRepository repoTipo;
	@Autowired
	private IGeneroRepository repoGenero;

	
	@GetMapping("/listadoContenido")
	public String listarContenidos(Model model) {
		model.addAttribute("lstContenido", repoContenido.findAll());
		
		return "listadoContenido";
	}
	@GetMapping("/detalleContenido/{id}")
	public String verDetalleContenido(@PathVariable int id, Model model) {
	    Contenido contenido = repoContenido.findById(id).orElse(null);

	    if (contenido == null) {
	        return "error";  // Puedes crear una página de error específica
	    }

	    model.addAttribute("contenido", contenido);
	    return "detalleContenido";
	}
	
	@GetMapping("/mantenimiento/contenido")
	public String cargarMantContenido(Model model) {
		model.addAttribute("contenido", new Contenido());
		model.addAttribute("lstContenido", repoContenido.findAll());
		model.addAttribute("lstTipo", repoTipo.findAll());
		model.addAttribute("lstGenero", repoGenero.findAll());
		return "crudcontenido";
	}
	@GetMapping("/editar/{id_con}")
	public String editarContenido(@PathVariable int id_con, Model model) {
		Contenido c = repoContenido.findById(id_con).get();
		model.addAttribute("contenido", c);
		model.addAttribute("lstContenido", repoContenido.findAll());
		model.addAttribute("lstTipo", repoTipo.findAll());
		model.addAttribute("lstGenero", repoGenero.findAll());
		return "crudcontenido";
	}
	
	@PostMapping("/guardar")
	public String guardarContenido(@ModelAttribute Contenido contenido, Model model) {
		try {
			repoContenido.save(contenido);
			model.addAttribute("mensaje", "Informacion Guardada Correctamente");
			model.addAttribute("cssmensaje", "alert alert-success");
		} catch (Exception e) {
			System.out.println(e.getCause().getMessage());
			model.addAttribute("mensaje", "Error en el Guardado de Informacion");
			model.addAttribute("cssmensaje", "alert alert-danger");
		}
		return "redirect:/mantenimiento/contenido";
	}
	@GetMapping("/eliminar/{id_con}")
	public String eliminarContenido(@PathVariable int id_con, Model model) {
		try {
			Contenido c = repoContenido.findById(id_con).get();
			repoContenido.delete(c);
			return "redirect:/mantenimiento/contenido";
		} catch (Exception e) {
			return "redirect:/mantenimiento/contenido";
		}
	}
	@Autowired
    private DataSource dataSource;
	
	@Autowired
    private ResourceLoader resourceLoader;
    
    @GetMapping("/contenido/reporte")
    public void reportesConFiltroContenido(HttpServletResponse response) {

        response.setHeader("Content-Disposition", "inline;");
        //
        response.setContentType("application/pdf");
        try {
            String ru = resourceLoader.getResource("classpath:reportes/resumenProductos.jasper").getURI().getPath();
            // Combinar el .jasper con la conexion
            JasperPrint jasperPrint = JasperFillManager.fillReport(ru, null, dataSource.getConnection());
            OutputStream outStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
