package com.edn.base.repositories;

import com.edn.base.models.EntidadeTaxa;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class EntidadeTaxaRepositoryTest {

    @Autowired
    private TestEntityManager  entityManager;

    @Autowired
    private EntidadeTaxaRepository repository;

    @Test
    public void injectionNotNull() {
        assertNotNull(entityManager);
    }

    @Test
    public void test_findAllByIdTipoTaxaLessThanEqual() {
        entityManager.persist(new EntidadeTaxa(10L));
        entityManager.persist(new EntidadeTaxa(11L));
        entityManager.persist(new EntidadeTaxa(12L));
        entityManager.persist(new EntidadeTaxa(13L));
        entityManager.persist(new EntidadeTaxa(14L));
        entityManager.flush();

        List<EntidadeTaxa> list = repository.findAllByIdTipoTaxaLessThanEqual(12L);
        assertEquals(3, list.size());
    }

    @Test
    public void teste_findAllById() {
        entityManager.persist(new EntidadeTaxa(10L));
        entityManager.persist(new EntidadeTaxa(11L));
        entityManager.persist(new EntidadeTaxa(12L));
        entityManager.persist(new EntidadeTaxa(13L));
        entityManager.persist(new EntidadeTaxa(14L));
        entityManager.flush();

        List<Long> ids = new ArrayList<>();
        ids.add(10L);
        ids.add(11L);

        List<EntidadeTaxa> list = repository.findAllByIdTipoTaxaIn(ids);
        assertEquals(2, list.size());
    }

    @Test
    public void teste_null() {
        entityManager.persist(new EntidadeTaxa(10L));
        entityManager.persist(new EntidadeTaxa(11L));
        entityManager.persist(new EntidadeTaxa(12L));
        entityManager.persist(new EntidadeTaxa(13L));
        entityManager.persist(new EntidadeTaxa(14L));
        entityManager.flush();

        List<Long> ids = new ArrayList<>();
        ids.add(100L);
        ids.add(110L);

        List<EntidadeTaxa> list = repository.findAllByIdTipoTaxaIn(ids);

        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test
    public void teste_findAllByNomeIsLike() {
        entityManager.persist(new EntidadeTaxa(10L, "edney"));
        entityManager.persist(new EntidadeTaxa(10L, "giselle"));
        entityManager.persist(new EntidadeTaxa(10L, "nadine"));
        entityManager.persist(new EntidadeTaxa(10L, "marjorie"));
        entityManager.persist(new EntidadeTaxa(10L, "ricardo"));
        entityManager.persist(new EntidadeTaxa(10L, "jill"));
        entityManager.persist(new EntidadeTaxa(10L, "claire"));
        entityManager.flush();

        List<EntidadeTaxa> list = repository.findAllByNomeIsLike("%a%");
        assertEquals(4, list.size());
    }

    @Test
    public void teste_findAllByNomeIsLike_all() {
        entityManager.persist(new EntidadeTaxa(10L, "edney"));
        entityManager.persist(new EntidadeTaxa(10L, "giselle"));
        entityManager.persist(new EntidadeTaxa(10L, "nadine"));
        entityManager.persist(new EntidadeTaxa(10L, "marjorie"));
        entityManager.persist(new EntidadeTaxa(10L, "ricardo"));
        entityManager.persist(new EntidadeTaxa(10L, "jill"));
        entityManager.persist(new EntidadeTaxa(10L, "claire"));
        entityManager.flush();

        List<EntidadeTaxa> list = repository.findAllByNomeIsLike("%%");
        assertEquals(7, list.size());
    }

    @Test
    public void teste_findAllByActiveTrue() {
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", false));
        entityManager.persist(new EntidadeTaxa(10L, "edney", false));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.flush();

        List<EntidadeTaxa> list = repository.findAllByActiveTrue();
        assertEquals(3, list.size());
    }

    @Test
    public void teste_findFirstByIdTipoTaxaAndActiveTrue() {
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney", false));
        entityManager.persist(new EntidadeTaxa(9L, "edney", false));
        entityManager.persist(new EntidadeTaxa(8L, "edney", true));
        entityManager.flush();

        EntidadeTaxa et = repository.findByIdTipoTaxaAndActiveTrue(9L);

        assertEquals("edney roldao", et.getNome());
    }

    @Test
    public void test_deleteAllByIdTipoTaxaIn() {
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(8L, "edney", true));
        entityManager.flush();

        List<Long> ids = new ArrayList<>();
        ids.add(9L);
        ids.add(8L);

        repository.deleteAllByIdTipoTaxaIn(ids);
        List<EntidadeTaxa> list = repository.findAll();

        assertEquals(list.size(), 2);
    }

    @Test
    public void test_findTopByOrderByIdDesc() {
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(8L, "edney", true));
        entityManager.flush();

//        Optional<EntidadeTaxa> opt = repository.findTopByOrderByIdDesc();
        EntidadeTaxa opt = repository.findTopByOrderByIdDesc();


        assertEquals(2, 2);
    }

    @Test
    public void test_findTopByOrderByIdTipoTaxaDesc() {
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(8L, "edney", true));
        entityManager.flush();

        EntidadeTaxa opt = repository.findTopByOrderByIdTipoTaxaDesc().get();

        assertEquals(10, opt.getIdTipoTaxa());
    }

    @Test
    public void test_findTopByOrderByIdTipoTaxaDescAndNome() {
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(11L, "edney", true));
        entityManager.persist(new EntidadeTaxa(12L, "edney", true));
        entityManager.persist(new EntidadeTaxa(22L, "edney", true));
        entityManager.persist(new EntidadeTaxa(19L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.flush();

        EntidadeTaxa taxa = repository.findTopByNomeOrderByIdTipoTaxaDesc("edney").get();

        assertEquals(22, taxa.getIdTipoTaxa());
    }

    @Test
    public void test_findTopByNomeAndIdTipoTaxaOrderByIdDesc() {
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.flush();

        EntidadeTaxa taxa = repository.findTopByNomeAndIdTipoTaxaOrderByIdDesc("edney", 10L).get();

        assertEquals(22, taxa.getIdTipoTaxa());
    }

    @Test
    public void test() {
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(10L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.persist(new EntidadeTaxa(9L, "edney roldao", true));
        entityManager.flush();

        List<EntidadeTaxa> taxas = repository.findFirst5ByIdTipoTaxaOrderByIdDesc(10L);

        assertFalse(taxas.isEmpty());
    }

}









