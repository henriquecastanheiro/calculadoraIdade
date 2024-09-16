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
        int mes = request.getMes();
        int dia = request.getDia();

        // Verifica se os números são negativos
        if (ano <= 0) {
            throw new IllegalArgumentException("Ano não pode ser negativo ou zero.");
        }
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("Mês inválido. Deve ser entre 1 e 12.");
        }
        if (dia < 1 || dia > diasNoMes(ano, mes)) {
            throw new IllegalArgumentException("Dia inválido. Deve estar entre 1 e " + diasNoMes(ano, mes) + " para o mês " + mes + ".");
        }
        if (ano > LocalDateTime.now().getYear()) {
            throw new IllegalArgumentException("Ano inválido! Ou você é realmente do futuro??");
        }
    }

    private int diasNoMes(int ano, int mes) {
        switch (mes) {
            case 1: // Janeiro
            case 3: // Março
            case 5: // Maio
            case 7: // Julho
            case 8: // Agosto
            case 10: // Outubro
            case 12: // Dezembro
                return 31;
            case 4: // Abril
            case 6: // Junho
            case 9: // Setembro
            case 11: // Novembro
                return 30;
            case 2: // Fevereiro
                return (isAnoBissexto(ano)) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Mês inválido.");
        }
    }

    private boolean isAnoBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }
}
