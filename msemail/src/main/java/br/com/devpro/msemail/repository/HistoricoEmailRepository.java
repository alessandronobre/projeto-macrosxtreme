package br.com.devpro.msemail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.devpro.msemail.model.HistoricoEmail;

@Repository
public interface HistoricoEmailRepository extends JpaRepository<HistoricoEmail, Long> {
	
	@Query(value="SELECT * FROM HISTORICO_EMAIL WHERE USUARIO = ?1 ORDER BY COD_HIST_EMAIL", nativeQuery = true)
	List<HistoricoEmail> findHistoricoPorUsuario(String usuario);

	
}
