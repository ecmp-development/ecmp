package com.api.ecmpdev.repositories;

import com.api.ecmpdev.enums.Types;
import com.api.ecmpdev.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByName(String name);

    @Query("""
select i from Item i where i.id = :id and i.name = :name and i.type = :type and i.description = :description and i.price = :price
""")
    List<Item> filterByParams(Long id, String name, Types type, String description, double price);
}
