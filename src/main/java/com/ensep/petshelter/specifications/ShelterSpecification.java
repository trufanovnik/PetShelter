package com.ensep.petshelter.specifications;

import com.ensep.petshelter.entities.AnimalKind;
import com.ensep.petshelter.entities.Pet;
import com.ensep.petshelter.entities.Shelter;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

public class ShelterSpecification {

    public static Specification<Shelter> distinct() {
        return (root, query, cb) -> {
            query.distinct(true);
            return null;
        };
    }

    public static Specification<Shelter> cityContains(String city) {
        return ((root, query, cb) -> {
            if (city == null || city.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(
                    cb.lower(root.get("city")),
                    "%" + city.toLowerCase() + "%"
            );
        });
    }

    public static Specification<Shelter> hasAnimalKind(AnimalKind animalKind) {
        return (root, query, cb) -> {
            if (animalKind == null) {
                return cb.conjunction();
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<Pet> pet = subquery.from(Pet.class);
            subquery.select(pet.get("shelter").get("id"))
                    .where(cb.and(
                            cb.equal(pet.get("shelter").get("id"), root.get("id")),
                            cb.equal(pet.get("animalKind"), animalKind)
                    ));

            return cb.exists(subquery);
        };
    }
}
