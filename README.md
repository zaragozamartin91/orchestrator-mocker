# orchestrator-mocker
Mocker de orquestadores de accusys

Importante: para mockear los metodos __final__ de los orquestadores es necesario crear la carpeta 
__mockito-extensions__ en el directorio __src/test/resources__ del proyecto del orquestador y alli depositar
el __archivo org.mockito.plugins.MockMaker__ que se encuentra en el directorio src/test/java de este
proyecto.
