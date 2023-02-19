FROM ubuntu:20.04
ENV DEBIAN_FRONTEND=noninteractive 

ARG JAVA_VERSION="17.0.6-ms"
ARG MAVEN_VERSION="3.8.7"

RUN apt-get update -y

# git 
RUN apt-get install -y git git-lfs
RUN git config --global credential.helper store 

# zip unzip curl
RUN apt-get install -y zip unzip curl

# Downloading SDKMAN! 
RUN curl -s "https://get.sdkman.io" | bash

# java & maven
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
    yes | sdk install java $JAVA_VERSION && \
    yes | sdk install maven $MAVEN_VERSION && \
    rm -rf $HOME/.sdkman/archives/* && \
    rm -rf $HOME/.sdkman/tmp/*"

# nodejs
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash
RUN apt-get install -y nodejs
