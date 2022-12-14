<p align="center">
      <img src="https://i.ibb.co/cLP7cLV/about.png" alt="Project Logo" width="726">
</p>

<p align="center">
   <img src="https://img.shields.io/badge/python-3.10-orange" alt="back version">
   <img src="https://img.shields.io/badge/kotlin-v1.6.0-yellow" alt="front version">
   <img src="https://img.shields.io/badge/version-Prototipe-blue" alt="app version">
   <img src="https://img.shields.io/badge/license-MIT-brightgreen" alt="license">
</p>

## About

COFFE - приложение для проведения операций на валютном рынке, которое позволяет не только удобно осуществлять обмен валют, но и проводить аналитику, находить лучшие решения для сбора оптимального портфеля, а также следить за последними новостями на FX.

## Documentation
Команда для сборки сервиса
```
docker build --tag coffe:latest .
```

Команда для запуска docker-compose
```
docker-compose -f docker-compose.yml up -d --remove-orphans
```


### Полезная информация

1. Все переменные можно менять в файле docker-compose.yml в графе `environment`.
2. Все подгружаеммые в бд данные/скрипты необходимо указывать в файле docker-compose.yml в графе `volumes`.
3. При изменении внешнего ip-адресса необходимо дописать в файле docker-compose.yml в графе `ports` до `:` и самого порта прописать желаемые ip-адрес и поставить `:`.
Итоговый вид: `127.0.0.1:5432:5432`7


## Distribute

- Увидеть приложение в работе можно по ссылке: [sovkombank.coffe](http://213.247.168.75:80)


## Developers

- [Ольга Миронова - Project Manager](https://t.me/Olga_MiSDM)
- [Дмитрий Шипилов - System analyst](https://github.com/TheSuspect17)
- [Павел Бахметов - BackEnd Developer](https://github.com/Memori707)
- [Егор Данилов - Android Developer](https://github.com/LostImagin4tion)
- [Елизавета Крапивина - Designer](https://t.me/eeeelzvt)

## License

Проект Sovkombank-COFFE распростроняется под лицензией MIT.
