### How is JSON Web Token (JWT) motivated?
HTTP is a stateless protocol. Every interaction in HTTP needs to contain all the information required for that interaction to complete successfully. Nothing is remembered.
There are two popular ways in which web servers manage sessions. They are using tokens. they are:
* Session Tokens
* JSON Web Tokens

Session ID + Cookie based authorization has been a popular authorization. But there are a few problems. While this technique works with traditional monolithic applications, they do not work with applications with modern microservice architectures. 
Every session is stored at a server level. When there are multiple servers, different servers will not be able to share the session information if this is stored on one server and another server tries to fetch it. A shared cache mechanism (redis) comes to the rescue. The drawback is there is one single point of failure, now. 
A sticky session technique helps the load balancer remember the server per user request, but this is not scalable.

### What is JWT?
Whenever a client successfully authenticates to the server, the server sends a **signed** JSON payload/object of all the information with respect to the request (not just the token) back to the client. The client is in possession of the token and uses this for every subsequent requests. This JSON object is known as a **JSON Web Token (JWT)**. The responsibility of the server to maintain the state is fully offloaded to the client. 
Session tokens are _reference tokens_. They refer to a state on the server. JWTs are _value tokens_. They contain the values (entire object). 