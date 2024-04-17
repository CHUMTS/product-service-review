# Пример приложения для работы с продуктами

## Запуск с помощью Docker Compose

Для запуска приложения с помощью Docker Compose выполните следующие шаги:

1. Склонируйте репозиторий: `git clone https://github.com/your-repo.git`
2. Перейдите в директорию проекта и запустите Docker Compose для поднятия Postgres: `docker-compose up`

## Запуск с помощью Gradle

`./gradlew bootRun`

Приложение будет доступно по адресу `http://localhost:8080`.

## Описание API

### Поиск продуктов по модели или производителю

**URL:** `http://localhost:8080/api/v1/products/search/{substring}`

**Метод:** GET

**Параметры:**

- `{substring}` - подстрока, которую нужно найти в имени модели или производителя

**Пример запроса:** `http://localhost:8080/api/v1/products/search/samsung`

**Пример ответа:**

`todo`

### Поиск продукта по айди

**URL:** `http://localhost:8080/api/v1/products/{id}`

**Метод:** GET

**Параметры:**

- `{id}` - Long - числовой идентификатор, по которому нужно найти продукт

**Пример запроса:** `http://localhost:8080/api/v1/products/15`

**Пример ответа:**

`{
"id": 15,
"price": 599.99,
"color": "green",
"manufacturer": "dyson",
"year": 2021,
"type": "vacuum cleaner",
"modelName": "v11 torque drive"
}`

### Добавление продукта

**URL:** `http://localhost:8080/api/v1/products/`

**Метод:** POST

**Тело запроса:**

```{
"price": 599.99,
"color": "green",
"manufacturer": "dyson",
"year": 2021,
"type": "vacuum cleaner",
"modelName": "v11 torque drive"
}
```

**Пример запроса:** `http://localhost:8080/api/v1/products/15`

**Пример ответа:**

`29`