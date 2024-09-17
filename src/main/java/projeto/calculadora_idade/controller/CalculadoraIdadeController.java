package projeto.calculadora_idade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> calcularIdade(@RequestBody CalculadoraIdadeRequestDTO request) {
        try {
            CalculadoraIdadeResponseDTO response = calculadoraIdadeService.calcularIdade(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor." +
                    "Entre em contato com henrique.castanheirotb@gmail.com");
        }
    }
}
