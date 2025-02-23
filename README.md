# README.md

## Reflexión y Síntesis sobre el Patrón DAO

### 1. ¿De qué lógica de aplicación se encarga el Patrón DAO? (0,5 puntos)

El Patrón DAO  se encarga de la lógica de acceso a los datos. Su principal responsabilidad es abstraer el acceso a la base de datos, separando la interacción con los datos de la lógica de negocio de la aplicación. De esta manera, la aplicación no necesita conocer los detalles de cómo se almacenan o recuperan los datos, sino que delega estas tareas al DAO. Esto facilita el mantenimiento del código y permite que la aplicación sea más flexible si se cambia la fuente de datos (por ejemplo, de una base de datos SQL a NoSQL).

### 2. ¿Por qué consideráis que es útil el Patrón DAO y en qué os ha servido? (0,5 puntos)

El Patrón DAO es útil porque proporciona una capa de abstracción para interactuar con los datos, lo que facilita la organización del código y mejora su mantenimiento. En mi caso, me ha servido para centralizar la lógica de acceso a la base de datos. Al utilizar los DAOs, ahora puedo realizar las operaciones de persistencia de manera más estructurada y sin mezclar la lógica de negocio con el acceso a los datos. Esto hace que el código sea más limpio y más fácil de modificar, ya que los cambios en la lógica de acceso a los datos no afectan al resto de la aplicación.

### 3. ¿Habéis tenido que hacer algún ajuste en vuestro código de aplicación (Main, Vistas, otras clases que no sean DAO, etc.)? Si es así, detallar brevemente qué cambios habéis hecho y por qué. (0,5 puntos)

Sí, he tenido que hacer algunos ajustes en mi código. Dado que en los controladores solo tengo el método `create`, he tenido que modificar las clases que interactúan con la base de datos para llamar al DAO cuando se necesite crear una nueva entrada. Por ejemplo, en la clase `Main`, ahora utilizo los métodos del DAO para insertar datos en lugar de hacerlo directamente. Esto fue necesario porque el patrón DAO organiza mejor el acceso a los datos y permite que la lógica de la aplicación se enfoque en el negocio, mientras que el DAO se encarga de la persistencia.


### 5. Valoración del papel de la clase abstracta. ¿Es en todos los casos necesaria? En el caso de la actividad A02 de la UF2, donde usasteis JDBC, ¿pensáis que sería útil? (1 punto)

La clase abstracta en el patrón DAO no es estrictamente necesaria en todos los casos, pero puede ser muy útil para centralizar comportamientos comunes entre los DAOs, como la conexión a la base de datos o el manejo de excepciones. En mi caso, aunque solo estoy usando el método `create` en los controladores, la clase abstracta facilita la organización del código y la reutilización de lógica común.

En el caso de la actividad A02, donde usé JDBC, la clase abstracta podría haber sido útil si se hubieran implementado más métodos (por ejemplo, `read`, `update`, `delete`) o si hubiera múltiples tablas a las que acceder. Sin embargo, dado que solo trabajamos con el método `create` para insertar datos, la necesidad de una clase abstracta no era tan crítica en ese momento. No obstante, en proyectos más grandes con más operaciones, la clase abstracta sería muy útil.

