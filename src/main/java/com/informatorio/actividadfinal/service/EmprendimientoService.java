package com.informatorio.actividadfinal.service;

import java.time.LocalDateTime;
import com.informatorio.actividadfinal.entity.Emprendimiento;
import com.informatorio.actividadfinal.entity.Usuario;
import com.informatorio.actividadfinal.repository.EmprendimientoRepository;
import com.informatorio.actividadfinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmprendimientoService {
    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    @Autowired
    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository,
                                 UsuarioRepository usuarioRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Emprendimiento guardar(Long usuarioId, Emprendimiento emprendimiento) {
        Usuario usuario = usuarioRepository.getById(usuarioId);
        emprendimiento.setCreador(usuario);
        return emprendimientoRepository.save(emprendimiento);
    }
    public Emprendimiento eliminar(Long id, Emprendimiento emprendimiento) {
        Emprendimiento emprendimientoEliminado = emprendimientoRepository.getById(id);
        emprendimientoEliminado.setActivo(false);
        return emprendimientoRepository.save(emprendimientoEliminado);
    }
    public Emprendimiento modificar(Long id, Emprendimiento emprendimiento) {
        Emprendimiento emprendimientoModificado = emprendimientoRepository.getById(id);
        if (!emprendimiento.getNombre().trim().isEmpty()) {
            emprendimientoModificado.setNombre(emprendimiento.getNombre()); }
        if (!emprendimiento.getDescripcion().trim().isEmpty()) {
            emprendimientoModificado.setDescripcion(emprendimiento.getDescripcion()); }
        if (!emprendimiento.getContenido().trim().isEmpty()) {
            emprendimientoModificado.setContenido(emprendimiento.getContenido()); }
        if (emprendimiento.getObjetivo() != null && emprendimiento.getObjetivo() > 0) {
            emprendimientoModificado.setObjetivo(emprendimiento.getObjetivo()); }
        if (emprendimiento.isPublicado() != true) { emprendimientoModificado.setPublicado(false); }
        if (emprendimiento.isPublicado() != false) { emprendimientoModificado.setPublicado(true); }
        if (emprendimiento.getUrl() != null) { emprendimientoModificado.setUrl(emprendimiento.getUrl()); }
        if (emprendimiento.getTags() != null) { emprendimientoModificado.setTags(emprendimiento.getTags()); }
        emprendimientoModificado.setUltimaModificacion(LocalDateTime.now());
        return emprendimientoRepository.save(emprendimientoModificado);
    }
}