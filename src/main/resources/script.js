document.getElementById('idadeForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const dia = document.getElementById('dia').value;
    const mes = document.getElementById('mes').value;
    const ano = document.getElementById('ano').value;
    const data = {
        dia: parseInt(dia),
        mes: parseInt(mes),
        ano: parseInt(ano)
    };

    fetch('http://localhost:8080/api/idade/calcular', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na solicitação');
            }
            return response.json();
        })
        .then(result => {
            // Exibe o resultado na página
            document.getElementById('resultant').innerHTML = `
            <p>Sua idade é: ${result.anos} anos, ${result.meses} meses e ${result.dias} dias.</p>
            <p>Desde seu nascimento já se passaram aproximadamente:</p>
            <p>${result.diasPercorridos} dias, ${result.horasPercorridas} horas, ${result.minutosPercorridos} minutos.</p>
        `;
        })
        .catch(error => {
            console.error('Erro:', error);
            document.getElementById('resultant').innerHTML = '<p>Ocorreu um erro ao calcular a idade. Tente novamente mais tarde.</p>';
        });
});
