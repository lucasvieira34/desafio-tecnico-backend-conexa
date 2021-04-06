# Desafio Técnico Backend Conexa

Projeto Java de API REST com Spring Framework para o desafio técnico da **Conexa Saúde**.
Foram utilizados o banco MySQL para ambiente de desenvolvimento e o Postgres para ambiente de produção.
A aplicação encontra-se deployada e pronta para testes no seguinte endereço:

[https://desafio-tecnico-backend-conexa.herokuapp.com/](https://desafio-tecnico-backend-conexa.herokuapp.com/)

# Steps To Follow

A seguir encontra-se um tutorial de como utilizar a API para se obter a melhor experiência do usuário na usabilidade do software.
A requisições deverão ser realizadas através do Postman/Insomnia ou outro software para requisições REST.

## Realizar Login

O usuário deverá realizar uma requisição **POST** para login através do endpoint:

> https://desafio-tecnico-backend-conexa.herokuapp.com/api/conexa-saude/auth

E deverá ser enviado no **body** da requisição os dados de login. Por exemplo:

    {
	    "username": "daniel.santos@email.com",
	    "password": "123"
    }

Como resposta, será retornado **200** seguido de um token.

    {
	    "token":  "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW5pZWwuc2FudG9zIiwiaWF0IjoxNjE3NzE2NDI0LCJleHAiOjE2MTc3MzQ0MjR9.2CqN6qn9uY3BvqXjYQ7Ok1H9NPrKfhTLIayeX4LCoBM"
    }

## Verificar Informações e Agendamentos do Médico

O usuário deverá realizar uma requisição **GET** para o seguinte endpoint:

> https://desafio-tecnico-backend-conexa.herokuapp.com/api/conexa-saude/schedules

***OBS*:** Deverá ser enviado no **header** da requisição o **Authorization** do tipo Bearer Token com o token gerado.

Como resposta, será retornado **200** seguido das informações do médico, o token utilizado e seus agendamentos.

    {
		"token":  "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYW5pZWwuc2FudG9zIiwiaWF0IjoxNjE3NzE2NjEyLCJleHAiOjE2MTc3MzQ2MTJ9.S87lsg07kGlxZ86EjvFDr0Wi2blbyLnvT93lOWs02do",
		"medico":  "Dr. Daniel Santos",
		"especialidade":  "Cardiologia",
		"agendamentos_hoje":  [
			{
				"id_paciente":  1,
				"data_hora_atendimento":  "2020-08-03 09:00:00"
			}
		]
    }

## Cadastramento de Pacientes

Deverá ser realizada uma requisição **POST** para o seguinte endpoint:

> https://desafio-tecnico-backend-conexa.herokuapp.com/api/conexa-saude/patients

E deverá ser enviado no **body** da requisição os dados do paciente a ser cadastrado. Por exemplo:

    {
		"nome":  "André Gazito",
		"cpf":  "302.302.201-12",
		"idade":  "30",
		"telefone":  "(21) 7374-4445"
    }

Como resposta, será retornado **201** com a URI do paciente cadastrado no **header** da resposta.

> OBS: Não será necessário enviar token de autenticação.

## Realizar Agendamento de Consulta

O médico logado deverá realizar uma requisição **POST** para o seguinte endpoint:

> https://desafio-tecnico-backend-conexa.herokuapp.com/api/conexa-saude/appointment

E deverá ser enviado no **body** da requisição os dados da hora de atendimento e o paciente a ser consultado. Por exemplo:

    {
		"data_hora_atendimento":  "2020-08-03 10:00:00",
		"id_paciente":  1
    }

***OBS*:** Deverá ser enviado no **header** da requisição o **Authorization** do tipo Bearer Token com o token do médico.

# Endpoints

A aplicação possui os seguintes endpoints:

> BASE PATH: https://desafio-tecnico-backend-conexa.herokuapp.com/api/conexa-saude

| ENDPOINT | MÉTODO | PARÂMETROS | DESCRIÇÃO
|--|--|--|--|
| /auth | POST | BODY | Realiza login enviando no corpo os dados de acesso.
| /doctors | GET | Authorization | Retorna todos os médicos cadastrados e seus agendamentos.
| /schedules | GET | Authorization | Retorna os agendamentos do médico autenticado.
| /patients | POST | BODY | Realiza o cadastramento de um novo paciente.
| /appointment | POST | BODY e Authorization | Realiza o agendamento de consulta para um paciente.
| /logout | POST | BODY | Realiza logout enviando o token no corpo da requisição.
