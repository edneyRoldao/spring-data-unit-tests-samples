package com.edn.base.repositories;

import com.edn.base.models.EntidadeTaxa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntidadeTaxaRepository extends JpaRepository<EntidadeTaxa, Long> {

    List<EntidadeTaxa> findAllByIdTipoTaxaIn(List<Long> tipoTaxaIds);

    List<EntidadeTaxa> findAllByIdTipoTaxaInAndActiveTrueAndIdIsNot(List<Long> tipoTaxaIds, Long id);

    List<EntidadeTaxa> findAllByNomeIsLike(String nome);

    List<EntidadeTaxa> findAllByActiveTrue();

    EntidadeTaxa findByIdTipoTaxaAndActiveTrue(Long idTipoTaxa);

    void deleteAllByIdTipoTaxaIn(List<Long> ids);

    EntidadeTaxa findTopByOrderByIdDesc();

    Optional<EntidadeTaxa> findTopByOrderByIdTipoTaxaDesc();

    Optional<EntidadeTaxa> findTopByNomeOrderByIdTipoTaxaDesc(String nome);

    Optional<EntidadeTaxa> findTopByNomeAndIdTipoTaxaOrderByIdDesc(String nome, Long taxa);

    List<EntidadeTaxa> findAllByIdTipoTaxaLessThanEqual(Long idTipoTaxa);

    List<EntidadeTaxa> findFirst5ByIdTipoTaxaOrderByIdDesc(Long idTipoTaxa);
}
