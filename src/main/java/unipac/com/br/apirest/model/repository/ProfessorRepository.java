package unipac.com.br.apirest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipac.com.br.apirest.model.domain.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
