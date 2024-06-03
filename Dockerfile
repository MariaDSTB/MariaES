FROM ubuntu:latest
LABEL authors="administration"

ENTRYPOINT ["top", "-b"]