# Лабораторная работа #1 - v.2184

Visit <https://se.ifmo.ru/~s333057/dist/> for details.

Выполнили

> Тучков Максим Русланович
>
> Кондратьева Ксения Михайловна

Разработать спецификацию в формате OpenAPI для набора веб-сервисов, реализующих следующую функциональность.

## Первый веб-сервис

Должен осуществлять управление коллекцией объектов. В коллекции необходимо хранить объекты класса `Ticket`, описание которого приведено ниже:

```java
public class Ticket {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private TicketType type; //Поле не может быть null
    private Venue venue; //Поле может быть null
}
public class Coordinates {
    private double x;
    private Float y; //Поле не может быть null
}
public class Venue {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long capacity; //Значение поля должно быть больше 0
    private VenueType type; //Поле не может быть null
    private Address address; //Поле не может быть null
}
public class Address {
    private String zipCode; //Длина строки должна быть не меньше 10, Поле не может быть null
}
public enum TicketType {
    VIP,
    BUDGETARY,
    CHEAP;
}
public enum VenueType {
    BAR,
    CINEMA,
    STADIUM;
}
```

Веб-сервис должен удовлетворять следующим требованиям

- API, реализуемый сервисом, должен соответствовать рекомендациям подхода RESTful.
- Необходимо реализовать следующий базовый набор операций с объектами коллекции: добавление нового элемента, получение элемента по ИД, обновление элемента, удаление элемента, получение массива элементов.
- Операция, выполняемая над объектом коллекции, должна определяться методом HTTP-запроса.
- Операция получения массива элементов должна поддерживать возможность сортировки и фильтрации по любой комбинации полей класса, а также возможность постраничного вывода результатов выборки с указанием размера и порядкового номера выводимой страницы.
- Все параметры, необходимые для выполнения операции, должны передаваться в URL запроса.
- Информация об объектах коллекции должна передаваться в формате `xml`.
- В случае передачи сервису данных, нарушающих заданные на уровне класса ограничения целостности, сервис должен возвращать код ответа http, соответствующий произошедшей ошибке.

Помимо базового набора, веб-сервис должен поддерживать следующие операции над объектами коллекции

- Рассчитать сумму значений поля price для всех объектов.
- Вернуть один (любой) объект, значение поля venue которого является минимальным.
- Вернуть массив уникальных значений поля venue по всем объектам.

Эти операции должны размещаться на отдельных URL.

## Второй веб-сервис

Должен располагаться на URL `/booking`, и реализовывать ряд дополнительных операций, связанных с вызовом API первого сервиса:

- `/sell/ticket-id/person-id/price`: продать билет за указанную сумму
- `/sell/discount/ticket-id/person-id/discount`: создать новый билет на основе указанного, указав скидку в заданное число %, и, одновременно, увеличив цену билета на ту же самую сумму

Эти операции также должны размещаться на отдельных URL.

Для разработанной спецификации необходимо сгенерировать интерактивную веб-документацию с помощью Swagger UI. Документация должна содержать описание всех REST API обоих сервисов с текстовым описанием функциональности каждой операции. Созданную веб-документацию необходимо развернуть на сервере `helios`.

## Вопросы к защите лабораторной работы

1. Подходы к проектированию приложений. "Монолитная" и сервис-ориентированная архитектура.
2. Понятие сервиса. Общие свойства сервисов.
3. Основные принципы SOA. Подходы к реализации SOA, стандарты и протоколы.
4. Общие принципы построения и элементы сервис-ориентированных систем.
5. Понятие веб-сервиса. Определение, особенности, отличия от веб-приложений.
6. Категоризация веб-сервисов. RESTful и SOAP. Сходства и отличия, области применения.
7. RESTful веб-сервисы. Особенности подхода. Понятия ресурса, URI и полезной нагрузки (payload).
8. Виды RESTful-сервисов. Интерпретация методов HTTP в RESTful.
9. Правила именования ресурсов в RESTful сервисах.
10. Спецификация RESTful-сервисов. Стандарт OpenAPI.
11. Автодокументирование RESTful-сервисов. Swagger Editor, Swagger UI (и Swagger Codegen).
12. Архитектурный принцип HATEOAS.
