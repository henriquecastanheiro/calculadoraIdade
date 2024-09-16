package projeto.calculadora_idade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculadoraIdadeRequestDTO {
        private int dia;
        private int mes;
        private int ano;
}
