package com.bcaf.ivan.SpringBootThymeleaf.Util;

import com.bcaf.ivan.SpringBootThymeleaf.Entity.Mahasiswa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MahasiswaDao extends JpaRepository<Mahasiswa,String> {
    

    Page<Mahasiswa> findByNamaContaining(String nama, Pageable pageable);
}
