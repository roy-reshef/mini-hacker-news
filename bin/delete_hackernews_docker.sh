#!/usr/bin/env bash

docker stop reshef-minihackernews-api
docker rm -v reshef-minihackernews-api
docker rmi reshef/minihackernews/api

sudo rm -Rf ../logs/*