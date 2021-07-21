# albo-marvel-library
## Prueba Backend Developer

Proyecto de integración de **API Marvel** para implementación de **librería comics "ALBO"**.


>Con el paso de los años la biblioteca **albo** de comics, necesita tener actualizado todo el listado de escritores, editores y coloristas de cómics que han estado involucrados en las historias de los siguientes integrantes de los Vengadores (Iron Man, Captain America). Así como todos los demás héroes que a través de cada cómic han interactuado con cada uno de ellos. Esto hay que actualizarlo diario, ya que hay que pagarles regalías respectivas a los escritores, editores y coloristas.


## Tabla de contenidos:
---

- AlboMarvelLibrary
- Base de Datos
- Servicio A
- Servicio B
- API





### AlboMarvelLibrary 1.0-rc1
---
  El proyecto es una aplicación web desarrollada en Netbean con pruebas en glassfish

  Cuenta con un arranque inicial que se encarga de conectar con el API MARVEL e ir llenando las información necesaria para los servicios principales de la biblioteca que serán descritos más adelante.



### DB **albo_comics_library_1.0.1**
---
  Se considero una base de datos para a la aplicación en mysql (Version 5.7.33), cuya instancia esta actualmente corriendo en Amazon RDS.
  Se maneja un catalogo principal en la tabla de *character_principal* , que son los registros con los que se inicia toda la carga de contenido.
  
  ```
    INSERT INTO `character_principal` (`id`, `name`, `marvel_name`, `template`, `system_update_date`)
      VALUES
	          (1, 'Iron Man', 'Iron Man', 'ironman', NULL),
	          (2, 'Capitan America', 'Captain America', 'capamerica', NULL);
   ```

 [albo_comics_library_1.0.1_2021-07-21.sql](../albo-marvel-library/albo_comics_library_1.0.1_2021-07-21.sq)
  
<p align="center"><img src="https://raw.githubusercontent.com/jacito/albo-marvel-library/main/albo_comics_library.png"/></p> 


### Servicio a) ```# GET```
---
  Permite obtener los colaboradores que han estado involucrados en los cómics del personaje.
  Actualmente solo disponible para 2 personajes

- [x] /marvel/colaborators/ironman
- [x] /marvel/colaborators/capamerica

#### Response :: Ejemplo


```json
{
                "result": {
                    "responseCode": 200,
                    "responseMessage": "Transacción exitosa"
                },
                "object": {
                            "last_sync": "Fecha de la última sincronización en 21/00/2021 12:00:25"
                            "jsonContent": {
                                "editor": [
                                            "Axel Alonso",
                                            "Wilson Moss"
                                ],
                                "colorist": [
                                            "Andrew J. Troy",
                                            "Andy Troy"
                                ],
                                "penciler": [
                                            "Avalon Lan Medina"
                                ]
                            }
                }
}
```

### Servicio b) ```# GET```
---
  Permite obtener otros personajes que intrectuan con el personaje de la consulta principal, dentro de sus comics. 
  Actualmente solo disponible para 2 personajes

- [x] /marvel/characters/ironman
- [x] /marvel/characters/capamerica

#### Response :: Ejemplo
```json
{
                "object": {
                            "result": {
                                "responseCode": 200,
                                "responseDescription": "Succesful transaction",
                                "responseMessage": "OK"
                            },
                            "last_sync": "Fecha de la última sincronización en 21/07/2021 02:18:13",
                            "characters": [
                                {
                                    "character": "Thor (Ultimate)",
                                    "comics": [
                                        "Ultimate Comics Spider-Man (2009) #150 (WRAPAROUND VARIANT)",
                                        "ULTIMATE X-MEN VOL. 5: ULTIMATE WAR TPB (Trade Paperback)"
                                    ]
                                }
                                {
                                    "character": "Sharon Carter",
                                    "comics": [
                                        "Captain America (2018) #22",
                                        "Captain America (2018) #27",
                                        "Captain America: Secret Empire (Trade Paperback)",
                                        "Captain America: Winter Soldier Vol. 2 (Trade Paperback)",
                                        "Secret Empire (2017) #9"
                                    ]
                                },
                                {
                                    "character": "Vision",
                                    "comics": [
                                        "ALL-NEW, ALL-DIFFERENT AVENGERS HC (Hardcover)",
                                        "Avengers (2016) #11",
                                        "Avengers: Unleashed Vol. 1 - Kang War One (Trade Paperback)",
                                        "Avengers: Unleashed Vol. 2 - Secret Empire (Trade Paperback)"
                                    ]
                                },
                                {
                                    "character": "Captain Marvel (Carol Danvers)",
                                    "comics": [
                                        "Generations (Hardcover)",
                                        "Secret Empire (2017) #8"
                                    ]
                                }
                            ]
                }
            }
```

### API  
---
  Para mayor información sobre los servicios cunsulte [API](https://jacito.docs.apiary.io/#)
  <p align="center">
	<img src="https://raw.githubusercontent.com/jacito/albo-marvel-library/main/Captura%20de%20Pantalla%202021-07-21%20a%20la(s)%2012.43.15.png"/>
 </p> 




  
