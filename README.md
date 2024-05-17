# Ads-online
## Описание
Учебный проект, реализующий бэкенд часть сайта по перепродаже вещей. Фронтенд и бэкенд части запущены в разных контейнерах.
Все данные хранятся в базе данных на локальной машине, сама база в контейнере не развернута.

<h2>Распределение прав</h2>

<table style="width: 70%; border-color: red; margin-left: auto; margin-right: auto;">
<thead>
<tr style="height: 13px;">
<th style="height: 13px; width: 11.0296%;"><span style="color: #ffffff;">Роль</span></th>
<th style="height: 13px; width: 60.9704%;"><span style="color: #ffffff;">Полномочия</span></th>
</tr>
</thead>
<tbody>
<tr style="height: 13px;">
<td style="height: 13px; width: 11.0296%;"><span style="color: #ffffff;">Гость</span></td>
<td style="height: 13px; width: 60.9704%;">
<ul>
<li><span style="color: #ffffff;">&nbsp;Просмотривать весь список объявлений на сайте</span></li>
</ul>
</td>
</tr>
<tr style="height: 13.2812px;">
<td style="height: 13.2812px; width: 11.0296%;"><span style="color: #ffffff;">Админ</span></td>
<td style="height: 13.2812px; width: 60.9704%;">
<ul>
<li><span style="color: #ffffff;">&nbsp;Редактировать и удалять любые комментарии.</span></li>
<li><span style="color: #ffffff;">&nbsp;Редактировать и удалять любые объявления.</span></li>
</ul>
</td>
</tr>
<tr style="height: 13px;">
<td style="height: 13px; width: 11.0296%;"><span style="color: #ffffff;">&nbsp;Пользователь</span></td>
<td style="height: 13px; width: 60.9704%; border-color: white;">
<ul>
<li><span style="color: #ffffff;">Просматривать список всех объявлений.</span></li>
<li><span style="color: #ffffff;">Просматривать каждое объявление.</span></li>
<li><span style="color: #ffffff;">Создавать объявление.</span></li>
<li><span style="color: #ffffff;">Редактировать и удалять свое объявление.</span></li>
<li><span style="color: #ffffff;">Просматривать все комментарии к объявлениям.</span></li>
<li><span style="color: #ffffff;">Создавать комментарии к любым объявлениям.</span></li>
<li><span style="color: #ffffff;">Редактировать/удалять свои комментарии.</span></li>
</ul>
</td>
</tr>
</tbody>
</table>


### Стек технологий:
* Java
* Maven
* Spring Boot
* Spring Security
* Spring Data
* PostgreSQL
* Liquibase
* Docker

**Для запуска нужно:**

1. Клонировать проект и настроить значения в файле [application.properties](src/main/resources/application.properties)
2. Скачать **[Docker](https://www.docker.com)** и запустить его.
3. Скачать и запустить Docker образ с помощью
   команды ```docker run -p 3000:3000 ghcr.io/bizinmitya/front-react-avito:latest```.
4. Запустить метод **main** программы.
5. После этого будет доступен функционал сайта  http://localhost:3000 и Swagger
   UI   http://localhost:8080/swagger-ui/index.html#.
