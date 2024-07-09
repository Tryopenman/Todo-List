<h1 align="center" style="font-weight: bold;">Todo List Api 💻</h1>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

<p align="center">
  <b>This project is an API built using <strong>Java, Java Spring</strong>.</b>
</p>
<p align="center">
  <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a>
</p>

<p align="center">
  <b>This is an entire list api project, developed during rockeseat's mini java course, thus taking the first steps with spring boot.</b>
</p>


<h2 id="started">🚀 Getting started</h2>

1. Clone the repository:

```bash
$ git clone https://github.com/Tryopenman/Todo-List.git
```

2. Install dependencies with Maven

3. Start the application with Maven

<h2 id="routes">📍 API Endpoints</h2>

| Route                          | Description                                                                       |
|--------------------------------|-----------------------------------------------------------------------------------|
| <kbd>POST /users</kbd>         | Register a new user, see [request details](#post-users-detail)                    |
| <kbd>POST /tasks</kbd>         | Register a new task, see [request details](#post-tasks-detail)                    |
| <kbd>GET /tasks</kbd>          | List all tasks, see [response details](#get-tasks-detail)                         |
| <kbd>PUT /tasks/{taskId}</kbd> | Update an existing task, see [request details](#put-tasks-detail)                 |

<h3 id="post-users-detail">POST /users</h3>

#### Request

```json
{
  "username": "fosorio",
  "name": "Francisco Osório",
  "password": "test123"
}
```

<h3 id="post-tasks-detail">POST /tasks</h3>

#### Request

```json
{
  "title": "Aplicação spring boot",
  "description": "Desenvolver uma aplicação spring boot",
  "priority": "ALTA",
  "startAt": "2024-07-09T17:00:00",
  "endAt": "2024-07-09T18:00:00"
}
```

#### Response

```json
{
  "id": "9a7eed9b-1af8-4875-ad6f-da7b3a0836ce",
  "title": "Aplicação spring boot",
  "description": "Desenvolver uma aplicação spring boot",
  "priority": "ALTA",
  "startAt": "2024-07-09T17:00:00",
  "endAt": "2024-07-09T18:00:00",
  "idUser": "9a7eed9b-1af8-4875-ad6f-da7b3a0836ce",
  "createdAt": "2024-07-09T15:00:00"
}
```

<h3 id="get-tasks-detail">GET /tasks</h3>

#### Response

```json
[
  {
    "id": "9a7eed9b-1af8-4875-ad6f-da7b3a0836ce",
    "title": "Aplicação spring boot",
    "description": "Desenvolver uma aplicação spring boot",
    "priority": "ALTA",
    "startAt": "2024-07-09T17:00:00",
    "endAt": "2024-07-09T18:00:00",
    "idUser": "9a7eed9b-1af8-4875-ad6f-da7b3a0836ce",
    "createdAt": "2024-07-09T15:00:00"
  }
]
```

<h3 id="put-tasks-detail">PUT /tasks/{taskId}</h3>

#### Request

```json
{
  "title": "Alteracao Aplicação spring boot"
}
```

#### Response

```json
{
  "id": "9a7eed9b-1af8-4875-ad6f-da7b3a0836ce",
  "title": "Alteracao Aplicação spring boot",
  "description": "Desenvolver uma aplicação spring boot",
  "priority": "ALTA",
  "startAt": "2024-07-09T17:00:00",
  "endAt": "2024-07-09T18:00:00",
  "idUser": "9a7eed9b-1af8-4875-ad6f-da7b3a0836ce",
  "createdAt": "2024-07-09T15:00:00"
}
```
