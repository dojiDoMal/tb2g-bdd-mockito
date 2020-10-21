package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    Visit visit;

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService service;

    @BeforeEach
    public void setUp(){
        visit = new Visit();
    }

    @Test
    void findAll() {

        //given
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        given(visitRepository.findAll()).willReturn(visits);
        //when
        Set<Visit> foundVisits = service.findAll();
        //then
        then(visitRepository).should().findAll();
        assertThat(foundVisits).hasSize(1);

    }

    @Test
    void findById() {

        //given
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visit));
        //when
        Visit foundVisit = service.findById(1L);
        //then
        then(visitRepository).should().findById(anyLong());
        assertThat(foundVisit).isNotNull();

    }

    @Test
    void save() {

        //given
        given(visitRepository.save(any(Visit.class))).willReturn(visit);
        //when
        Visit savedVisit = service.save(new Visit());
        //then
        then(visitRepository).should().save(any(Visit.class));
        assertThat(savedVisit).isNotNull();

    }

    @Test
    void delete() {

        //when
        service.delete(visit);
        //then
        then(visitRepository).should().delete(any(Visit.class));

    }

    @Test
    void deleteById() {

        //when
        service.deleteById(1L);
        //then
        then(visitRepository).should().deleteById(anyLong());

    }
}