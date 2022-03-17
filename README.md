## Star Wars Resistence Social Network API

API que permite o compartilhamento de recursos entre os membros da resistência rebelde durante a interminável guerra contra o temível e opressor império galáctico.

## Funcionalidades

### Gerenciando rebeldes

[POST] `http://localhost:8080/v1/rebels` Adiciona um novo rebelde

**Deve ser passado no corpo da requisição o seguinte formato do exemplo abaixo:**

```
{
	"name":String,
	"age":Integer,
	"gender":String,
	"location": {
	    "latitude":Integer,
	    "longitude" : Integer,
	    "baseName": String
		},
	"inventory":[String]
}
```

**Observações:**

- Os valores dos atributos "gender" e "inventory" devem ser passados em UpperCase.
- Os possíveis valores para "gender" são:
  - "MALE", "FEMALE"
- Os possíveis valores dos itens de "inventory" são:
  - [WEAPON, WATER, FOOD, AMMUNITION]

**A API retorna um objeto com o seguinte formato:**

```
{
    "id": "0126b86e-cba2-4ae2-afe0-4900b5d94ca1",
	"name": "Luke",
	"age": 20,
	"gender": "MALE",
	"location": {
		"latitude": 56,
		"longitude": 49,
		"baseName": "Beteugelse"
	},
	"inventory": [
		"WEAPON",
		"WEAPON",
		"FOOD",
		"WATER"
	],
	"confidence_level": 3,
	"registration_date": "2022-03-16",
	"reported_rebels_id": []
}
```

[GET] `http://localhost:8080/v1/rebels/list` Lista todos os rebeldes

[POST] `http://localhost:8080/v1/rebels/denounce` Denuncia um rebelde como um traidor

Deve ser passado no corpo da requisição o seguinte formato:

```
{
	"rebelId" : uuid,
	"traitorId" :uuid
}
```
> Caso o rebelde seja denunciado por 3 vezez, ele passa a ser um traidor.

[PATCH] ` http://localhost:8080/v1/rebels/{id}` Atualiza a localização de um rebelde

O id deve ser passado na URL, o corpo deve conter o seguinte formato:

```
{
	"latitude": Integer,
	"longitude" : Integer,
	"baseName" : String
}
```

Retorna um rebelde com sua localização atualizada.

### Gerenciamento de recursos

Para realizar as trocas entre os rebeldes, deve ser respeitada uma pontuação de acordo com cada item:

| ITEM      | PONTOS |
| --------- | ------ |
| 1 Arma    | 4      |
| 1 Munição | 3      |
| 1 Água    | 2      |
| 1 Comida  | 1      |

[POST] `http://localhost:8080/v1/deals/save-deal` Propõe um acordo de troca de itens

Deve ser passado pelo corpo o seguinte formato:

```
{
	"partyId": uuid,
	"offer": [itens],
	"demand":[itens]
}
```

[POST] `http://localhost:8080/v1/deals/execute-deal` Realiza um acordo de troca de itens

Deve ser passado no corpo da requisição o seguinte formato:

```
{
	"counterpartyId": uuid,
	"dealId": uuid
}
```

Caso os itens ofertados satisfaçam os itens da demanda(quantidade de pontos), a API retorna o rebelde que fez o acordo com os seus itens do inventário atualizados.

[GET] `http://localhost:8080/v1/deals/open-deals`
Retorna a lista de ofertas disponíveis.

 ### Relatórios

[GET] `http://localhost:8080/v1/reports/reliable-rebels` Retorna a porcentagem de rebeldes confiáveis.

[GET] `http://localhost:8080/v1/reports/traitors` Retorna a porcentagem de traidores.

[GET] `http://localhost:8080/v1/reports/rebels/amount-items` Retorna a quantidade média de cada item por rebelde.

[GET] `http://localhost:8080/v1/reports/traitors/lost-points` Retorna a quantidade de pontos perdidos por conta dos traidores.
