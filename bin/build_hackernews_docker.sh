#!/usr/bin/env bash

cd minihackernewsapi
mvn clean install
docker build -t reshef/minihackernews/api .