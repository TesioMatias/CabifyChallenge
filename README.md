# Cabify Mobile Challenge

En este repositorio se presenta la solucion al challenge propuesto por cabify

[Repositorio del challenge](https://github.com/cabify/MobileChallenge)

## Consideraciones
Dejo algunas de las herramientas usadas para la creacion del challenge

1) Apiary (mockeo de servicios)
   catalogo: https://private-6566b-matiastesio.apiary-mock.com/products
   descuentos: https://private-6566b-matiastesio.apiary-mock.com/discounts

2) Color picker (https://imagecolorpicker.com/en)
   -> Color primario obtenido del logo de cabify : #7f33ff

3) icons (https://www.flaticon.com/search?word=shop)

4) animaciones con Lottie

5) Icons coverted to webp para que sean mas livianos

elegí no mencionar herramientas como retrofit2, OkHttp3, Glide, Room y demas ya que su uso está demostrado en el build.gradle

## Que podria mejorarse

1) Puede migrarse a jetpack compose.
2) Prian hacerse custom views para componentizar vistas y que luego estas sean reutilizables.
3) Puede agregarse un wrappear sobre response.body en alguna clase por ejemplo llamada "Resource" que pueda manejar success y failure.
4) Podria mejorarse manejo del versionado en el build.gradle por ej pasandolo a un toml
5) Puede mejorarse el manejo de timeouts y errores de api calls (con mejores interceptors y/o manejo de respuesta separado por numero de error HTTP)
6) Puede agregarse un empty state en el detailFragment
7) Seria una buena mejora poder eliminar items del cart de a 1 (mas alla de todos los items juntos)
8) Seria una buena idea manejar el contentDescription desde en backend para mayor flexibilidad en accessibilidad
9) Seria una buena mejora poder agregar de a mas de 1 elemento a la vez al carrito desde el detalle.
10) Debería hacerse enfasis en el manejo de inconsistencias como precios con valores negativos.
11) Debería marcarse en que lugares del codigo pueden hacerse trackeos a alguna herramienta de analytics
12) Se Necesita mayor covertura de unit test y de instrumentation tests
13) Puede agregarse mayor uso de async y await (junto con supervisor Job) en las coroutines para los casos donde se quiera paralelizar el manejo de las tareas en background que se lanzan desde los viewModels - por defecto los metodos en las coroutines son secuenciales, por eso, de querer paralelizarlos, podria usarse async-await y supervisorJob para que puedan fallar independientemente sin hacer que todo falle.
14) Podria haberse usado stateFlow en vez de LiveData
15) Agregado de tests de instrumentacion (hay un muy bien patron llamado Robot para esto, pero sino aunque sea usando espress o robolectric)

[Robot Pattern](https://medium.com/android-bits/espresso-robot-pattern-in-kotlin-fc820ce250f7)

