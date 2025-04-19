#!/bin/bash

./gradlew bootBuildImage --imageName ghcr.io/mdsagir/catalog-service --publishImage \
-PregistryUrl=ghcr.io \
-PregistryUsername=mdsagir \
-PregistryToken=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