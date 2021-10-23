#
# Arquivo de receita para a construção da imagem da nossa aplicação
# (>docker image build -t algafood .)


# Utilizamos o modelo da openjdk
FROM openjdk:11-jre-slim

# Setamos o diretório da aplicação (se não existir ele cria) 
WORKDIR /app

# Copia o arquivo da aplicação pra dentro da imagem
COPY target/*.jar /app/api.jar

# Define a porta que o container deve escutar quando levantar a aplicação
EXPOSE 8080

# Comando (array de instruções) que deve ser executado (no WORKDIR), como padrão, quando o container levantar
CMD ["java", "-jar", "api.jar"]