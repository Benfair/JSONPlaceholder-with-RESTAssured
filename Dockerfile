FROM maven:3.9.6-eclipse-temurin-21

# Install dependencies
RUN apt-get update && apt-get install -y unzip curl && \
    rm -rf /var/lib/apt/lists/*

# Download and install Allure (using latest version 2.34.0)
RUN curl --retry 5 --retry-delay 5 -L -o allure-2.34.0.tgz \
    https://github.com/allure-framework/allure2/releases/download/2.34.0/allure-2.34.0.tgz && \
    tar -zxvf allure-2.34.0.tgz && \
    mv allure-2.34.0 /opt/allure && \
    ln -s /opt/allure/bin/allure /usr/bin/allure && \
    rm allure-2.34.0.tgz

# Verify Allure installation
RUN allure --version

WORKDIR /app

COPY . /app

CMD ["sh", "-c", "mvn clean test && allure generate target/allure-results --clean -o allure-report"]