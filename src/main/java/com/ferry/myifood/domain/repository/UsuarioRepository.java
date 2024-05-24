package com.ferry.myifood.domain.repository;

import com.ferry.myifood.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}