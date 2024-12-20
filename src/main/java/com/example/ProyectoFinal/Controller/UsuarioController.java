/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.ProyectoFinal.Controller;

import com.example.ProyectoFinal.model.Cliente;
import com.example.ProyectoFinal.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("login", new Login());
        return "login";
    }

    @PostMapping("/login")
    public String showForm(Login login, Model model) {
        if ("admin".equals(login.getUsers()) && "admin".equals(login.getPassword())) {
            model.addAttribute("mensaje", "Bienvenido, acabas de ingresar como administrador");
            return "vistaAdmin";
        } else {
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

    @GetMapping("/registro")
    public String showRegistroForm(Model model) {
        model.addAttribute("registro", new Cliente());
        return "registro";
    }

    @PostMapping("/registro")
    public String submitCliente(Cliente cliente, Model model) {
        String sql = "INSERT INTO clientes (Nombre, Apellido, Contraseña, Correo, FechaNac, Edad) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, cliente.getNombre(), cliente.getApellido(), cliente.getContraseña(), cliente.getCorreo(), cliente.getFechaNac(), cliente.getEdad());
        return "redirect:/login"; // Redirigir a la página de inicio de sesión después del registro
    }
}




