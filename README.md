# albo-marvel-library
## Prueba Backend Developer

Proyecto de integración de **API Marvel** para implementación de **librería comics "ALBO"**.

### AlboMarvelLibrary 1.0-rc1
  El proyecto es una aplicación web desarrollada en Netbean con pruebas en glassfish

  Cuenta con un arranque inicial que se encarga de conectar con el API MARVEL e ir llenando las información necesaria para los servicios principales de la biblioteca que serán descritos más adelante.

### DB **albo_comics_library_1.0.1**
  Se considero una base de datos para a la aplicación en mysql, cuya instancia esta actualmente corriendo en una instancia de amazon.
  Se maneja un catalogo principal en la tabla de *character_principal* , que son los registros con los que se inicia toda la carga de contenido.
  
  ```
    INSERT INTO `character_principal` (`id`, `name`, `marvel_name`, `template`, `system_update_date`)
      VALUES
	          (1, 'Iron Man', 'Iron Man', 'ironman', NULL),
	          (2, 'Capitan America', 'Captain America', 'capamerica', NULL);
   ```

  

![ER](https://raw.githubusercontent.com/jacito/albo-marvel-library/main/albo_comics_library.png)


### Servicio a)
  Permite obtener los colaboradores que han estado involucrados en los cómics del personaje.
  
  
### Servicio b)
  Permite obtener otros personajes que intrectuan con el personaje de la consulta principal, dentro de sus comics. 

  
