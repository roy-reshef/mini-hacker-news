#!/usr/bin/env bash

DATA_VOLUME=/home/roy/workspaces/personal/minihackernews/logs

docker run -d \
        -e "SERVICE_NAME=minihackernews" \
        -p 8080:8080 \
        -v ${DATA_VOLUME}:/tmp \
        --name reshef-minihackernews-api \
        -t reshef/minihackernews/api \
        -DSERVICE_NAME=minihackernews