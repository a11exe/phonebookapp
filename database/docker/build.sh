#!/bin/bash

echo "Removing existing 'phonebookapp/database' image..."
docker rmi --force phonebookapp/database

echo "Building a new 'phonebookapp/database' image..."
docker build -t phonebookapp/database .
