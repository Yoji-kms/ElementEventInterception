## Нетология. Введение в Android. Урок 11 (Работа с конфигурируемым интерфейсом на примере списков. Классы Adapter, AdapterView). Задача 2. Список с custom элементами

### [Задание](https://github.com/netology-code/and-homeworks/tree/master/4.2.Adapter/4.2.2):

Продолжаем работать со списком Sample-ов например из [Задания](https://github.com/Yoji-kms/AppBarInApplication) или придумать свой набор данных.

1. Добавляем каждому элементу списка кнопку “Удалить”. По ее нажатию Sample удаляется из списка.
2. В случае длительного нажатия на элемент списка показать Toast с названием примера.

Точно также как в примере лекции заменяем в разметке item_list_view.xml CheckBox на Button c текстом "Удалить" и вешаем на знакомый OnClickListener свой код. В нем мы удаляем наш элемент из списка и обновляем данные адаптера, смотрим еще раз код из лекции. Не забываем notifyDataSetChanged() в адаптере, если он не вызывался внутри установки новых данных.

OnLongClickListener – запускает обработчик долгого нажатия, он очень похож на простой клик (с ним вы знакомы). Единственное, что нужно будет возвращать булеановское значение:

- true — если вы установили на запуск свой код и не хотите, чтобы он дополнительно обрабатывался,
- false — если ничего не обрабатывалось.