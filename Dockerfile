#
# Arquivo de receita para a construção da imagem da nossa aplicação
# (>docker image build -t algafood .)


# Utilizamos o modelo da openjdk
FROM openjdk:11-jre-slim

# Setamos o diretório da aplicação (se não existir ele cria) 
WORKDIR /app

# Define uma variável que pode ser passada em tempo de Build (neste caso o nome do arquivo da variável JAR_FILE do pom
ARG JAR_FILE

# Copia o arquivo da aplicação e o wait-for-it.sh, pra dentro da imagem
COPY target/${JAR_FILE} /app/api.jar
COPY wait-for-it.sh /wait-for-it.sh

# Tranforma o arquivo wait-for-it.sh em um executável
RUN chmod +x /wait-for-it.sh 

# Define a porta que o container deve escutar quando levantar a aplicação
EXPOSE 8080

# Comando (array de instruções) que deve ser executado (no WORKDIR), como padrão, quando o container levantar
CMD ["java", "-jar", "api.jar"]