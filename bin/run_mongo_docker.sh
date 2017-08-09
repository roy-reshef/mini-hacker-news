#!/usr/bin/env bash

mkdir ~/data
sudo docker run -d -p --name reshef-minihackernews-mongo 27017:27017 -v ~/data:/data/db mongo