#! /bin/sh


echo "________________________________"
echo "Deleting app $1"
echo "________________________________"
heroku apps:destroy --app $1 --confirm $1
echo "________________________________"
echo "$1 - deleted"
echo "________________________________"