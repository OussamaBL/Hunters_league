package com.oussama.hunters_league.service.impl;

import com.oussama.hunters_league.domain.Enum.SpeciesType;
import com.oussama.hunters_league.domain.Species;
import com.oussama.hunters_league.exception.NullVarException;
import com.oussama.hunters_league.exception.Species.SpeciesAlreadyExistException;
import com.oussama.hunters_league.repository.SpeciesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpeciesServiceImplTest {

    @Mock
    private SpeciesRepository speciesRepository;

    @InjectMocks
    private SpeciesServiceImpl speciesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSpecies_shouldSaveSpecies_whenNameDoesNotExist() {
        // Arrange
        Species species = new Species();
        species.setName("Deer");
        when(speciesRepository.findByName("Deer")).thenReturn(Optional.empty());
        when(speciesRepository.save(species)).thenReturn(species);

        // Act
        Species result = speciesService.addSpecies(species);

        // Assert
        assertEquals("Deer", result.getName());
        verify(speciesRepository).save(species);
    }

    @Test
    void addSpecies_shouldThrowException_whenNameAlreadyExists() {
        // Arrange
        Species existingSpecies = new Species();
        existingSpecies.setName("Deer");
        when(speciesRepository.findByName("Deer")).thenReturn(Optional.of(existingSpecies));

        // Act & Assert
        assertThrows(SpeciesAlreadyExistException.class, () -> speciesService.addSpecies(existingSpecies));
    }

    @Test
    void updateSpecies_shouldUpdateSpecies_whenValidIdAndUniqueName() {
        // Arrange
        UUID id = UUID.randomUUID();
        Species existingSpecies = new Species();
        existingSpecies.setId(id);
        existingSpecies.setName("Deer");

        Species updatedSpecies = new Species();
        updatedSpecies.setId(id);
        updatedSpecies.setName("Wolf");

        when(speciesRepository.findById(id)).thenReturn(Optional.of(existingSpecies));
        when(speciesRepository.findByName("Wolf")).thenReturn(Optional.empty());
        when(speciesRepository.save(existingSpecies)).thenReturn(existingSpecies);

        // Act
        Species result = speciesService.updateSpecies(updatedSpecies);

        // Assert
        assertEquals("Wolf", result.getName());
        verify(speciesRepository).save(existingSpecies);
    }

    @Test
    void updateSpecies_shouldThrowException_whenIdIsNull() {
        // Arrange
        Species species = new Species();
        species.setName("Deer");

        // Act & Assert
        assertThrows(NullVarException.class, () -> speciesService.updateSpecies(species));
    }

    @Test
    void updateSpecies_shouldThrowException_whenSpeciesIdDoesNotExist() {
        // Arrange
        UUID id = UUID.randomUUID();
        Species species = new Species();
        species.setId(id);

        when(speciesRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(SpeciesAlreadyExistException.class, () -> speciesService.updateSpecies(species));
    }

    @Test
    void getSpeciesByCategory_shouldReturnPagedSpecies() {
        // Arrange
        SpeciesType category = SpeciesType.BIRD;
        Pageable pageable = PageRequest.of(0, 10);
        List<Species> speciesList = List.of(new Species(), new Species());
        Page<Species> speciesPage = new PageImpl<>(speciesList, pageable, speciesList.size());

        when(speciesRepository.getAllByCategory(category, pageable)).thenReturn(speciesPage);

        // Act
        Page<Species> result = speciesService.getSpeciesByCategory(category, 0, 10);

        // Assert
        assertEquals(2, result.getContent().size());
        verify(speciesRepository).getAllByCategory(category, pageable);
    }

    @Test
    void deleteSpecies_shouldDeleteSpecies_whenIdIsValid() {
        // Arrange
        UUID id = UUID.randomUUID();

        // Act
        speciesService.deleteSpecies(id);

        // Assert
        verify(speciesRepository).deleteById(id);
    }

    @Test
    void deleteSpecies_shouldNotThrowException_whenIdIsNotFound() {
        // Arrange
        UUID id = UUID.randomUUID();
        doNothing().when(speciesRepository).deleteById(id);

        // Act & Assert
        assertDoesNotThrow(() -> speciesService.deleteSpecies(id));
    }
}
