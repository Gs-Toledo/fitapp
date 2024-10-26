package helenocampos.github.io.fitapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import helenocampos.github.io.fitapp.controller.ImcController;
import helenocampos.github.io.fitapp.service.GenderService;

public class ImcControllerTest {
    private ImcController imcController;
    private GenderService genderServiceMock;

    @BeforeEach
    public void setUp() {
        genderServiceMock = Mockito.mock(GenderService.class);
        imcController = new ImcController(genderServiceMock);
    }

    @Test
    public void testImcValue() {
        double result = imcController.getIMC(70, 1.70);
        double expected = 24.22;
        assertEquals(expected, result, 0.002);
    }

    @Test
    public void testImcValueAltura1() {
        double result = imcController.getIMC(50, 1);
        double expected = 50;
        assertEquals(expected, result, 0.002);
    }

    @Test
    public void testGetIMCClassificationByGender_AbaixoPeso() {
        String result = imcController.getIMCClassificationByGender(19.99, "male");
        String expected = "Abaixo do peso";
        assertEquals(expected, result);
    }

    @Test
    public void testGetIMCClassificationByGender_AbaixoPesoFeminino() {
        String result = imcController.getIMCClassificationByGender(21, "female");
        String expected = "Peso normal";
        assertEquals(expected, result);
    }

    @Test
    public void testGetIMCClassificationByGender_PesoNormal() {
        String result = imcController.getIMCClassificationByGender(24.9, "male");
        String expected = "Peso normal";
        assertEquals(expected, result);
    }

    @Test
    public void testGetIMCClassificationByGender_ObesidadeLeve() {
        String result = imcController.getIMCClassificationByGender(29.9, "male");
        String expected = "Obesidade leve";
        assertEquals(expected, result);
    }

    @Test
    public void testGetIMCClassificationByGender_ObesidadeModerada() {
        String result = imcController.getIMCClassificationByGender(39.9, "male");
        String expected = "Obesidade moderada";
        assertEquals(expected, result);
    }

    @Test
    public void testGetIMCClassificationByGender_HiperObeso() {
        String result = imcController.getIMCClassificationByGender(999999999, "male");
        String expected = "Obesidade mórbida";
        assertEquals(expected, result);
    }

    @Test
    public void testGetIMCClassification_PesoNormal() {
        String result = imcController.getIMCClassification(24.9);
        String expected = "Peso normal";
        assertEquals(expected, result);
    }

    @Test
    public void testGetIMCClassification_ExcessoPeso() {
        String result = imcController.getIMCClassification(29);
        String expected = "Excesso de peso";
        assertEquals(expected, result);
    }

    @Test
    public void testGetIMCClassification_Obeso_1() {
        String result = imcController.getIMCClassification(34);
        String expected = "Obesidade classe I";
        assertEquals(expected, result);
    }

    @Test
    public void testGetIMCClassification_Obeso_2() {
        String result = imcController.getIMCClassification(39);
        String expected = "Obesidade classe II";
        assertEquals(expected, result);
    }

    @Test
    public void testGetIMCClassification_HiperObeso() {
        String result = imcController.getIMCClassification(999999999);
        String expected = "Obesidade classe III";
        assertEquals(expected, result);
    }

    @Test
    public void testGetGeneroPt_male() {
        String result = imcController.getGeneroPt("male");
        String expected = "Masculino";
        assertEquals(expected, result);
    }

    @Test
    public void testGetGeneroPt_female() {
        String result = imcController.getGeneroPt("female");
        String expected = "Feminino";
        assertEquals(expected, result);
    }

    @Test
    public void testGetGeneroPt_Indefinido() {
        String result = imcController.getGeneroPt("Helicoptero");
        String expected = "Indefinido";
        assertEquals(expected, result);
    }
}
