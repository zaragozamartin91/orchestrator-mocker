# orchestrator-mocker
Mocker de orquestadores de accusys

Importante: para mockear los metodos __final__ de los orquestadores es necesario crear la carpeta 
__mockito-extensions__ en el directorio __src/test/resources__ del proyecto del orquestador y alli depositar
el __archivo org.mockito.plugins.MockMaker__ que se encuentra en el directorio src/test/java de este
proyecto.

## Build del artefacto
Para crear el JAR, se debe invocar el comando "gradlew build" desde el directorio del proyecto.

Para deployar el componente en artifactory, se debe correr el comando "gradlew artifactoryPublish" 
desde el directorio del proyecto.