#!/bin/bash

./gradlew bootBuildImage --imageName ghcr.io/<your_github_username>/catalog-service \
--publishImage \
-PregistryUrl=ghcr.io \
-PregistryUsername=<your_github_username> \
-PregistryToken=<your_github_token>