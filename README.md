malabarista
===========
malabarista (portuguese for "juggler") is a java TCP load balancer built on top of Netty.

It's an extremely simple project that creates TCP tunnels between two endpoints, based on Netty's TCP tunnel example.
Different destination selection algorithms can be used and new ones easily plugged in.

**Warning** this project seriously needs some code documentation and instructions.
I'll also add more target selection strategies and options meanwhile.

usage
=====
Example 1:
  java -jar Malabarista.jar -b*:80 -t10.0.0.1:80 -t10.0.0.2:80 -t10.0.0.3:80 -sRR

Run the load balancer on all available NIC's on port 80 and redirect to hosts 10.0.0.1 through 3 using RoundRobin (RR) load balancing strategy.
RoundRobin assingns every new incoming connection to the next target host.

Example 2:
  java -jar Malabarista.jar -b10.0.0.100:80 -t10.0.0.1:80 -t10.0.0.2:80 -sFAIR

Run the load balancer on 10.0.0.100 port 80 and redirect to hosts 10.0.0.1/2 using FAIR strategy.
Fair strategy attempts to balance the number of connections between all target hosts.

build
=====
Such a small project isn't worth of a full maven structure so I just made a build.xml to run with ant that generates a single jar with dependencies inside.

The file is located at src/ant/build.xml and the target to generate the package is 'jar.loadbalancer'.

license
=======
This project is licensed under the Apache Software License 2.0.
