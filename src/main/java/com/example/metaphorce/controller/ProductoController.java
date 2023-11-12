package com.example.metaphorce.controller;

import com.example.metaphorce.model.Producto;
import com.example.metaphorce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/producto")
public class ProductoController {
    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object> getProducto() {
        return productoService.getProducto();
    } //close method

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Object> getProducto(@PathVariable Integer id) {
        return this.productoService.getOne(id);
    } //close method

    @PostMapping("/create")
    public ResponseEntity<Object> registrarProducto(@RequestBody Producto producto) {
        return this.productoService.newProducto(producto);
    } //close method

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return this.productoService.updateProducto(id, producto);
    } //close method

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> elimirProducto(@PathVariable Integer id) {
        return this.productoService.eliminar(id);
    } //close method
} //close class
