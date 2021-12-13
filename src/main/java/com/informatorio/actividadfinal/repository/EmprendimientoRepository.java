package com.informatorio.actividadfinal.repository;

import java.util.List;
import com.informatorio.actividadfinal.entity.Emprendimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Long> {
    @Query("select e from Emprendimiento e join fetch e.tags t where t.nombre like %:tag%")
    List<Emprendimiento> findByTag(@Param("tag") String tag);
    List<Emprendimiento> findByPublicado(Boolean publicado);
}