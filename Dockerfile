FROM maven:3.9.6-eclipse-temurin-21


RUN apt-get update && apt-get install -y unzip curl && \
    rm -rf /var/lib/apt/lists/*

RUN curl -o allure-2.29.0.tgz -L https://github.com/allure-framework/allure2/releases/download/2.29.0/allure-2.29.0.tgz && \
    tar -zxvf allure-2.29.0.tgz && \
    mv allure-2.29.0 /opt/allure && \
    ln -s /opt/allure/bin/allure /usr/bin/allure && \
    rm allure-2.29.0.tgz

WORKDIR /app

COPY . /app

CMD ["sh", "-c", "mvn clean test && allure generate target/allure-results --clean -o allure-report"]
