package com.mazowiecka.demo.Repository;

import com.mazowiecka.demo.Entity.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {
}
