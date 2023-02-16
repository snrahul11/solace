FROM --platform=linux/amd64 ubuntu:20.04 
ENV DEBIAN_FRONTEND=noninteractive 
RUN apt-get update -y

# git 
RUN apt-get install -y git git-lfs
RUN git config --global credential.helper store 
