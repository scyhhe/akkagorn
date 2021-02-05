# akkagorn
![Akkagorn](https://i.imgflip.com/4wsxwi.jpg)


"My friends, you subscribe to no one" 
- Akkagorn
##  Design doc
A quick design doc to explain the project
### Overview
This projects goal is to have a server that can be used for a multitude of different client applications. 
It should be possible to send messages to the server to be distributed to subscribed clients.

### Goals
Hosting pub-sub topics
Creating topics
Subscribing queues to topics
Distributing received messages among queues based on the topic

### Non-Goals
Persistence of any kind of state
Creating a server for a specific application

### Milestones
  1. running server :heavy_check_mark:
  2. create topics
  3. subscribing to topics
  4. making chat application
  5. create CI/CD pipeline
  6. hosting

### Implementation
Technologies
- akka-http to provide the API
- akka-typed for the internal implementation of the server
- Websockets for communication (initial implementation with REST)
- use JSON as serialization format (via circe)
- use either Scala.js with Slinky, Typescript or Elm

Have a parent webserver that allows the managing of topics/subscribers. Spawn an actor for each topic.
REST API to manage subscriptions and create topics.
Websockets for messages sending / receiving.
For now cache messages in memory.

For the chat app:
- inital client can create a chatroom (topic)
- other clients can connect to the chatroom (subscribe to topic)
- each client has his own queue of messages that he can receive
- on connecting to the chatroom receive all past messages
- send messages to chatroom and distribute to other clients

## Contributors
[Sebastian](https://github.com/sebastianpfluegelmeier)

[Michele](https://github.com/zZKato)

[Ivan](https://github.com/scyhhe)