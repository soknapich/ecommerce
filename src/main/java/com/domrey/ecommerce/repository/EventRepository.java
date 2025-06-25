package com.domrey.ecommerce.repository;

import com.domrey.ecommerce.entity.MyEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<MyEvent, Long> {
}
