#!/bin/bash

#start
if [ "$1" == "down" ]; then
  if [ "$2" == "rm" ]; then
    docker-compose -f docker-compose-military.yaml -f docker-compose-ca.yaml -f docker-compose-couchdb.yaml -f docker-compose-server.yaml down --volumes --remove-orphans
  else
    docker-compose -f docker-compose-military.yaml -f docker-compose-ca.yaml -f docker-compose-couchdb.yaml -f docker-compose-server.yaml down
  fi
fi

#end
if [ "$1" == "up" ]; then
  docker-compose -f docker-compose-military.yaml -f docker-compose-ca.yaml -f docker-compose-couchdb.yaml -f docker-compose-server.yaml up -d
fi

#test
if [ "$1" == "test" ]; then
  echo "successful..."
  if [ "$2" == "rm" ]; then
    echo "rm"
  else
    echo "no rm"
  fi
fi
