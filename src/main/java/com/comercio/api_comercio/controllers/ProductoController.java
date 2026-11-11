package com.comercio.api_comercio.controllers;

import com.comercio.api_comercio.models.Producto;
import com.comercio.api_comercio.services.CategoriaService;
import com.comercio.api_comercio.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    // 1. LEER
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarTodos());
        return "lista_productos";
    }

    // 2. CREAR (Mostrar formulario)
    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "formulario_producto";
    }

    // 2. CREAR (Guardar)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/productos";
    }

    // 3. ACTUALIZAR (Mostrar formulario con datos)
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Producto producto = productoService.buscarPorId(id).orElse(null);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaService.listarTodas());
        return "formulario_producto";
    }

    // 4. ELIMINAR
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}