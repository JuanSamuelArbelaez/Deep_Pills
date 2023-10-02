package deep_pills.repositories.accounts.users;

import deep_pills.model.entities.accounts.users.physicians.Physician;
import deep_pills.model.entities.schedule.Shift;
import deep_pills.model.enums.lists.City;
import deep_pills.model.enums.lists.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.Collection;
import java.util.List;

@Repository
public interface PhysicianRepository extends JpaRepository<Physician, Long> {
    @Query("select ph from Physician ph")
    List<Physician> findAll();

    @Query("select ph from Physician ph where ph.personalId = :personalId")
    Physician findByPersonalId(String personalId);

    @Query("select ph from Physician ph where ph.name = :name")
    List<Physician> findByName(String name);

    @Query("select ph from Physician ph where ph.lastName = :lastName")
    List<Physician> findByLastName(String lastName);

    @Query("select ph from Physician ph where ph.shift = :shift")
    List<Physician> findByShift(Shift shift);

    @Query("SELECT DISTINCT p FROM Physician p JOIN p.physicianSpecialization s WHERE s = :specialization")
    List<Physician> findBySpecialization(Specialization specialization);

    @Query("SELECT DISTINCT p FROM Physician p JOIN p.city c WHERE c = :city")
    List<Physician> findByCity(City city);
}
