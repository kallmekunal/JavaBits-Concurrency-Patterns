Active-Object

Active-Object concurrency patterns aims to decouple method invocation from method execution.
It aims to utilise higher level of concurrency.

The pattern consists of six elements:

    A proxy, which provides an interface towards clients with publicly accessible methods.
    An interface which defines the method request on an active object.
    A list of pending requests from clients.
    A scheduler, which decides which request to execute next.
    The implementation of the active object method.
    A callback or variable for the client to receive the result.

    
    