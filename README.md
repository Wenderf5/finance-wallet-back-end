## Routes

> ***/api/v1/user***
>
> POST: Create a new user </br>
> Request body:
> ```json
> {
>    "email": "string",
>    "password": "string",
>    "userName": "string"
> }
> ```
> Response body:
> ```json
> {
>     "status": "number",
>     "message": "string"
> }
> ```
---

> ***/api/v1/user***
>
> POST: Create a new user </br>
> Request body:
> ```json
> {
>    "email": "string",
>    "password": "string",
>    "userName": "string"
> }
> ```
> Response body:
> ```json
> {
>     "status": "number",
>     "message": "string"
> }
> ```
---

## Services

> ***E-mail service***
>
> É uma serviço para enviar E-mail. Está no pacote `com.financewallet.services.EmailService.java`, os métodos para enviar são chamados em cadeia (getSession(), setEmail(), send()). O método getSession() precisa do email do provedor de email, senha do email e um arquivo de propriedades que vou deixar um modelo de referência.
>
> ```properties
> mail.smtp.host
> mail.smtp.port
> mail.smtp.auth
> mail.smtp.starttls.enable
> ```
---