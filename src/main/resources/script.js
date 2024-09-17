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

    console.log("Dados enviados:", data);

    fetch('http://localhost:8080/api/idade/calcular', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            const contentType = response.headers.get("content-type");
            if (contentType && contentType.indexOf("application/json") !== -1) {
                return response.json().then(data => {
                    if (!response.ok) {
                        throw new Error(data.error || "Erro ao calcular a idade");
                    }
                    return data;
                });
            } else {
                return response.text().then(text => {
                    throw new Error(text);
                });
            }
        })
        .then(result => {
            document.getElementById('resultant').innerHTML = `
            <p>Sua idade é: </p>
            <p>${result.anos} anos, ${result.meses} meses e ${result.dias} dias.</p>
            <p>Desde seu nascimento já se passaram aproximadamente:</p>
            <p>${result.diasPercorridos} dias, 
            <p>${result.horasPercorridas} horas, 
            <p>${result.minutosPercorridos} minutos.</p>
        `;
        })
        .catch(error => {
            console.error('Erro completo:', error);
            document.getElementById('resultant').innerHTML = `<p style="color: red;">Erro: ${error.message}</p>`;
        });
});

