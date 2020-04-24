package unipac.com.br.apirest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unipac.com.br.apirest.model.domain.Trabalho;

@Repository
public interface TrabalhoRepository extends JpaRepository<Trabalho, Long> {
}
