
#Grobot -- the Groovy Robot

***

Grobot is a remote execution framework which draws inspiration from mcollective, Salt, func, and others. Grobot aims to fill in the space for a framework written in java with support for plugins in groovy. 

Grobot consists of a server (the master) and agents that run on the remote target hosts (the minions). The master's role is to collect information from the minions, dispatch jobs, monitor and report on the status of jobs.
 
### Developer Setup
It's not strictly required but you'll probably want to install SpringSource's STS. Grobot makes extensive use of Groovy, Spring-JMS, Spring-WebMVC and is built with Gradle, all of which have tooling support in STS.  Again, not required but highly recommended.

From the root of the project, you can build everything by issuing a [gradle clean build]. This will download all of the dependencies from Maven Central, install them, and compile and test each component.

