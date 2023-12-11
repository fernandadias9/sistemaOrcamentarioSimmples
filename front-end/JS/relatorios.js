const form = document.querySelector("#relatorios");

let usuario = sessionStorage.getItem('usuario');
usuario = JSON.parse(usuario);

let dados = [];
let meuGrafico = null;

form.addEventListener("submit", (evento) => {
    evento.preventDefault();
    gerarRelatorio();
});

async function gerarRelatorio(){
  let ano = document.querySelector('#ano').value;
  let options = {
    method: 'GET',
    headers: {'Content-type': 'application/json'}
  };
  const relatorio = await fetch('http://localhost:8080/senhor-financas/rest/relatorio/' + usuario.idUsuario + '/' + ano, options);
  dados = await relatorio.json();
  const receitas = dados.map(dados => dados.somaReceitas);
  const despesas = dados.map(dados => dados.somaDespesas);
  
  if(meuGrafico != null){
    meuGrafico.destroy();
  }

  meuGrafico = new Chart(grafico, {
    type: 'bar',
    data: {
      labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
      datasets: [
        {
        label: 'Total de Receitas',
        data:  receitas,
        backgroundColor: '#9cffe5',
        borderWidth: 1
      },
      {
        label: 'Total de Despesas',
        data: despesas,
        backgroundColor: '#f4c5da',
        borderWidth: 1
      }],
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  }); 
}