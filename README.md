# Servidor de Reserva de Passagens

Serviço de reserva de passagens de ônibus implementado usando Threads e sincronização entre processos.

Não há permanência de dados, somente um arquivo _log.txt_ com os dados das reservas.  

### TODO

- [x] Classe de _request_
- [x] Classe de _response_
- [x] Thread de conexão com o servidor
- [x] Thread de reserva de passagens
- [x] Implementar Produtores e Consumidores para a escrita no arquivo _Log.txt_
- [x] Página de reservas
- [x] Classe para a página de reservas
- [x] Consertar alguns bugs
- [ ] Entregar o trabalho

### Funcionamento

###### Tela inicial

- O usuário deve selecionar uma poltrona e em seguida informar o nome do passageiro.

![](C:\Users\Érico\Projetos\servidor-reserva-passagens\capturas-tela\tela-reserva-passagens.jpeg)

###### Efetuando reserva

- Se a reserva for efetuada, uma tela de confirmação irá aparecer junto com os dados da reserva.

![](C:\Users\Érico\Projetos\servidor-reserva-passagens\capturas-tela\reserva-efetuada.jpeg)

###### Voltando à tela inicial

- Após retornar à tela inicial, os mesmos dados aparecerão em uma tabela abaixo do ônibus, e a poltrona selecionada por ele já estará desabilitada para reserva.

![](C:\Users\Érico\Projetos\servidor-reserva-passagens\capturas-tela\apos-efetuada.jpeg)
