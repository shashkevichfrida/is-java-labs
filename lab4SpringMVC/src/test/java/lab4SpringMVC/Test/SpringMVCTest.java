package lab4SpringMVC.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.controllers.CarBrandController;
import org.example.dto.CarBrandDto;
import org.example.models.Role;
import org.example.models.Status;
import org.example.services.impl.CarBrandServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithMockUser(username = "admin", password = "admin")
public class SpringMVCTest {

    @InjectMocks
    private CarBrandController carBrandController;

    @Mock
    private CarBrandServiceImpl carBrandService;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();



    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carBrandController).build();
    }

    @Test
    public void save() throws Exception {
        CarBrandDto carBrandDto = new CarBrandDto();
        carBrandDto.setName("test");
        carBrandDto.setDate(new Date(1l));
        carBrandDto.setPassword("test");
        carBrandDto.setStatus(Status.ACTIVE);
        carBrandDto.setRole(Role.USER);
        when(carBrandService.save(any())).thenReturn(carBrandDto);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/carBrand/save")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(carBrandDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        List<CarBrandDto> carBrandDtoList = new ArrayList<>();
        CarBrandDto carBrandDto1 = new CarBrandDto();

        carBrandDto1.setName("BMW");

        carBrandDtoList.add(carBrandDto1);

        when(carBrandService.getAll()).thenReturn(carBrandDtoList);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/carBrand/getAll")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(carBrandDtoList)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void deleteAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/carBrand/deleteAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void getById() throws Exception {
        CarBrandDto carBrandDto = new CarBrandDto();
        carBrandDto.setName("BMW");
        when(carBrandService.getById(1L)).thenReturn(carBrandDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/carBrand/getById")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("BMW")));
        when(carBrandService.save(any())).thenReturn(carBrandDto);

        Assert.assertEquals(carBrandController.getById(1L).getName(), carBrandDto.getName());
    }

    @Test
    public void deleteById() throws Exception {
        CarBrandDto carBrandDto = new CarBrandDto();
        carBrandDto.setName("BMW");

        when(carBrandService.getById(1L)).thenReturn(carBrandDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/carBrand/getById")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("BMW")));
        when(carBrandService.save(any())).thenReturn(carBrandDto);

        Assert.assertEquals(carBrandDto.getName(), carBrandController.getById(1L).getName());

        carBrandService.deleteById(1L);
        doNothing().when(carBrandService).deleteById(1L);
        mockMvc.perform(MockMvcRequestBuilders.post("/carBrand/deleteById")
                        .param("id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void deleteByEntity() throws Exception {
        CarBrandDto carBrandDto = new CarBrandDto();
        carBrandDto.setName("Ford");

        carBrandController.deleteByEntity(carBrandDto, 1L);

        verify(carBrandService, times(1)).deleteByEntity(carBrandDto, 1L);
    }
}

