package projeto.calculadora_idade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projeto.calculadora_idade.dto.CalculadoraIdadeRequestDTO;
import projeto.calculadora_idade.dto.CalculadoraIdadeResponseDTO;
import projeto.calculadora_idade.service.CalculadoraIdadeService;

@RestController
@RequestMapping("/api/idade")
@CrossOrigin(origins = "*")
public class CalculadoraIdadeController {

    @Autowired
    private CalculadoraIdadeService calculadoraIdadeService;

    @PostMapping("/calcular")
    public CalculadoraIdadeResponseDTO calcularIdade(@RequestBody CalculadoraIdadeRequestDTO request) {
        return calculadoraIdadeService.calcularIdade(request);
    }
}
