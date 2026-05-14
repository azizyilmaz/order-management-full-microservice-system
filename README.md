# Order Management Full Microservice System

## 📋 Project Description
A comprehensive order management system consisting of four independent microservices and an API Gateway. The system is built using Spring Boot 3.4.4, Spring Cloud, and Consul Service Discovery.

## 🏗️ System Architecture

```
                          ↓
                    API Gateway (8080)
                    /      |      \
                   /       |       \
              Order   Product   Inventory
            Service   Service    Service
            (H2 DB)  (MongoDB)   (H2 DB)
             :8001    :8002      :8003
                   \       |       /
                    \      |      /
                 Consul Service Discovery
```

### Services

#### 1. **API Gateway** (Port: 8080)
- Spring Cloud Gateway
- Request routing and load balancing
- Consul Service Discovery integration
- Spring Boot Actuator health checks

#### 2. **Order Service** (Port: 8001)
- JPA + H2 Database
- WebFlux support
- Order creation and management
- WebClient calls to other services

#### 3. **Product Service** (Port: 8002)
- MongoDB database
- Product catalog and management
- RESTful API endpoints

#### 4. **Inventory Service** (Port: 8003)
- JPA + H2 Database
- Stock management and tracking
- Integration with Order Service

## 🛠️ Technologies

- **Java**: 21
- **Spring Boot**: 3.4.4
- **Spring Cloud**: 2024.0.1
- **Service Discovery**: Consul
- **Databases**: H2, MongoDB
- **Build Tool**: Maven
- **Testing**: TestContainers, JUnit 5
- **Utilities**: Lombok

## 📦 Prerequisites

- Java 21 JDK
- Maven 3.6+
- Docker & Docker Compose (optional, for containerized deployment)
- Consul (for service discovery)

## 🚀 Installation and Running

### Local Execution

1. **Clone the repository:**
```bash
git clone <repository-url>
cd order-management-full-microservice-system
```

2. **Build the entire project with Maven:**
```bash
mvn clean package
```

3. **Start Consul** (localhost:8500):
```bash
consul agent -server -bootstrap-expect=1 -ui -client=0.0.0.0
```

4. **Run each service** (in separate terminal windows):

```bash
# Order Service
cd order-service
mvn spring-boot:run

# Product Service
cd product-service
mvn spring-boot:run

# Inventory Service
cd inventory-service
mvn spring-boot:run

# API Gateway (start this last)
cd api-gateway
mvn spring-boot:run
```

### Running with Docker Compose

```bash
cd docker
docker-compose up -d
```

## 📊 Service Endpoints

### API Gateway (http://localhost:8080)
```
GET /api/orders          - List all orders
GET /api/orders/{id}     - Get order details
POST /api/orders         - Create new order
PUT /api/orders/{id}     - Update order
DELETE /api/orders/{id}  - Delete order

GET /api/products        - List all products
GET /api/inventory       - Check stock status
```

## 🧪 Testing

To run tests in the project:

```bash
mvn test
```

To run tests for a specific service:

```bash
cd order-service
mvn test
```

Integration tests with TestContainers will run automatically.

## 📁 Project Structure

```
order-management-full-microservice-system/
├── api-gateway/                 # API Gateway service
│   ├── src/
│   │   ├── main/java/
│   │   └── resources/
│   └── pom.xml
├── order-service/               # Order Management service
│   ├── src/
│   │   ├── main/java/
│   │   └── resources/
│   └── pom.xml
├── product-service/             # Product Catalog service
│   ├── src/
│   │   ├── main/java/
│   │   └── resources/
│   └── pom.xml
├── inventory-service/           # Inventory Management service
│   ├── src/
│   │   ├── main/java/
│   │   └── resources/
│   └── pom.xml
├── docker/                      # Docker Compose configuration
│   ├── src/
│   │   └── main/resources/
│   │       └── docker-compose.yaml
│   └── pom.xml
└── .git/                        # Git repository
```

## 🌐 Service Discovery (Consul)

Each service registers itself with Consul on startup. Health checks are performed at regular intervals.

**Consul UI:** http://localhost:8500

## 🔗 Inter-Service Communication

- Order Service communicates with Product Service and Inventory Service using WebClient/RestTemplate
- Load Balancer distributes load among instances discovered from Consul
- API Gateway routes all requests to appropriate services

## 📝 Configuration

Configuration files are located in each service's `application.yaml`:
- Server port number
- Consul discovery settings
- Database configuration
- Actuator health check properties

## 🐛 Troubleshooting

### Service fails to start
- Verify Consul is running
- Check for port conflicts
- Ensure Java 21 is installed

### Services cannot discover each other
- Check registered services in Consul UI
- Verify network connectivity
- Check Consul logs

### Database issues
```bash
# H2 Console (available in Order/Inventory Services)
http://localhost:8001/h2-console
```

## 📞 Contact & Contribution

Feel free to open issues or submit pull requests with your questions or suggestions.

## 📄 License

This project is licensed by [Aziz Yilmaz](LICENSE).

