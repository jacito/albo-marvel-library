# albo-marvel-library
## Prueba Backend Developer

Proyecto de integración de **API Marvel** para implementación de **librería comics "ALBO"**.



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
    	}
    	"object": {
    		"last_sync": "Fecha de la última sincronización en 21/00/2021 12:00:25"
        	"jsonContent": {
            		"editor": [
                	"Axel Alonso",
                	"Edward Devin Lewis"
            		],
            		"colorist": [
  	              		"Andrew J. Troy",
        	        	"Andy Troy",
                		"Dean White"
           	 	],
            		"letterer": [
               			"Cory Petit",
       		         	"Vc Ariana Maher"
            		],
          	  	"penciller": [
                		"Brad K. Joyce",
                		"Cory Petit"
           	 	],
            		"colorist (cover)": [
                		"Guru Efx"
            		],
            		"writer": [
               		 	"Alyssa Wong",
                		"Barbara Kesel"
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
  


### API  
---
  Para mayor información sobre los servicios cunsulte [API](https://jacito.docs.apiary.io/#)
  <p align="center">
	<img src="https://raw.githubusercontent.com/jacito/albo-marvel-library/main/Captura%20de%20Pantalla%202021-07-21%20a%20la(s)%2012.43.15.png"/>
 </p> 




  
