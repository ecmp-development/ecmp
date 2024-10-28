package com.ecmp.api.repositories;

import com.ecmp.api.enums.Types;
import com.ecmp.api.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByName(String name);

    @Query("""
select i from Item i where i.id = :id or i.name = :name or i.type = :type or i.description = :description or i.price = :price
""")
    List<Item> filterByParams(Long id, String name, Types type, String description, double price);
}
