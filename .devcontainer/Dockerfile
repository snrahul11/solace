FROM --platform=linux/amd64 ubuntu:20.04 
ENV DEBIAN_FRONTEND=noninteractive 
RUN apt-get update -y

# git 
RUN apt-get install -y git git-lfs
RUN git config --global credential.helper store 

# java
RUN apt-get install -y openjdk-17-jdk openjdk-17-jre

# zip unzip curl
RUN apt-get install -y zip unzip curl

# sdkman
RUN curl -s https://get.sdkman.io | bash
RUN source "/root/.sdkman/bin/sdkman-init.sh"
RUN sdk install maven 3.8.7
