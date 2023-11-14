package com.example.metaphorce.controller;


import com.example.metaphorce.model.Categoria;
import com.example.metaphorce.service.CategoriaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categoria")
public class CategoriaController {
    private final CategoriaServices categoriaServices;

    @Autowired
    public CategoriaController(final CategoriaServices pCategoriaServices) {
        this.categoriaServices = pCategoriaServices;
    }

    //@PreAuthorize("hasRole('Comprador') OR hasRole('Supplier') OR ")
    @GetMapping("/all")
    public ResponseEntity<Object> getCategoria() {
        return categoriaServices.getCategoria();
    } //close method

    @GetMapping("/getOne/{id}")
    @PreAuthorize("hasAnyRole('Comprador','Admin','Supplier')")
    public ResponseEntity<Object> getCategoria(@PathVariable final Long id) {
        return this.categoriaServices.getOne(id);
    } //close method

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('Comprador','Admin','Supplier')")
    public ResponseEntity<Object> registrarCategoria(@RequestBody final Categoria categoria) {
        return this.categoriaServices.newCategoria(categoria);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> actualizarCategoria(@PathVariable final Long id, @RequestBody final Categoria categoria) {
        return this.categoriaServices.updateCategoria(id, categoria);
    } //close method

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Object> eliminarUsuario(@PathVariable final Long id) {
        return this.categoriaServices.eliminar(id);
    } //close method
} //close class
