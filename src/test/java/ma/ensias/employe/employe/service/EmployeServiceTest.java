package ma.ensias.employe.employe.service;

import ma.ensias.employe.employe.entite.Employe;
import ma.ensias.employe.employe.repository.EmployeRepo;
import ma.ensias.employe.employe.response.EmployeReponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeServiceTest {

    @Mock
    private EmployeRepo employeRepo;

    @Spy
    private ModelMapper mapper = new ModelMapper();

    @InjectMocks
    private EmployeService employeService;

    @Test
    void getEmployeById_retourneLEmploye() {
        when(employeRepo.findById(10)).thenReturn(Optional.of(new Employe(10, "Benali", 25)));

        EmployeReponse reponse = employeService.getEmployeById(10);

        assertEquals(10, reponse.getId());
        assertEquals("Benali", reponse.getName());
        assertEquals(25, reponse.getAge());
    }
}