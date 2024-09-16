package projeto.calculadora_idade.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CalculadoraIdadeResponseDTO {
    private long anos;
    private long meses;
    private long dias;
    private long diasPercorridos;
    private long horasPercorridas;
    private long minutosPercorridos;
}
