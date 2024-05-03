package com.example.demo.data.repository;

import com.example.demo.data.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

// Оголошення інтерфейсу, який наслідує JpaRepository. Це забезпечить автоматичну реалізацію стандартних CRUD методів.
@Repository
public interface NoteNewRepositoryJpa extends JpaRepository <NoteEntity, UUID> {

//За потреби тут можна додати додаткові методи
}