
# readme

This project is the foundation for a custom command line tool.
The core consists of components which communicate via messages
with each other (i.e. a message queue). Components run "independently"
(i.e in a separate thread) and react to the communication. This
approach allows to add and remove custom components as needed. See
documentation and code documenttion for details.
