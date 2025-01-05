# Green Energy Management 🍃⚡

Green Energy Management is a POC project, intended to show Apache Kafka and RabbitMQ producers and consumers working together, with MongoDB Docker containerization.

On a regular schedule, data is published to the Kafka topic and gets persisted to a MongoDB database. Afterwards, a message gets routed through an exchange and gets posted to a RabbitMQ queue.

Once the Rabbit message is consumed, a data aggregation is performed and the result is also stored in the MongoDB database.

Kafka, RabbitMQ and MongoDB, along with the three apps (Kafka producer, Kafka consumer/producer and Rabbit consumer), are all deployed in Docker containers. 🐋

A docker-compose.yml file is used to ease management of the deployment. Volumes are used for persisting data for Kafka, RabbitMQ and MongoDB.