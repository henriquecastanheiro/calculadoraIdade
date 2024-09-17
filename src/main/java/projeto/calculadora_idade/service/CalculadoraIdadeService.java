package projeto.calculadora_idade.service;

import org.springframework.stereotype.Service;
import projeto.calculadora_idade.dto.CalculadoraIdadeRequestDTO;
import projeto.calculadora_idade.dto.CalculadoraIdadeResponseDTO;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class CalculadoraIdadeService {

    public CalculadoraIdadeResponseDTO calcularIdade(CalculadoraIdadeRequestDTO request) {

        validarData(request);

        LocalDateTime dataNascimento = LocalDateTime.of(request.getAno(), request.getMes(), request.getDia(), 0, 0);
        LocalDateTime dataAtual = LocalDateTime.now();

        long diasPercorridos = ChronoUnit.DAYS.between(dataNascimento, dataAtual);
        long horasPercorridas = ChronoUnit.HOURS.between(dataNascimento, dataAtual);
        long minutosPercorridos = ChronoUnit.MINUTES.between(dataNascimento, dataAtual);

        long anos = ChronoUnit.YEARS.between(dataNascimento, dataAtual);
        dataNascimento = dataNascimento.plusYears(anos);

        long meses = ChronoUnit.MONTHS.between(dataNascimento, dataAtual);
        dataNascimento = dataNascimento.plusMonths(meses);

        long dias = ChronoUnit.DAYS.between(dataNascimento, dataAtual);
        dataNascimento = dataNascimento.plusDays(dias);

        long horas = ChronoUnit.HOURS.between(dataNascimento, dataAtual);
        dataNascimento = dataNascimento.plusHours(horas);

        long minutos = ChronoUnit.MINUTES.between(dataNascimento, dataAtual);

        return new CalculadoraIdadeResponseDTO(anos, meses, dias, diasPercorridos, horasPercorridas, minutosPercorridos);
    }

    private void validarData(CalculadoraIdadeRequestDTO request) {
        int ano = request.getAno();
        LocalDateTime dataNascimento = LocalDateTime.of(request.getAno(), request.getMes(), request.getDia(), 0, 0);

        if (ano <= 0) {
            throw new IllegalArgumentException("Ano não pode ser negativo ou zero.");
        }
        if ((ano > LocalDateTime.now().getYear()) || dataNascimento.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Ano inválido! Ou você é realmente do futuro??");
        }
    }
}
