FROM mongo:latest
LABEL name="petsmongo"
EXPOSE 27017

#LABEL name="petsmongo"  - установка имени, можно также устанавливать кто сделал через maintainer= и т.д.
#EXPOSE 27017 - открытие портов