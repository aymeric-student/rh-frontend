package com.courses.rhproject;

import com.courses.rhproject.core.errors.BusinessException;
import com.courses.rhproject.modules.enterprises.*;
import com.courses.rhproject.modules.jobOffer.ContractType;
import com.courses.rhproject.modules.jobOffer.JobOffer;
import com.courses.rhproject.modules.jobOffer.JobOfferMapper;
import com.courses.rhproject.modules.jobOffer.dtos.JobOfferResponse;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class EnterpriseServiceTest {

    private EnterpriseRepository enterpriseRepository;
    private EnterpriseMapper enterpriseMapper;
    private JobOfferMapper jobOfferMapper;
    private EnterpriseService enterpriseService;

    @BeforeEach
    void setUp() {
        enterpriseRepository = mock(EnterpriseRepository.class);
        enterpriseMapper = mock(EnterpriseMapper.class);
        jobOfferMapper = mock(JobOfferMapper.class);

        enterpriseService = new EnterpriseService(
                enterpriseRepository,
                enterpriseMapper,
                jobOfferMapper
        );
    }

    @Test
    void should_GetAllEnterprises() {
        // Act
        CreateEnterprise enterprise1 = new CreateEnterprise(
                "Efrei",
                "Ecole d'élève ingénieur",
                "https://www.efrei-alumni.org",
                "0671279539",
                "398 898 338 00023",
                "technologie",
                "Villejuif",
                2000,
                1936
        );


        CreateEnterprise enterprise2 = new CreateEnterprise(
                "42",
                "Ecole d'informatique",
                "https://www.42.fr",
                "0123456789",
                "123 456 789 00010",
                "informatique",
                "Paris",
                500,
                2013
        );

        Enterprise entity1 = Enterprise.builder()
                .enterpriseId(UUID.randomUUID())
                .name(enterprise1.name())
                .description(enterprise1.description())
                .website(enterprise1.website())
                .phoneNumber(enterprise1.phoneNumber())
                .siret(enterprise1.siret())
                .industry(enterprise1.industry())
                .headquartersLocation(enterprise1.headquartersLocation())
                .numberOfEmployees(enterprise1.numberOfEmployees())
                .foundedYear(enterprise1.foundedYear())
                .build();

        Enterprise entity2 = Enterprise.builder()
                .enterpriseId(UUID.randomUUID())
                .name(enterprise2.name())
                .description(enterprise2.description())
                .website(enterprise2.website())
                .phoneNumber(enterprise2.phoneNumber())
                .siret(enterprise2.siret())
                .industry(enterprise2.industry())
                .headquartersLocation(enterprise2.headquartersLocation())
                .numberOfEmployees(enterprise2.numberOfEmployees())
                .foundedYear(enterprise2.foundedYear())
                .build();


        EnterpriseResponse response1 = new EnterpriseResponse(
                entity1.getEnterpriseId(), entity1.getName(), entity1.getDescription(), entity1.getWebsite(),
                entity1.getPhoneNumber(), entity1.getSiret(), entity1.getIndustry(),
                entity1.getHeadquartersLocation(), entity1.getNumberOfEmployees(), entity1.getFoundedYear(),
                List.of()
        );

        EnterpriseResponse response2 = new EnterpriseResponse(
                entity2.getEnterpriseId(), entity2.getName(), entity2.getDescription(), entity2.getWebsite(),
                entity2.getPhoneNumber(), entity2.getSiret(), entity2.getIndustry(),
                entity2.getHeadquartersLocation(), entity2.getNumberOfEmployees(), entity2.getFoundedYear(),
                List.of()
        );

        when(enterpriseRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(enterpriseMapper.toDto(entity1)).thenReturn(response1);
        when(enterpriseMapper.toDto(entity2)).thenReturn(response2);

        // Act
        List<EnterpriseResponse> result = enterpriseService.getAllEnterprises();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());

        assertEquals("Efrei", result.get(0).name());
        assertEquals("Villejuif", result.get(0).headquartersLocation());

        assertEquals("42", result.get(1).name());
        assertEquals("Paris", result.get(1).headquartersLocation());
    }

    @Test
    void should_CreateEnterprise() {
        // Act
        CreateEnterprise createEnterprise = new CreateEnterprise(
                "Efrei",
                "Ecole d'élève ingénieur",
                "https://www.efrei-alumni.org",
                "0671279539",
                "398 898 338 00023",
                "technologie",
                "Villejuif",
                2000,
                1936
        );

        Enterprise entity = Enterprise.builder()
                .enterpriseId(java.util.UUID.randomUUID())
                .name(createEnterprise.name())
                .description(createEnterprise.description())
                .website(createEnterprise.website())
                .phoneNumber(createEnterprise.phoneNumber())
                .siret(createEnterprise.siret())
                .industry(createEnterprise.industry())
                .headquartersLocation(createEnterprise.headquartersLocation())
                .numberOfEmployees(createEnterprise.numberOfEmployees())
                .foundedYear(createEnterprise.foundedYear())
                .build();

        EnterpriseResponse expectedResponse = new EnterpriseResponse(
                entity.getEnterpriseId(),
                entity.getName(),
                entity.getDescription(),
                entity.getWebsite(),
                entity.getPhoneNumber(),
                entity.getSiret(),
                entity.getIndustry(),
                entity.getHeadquartersLocation(),
                entity.getNumberOfEmployees(),
                entity.getFoundedYear(),
                List.of()
        );

        // Arrange
        when(enterpriseMapper.toEntity(createEnterprise)).thenReturn(entity);
        when(enterpriseRepository.save(entity)).thenReturn(entity);
        when(enterpriseMapper.toDto(entity)).thenReturn(expectedResponse);

        // Assert
        EnterpriseResponse result = enterpriseService.createEnterprise(createEnterprise);

        assertNotNull(result);
        assertEquals(entity.getEnterpriseId(), result.enterpriseId());
        assertEquals("Efrei", result.name());
        assertEquals("Ecole d'élève ingénieur", result.description());
        assertEquals("https://www.efrei-alumni.org", result.website());
        assertEquals("0671279539", result.phoneNumber());
        assertEquals("398 898 338 00023", result.siret());
        assertEquals("technologie", result.industry());
        assertEquals("Villejuif", result.headquartersLocation());
        assertEquals(2000, result.numberOfEmployees());
    }

    @Test
    void should_throwException_when_NoEnterpriseFound() {
        when(enterpriseRepository.findAll()).thenReturn(List.of());

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> enterpriseService.getAllEnterprises()
        );

        assertEquals(EnterprisesErrors.ENTERPRISES_NOT_FOUND.getMessage(), exception.getMessage());
    }

    @Test
    void should_returnJobOffers_whenEnterpriseHasOffers() {
        UUID enterpriseId = UUID.randomUUID();

        JobOffer jobOffer = JobOffer.builder()
                .id(UUID.randomUUID())
                .title("Développeur Java")
                .description("Mission bancaire")
                .salary(BigDecimal.valueOf(45000))
                .location("Paris")
                .publicationDate(LocalDate.now())
                .expirationDate(LocalDate.now().plusDays(30))
                .contractType(ContractType.CONTRACT)
                .build();

        List<JobOffer> jobOffers = List.of(jobOffer);

        Enterprise enterprise = Enterprise.builder()
                .enterpriseId(enterpriseId)
                .name("Efrei")
                .jobOffers(jobOffers)
                .build();

        JobOfferResponse response = new JobOfferResponse(
                jobOffer.getId(),
                jobOffer.getTitle(),
                jobOffer.getDescription(),
                jobOffer.getContractType(),
                jobOffer.getSalary(),
                jobOffer.getLocation(),
                jobOffer.getPublicationDate(),
                jobOffer.getExpirationDate(),
                null
        );

        when(enterpriseRepository.findByIdWithOffersAndWorkflow(enterpriseId)).thenReturn(Optional.of(enterprise));
        when(jobOfferMapper.toDto(jobOffer)).thenReturn(response);

        List<JobOfferResponse> result = enterpriseService.getAllOffersByEnterprise(enterpriseId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Développeur Java", result.get(0).title());
    }

    @Test
    void should_throwException_whenEnterpriseHasNoOffers() {
        UUID enterpriseId = UUID.randomUUID();

        Enterprise enterprise = Enterprise.builder()
                .enterpriseId(enterpriseId)
                .name("Efrei")
                .jobOffers(List.of())
                .build();

        when(enterpriseRepository.findByIdWithOffersAndWorkflow(enterpriseId)).thenReturn(Optional.of(enterprise));

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> enterpriseService.getAllOffersByEnterprise(enterpriseId)
        );

        assertEquals(EnterprisesErrors.ENTERPRISE_HAS_NOT_JOB_OFFER.getMessage(), exception.getMessage());
    }
}
