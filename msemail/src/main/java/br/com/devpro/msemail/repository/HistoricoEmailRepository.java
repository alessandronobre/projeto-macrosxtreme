package br.com.devpro.msemail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.devpro.msemail.model.HistoricoEmail;

@Repository
public interface HistoricoEmailRepository extends JpaRepository<HistoricoEmail, Long> {
	
	@Query(value="SELECT HE.* FROM HISTORICO_EMAIL HE WHERE HE.USUARIO = ?1", nativeQuery = true)
	List<HistoricoEmail> findHistoricoPorUsuario(String usuario);

	
}
