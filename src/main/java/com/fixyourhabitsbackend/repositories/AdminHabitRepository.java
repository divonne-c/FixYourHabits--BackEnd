package com.fixyourhabitsbackend.repositories;

import com.fixyourhabitsbackend.models.AdminHabit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminHabitRepository extends JpaRepository<AdminHabit, Long> {
}
